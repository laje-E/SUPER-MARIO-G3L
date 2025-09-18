package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaGameOver extends JFrame {
	
	private boolean mostrarTexto = true;
	
	public PantallaGameOver() {
		setTitle("GAME OVER");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	
	
	
	JPanel panel = new JPanel() {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			
			// fondo negro
			g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
			
			// texto que dice game over
			g.setFont(new Font("Arial", Font.BOLD, 50));
            g.setColor(Color.DARK_GRAY);
            g.drawString("GAME OVER", 105, 150);
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 100, 145);
			
			
			// texto para que el jugador presione un boton
            if (mostrarTexto) {
                g.setFont(new Font("Arial", Font.BOLD, 30));
                g.setColor(Color.WHITE);
                g.drawString("¿Continuar?", 160, 220);
            }
            
			
		}
	};
	panel.setLayout(null);
	
	// boton para reintentar
	JButton retryButton = new JButton("Reintentar");
    retryButton.setBounds(100, 320, 120, 40);
    retryButton.setBackground(new Color(0, 120, 0));
    retryButton.setForeground(Color.WHITE);
    retryButton.setFocusPainted(false);
    panel.add(retryButton);
	
	
	// boton para salir
    JButton exitButton = new JButton("Salir");
    exitButton.setBounds(280, 320, 120, 40);
    exitButton.setBackground(new Color(150, 0, 0));
    exitButton.setForeground(Color.WHITE);
    exitButton.setFocusPainted(false);
    panel.add(exitButton);
	
	
	// accion para que funcionen los botones
	retryButton.addActionListener(new ActionListener(){
	@Override
	public void actionPerformed(ActionEvent e) {
		// con esto se reiniciaria el juego
		JOptionPane.showMessageDialog(null, "Reiniciando!");
		dispose(); // esto cierra la pantalla de game over
	}
	});
	
	// esto es el boton para cerrar el juego
	exitButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0); // cierra el juego
		}
	});
	Timer timer = new Timer(500, e -> {
        mostrarTexto = !mostrarTexto;
        panel.repaint();
    });
    timer.start();

   

	
	add(panel);
	}
	
	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> {
	        new PantallaGameOver().setVisible(true);
	    });
	}
	

}
