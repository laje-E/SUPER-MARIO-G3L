package GUI;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Obstaculo extends JPanel {
    
	private static final long serialVersionUID = 1L;
	
    private JLabel imagenLabel;
    
    public boolean traspasable = true;

    public Obstaculo(ArrayList<Obstaculo> obstaculos, ImageIcon icon, boolean traspasable) {
        this.setLayout(null);
        if (icon != null) {
            imagenLabel = new JLabel(icon);
            imagenLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
            this.add(imagenLabel);
            traspasable = this.traspasable;
        }
    }

    public Obstaculo(ArrayList<Obstaculo> obstaculos) {
        this(obstaculos, null, true); // constructor sin imagen
  	}


}
