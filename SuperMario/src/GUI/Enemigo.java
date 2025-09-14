package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Enemigo extends JPanel{
	
	private boolean hacia_derecha = true;
	
	public Enemigo(int posX, int posY, int ancho, int alto) {
		setBounds(posX, posY, ancho, alto);
	}
	
	public void moverDerecha(int anchoPanel) {
		int posX = getX();
		int posY = getY();

		if (posX + getWidth() < anchoPanel) {
			setLocation(posX + 15, posY);
		}
	}
	
	public void moverIzquierda() {
		int posX = getX();
		int posY = getY();
		
		if (posX > 0) {
			setLocation(posX - 15, posY);
		}
	}
	
	public void patrullar (int anchoPanel) {
		Timer movimiento = new Timer(50, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (hacia_derecha) {
					if (getX() + getWidth() + 15 < anchoPanel) {
	                    moverDerecha(anchoPanel);
	                } else {
	                    hacia_derecha = false;
	                }
				} else {
					if (getX() - 15 > 0) {
	                    moverIzquierda();
	                } else {
	                    hacia_derecha = true;
	                }
				}
			}
		});
	}
	
}