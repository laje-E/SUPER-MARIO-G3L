package GUI;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Obstaculo extends JPanel {
    private ArrayList<Obstaculo> obstaculos;
    private JLabel imagenLabel;

    public Obstaculo(ArrayList<Obstaculo> obstaculos, ImageIcon icon) {
        this.obstaculos = obstaculos;
        this.setLayout(null);
        if (icon != null) {
            imagenLabel = new JLabel(icon);
            imagenLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
            this.add(imagenLabel);
        }
    }

    public Obstaculo(ArrayList<Obstaculo> obstaculos) {
        this(obstaculos, null); // constructor sin imagen
    }
}
