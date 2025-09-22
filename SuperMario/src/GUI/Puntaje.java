package GUI;

import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Puntaje extends JLabel{
	private String nombreJugador;
	private int Puntos;
	private int Nivel = 1;
	private int Monedas;
	private int Tiempo;
	private Timer timer;
	
	public Puntaje(String nombreJugador, int nivelInicial, int tiempoInicial) {
		this.nombreJugador = nombreJugador;
		this.Nivel = 1;
		this.Tiempo = tiempoInicial;
		this.Puntos = 0;
		this.Monedas = 0;
		
		
		actualizarTexto();
		
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Tiempo > 0) {
					Tiempo--;
					actualizarTexto();
				} else {
					timer.stop();
				}
			}
		});
		timer.start();
		
		
		
	}
	private void actualizarTexto() {
		setText(
				nombreJugador + " | Puntos: " + Puntos + " | Nivel: " + Nivel + " | Tiempo; " + Tiempo + " |"
				);
	}
	public void sumarPuntos(int cantidad) {
		Puntos += cantidad;
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
	