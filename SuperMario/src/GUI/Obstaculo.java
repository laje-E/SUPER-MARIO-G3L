package GUI;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Obstaculo extends JPanel {
    
	private static final long serialVersionUID = 1L;
	
    private JLabel imagenLabel;

    public Obstaculo(ArrayList<Obstaculo> obstaculos, ImageIcon icon) {
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
