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
			private int fuerzaSalto = -13; // negativa porque sube
			private int gravedad = 1;
			private boolean enElAire = false;
			public ArrayList<Obstaculo> obstaculos;
			private NivelBase nivel;
			private Timer gravedadTimer;
			public ArrayList<Bala> balas;

			public Player(int posX, int posY, int ancho, int alto, ArrayList<Obstaculo> obstaculos, ArrayList<Enemigo> enemigos, NivelBase nivel, ArrayList<Bala> balas) {
		        setBounds(posX, posY, ancho, alto);
		        
		        this.balas = balas;
		        this.obstaculos = obstaculos;
		        this.nivel = nivel;
				
				// Se va a ejecutar el timer de gravedad dentro del Player
		        this.gravedadTimer = new Timer(30, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                aplicarGravedad(enemigos);
		            }
		        });
		        this.gravedadTimer.start();
			}
			
			
			private void aplicarGravedad(ArrayList<Enemigo> enemigos) {
			    velocidadY += gravedad;
			    setLocation(getX(), getY() + velocidadY);

			    enElAire = true; // asumimos que está en el aire hasta comprobar piso

			    // 👉 copiar obstaculos para evitar CME si se limpian al cambiar de nivel
			    for (Obstaculo obstaculo : new ArrayList<>(obstaculos)) {
			        if (velocidadY > 0 && chequeoColisionAbajo(obstaculo)) { // solo si cae
			            setLocation(getX(), obstaculo.getY() - getHeight());
			            velocidadY = 0;
			            enElAire = false;
			        }
			        if (!obstaculo.traspasable) {
			            if (chequeoColisionArriba(3, obstaculo)) {
			                velocidadY = 0; // tope al saltar
			            }
			        }
			    }

			    ArrayList<Enemigo> aEliminar = new ArrayList<>();

			    // 👉 también iterar sobre una copia de enemigos
			    for (Enemigo enemigo : new ArrayList<>(enemigos)) {
			        if (colisionaConEnemigoDesdeArriba(enemigo)) {
			        	if (enemigo.restarVida(1)){
			            enemigo.detenerPatrulla();
			            enemigo.setVisible(false);
			            if (getParent() != null) {
			                getParent().remove(enemigo);
			                getParent().repaint();
			            }
			            aEliminar.add(enemigo);
			            
			            if (nivel != null && nivel.puntaje != null) {
			            	if("mate".equals(enemigo.tipo)){
			            		nivel.puntaje.sumarPuntos(200);
			            	} else if("carbon".equals(enemigo.tipo)) {
			            		nivel.puntaje.sumarPuntos(100);
			            	} else if("pelota".equals(enemigo.tipo)) {
			            		nivel.puntaje.sumarPuntos(500);
			            	}
			            	
			            }
			        	}
			        	rebote();
			        } else if (colisionaConEnemigoDesdeCostado(enemigo)) {
			            // Evitar múltiples disparos de game over
			            detenerTimers();

			            Container parent = getParent();
			            setVisible(false);
			            if (parent != null) {
			                parent.remove(this);
			                parent.repaint();
			            }
			            nivel.mostrarPantallaGameOver();
			            return; // salgo para no seguir procesando
			        }
			        
			    }
			    
			    for (Bala bala : nivel.getBalas()) {
			        if (colisionaConBala(bala)) {
			            detenerTimers();
			            Container parent = getParent(); // ✅ cachear antes
			            setVisible(false);
			            if (parent != null) {
			                parent.remove(this);
			                parent.repaint(); // ✅ ahora sí seguro
			            }
			            nivel.mostrarPantallaGameOver();
			            return;
			        }
			    }

			    // eliminar todos juntos al final (y SOLO una vez)
			    enemigos.removeAll(aEliminar);
			}
			
			private void revisarMonedas() {
				
				for(Obstaculo obstaculo : new ArrayList<>(obstaculos)) {  // recorre todos los obstaculos
					if(obstaculo.esMoneda && getBounds().intersects(obstaculo.getBounds())) { // solo lo que son monedas
						
						nivel.puntaje.sumarMoneda(); // actualiza el hud
						nivel.puntaje.sumarPuntos(200); // cada moneda suma 200 puntos
						obstaculos.remove(obstaculo); // elimina la moneda
						Container parent = getParent();
						if(parent != null) {
							parent.remove(obstaculo);
							parent.repaint();
						}
					}
				}
			}
			
			public void detenerTimers() {
			    if (gravedadTimer != null) {
			        gravedadTimer.stop();
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
		    
		    public boolean colisionaConBala(Bala bala) {
		    	Rectangle jugador = getBounds();
		        Rectangle balaRectangle = bala.getBounds();
			    return jugador.intersects(balaRectangle);
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
	
		
	}