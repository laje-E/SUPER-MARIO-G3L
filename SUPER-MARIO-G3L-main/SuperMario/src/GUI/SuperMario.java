package GUI;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SuperMario extends JFrame{
	private JPanel contentPane;	
	private boolean aPressed = false;
	private boolean dPressed = false;
	private boolean wPressed = false;
	
	public SuperMario(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setFocusable(true);
		requestFocusInWindow();
		setBounds(100, 100, 816, 572);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		Player player = new Player(360, 470, 80, 10);
		player.setBackground(Color.red);
		contentPane.add(player);
		
		Obstaculo obstaculo = new Obstaculo(100, 470, 80, 10);
		obstaculo.setBackground(Color.GREEN);
		contentPane.add(obstaculo);
		

		
		addKeyListener(new KeyListener() { // Se abre el listener para poder escuchar input del teclado en el juego.
			
			public void keyTyped(KeyEvent e) {} 

		    public void keyPressed(KeyEvent e) {
		        int teclaPresionada = e.getKeyCode();
		        
		        if (teclaPresionada == KeyEvent.VK_A) { aPressed = true; }      // Actualiza los booleanos de las teclas cuando están presionadas.
		        if (teclaPresionada == KeyEvent.VK_D) { dPressed = true; }
		        if (teclaPresionada == KeyEvent.VK_W) { wPressed = true; }

		        if (aPressed) { 
		        		/*alternativa = player.getX() <= obstaculo.getX() + obstaculo.getWidth() && player.getX() + getWidth() >= obstaculo.getX() &&
		        						player.getY() <= obstaculo.getY() + obstaculo.getHeight() && player.getY() + getHeight() >= obstaculo.getY()*/
		        	/*
		        	{
		        		player.moverDerecha(contentPane.getWidth());
		        	}
		        	else {
		        		player.moverIzquierda(); 
		        	}
		        	*/
		        	if (!player.chequeoColisionX(-15, obstaculo)) {
		        			player.moverIzquierda(); 
		        		}
		        	
		        	}
		        if (dPressed) { 
		        	if (!player.chequeoColisionX(15, obstaculo)) {
		        		player.moverDerecha(contentPane.getWidth());
		        	}
		        	
		        }
		        if (wPressed) { 
		        	/*
		        	if (player.getBounds().intersects(obstaculo.getBounds())) {
		        		
		        	}
		        	else {
		        		player.saltar();
		        	}
		        	*/
		        		player.saltar(obstaculo);
		        	
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
	}

	public static void main(String[] args) {
	    SuperMario juego = new SuperMario();
	    juego.setVisible(true);
	}
}
