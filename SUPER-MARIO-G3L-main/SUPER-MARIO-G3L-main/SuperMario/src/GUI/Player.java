	package GUI;
	
		import java.awt.Rectangle;
	
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.ArrayList;
	import javax.swing.JPanel;
	import javax.swing.Timer;
	
		public class Player extends JPanel {
	
			private static final long serialVersionUID = 1L;
			
			private int velocidadY = 0;
			private int fuerzaSalto = -12; // negativa porque sube
			private int gravedad = 1;
			private boolean enElAire = false;
			
			public ArrayList<Obstaculo> obstaculos;
			
			public Player(int posX, int posY, int ancho, int alto, ArrayList<Obstaculo> obstaculos, ArrayList<Enemigo> enemigos) {
		        setBounds(posX, posY, ancho, alto);
		        
		        this.obstaculos = obstaculos;
				
				
				// Se va a ejecutar el timer de gravedad dentro del Player
		        Timer gravedadTimer = new Timer(30, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                aplicarGravedad(enemigos);
		            }
		        });
		        gravedadTimer.start();
			}
			
			
			private void aplicarGravedad(ArrayList<Enemigo> enemigos) {
			    velocidadY += gravedad;
			    setLocation(getX(), getY() + velocidadY);

			    enElAire = true; // asumimos que está en el aire hasta comprobar piso

			    for (Obstaculo obstaculo : new ArrayList<>(obstaculos)) {
			        if (velocidadY > 0 && chequeoColisionAbajo(obstaculo)) { // solo si cae
			            setLocation(getX(), obstaculo.getY() - getHeight());
			            velocidadY = 0;
			            enElAire = false;
			        }
			        if (!obstaculo.traspasable) {
			        	if (chequeoColisionArriba(3, obstaculo)) {
				        	// Si está subiendo, se va a detener el salto.
			                velocidadY = 0;
			            }
			        }
			    }
			    for (Enemigo enemigo : enemigos) {
			    	if (colisionaConEnemigoDesdeArriba(enemigo)) {
	            		// Eliminar enemigo del panel
			    		enemigo.setVisible(false);
	           	    	getParent().remove(enemigo);
	           	    	getParent().repaint();
	           	    	
	           	    	// aqui hay un error que parece que el enemigo no se elimina por completo
	           	    
	           	    	rebote();
			    	}
	        	}
			}



		    public void saltar() {
		        if (!enElAire) { // solo salta si está en el piso
		            velocidadY = fuerzaSalto;
		            enElAire = true;
		        }
		    }
		    
		    public void rebote() {
		        velocidadY = fuerzaSalto;
		        enElAire = true;
		    }
			
			
			
		    public boolean chequeoColisionX(int dx, Obstaculo obstaculo) {
		        Rectangle futuraPos = new Rectangle(getX() + dx, getY(), getWidth(), getHeight());
		        return futuraPos.intersects(obstaculo.getBounds());
		    }

		    public boolean chequeoColisionAbajo(Obstaculo obstaculo) {
		        Rectangle piesJugador = new Rectangle(getX(), getY() + getHeight(), getWidth(), Math.max(5, velocidadY));
		        return piesJugador.intersects(obstaculo.getBounds());
		    }
		    
		    public boolean colisionaConEnemigoDesdeArriba(Enemigo enemigo) {
			    Rectangle abajoJugador = new Rectangle(getX(), getY() + getHeight(), getWidth(), 5);
			    Rectangle arribaEnemigo = new Rectangle(enemigo.getX(), enemigo.getY(), enemigo.getWidth(), 5);
			    return abajoJugador.intersects(arribaEnemigo);
			}
		    
			
			
			
//			public boolean chequeoColisionX(int dx, Obstaculo obstaculo) {
//			    int nuevaX = getX() + dx;
//			    int nuevaY = getY();
//			    Rectangle futuraPos = new Rectangle(nuevaX, nuevaY, getWidth(), getHeight());
//			    
//			    return futuraPos.intersects(obstaculo.getBounds());
//			    
//			}
//			
			public boolean chequeoColisionArriba(int dy, Obstaculo obstaculo) {
			    int nuevaX = getX();
			    int nuevaY = getY() - dy; // Movimiento hacia arriba
			    Rectangle futuraPos = new Rectangle(nuevaX, nuevaY, getWidth(), getHeight());
			    return futuraPos.intersects(obstaculo.getBounds());
			}
//			
//			public boolean chequeoColisionAbajo(int dy, Obstaculo obstaculo) {
//			    int nuevaX = getX();
//			    int nuevaY = getY() + dy; // Movimiento hacia abajo
//			    Rectangle futuraPos = new Rectangle(nuevaX, nuevaY, getWidth(), getHeight());
//			    return futuraPos.intersects(obstaculo.getBounds());
//			}
//	
//			public boolean estaApoyado (ArrayList<Obstaculo> obstaculos) {
//				for (Obstaculo obstaculo : obstaculos) {
//					Rectangle abajoJugador = new Rectangle (getX(), getY() + 1, getWidth(), getHeight());
//					if (abajoJugador.intersects(obstaculo.getBounds())) {
//						return true;
//					}	
//				}
//				return false;
//			}
	
			
			public void moverDerecha(int anchoPanel, int velocidad) {
				int posX = getX();
				int posY = getY();
				if (posX + getWidth() < anchoPanel) {
					setLocation(posX + velocidad, posY);
				}
			}	
	
			public void moverIzquierda(int velocidad) {
				int posX = getX();
				int posY = getY();
				if (posX > 0) {
					setLocation(posX - velocidad, posY);
				}
			}			
	
		
	}