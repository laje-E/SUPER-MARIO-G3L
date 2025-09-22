package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class menuInicio extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private Image fondoMenu;
	private panelInicio panelInicio;
	private Font fuenteSAO;
	private boolean mostrarTexto = true;
	private Clip musicaMenu;
	private Clip sonidoInicio;
			
	public menuInicio() {
		setBounds(0, 0, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null); // Centrar en pantalla
		
		// Cargar música de fondo
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(
					getClass().getResource("/sonidos/temasInGame/creditos.wav"));
			musicaMenu = AudioSystem.getClip();
			musicaMenu.open(audioIn);
			musicaMenu.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch (Exception e) {
			System.out.println("Error al cargar música: " + e.getMessage());
		}
	
		// Cargar sonido de inicio
		
		
		// Cargar fuente personalizada
		try {
			fuenteSAO = Font.createFont(Font.TRUETYPE_FONT, 
					getClass().getResourceAsStream("/fuentes/SuperMario64.ttf"));
		} 
		catch (Exception e) {
			fuenteSAO = new Font("Arial", Font.BOLD, 48);
		}
	
		panelInicio = new panelInicio();
		setContentPane(panelInicio);
		panelInicio.setFocusable(true);  // ← El panel debe ser focusable
	    panelInicio.requestFocusInWindow();
		addKeyListener(this);
		setFocusable(true);
	
		fondoMenu = new ImageIcon(getClass().getResource("/img/imagenMenu/imagenMenu_800x600.png")).getImage();
	
		// Timer para texto parpadeante
		Timer parpadearTexto = new Timer(1200, e -> {
			mostrarTexto = !mostrarTexto;
			repaint();
		});
		parpadearTexto.start();
	}
		
	private class panelInicio extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(fondoMenu, 0, 0, getWidth(), getHeight(), this);
			
			if (mostrarTexto) {
				g.setColor(Color.WHITE);
				g.setFont(fuenteSAO.deriveFont(Font.BOLD, 36f));
				g.drawString("Presiona ENTER para jugar", 140, 520);
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (musicaMenu != null && musicaMenu.isRunning()) {
				musicaMenu.stop();
			}
			
			if (sonidoInicio != null) {
				sonidoInicio.setFramePosition(0);
				sonidoInicio.start();
			}
			
			dispose();
			Nivel1 nivel1 = new Nivel1();
			nivel1.setVisible(true);
		}
	}
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
