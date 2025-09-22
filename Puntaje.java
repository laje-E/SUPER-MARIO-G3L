package GUI;

import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Puntaje extends JLabel{
	private int Puntos;
	private int Nivel;
	private int Tiempo;
	private Timer timer;
	
	public Puntaje(int nivelInicial, int tiempoInicial) {
		this.Nivel = nivelInicial;
		this.Tiempo = tiempoInicial;
		this.Puntos = 0;
		
		
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
			" Puntos: " + Puntos + " | Nivel: " + Nivel +  " | Tiempo: " + Tiempo
				);
	}
	public void sumarPuntos(int cantidad) {
		Puntos += cantidad;
		actualizarTexto();
	}
	
	public void siguienteNivel() {
		Nivel++;
		actualizarTexto();
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
	