package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SuperMario extends JFrame{
	private JPanel contentPane;	
	private boolean aPressed = false;
	private boolean dPressed = false;
	private boolean wPressed = false;
	public ArrayList<Obstaculo> obstaculos;
	
	public SuperMario(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setFocusable(true);
		requestFocusInWindow();
		setBounds(100, 100, 816, 572);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		obstaculos = new ArrayList<>();
	
		Player player = new Player(360, 470, 20, 20);
		player.setBackground(Color.BLUE);
		contentPane.add(player);
		
		Obstaculo obstaculo = new Obstaculo(100, 470, 80, 10);
		obstaculo.setBackground(Color.GREEN);
		contentPane.add(obstaculo);
		obstaculos.add(obstaculo);
		
		Obstaculo obstaculo2 = new Obstaculo(200, 420, 80, 10);
		obstaculo2.setBackground(Color.GREEN);
		contentPane.add(obstaculo2);
		obstaculos.add(obstaculo2);
		
		Obstaculo suelo = new Obstaculo(0, 500, 1000, 10);
		suelo.setBackground(Color.black);
		contentPane.add(suelo);
		obstaculos.add(suelo);
		
		Obstaculo pared = new Obstaculo(500, 450, 10, 50);
		pared.setBackground(Color.black);
		contentPane.add(pared);
		obstaculos.add(pared);
		
		Enemigo enemigo = new Enemigo (100, 450, 50, 50, 500, 801);
		enemigo.setBackground(Color.RED);
		contentPane.add(enemigo);
		
		enemigo.patrullar();
		

		
		addKeyListener(new KeyListener() { // Se abre el listener para poder escuchar input del teclado en el juego.
			
			public void keyTyped(KeyEvent e) {} 

		    public void keyPressed(KeyEvent e) {
		        int teclaPresionada = e.getKeyCode();
		        
		        if (teclaPresionada == KeyEvent.VK_A) { aPressed = true; }      // Actualiza los booleanos de las teclas cuando están presionadas.
		        if (teclaPresionada == KeyEvent.VK_D) { dPressed = true; }
		        if (teclaPresionada == KeyEvent.VK_W) { wPressed = true; }

		        if (aPressed) { 
		        	boolean puede_mover = true;	
		        	for (Obstaculo obstaculo : obstaculos) {
		        		if (player.chequeoColisionX(-15, obstaculo)) {
		        				puede_mover = false;
		        				break;
		        			}
		        	
		        		}
		        	if (puede_mover) {
		        		player.moverIzquierda(); 
		        	}
		        	}
		        if (dPressed) { 
		        	boolean puede_mover = true;
		        	for (Obstaculo obstaculo : obstaculos) {
		        		if (player.chequeoColisionX(15, obstaculo)) {
		        			puede_mover = false;
		        			break;
		        			
		        		}
		        	
		        	}
		        	if (puede_mover) {
		        		player.moverDerecha(contentPane.getWidth());
		        	}
		        }
		        if (wPressed) { 
		        	
		        		player.saltar(obstaculos);
		        	
		        } 
		        
		    }

		    @Override
		    public void keyReleased(KeyEvent e) {
		        int teclaPresionada = e.getKeyCode();
		        if (teclaPresionada == KeyEvent.VK_A) { aPressed = false; }     // Actualiza los booleanos cuando una tecla es soltada.
		        if (teclaPresionada == KeyEvent.VK_D) { dPressed = false; }
		        if (teclaPresionada == KeyEvent.VK_W) { wPressed = false; }
		    }
		});
		Timer gravedad = new Timer(30, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (!player.estaApoyado(obstaculos)) {
		            player.setLocation(player.getX(), player.getY() + 1); // velocidad de caída
		        }
		    }
		});
		gravedad.start();
	}

	public static void main(String[] args) {
	    SuperMario juego = new SuperMario();
	    juego.setVisible(true);
	}
}
