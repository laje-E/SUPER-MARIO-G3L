		package GUI;
	
		import java.awt.Rectangle;
	
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.ArrayList;
	import java.util.List;
	
	import javax.swing.JPanel;
	import javax.swing.Timer;
	
		public class Player extends JPanel {
	
			private static final long serialVersionUID = 1L;
			
			private int velocidadY = 0;
			private int fuerzaSalto = -13; // negativa porque sube
			private int gravedad = 1;
			private boolean enElAire = false;
//			private boolean teclaPresionada=true;
			
			public ArrayList<Obstaculo> obstaculos;
			
			public Player(int posX, int posY, int ancho, int alto, ArrayList<Obstaculo> obstaculos) {
		        setBounds(posX, posY, ancho, alto);
		        this.obstaculos = obstaculos;
				
				
				// Se va a ejecutar el timer de gravedad dentro del Player
		        Timer gravedadTimer = new Timer(30, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                aplicarGravedad();
		            }
		        });
		        gravedadTimer.start();
			}
			
			
			private void aplicarGravedad() {
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
			    }
			}


		    public void saltar() {
		        if (!enElAire) { // solo salta si está en el piso
		            velocidadY = fuerzaSalto;
		            enElAire = true;
		        }
		    }
			
			
			
		    public boolean chequeoColisionX(int dx, Obstaculo obstaculo) {
		        Rectangle futuraPos = new Rectangle(getX() + dx, getY(), getWidth(), getHeight());
		        return futuraPos.intersects(obstaculo.getBounds());
		    }

		    public boolean chequeoColisionAbajo(int dy, Obstaculo obstaculo) {
		        Rectangle futuraPos = new Rectangle(getX(), getY() + dy, getWidth(), getHeight());
		        return futuraPos.intersects(obstaculo.getBounds());
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
//			public boolean chequeoColisionArriba(int dy, Obstaculo obstaculo) {
//			    int nuevaX = getX();
//			    int nuevaY = getY() - dy; // Movimiento hacia arriba
//			    Rectangle futuraPos = new Rectangle(nuevaX, nuevaY, getWidth(), getHeight());
//			    return futuraPos.intersects(obstaculo.getBounds());
//			}
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
