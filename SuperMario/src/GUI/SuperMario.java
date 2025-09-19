package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;	
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SuperMario extends JFrame {
  
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private boolean aPressed = false;
    private boolean dPressed = false;
    private boolean wPressed = false;
    public ArrayList<Obstaculo> obstaculos;
    public ArrayList<Enemigo> enemigos;
    private Player player; // lo declaro arriba por claridad
    private FondoPanel fondoPanel;
    private int worldOffset = 0; // cuántos píxeles se movió el mundo
    private int anchoMapa = 4480;
    private boolean nivelFinalizado = false;


    public SuperMario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 800, 600);

        contentPane = new JPanel();
        contentPane.setLayout(null);

        contentPane.setFocusable(true);
        contentPane.setFocusTraversalKeysEnabled(false);

        setContentPane(contentPane);
        
        
        obstaculos = new ArrayList<>();
        
        enemigos = new ArrayList<>();

        
        Enemigo enemigo = new Enemigo (500, 386, 37, 50, 500, 801); // matecinini --> mirar tamaños!!
//        Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801); //carboncini --> mirar tamaños!!
//        Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
//        Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
//        Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
//        Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
//        Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
		enemigo.setBackground(Color.RED);
		contentPane.add(enemigo);
		enemigos.add(enemigo);

        player = new Player(100, 350, 30, 50, obstaculos, enemigos);
        player.setBackground(Color.RED);
        player.setFocusable(false); // que el jugador no robe el foco
        contentPane.add(player);
        
		enemigo.patrullar();
        
       ImageIcon pastoIcon = new ImageIcon(getClass().getResource("/img/pisos/pastoFixed.png"));
       ImageIcon tierraIcon = new ImageIcon(getClass().getResource("/img/pisos/tierraFixed.png"));

        
        
        int sueloY = 500; // coordenada Y donde empieza la tierra
        int alturaVentana = 600;
        int tileSize = 64;

        
        // Calcular cuántos tiles caben en ancho y alto
        int tilesX = anchoMapa / tileSize; // ancho del nivel en pixeles / tamaño tile
        int tilesAbajo = (alturaVentana - sueloY) / tileSize;
        
        // Capa de pasto (sobre el suelo)
        for (int i = 0; i < tilesX; i++) {
            Obstaculo pasto = new Obstaculo(obstaculos, pastoIcon, false);
            pasto.setBounds(i * tileSize, sueloY - tileSize, tileSize, tileSize);
            contentPane.add(pasto);
            obstaculos.add(pasto);
        }
        
        
        for (int y = 0; y < tilesAbajo; y++) {
            for (int i = 0; i < tilesX; i++) {
                Obstaculo tierra = new Obstaculo(obstaculos, tierraIcon, false);
                tierra.setBounds(i * tileSize, sueloY + y * tileSize, tileSize, tileSize);
                contentPane.add(tierra);
                obstaculos.add(tierra);
            }
        }
        
        
        int[][] nivel1 = {
        		{170, 350, 20, 20}, // plataforma 2
        		{250, 370, 80, 10}, // plataforma 3
        		{500, 390, 10, 50}, // pared 1
        		{795, 390, 10, 50}  // pared 2
//                {370, 236, 122, 200},   // edificio 1. Edificios que después se van a ir al fondo no como obstáculos.
//                {500, 206, 117, 230},   // edificio 2
//                {640, 221, 125, 215}   // edificio 3
//                {4000, 234, 50, 202}   // obelisco
           };

            
            
           for (int[] bloque : nivel1) {
                Obstaculo o = new Obstaculo(obstaculos);
                o.setBackground(Color.GREEN);
                o.setBounds(bloque[0], bloque[1], bloque[2], bloque[3]);
                contentPane.add(o);
                obstaculos.add(o);
            }
        
        fondoPanel = new FondoPanel();
        fondoPanel.setBounds(0, 0, anchoMapa, 600);
        contentPane.add(fondoPanel);
        
        
     // Se abre el listener para poder escuchar input del teclado en el juego.
        contentPane.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                int tecla = e.getKeyCode();
                if (tecla == KeyEvent.VK_A) aPressed = true;
                if (tecla == KeyEvent.VK_D) dPressed = true;
                if (tecla == KeyEvent.VK_W) wPressed = true;
            }
            public void keyReleased(KeyEvent e) {
                int tecla = e.getKeyCode();
                if (tecla == KeyEvent.VK_A) aPressed = false;
                if (tecla == KeyEvent.VK_D) dPressed = false;
                if (tecla == KeyEvent.VK_W) wPressed = false;
            }
        });

        // Pide mantener bien el foco para cuando la ventana se abra
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                contentPane.requestFocusInWindow();
            }
        });

        Timer movimientoFluido = new Timer(15, new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	
            	if (aPressed) {
            	    boolean puede_mover = true;
            	    for (Obstaculo o : new ArrayList<>(obstaculos)) {
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
                    
               
                if (posicionJugador >= 4155 && !nivelFinalizado) {
                	nivelFinalizado = true;
                    fondoPanel.setearNivelSuperado(true);
                    repaint();
                    
                 // Esperar 2 segundos y pasar al siguiente nivel
                    new Timer(2000, new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            ((Timer) evt.getSource()).stop();
                            GestorNiveles.avanzarNivel(SuperMario.this);
                        }
                    }).start();
                    
                    System.out.println("Nivel superado!");
                }
                
            }
                        
        });
        movimientoFluido.start();


    }

    public static void main(String[] args) {
        SuperMario juego = new SuperMario();
        juego.setVisible(true);
    }
}