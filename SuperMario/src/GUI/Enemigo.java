package GUI;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Enemigo extends JPanel {

    private static final long serialVersionUID = 1L;

    private boolean hacia_derecha = true;
    private Timer movimiento;
    private Timer disparoTimer;

    public int limiteIzquierdo;
    public int limiteDerecho;
    public int desplazamiento = 0;
    public ImageIcon icon;
    public int vida;
    private NivelBase nivel;

    private boolean dispara; // true si es Matecinini o similar

    public void ajustarLimites(int desplazamiento) {
        limiteIzquierdo += desplazamiento;
        limiteDerecho += desplazamiento;
    }

    public Enemigo(int posX, int posY, int ancho, int alto, int limiteIzquierdo, int limiteDerecho, ImageIcon icon, NivelBase nivel) {
        this(posX, posY, ancho, alto, limiteIzquierdo, limiteDerecho, icon, false, 1, nivel);
    }

    public Enemigo(int posX, int posY, int ancho, int alto, int limiteIzquierdo, int limiteDerecho, ImageIcon icon, boolean dispara, int vida, NivelBase nivel) {
        setBounds(posX, posY, ancho, alto);
        this.icon = icon;
        this.limiteDerecho = limiteDerecho;
        this.limiteIzquierdo = limiteIzquierdo;
        this.dispara = dispara;
        this.vida = vida;
        this.nivel = nivel;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (icon != null) {
            if (hacia_derecha) {
                // normal (mirando a la izquierda)
            	g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(),
                        icon.getIconWidth(), 0, 0, icon.getIconHeight(), this);
            	
            } else {
                // espejado horizontal (mirando a la derecha)
            	g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(),
                        0, 0, icon.getIconWidth(), icon.getIconHeight(), this);
            }
        }
    }

    // --- Enemigos que caminan ---
    public void patrullar() {
        if (dispara) return; // si dispara, no se mueve
        movimiento = new Timer(30, e -> {
            if (hacia_derecha) {
                if (getX() + getWidth() <= limiteDerecho) {
                    setLocation(getX() + 3, getY());
                } else {
                    hacia_derecha = false;
                }
            } else {
                if (getX() > limiteIzquierdo) {
                    setLocation(getX() - 3, getY());
                } else {
                    hacia_derecha = true;
                }
            }
        });
        movimiento.start();
    }

    public void detenerPatrulla() {
        if (movimiento != null) movimiento.stop();
        if (disparoTimer != null) disparoTimer.stop();
    }

    
    public void empezarADisparar(Player jugador) { // Para enemigos que van a disparar
        if (!dispara) return;
        disparoTimer = new Timer(1500, e -> disparar(jugador));
        disparoTimer.start();
    }

    private void disparar(Player jugador) {
        hacia_derecha = (jugador.getX() > getX());   // actualiza la direccion antes de disparar

        int dx = hacia_derecha ? 5 : -5;
        Bala bala = new Bala(getX() + getWidth() / 2, getY() + getHeight() / 2, dx);

        if (getParent() != null) {
            getParent().add(bala);
            getParent().setComponentZOrder(bala, 0);
            getParent().repaint();
        }
        nivel.agregarBala(bala);
        repaint(); // fuerza redibujo con la dirección actualizada
    }
    
    public void movimientoJefe(Player player) {
    		movimiento = new Timer(75, e -> {
                	if (player.getX() < getX() && getX() > limiteIzquierdo) {
                        setLocation(getX() - 3, getY());
                    } else if (player.getX() > getX() && getX() + getWidth() <= limiteDerecho){
                    	setLocation(getX() + 3, getY());
                    }
                
            });
            movimiento.start();
    	
    }
    
    public boolean restarVida(int cantidad) {
        vida -= cantidad;
        return vida <= 0;
    }
}
