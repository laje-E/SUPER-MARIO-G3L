package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public abstract class NivelBase extends JFrame {	
// Abstract señala que esta clase es un constructor y que no puede crear nada por si solo, sino que es una base
	protected JPanel contentPane;
    protected Player player;
    protected ArrayList<Obstaculo> obstaculos = new ArrayList<>();
    protected ArrayList<Enemigo> enemigos = new ArrayList<>();
    protected FondoPanel fondoPanel;
    protected int worldOffset = 0;
    protected boolean nivelSuperado = false;
    protected int anchoMapa = 4480;
    boolean aPressed = false;
	boolean dPressed = false;
	boolean wPressed = false;
	protected Timer movimientoFluido;

    public NivelBase() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel(null);
        setContentPane(contentPane);
        contentPane.setFocusable(true);
        contentPane.setFocusTraversalKeysEnabled(false);
        
        configurarJugador();
        construirNivel(); // método abstracto
        configurarFondo();
        configurarMovimiento();
    }

    protected abstract void construirNivel(); // cada nivel define sus obstáculos y enemigos

    protected void configurarJugador() {
        player = new Player(100, 350, 30, 50, obstaculos, enemigos);
        player.setBackground(Color.RED);
        player.setFocusable(false);
        contentPane.add(player);
    }

    protected void configurarFondo() {
        fondoPanel = new FondoPanel();
        fondoPanel.setBounds(0, 0, anchoMapa, 600);
        contentPane.add(fondoPanel);
    }

    protected void configurarMovimiento() {
    	
    	
    	
    	// Pide mantener bien el foco para cuando la ventana se abra
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                contentPane.requestFocusInWindow();
            }
        });
        
        
        contentPane.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {}

            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                int tecla = e.getKeyCode();
                if (tecla == java.awt.event.KeyEvent.VK_A) aPressed = true;
                if (tecla == java.awt.event.KeyEvent.VK_D) dPressed = true;
                if (tecla == java.awt.event.KeyEvent.VK_W) wPressed = true;
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                int tecla = e.getKeyCode();
                if (tecla == java.awt.event.KeyEvent.VK_A) aPressed = false;
                if (tecla == java.awt.event.KeyEvent.VK_D) dPressed = false;
                if (tecla == java.awt.event.KeyEvent.VK_W) wPressed = false;
            }
        });
        
        

        movimientoFluido = new Timer(15, new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	
            	if (aPressed) {
            	    boolean puede_mover = true;
            	    for (Obstaculo o : obstaculos) {
            	        if (player.chequeoColisionX(-3, o)) {
            	            puede_mover = false;
            	            break;
            	        }
            	    }
            	    if (puede_mover) {
            	        // Si Mario todavía no llegó al centro de la pantalla o el mundo ya está en el inicio
            	        if (player.getX() > getWidth() / 2 || worldOffset <= 0) {
            	            player.moverIzquierda(3);
            	        } else {
            	            // Desplazamos el mundo a la derecha
            	            worldOffset -= 3;
            	            fondoPanel.desplazamiento = worldOffset;
            	            for (Obstaculo o : new ArrayList<>(obstaculos)) {
            	                o.setLocation(o.getX() + 3, o.getY()); // mover obstáculos para la derecha.
            	            }
            	            for (Enemigo enemigo : new ArrayList<>(enemigos)) {
            	            	enemigo.setLocation(enemigo.getX() + 3, enemigo.getY()); 
            	            	enemigo.ajustarLimites(3); // si el mundo se mueve a la derecha (A)
            	            }
            	            fondoPanel.repaint();
            	        }
            	    }
            	}

                
                
                if (dPressed) {                	
                    boolean puede_mover = true;
                    for (Obstaculo o : new ArrayList<>(obstaculos)) {
                        if (player.chequeoColisionX(3, o)) {
                            puede_mover = false;
                            break;
                        }
                    }
                    if (puede_mover) {
                        // Si Mario está en la primera mitad de la pantalla o ya llegamos al final del mapa
                        if (player.getX() < getWidth() / 2 || worldOffset >= anchoMapa - getWidth()) {
                            player.moverDerecha(contentPane.getWidth(), 3);
                        } else {
                            // Se desplaza el mundo a la izquierda.
                            worldOffset += 3;
                            fondoPanel.desplazamiento = worldOffset;
                            for (Obstaculo o : new ArrayList<>(obstaculos)) {
                                o.setLocation(o.getX() - 3, o.getY()); // mover obstáculos para la izquierda.
                            }
                            for (Enemigo enemigo : new ArrayList<>(enemigos)) {
                            	enemigo.setLocation(enemigo.getX() - 3, enemigo.getY());
                            	enemigo.ajustarLimites(-3); // si el mundo se mueve a la izquierda (D)
            	            }
                            fondoPanel.repaint();
                        }
                    }
                }

                if (wPressed) {
                    player.saltar();
                    wPressed = false;
                }
                
                int posicionJugador = worldOffset + player.getX();
                if (posicionJugador >= 4155 && !nivelSuperado) {
                    nivelSuperado = true; 				// evita que se vuelva a superar el nivel.
                    fondoPanel.setearNivelSuperado(true);
                    repaint();
                    System.out.println("Nivel superado!");
                    
                    new Timer(2000, new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            ((Timer) evt.getSource()).stop();
                            GestorNiveles.avanzarNivel(NivelBase.this);
                        }
                    }).start();
                }
                
            }
                        
        });
        movimientoFluido.start();
    }
    
    public void cerrarNivel() {
        if (movimientoFluido != null) {
            movimientoFluido.stop();
        }
        for (Enemigo enemigo : enemigos) {
            enemigo.detenerPatrulla(); // suponiendo que tengas este método en Enemigo
        }
        dispose(); // cierra la ventana
        obstaculos.clear();
        enemigos.clear();
        contentPane.removeAll();

    }
    
    Timer juegoTimer = new Timer(15, e -> {
    	configurarJugador();
        construirNivel(); // método abstracto
        configurarFondo();
        configurarMovimiento();
        repaint();
    });


    
}
