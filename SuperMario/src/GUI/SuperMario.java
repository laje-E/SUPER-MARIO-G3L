package GUI;

import javax.swing.JFrame;

public class SuperMario extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        Nivel1 juego = new Nivel1();
        juego.setVisible(true);
    }
    
}