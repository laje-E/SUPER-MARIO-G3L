package GUI;

import java.awt.Container;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Bala extends JPanel {
    private static final long serialVersionUID = 1L;

    private int dx; 
    private Timer movimientoBala;
    private boolean destruida = false;
    private ImageIcon icon;

    // Constructor sin imagen
    public Bala(int x, int y, int dx) {
        this(x, y, dx, null);
        System.out.println("Hola soy una bala sin imagen");
    }

    public Bala(int x, int y, int dx, ImageIcon icon) { 
        this.dx = dx;
        this.icon = icon;
        setOpaque(false);

        int w;
        int h;

        if (icon != null) {
            w = 30;  // tamaño mayor si tiene imagen (pelota, yerba)
            h = 30;
        } else {
            w = 20;  // tamaño por defecto (bala "normal")
            h = 20;
        }

        setBounds(x, y, w, h);

        movimientoBala = new Timer(15, e -> mover());
        movimientoBala.start();
    }

    private void mover() {
        if (destruida) return;
        setLocation(getX() + dx, getY());

        Container p = getParent();
        if (p != null) {
            int ancho = p.getWidth();
            if (getX() < 0 || getX() > ancho) {
                destruir();
            }
        }
    }

    public void destruir() {
        if (destruida) return;
        destruida = true;

        if (movimientoBala != null) {
            movimientoBala.stop();
        }

        Container p = getParent();
        if (p != null) {
            p.remove(this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (icon != null) {
            g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(java.awt.Color.CYAN);
            g.fillOval(0, 0, getWidth(), getHeight());
        }
    }
}
