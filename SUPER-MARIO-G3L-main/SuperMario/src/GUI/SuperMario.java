package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SuperMario extends JFrame {
    private JPanel contentPane;
    private boolean aPressed = false;
    private boolean dPressed = false;
    private boolean wPressed = false;
    public ArrayList<Obstaculo> obstaculos;
    private Player player; // lo declaro arriba por claridad

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

        player = new Player(360, 470, 80, 10, obstaculos);
        player.setBackground(Color.RED);
        player.setFocusable(false); // que el jugador no robe el foco
        contentPane.add(player);

        Obstaculo obstaculo = new Obstaculo(100, 470, 80, 10);
        obstaculo.setBackground(Color.GREEN);
        obstaculo.setFocusable(false);
        contentPane.add(obstaculo);
        obstaculos.add(obstaculo);

        Obstaculo obstaculo2 = new Obstaculo(200, 400, 80, 10);
        obstaculo2.setBackground(Color.GREEN);
        obstaculo2.setFocusable(false);
        contentPane.add(obstaculo2);
        obstaculos.add(obstaculo2);

        Obstaculo suelo = new Obstaculo(0, 500, 1000, 10);
        suelo.setBackground(Color.BLACK);
        suelo.setFocusable(false);
        contentPane.add(suelo);
        obstaculos.add(suelo);

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
                    for (Obstaculo o : obstaculos) {
                        if (player.chequeoColisionX(-3, o)) {
                            puede_mover = false;
                            break;
                        }
                    }
                    if (puede_mover) {
                        player.moverIzquierda(3);
                    }
                }
                if (dPressed) {
                    boolean puede_mover = true;
                    for (Obstaculo o : obstaculos) {
                        if (player.chequeoColisionX(3, o)) {
                            puede_mover = false;
                            break;
                        }
                    }
                    if (puede_mover) {
                        player.moverDerecha(contentPane.getWidth(), 3);
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
