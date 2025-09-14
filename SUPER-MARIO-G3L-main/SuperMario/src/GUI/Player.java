	package GUI;

	import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

	public class Player extends JPanel {

		private static final long serialVersionUID = 1L;
		
		private boolean teclaPresionada=true;
		
		public ArrayList<Obstaculo> obstaculos;
		
		private ImageIcon marioIcon;
		
		public Player(int posX, int posY, int ancho, int alto) {
			setBounds(posX, posY, ancho, alto);
		}
		
		/*
		public Player(ImageIcon mario) {
			this.marioIcon = mario;
			setOpaque(false); // esto sirve para que el fondo no tape la imagen
		}
		*/
		
		/*
		@Override
		protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    if (marioIcon != null) {
		        g.drawImage(marioIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
		    }
		}
		*/
		
		public boolean chequeoColisionX(int dx, Obstaculo obstaculo) {
		    int nuevaX = getX() + dx;
		    int nuevaY = getY();
		    Rectangle futuraPos = new Rectangle(nuevaX, nuevaY, getWidth(), getHeight());
		    
		    return futuraPos.intersects(obstaculo.getBounds());
		    
		}
		
		public boolean chequeoColisionArriba(int dy, Obstaculo obstaculo) {
		    int nuevaX = getX();
		    int nuevaY = getY() - dy; // Movimiento hacia arriba
		    Rectangle futuraPos = new Rectangle(nuevaX, nuevaY, getWidth(), getHeight());
		    return futuraPos.intersects(obstaculo.getBounds());
		}
		
		public boolean chequeoColisionAbajo(int dy, Obstaculo obstaculo) {
		    int nuevaX = getX();
		    int nuevaY = getY() + dy; // Movimiento hacia abajo
		    Rectangle futuraPos = new Rectangle(nuevaX, nuevaY, getWidth(), getHeight());
		    return futuraPos.intersects(obstaculo.getBounds());
		}

		public boolean estaApoyado (ArrayList<Obstaculo> obstaculos) {
			for (Obstaculo obstaculo : obstaculos) {
				Rectangle abajoJugador = new Rectangle (getX(), getY() + 1, getWidth(), getHeight());
				if (abajoJugador.intersects(obstaculo.getBounds())) {
					return true;
				}	
			}
			return false;
		}
		
		public boolean colisionaConEnemigoDesdeArriba(Enemigo enemigo) {
		    Rectangle abajoJugador = new Rectangle(getX(), getY() + getHeight(), getWidth(), 5);
		    Rectangle arribaEnemigo = new Rectangle(enemigo.getX(), enemigo.getY(), enemigo.getWidth(), 5);
		    return abajoJugador.intersects(arribaEnemigo);
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

		public void saltar(ArrayList<Obstaculo> obstaculos, Enemigo enemigo) { 
			int velocidad_salto = 5;
			if(teclaPresionada) {
			int altura_minima = 470;
			int altura_limite = 100;
			
			
			Timer timer = new Timer(30, new ActionListener() {
				int alturaActual = 0;
				boolean sube = true;
				
				public void actionPerformed(ActionEvent e) {
					int posX = getX();
					int posY = getY();
					
					
					
					if (sube == true) { // Subiendo
						teclaPresionada=false;
		                if (alturaActual < altura_limite) {
		                	boolean colisionSubida = false;
		                	for (Obstaculo obstaculo : obstaculos) {
		                		if (chequeoColisionArriba(velocidad_salto, obstaculo)) {
		                			colisionSubida = true;
		                			break;
		        				}
		                	}
		                	if (!colisionSubida) {
		                		setLocation(posX, posY - velocidad_salto);
		                    	alturaActual += velocidad_salto;
		                	}
		                	else {
		                		sube = false;
		                	}
		                } else {
		                    sube = false; // Cambia a bajada
		                }
		                
		            } else { // Bajando
		                if (alturaActual > 0) {
		                	boolean colisionBajada = false;
		                	for (Obstaculo obstaculo : obstaculos) {
		                		if (chequeoColisionAbajo(velocidad_salto, obstaculo)) {
		                			colisionBajada = true;
		                			// ¡¡IMPORTANTE PREGUNTAR AL PROFE POR ESTA LINEA!!
		                			setLocation(getX(), getY() /*- (obstaculo.getHeight() /*+ getHeight())*/); 
		                			break;
		                		}
		                	}
		                	
		                	if (colisionaConEnemigoDesdeArriba(enemigo)) {
		                		// Eliminar enemigo del panel
		                	    enemigo.setVisible(false);
		                	    getParent().remove(enemigo);
		                	    getParent().repaint();

		                	    ((Timer) e.getSource()).stop(); // Termina el salto
		                	    teclaPresionada = true;
		                	    return;
			        		}
		                	
		                	if (!colisionBajada) {
		                		setLocation(posX, posY + velocidad_salto);
			                    alturaActual -= velocidad_salto;
		                	}
		                	else {
		                		
		                		((Timer) e.getSource()).stop(); // Termina el salto
		                        teclaPresionada = true;
		        			}
		                    
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
