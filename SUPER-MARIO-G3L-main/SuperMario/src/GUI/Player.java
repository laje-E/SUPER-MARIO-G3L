	package GUI;

	import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

	public class Player extends JPanel {

		private static final long serialVersionUID = 1L;
		
		private boolean teclaPresionada=true;
		
		public Player(int posX, int posY, int ancho, int alto) {
			setBounds(posX, posY, ancho, alto);
		}
		
		public boolean chequeoColisionX(int dx, Obstaculo obstaculo) {
		    int nuevaX = getX() + dx;
		    int nuevaY = getY();
		    return new java.awt.Rectangle(nuevaX, nuevaY, getWidth(), getHeight())
		            .intersects(obstaculo.getBounds());
		}
		
		public boolean chequeoColisionY(int dy, Obstaculo obstaculo) {
		    int nuevaX = getX();
		    int nuevaY = getY() - dy; // Movimiento hacia arriba
		    Rectangle futuraPos = new Rectangle(nuevaX, nuevaY, getWidth(), getHeight());
		    return futuraPos.intersects(obstaculo.getBounds());
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

		public void saltar(Obstaculo obstaculo) { 
			if(teclaPresionada) {
			int altura_limite = 100;
			int velocidad_salto = 5;
			
			Timer timer = new Timer(30, new ActionListener() {
				int alturaActual = 0;
				boolean sube = true;
				
				public void actionPerformed(ActionEvent e) {
					int posX = getX();
					int posY = getY();
					
					
					if (sube == true) { // Subiendo
						teclaPresionada=false;
		                if (alturaActual < altura_limite) {
		                	if (!chequeoColisionY(velocidad_salto, obstaculo)) {
		                		setLocation(posX, posY - velocidad_salto);
		                    	alturaActual += velocidad_salto;
		                	}
		                	else {
		                		sube = false;
		                		setLocation(posX, posY - obstaculo.getHeight());
		                	}
		                } else {
		                    sube = false; // Cambia a bajada
		                }
		            } else { // Bajando
		                if (alturaActual > 0) {
		                    setLocation(posX, posY + velocidad_salto);
		                    alturaActual -= velocidad_salto;
		                } else {
		                    ((Timer) e.getSource()).stop(); // Termina el salto
		                    teclaPresionada=true;
		                }
		            }
					
					if(getX() + getHeight() == 0)
					{
						teclaPresionada=true;
					}
				}
			});
			timer.start();
			}
		}

	
}
