package GUI;

import javax.swing.*;
import java.awt.*;


public class Puntaje extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int puntos;
	private int monedas;
	private String mundo;
	private int tiempo;
	
	
	
	public Puntaje() {
		this.puntos = 0;
		this.monedas = 0;
		this.mundo = "1-1";
		this.tiempo = 400; // en el mario original arranca con 400 segundos
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(800, 50));
	}
	
	public void sumarPuntos(int valor) {
		puntos+= valor;
		repaint();
	}
	
	
	public void sumarMoneda() {
		monedas++;
		repaint();
	}
	
	
	public void disminuirTiempo() {
		if(tiempo > 0) {
			tiempo--;
			repaint();
		}
	}
	@Override
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospaced", Font.BOLD, 20));
		
		// esta parte seria el nombre
		g.drawString("JUANCHO", 50, 30);//EL NOMBRE NO ESTA CONFIRMADO
		g.drawString(String.format("%06d", puntos), 50, 50);
		
		// esta parte seria las monedas
		g.drawString("PESOS", 250, 30);
		g.drawString("x" + monedas, 270, 50);
		
		// esta parte seria el mundo
		g.drawString("PROVINCIA", 450, 30);
		g.drawString(mundo, 470, 50);
		
		// esta parte seria el tiempo
		g.drawString("Tiempo", 650, 30);
		g.drawString(String.valueOf(tiempo), 660, 50);
		
		
	}
	public static void main(String[] args) {
	    JFrame ventana = new JFrame("Mario Argento");
	    Puntaje marcador = new Puntaje();                                     // todo esto es para probar si funca, dsp se borra

	    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    ventana.add(marcador);
	    ventana.pack();
	    ventana.setVisible(true);

	    
	    new Timer(1000, e -> marcador.disminuirTiempo()).start();
	}
	
}
	
	