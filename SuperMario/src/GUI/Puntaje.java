package GUI;

import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Puntaje extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Puntos;
	private int Nivel = 1;
	private int Tiempo;
	private Timer timer;
	
	public Puntaje(int nivelInicial, int tiempoInicial, int puntosIniciales, NivelBase nivelBase) {
	    this.Nivel = nivelInicial;
	    this.Tiempo = tiempoInicial;
	    this.Puntos = puntosIniciales;
		actualizarTexto();
		
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Tiempo > 0) {
					Tiempo--;
					actualizarTexto();
				} else {
					timer.stop();
					Sonido.reproducirEfecto("sonidos/player/gameOver.wav");

			        new javax.swing.Timer(6000, ev -> {
			            ((javax.swing.Timer) ev.getSource()).stop();
			            nivelBase.mostrarPantallaGameOver();
			        }).start();
				}
			}
		});
		timer.start();
		
		
		
	}
	private void actualizarTexto() {
		setText("Puntos: " + Puntos + " | Nivel: " + Nivel + " | Tiempo; " + Tiempo);
	}
	
	public void sumarPuntos(int cantidad) {
	    Puntos += cantidad;
	    GestorNiveles.sumarPuntos(cantidad); // <- lo guardamos globalmente
	    actualizarTexto();
	}
	
	public void siguienteNivel() {
		Nivel++;
	    System.out.println("Nivel actualizado a: " + Nivel);
	    actualizarTexto();
	    revalidate(); // si estás en un JPanel
	    repaint();
	}
	
	public int getPuntos() {
		return Puntos; 
	}
	
	public int getNivel() {
		return Nivel;
	}
	
	public int getTiempo() {
		return Tiempo;
	}
	
	public void detenerTimer() {
		timer.stop();
	}
	
}
	