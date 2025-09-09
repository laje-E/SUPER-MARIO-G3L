	package GUI;

	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

	public class Player extends JPanel {

		private static final long serialVersionUID = 1L;
		
		public Player(int posX, int posY, int ancho, int alto) {
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

		public void saltar() {
			int altura_limite = 100;
			int velocidad_salto = 5;
			
			Timer timer = new Timer(30, new ActionListener() {
				int alturaActual = 0;
				boolean sube = true;
				
				public void actionPerformed(ActionEvent e) {
					int posX = getX();
					int posY = getY();
					
					
					if (sube == true) { // Subiendo
		                if (alturaActual < altura_limite) {
		                    setLocation(posX, posY - velocidad_salto);
		                    alturaActual += velocidad_salto;
		                } else {
		                    sube = false; // Cambia a bajada
		                }
		            } else { // Bajando
		                if (alturaActual > 0) {
		                    setLocation(posX, posY + velocidad_salto);
		                    alturaActual -= velocidad_salto;
		                } else {
		                    ((Timer) e.getSource()).stop(); // Termina el salto
		                }
		            }
				}
			});
			timer.start();
			
		}

	
}
