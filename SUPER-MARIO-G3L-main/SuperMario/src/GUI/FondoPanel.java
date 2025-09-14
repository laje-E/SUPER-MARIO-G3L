package GUI;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FondoPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Image fondo;
    private Image[] edificios;

    public int desplazamiento = 0;

    public FondoPanel() {
        fondo = new ImageIcon(getClass().getResource("/img/fondoPrueba2.png")).getImage();

        edificios = new Image[3];
        edificios[0] = new ImageIcon(getClass().getResource("/img/edif1.png")).getImage();
        edificios[1] = new ImageIcon(getClass().getResource("/img/edif2.png")).getImage();
        edificios[2] = new ImageIcon(getClass().getResource("/img/edif3.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = -desplazamiento / 4; x < getWidth(); x += fondo.getWidth(null)) {
            g.drawImage(fondo, x, 0, null);
        }

        // Edificios: se mueven un poco más rápido que el fondo
        g.drawImage(edificios[0], 370 - desplazamiento / 2, 236, 122, 200, this);
        g.drawImage(edificios[1], 500 - desplazamiento / 2, 206, 117, 230, this);
        g.drawImage(edificios[2], 640 - desplazamiento / 2, 221, 125, 215, this);
    }
}
