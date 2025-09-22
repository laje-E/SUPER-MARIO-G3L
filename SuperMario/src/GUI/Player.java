package GUI;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;
	
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
	
	public class Player extends JPanel {
	
			private static final long serialVersionUID = 1L;
			
			private int velocidadY = 80;
			private int fuerzaSalto = -13; // negativa porque sube
			private int gravedad = 1;
			private boolean enElAire = false;
			public ArrayList<Obstaculo> obstaculos;
			private NivelBase nivel;
			private Timer gravedadTimer;
			public ArrayList<Bala> balas;
			private boolean muerto = false;
			public ImageIcon icon;
			
			
			public Player(int posX, int posY, int ancho, int alto, ArrayList<Obstaculo> obstaculos, ArrayList<Enemigo> enemigos, NivelBase nivel, ArrayList<Bala> balas, ImageIcon icono) {
		        setBounds(posX, posY, ancho, alto);
		        
		        this.balas = balas;
		        this.obstaculos = obstaculos;
		        this.nivel = nivel;
		        setOpaque(false);
				
				// Se va a ejecutar el timer de gravedad dentro del Player
		        this.gravedadTimer = new Timer(30, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                aplicarGravedad(enemigos);
		            }
		        });
		        	this.gravedadTimer.start();
			}
			
			public boolean estaMuerto() {
			    return muerto;
			}
			
			public void setIcon(ImageIcon nuevaImagen) {
			    this.icon = nuevaImagen;
			    repaint();
			}
			
			@Override
			protected void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    if (icon != null) {
			        g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
			    }
			}
			
			
			
			
			private void aplicarGravedad(ArrayList<Enemigo> enemigos) {
				if (muerto) return;
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
			        		Sonido.reproducirEfecto("sonidos/player/pop.wav");
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
			          
			        	morir();    // <- en vez de todo el bloque que tenías

			            return; // salgo para no seguir procesando
			        }
			        
			    }
			    
			    for (Bala bala : nivel.getBalas()) {
			        if (colisionaConBala(bala)) {
			        	morir();    // <- en vez de todo el bloque que tenías

			            return; // salgo para no seguir procesando
			        }
			    }

			    enemigos.removeAll(aEliminar);	// eliminar todos juntos al final (y SOLO una vez)

			}
			
			
			private void morir() {
		        muerto = true;       // <- bloquea todos los movimientos
		        detenerTimers();
		        setVisible(false);

		        Container parent = getParent();
		        if (parent != null) {
		            parent.remove(this);
		            parent.repaint();
		        }

		        Sonido.reproducirEfecto("sonidos/player/gameOver.wav");

		        new javax.swing.Timer(6000, ev -> {
		            ((javax.swing.Timer) ev.getSource()).stop();
		            nivel.mostrarPantallaGameOver();
		        }).start();
		    }

			
			public void detenerTimers() {
			    if (gravedadTimer != null) {
			        gravedadTimer.stop();
			    }
			}



		    public void saltar() {
		    	 if (muerto) return;
		    	 
		        if (!enElAire) { // solo salta si está en el piso
		            velocidadY = fuerzaSalto;
		            enElAire = true;
		            Sonido.reproducirEfecto("sonidos/player/salto.wav"); 
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
				if (muerto) return;
				int posX = getX();
				int posY = getY();
				if (posX + getWidth() < anchoPanel) {
					setLocation(posX + velocidad, posY);
				}
			}	
	
			public void moverIzquierda(int velocidad) {
				if (muerto) return;
				int posX = getX();
				int posY = getY();
				if (posX > 0) {
					setLocation(posX - velocidad, posY);
				}
			}			
	
		
	}