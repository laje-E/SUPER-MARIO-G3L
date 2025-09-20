package GUI;

import java.awt.Container;
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

			private ControladorJuego controlador;
			
			public Player(int posX, int posY, int ancho, int alto, ArrayList<Obstaculo> obstaculos, ArrayList<Enemigo> enemigos, ControladorJuego controlador) {
		        setBounds(posX, posY, ancho, alto);
		        
		        this.obstaculos = obstaculos;
		        this.controlador = controlador;
				
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

			    for (Obstaculo obstaculo : obstaculos) {
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

			    for (Enemigo enemigo : new ArrayList<>(enemigos)) {
			    	if (colisionaConEnemigoDesdeArriba(enemigo)) {
	            		
			    		enemigo.detenerPatrulla();
			    		// Eliminar enemigo del panel
			    		enemigo.setVisible(false);
	           	    	getParent().remove(enemigo);
	           	    	getParent().repaint();
	           	    	enemigos.remove(enemigo); // Se elimina al enemigo de la lista para que deje de "rastrearlo".
	           	    
	           	    	rebote();
			    	}
			    	else if (colisionaConEnemigoDesdeCostado(enemigo)) {
			    		Container parent = getParent(); // guardamos el contenedor antes de remover
			    		this.setVisible(false);
			    		if (parent != null) {
			    		    parent.remove(this);
			    		    parent.repaint();
			    		}
			    		controlador.mostrarPantallaGameOver();
			    		
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
		    
		    public boolean colisionaConEnemigoDesdeCostado(Enemigo enemigo) {
		    	Rectangle jugadorCostado = getBounds();
		        Rectangle enemigoCostado = enemigo.getBounds();
			    return jugadorCostado.intersects(enemigoCostado) && !colisionaConEnemigoDesdeArriba(enemigo);
			}
		    
			

			public boolean chequeoColisionArriba(int dy, Obstaculo obstaculo) {
			    int nuevaX = getX();
			    int nuevaY = getY() - dy; // Movimiento hacia arriba
			    Rectangle futuraPos = new Rectangle(nuevaX, nuevaY, getWidth(), getHeight());
			    return futuraPos.intersects(obstaculo.getBounds());
			}

			
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


			public void gravedadTimer(ArrayList<Enemigo> enemigos) {
				Timer gravedadTimer = new Timer(30, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                aplicarGravedad(enemigos);
		            }
		        });
		        gravedadTimer.start();
			}


			public void procesarMovimiento(boolean aPressed, boolean dPressed, boolean wPressed, int worldOffset, int anchoMapa, JPanel contentPane, FondoPanel fondoPanel, ArrayList<Obstaculo> obstaculos, ArrayList<Enemigo> enemigos) {
				Timer movimientoFluido = new Timer(15, new ActionListener() { 
		            public void actionPerformed(ActionEvent e) {
		            	
		            	if (aPressed) {
		            	    boolean puede_mover = true;
		            	    for (Obstaculo o : new ArrayList<>(obstaculos)) {
		            	        if (chequeoColisionX(-3, o)) {
		            	            puede_mover = false;
		            	            break;
		            	        }
		            	    }
		            	    if (puede_mover) {
		            	        // Si Mario todavía no llegó al centro de la pantalla o el mundo ya está en el inicio
		            	        if (getX() > getWidth() / 2 || worldOffset <= 0) {
		            	            moverIzquierda(5);
		            	        } else {
		            	            // Desplazamos el mundo a la derecha
		            	        	worldOffset -= 5;
		            	            fondoPanel.desplazamiento = worldOffset;
		            	            for (Obstaculo o : new ArrayList<>(obstaculos)) {
		            	                o.setLocation(o.getX() + 5, o.getY()); // mover obstáculos para la derecha.
		            	            }
		            	            for (Enemigo enemigo : new ArrayList<>(enemigos)) {
		            	            	enemigo.setLocation(enemigo.getX() + 3, enemigo.getY()); 
		            	            	enemigo.ajustarLimites(5); // si el mundo se mueve a la derecha (A)
		            	            }
		            	            fondoPanel.repaint();
		            	        }
		            	    }
		            	}

		                
		                if (dPressed) {                	
		                    boolean puede_mover = true;
		                    for (Obstaculo o : new ArrayList<>(obstaculos)) {
		                        if (chequeoColisionX(5, o)) {
		                            puede_mover = false;
		                            break;
		                        }
		                    }
		                    if (puede_mover) {
		                        // Si Mario está en la primera mitad de la pantalla o ya llegamos al final del mapa
		                        if (getX() < getWidth() / 2 || worldOffset >= anchoMapa - getWidth()) {
		                            moverDerecha(contentPane.getWidth(), 5);
		                        } else {
		                            // Se desplaza el mundo a la izquierda.
		                            worldOffset += 5;
		                            fondoPanel.desplazamiento = worldOffset;
		                            for (Obstaculo o : new ArrayList<>(obstaculos)) {
		                                o.setLocation(o.getX() - 5, o.getY()); // mover obstáculos para la izquierda.
		                            }
		                            for (Enemigo enemigo : new ArrayList<>(enemigos)) {
		                            	enemigo.setLocation(enemigo.getX() - 5, enemigo.getY());
		                            	enemigo.ajustarLimites(-5); // si el mundo se mueve a la izquierda (D)
		            	            }
		                            fondoPanel.repaint();
		                        }
		                    }
		                }

		                if (wPressed) {
		                    saltar();
		                    wPressed = false;
		                }
		            }
	                        
	        });
	        movimientoFluido.start();
			}

		
	}