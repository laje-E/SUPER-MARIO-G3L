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
		
		private int velocidadY = 0;
		private int fuerzaSalto = -13; // negativa porque sube
		private int gravedad = 1;
		private boolean enElAire = false;
		
		public Player(int posX, int posY, int ancho, int alto, ArrayList<Obstaculo> obstaculos, Enemigo enemigo) {
			setBounds(posX, posY, ancho, alto);
			this.obstaculos = obstaculos;
			
			// Se va a ejecutar el timer de gravedad dentro del Player
	        Timer gravedadTimer = new Timer(30, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                aplicarGravedad(enemigo);
	            }
	        });
	        gravedadTimer.start();
		}
		
		private void aplicarGravedad(Enemigo enemigo) {
		    velocidadY += gravedad;
		    setLocation(getX(), getY() + velocidadY);

		    for (Obstaculo obstaculo : obstaculos) {
		        if (chequeoColisionAbajo(0, obstaculo)) {
		            if (velocidadY > 0) {
		                setLocation(getX(), obstaculo.getY() - getHeight());
		                velocidadY = 0;
		                enElAire = false;
		            } else {
		                // Si está subiendo, se va a detener el salto.
		                velocidadY = 0;
		            }
		            
		        }
		        if (chequeoColisionArriba(3, obstaculo)) {
		        	// Si está subiendo, se va a detener el salto.
	                velocidadY = 0;
	            }
		    }
		    if (colisionaConEnemigoDesdeArriba(enemigo)) {
            	// Eliminar enemigo del panel
                enemigo.setVisible(false);
           	    getParent().remove(enemigo);
           	    getParent().repaint();
           	    
           	    
        	}
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

		
		public void moverIzquierda(int velocidad) {
		    int posX = getX();
		    int posY = getY();
		    if (posX > 0) {
		        setLocation(posX - velocidad, posY);
		    }
		}
		
		public void moverDerecha(int anchoPanel, int velocidad) {
		    int posX = getX();
		    int posY = getY();
		    if (posX + getWidth() < anchoPanel) {
		        setLocation(posX + velocidad, posY);
		    }
		}

		public void saltar(ArrayList<Obstaculo> obstaculos, Enemigo enemigo) { 
			int velocidad_salto = 5;
			int altura_minima = 470;
			int altura_limite = 100;
					
						if (!enElAire) { // solo salta si está en el piso
				            velocidadY = fuerzaSalto;
				            enElAire = true;
				        }
		                	
		                
		                
		                	
				
			
		}

	
}
