package GUI;

import java.awt.*;
import javax.swing.*;

public class PantallaGameOver extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean mostrarTexto = true;
    private JButton retryButton;
    private JButton exitButton;

    public PantallaGameOver() {
        setLayout(null);

        // Timer que hace parpadear el texto de "¿Continuar?"
        Timer timer = new Timer(500, e -> {
            mostrarTexto = !mostrarTexto;
            repaint();
        });
        timer.start();

        // Botón de reintento
        retryButton = new JButton("Reintentar");
        retryButton.setSize(120, 40);
        retryButton.setBackground(new Color(0, 120, 0));
        retryButton.setForeground(Color.WHITE);
        retryButton.setFocusPainted(false);
        add(retryButton);

        // Botón para salir
        exitButton = new JButton("Salir");
        exitButton.setSize(120, 40);
        exitButton.setBackground(new Color(150, 0, 0));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        add(exitButton);

        // Acción del botón reintentar → recarga el último nivel jugado
        retryButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(PantallaGameOver.this);
            if (topFrame instanceof NivelBase) {
                topFrame.dispose(); 
                // Se carga el mismo nivel en el que estaba
                GestorNiveles.cargarNivel(topFrame);
            }
        });

        // Acción del botón salir → cierra el juego
        exitButton.addActionListener(e -> System.exit(0));
    }

    @Override
    public void doLayout() {
        super.doLayout();
        int anchoPanel = getWidth();
        int espacioEntre = 40;

        int anchoTotal = retryButton.getWidth() + espacioEntre + exitButton.getWidth();
        int startX = (anchoPanel - anchoTotal) / 2;
        int y = getHeight() - 80;

        retryButton.setLocation(startX, y);
        exitButton.setLocation(startX + retryButton.getWidth() + espacioEntre, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();

        // Fondo negro
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, anchoPanel, getHeight());

        // Texto "GAME OVER" con sombra
        Font fontGameOver = new Font("Arial", Font.BOLD, 50);
        g.setFont(fontGameOver);
        FontMetrics fm = g.getFontMetrics(fontGameOver);

        String textoGameOver = "GAME OVER";
        int xTexto = (anchoPanel - fm.stringWidth(textoGameOver)) / 2;
        int yTexto = 150;

        g.setColor(Color.DARK_GRAY);
        g.drawString(textoGameOver, xTexto + 5, yTexto + 5);
        g.setColor(Color.RED);
        g.drawString(textoGameOver, xTexto, yTexto);

        // Texto "¿Continuar?" parpadeante
        if (mostrarTexto) {
            Font fontContinuar = new Font("Arial", Font.BOLD, 30);
            g.setFont(fontContinuar);
            FontMetrics fm2 = g.getFontMetrics(fontContinuar);

            String textoContinuar = "¿Continuar?";
            int xTexto2 = (anchoPanel - fm2.stringWidth(textoContinuar)) / 2;
            int yTexto2 = 220;

            g.setColor(Color.WHITE);
            g.drawString(textoContinuar, xTexto2, yTexto2);
        }
    }
}
