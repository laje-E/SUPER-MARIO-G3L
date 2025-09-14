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
    private Player player; // lo declaro arriba por claridad
    private FondoPanel fondoPanel;

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


        player = new Player(100, 350, 30, 50, obstaculos);
        player.setBackground(Color.RED);
        player.setFocusable(false); // que el jugador no robe el foco
        contentPane.add(player);
        
        
        ImageIcon pastoIcon = new ImageIcon("bin/img/masPasto64a.png");
        ImageIcon tierraIcon = new ImageIcon("bin/img/masTierra64a.png");
        
        
        int sueloY = 500; // coordenada Y donde empieza la tierra
        int alturaVentana = 600;
        int tileSize = 64;

        // Calcular cuántos tiles caben en ancho y alto
        int tilesX = 1000 / tileSize; // ancho del nivel en pixeles / tamaño tile
        int tilesAbajo = (alturaVentana - sueloY) / tileSize;
        
        // Capa de pasto (sobre el suelo)
        for (int i = 0; i < tilesX; i++) {
            Obstaculo pasto = new Obstaculo(obstaculos, pastoIcon);
            pasto.setBounds(i * tileSize, sueloY - tileSize, tileSize, tileSize);
            contentPane.add(pasto);
            obstaculos.add(pasto);
        }
        
        
        for (int y = 0; y < tilesAbajo; y++) {
            for (int i = 0; i < tilesX; i++) {
                Obstaculo tierra = new Obstaculo(obstaculos, tierraIcon);
                tierra.setBounds(i * tileSize, sueloY + y * tileSize, tileSize, tileSize);
                contentPane.add(tierra);
                obstaculos.add(tierra);
            }
        }
        
        
        int[][] nivel1 = {
                {50, 400, 80, 10},   // plataforma 1
                {150, 370, 80, 10},   // plataforma 2
                {250, 320, 80, 10}    // plataforma 3
//                {370, 236, 122, 200},   // edificio 1. Edificios que después se van a ir al fondo no como obstáculos.
//                {500, 206, 117, 230},   // edificio 2
//                {640, 221, 125, 215}   // edificio 3
           };

            
            
           for (int[] bloque : nivel1) {
                Obstaculo o = new Obstaculo(obstaculos);
                o.setBackground(Color.GREEN);
                o.setBounds(bloque[0], bloque[1], bloque[2], bloque[3]);
                contentPane.add(o);
                obstaculos.add(o);
            }
           
           
           
        
        
        fondoPanel = new FondoPanel();
        fondoPanel.setBounds(0, 0, 1600, 600);
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
                        player.moverIzquierda(3);
                        fondoPanel.desplazamiento -= 2; // mueve el fondo hacia la derecha
                        fondoPanel.repaint();
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
                        player.moverDerecha(contentPane.getWidth(), 3);
                        fondoPanel.desplazamiento += 2; // mueve el fondo hacia la izquierda
                        fondoPanel.repaint();
                    }
                }
                if (wPressed) {
                    player.saltar();
                    wPressed = false;
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