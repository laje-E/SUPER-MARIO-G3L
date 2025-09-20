package GUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Enemigo extends JPanel{
	
	private static final long serialVersionUID = 1L;


	private boolean hacia_derecha = true;
	
	private Timer movimiento;
	
	public int limiteIzquierdo;
    public int limiteDerecho;
    public int desplazamiento = 0;
    public ImageIcon icon;
	
    public void ajustarLimites(int desplazamiento) {
        limiteIzquierdo += desplazamiento;
        limiteDerecho += desplazamiento;
        
        
    }
    
	public Enemigo(int posX, int posY, int ancho, int alto, int limiteIzquierdo, int limiteDerecho, ImageIcon icon) {
		setBounds(posX, posY, ancho, alto);
		this.icon = icon;
		this.limiteDerecho = limiteDerecho;
		this.limiteIzquierdo = limiteIzquierdo;
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    if (icon != null) {
	        g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
	    }
	}
	
	public void moverDerecha() {
		int posX = getX();
		int posY = getY();

		// Evita que se pase del borde derecho
        if (posX + 3 < limiteDerecho) {
            setLocation(posX + 3, posY);
        } else {
            setLocation((limiteDerecho) - getWidth(), posY);
        }
	}
	
	public void moverIzquierda() {
		int posX = getX();
		int posY = getY();
		
		// Evita que se meta en el borde izquierdo
        if (posX - 3 >= limiteIzquierdo) {
            setLocation(posX - 3, posY);
        } else {
            setLocation(limiteIzquierdo, posY);
        }
	}
	
	public void patrullar () {
		movimiento = new Timer(10, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (hacia_derecha) {
					if (getX() + getWidth() <= limiteDerecho) {
	                    moverDerecha();
	                } else {
	                    hacia_derecha = false;
	                    moverIzquierda();
	                }
				} else {
					if (getX() > limiteIzquierdo) {
	                    moverIzquierda();
	                } else {
	                    hacia_derecha = true;
	                    moverDerecha();
	                }
				}
			}
		});
		movimiento.start();
	}
	
	public void detenerPatrulla() {
	    if (movimiento != null) {
	        movimiento.stop();
	    }
	}
	
}