package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Bala extends JPanel {
    private static final long serialVersionUID = 1L;

    private int dx; // velocidad en X
    private Timer movimientoBala;
    private boolean destruida = false;

    public Bala(int x, int y, int dx) {
        setBounds(x, y, 10, 10); // tamaño de la bala
        this.dx = dx;
        setOpaque(false);

        movimientoBala = new Timer(20, e -> mover()); // timer que mueve la bala
        movimientoBala.start();
    }

    private void mover() {
        if (destruida) return; // ya destruida, no mover más

        setLocation(getX() + dx, getY());

        // si sale de la pantalla se elimina
        if (getX() < 0 || getX() > 800) {
            destruir();
            return;
        }
    }

    public void destruir() {
        if (destruida) return; // evita doble ejecución
        destruida = true;

        if (movimientoBala != null) {
            movimientoBala.stop(); // detener primero el timer
        }

        // ¡Clave!: cachear el parent antes de remover
        Container p = getParent();
        if (p != null) {
            p.remove(this);   // después de esto getParent() ya puede ser null
            p.repaint();      // por eso usamos la variable 'p'
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}
