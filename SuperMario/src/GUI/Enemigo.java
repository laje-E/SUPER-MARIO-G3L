package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Enemigo extends JPanel{
	
	private static final long serialVersionUID = 1L;


	private boolean hacia_derecha = true;
	
	private Timer movimiento;
	
	public int limiteIzquierdo;
    public int limiteDerecho;
    public int desplazamiento = 0;
	
    public void ajustarLimites(int desplazamiento) {
        limiteIzquierdo += desplazamiento;
        limiteDerecho += desplazamiento;
        
        
    }
    
	public Enemigo(int posX, int posY, int ancho, int alto, int limiteIzquierdo, int limiteDerecho) {
		setBounds(posX, posY, ancho, alto);
		this.limiteDerecho = limiteDerecho;
		this.limiteIzquierdo = limiteIzquierdo;
	}
	
	public void moverDerecha() {
		int posX = getX();
		int posY = getY();

		// Evita que se pase del borde derecho
        if (posX + 15 < limiteDerecho) {
            setLocation(posX + 15, posY);
        } else {
            setLocation((limiteDerecho) - getWidth(), posY);
        }
	}
	
	public void moverIzquierda() {
		int posX = getX();
		int posY = getY();
		
		// Evita que se meta en el borde izquierdo
        if (posX - 15 >= limiteIzquierdo) {
            setLocation(posX - 15, posY);
        } else {
            setLocation(limiteIzquierdo, posY);
        }
	}
	
	public void patrullar () {
		movimiento = new Timer(50, new ActionListener(){
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