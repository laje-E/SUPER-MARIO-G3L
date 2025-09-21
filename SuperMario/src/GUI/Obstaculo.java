package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Obstaculo extends JPanel {

    private static final long serialVersionUID = 1L;

    public boolean traspasable = false;
    private Image image;

    public Obstaculo(ArrayList<Obstaculo> obstaculos, ImageIcon icon, boolean traspasable) {
        setLayout(null);
        setOpaque(false);
        this.traspasable = traspasable;

        if (icon != null) {
            this.image = icon.getImage();
        } else {
            this.image = null;
        }
    }

    public Obstaculo(ArrayList<Obstaculo> obstaculos) {
        this(obstaculos, null, false);
    }

    public void setIcon(ImageIcon icon) {
        if (icon != null) {
            this.image = icon.getImage();
        } else {
            this.image = null;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
