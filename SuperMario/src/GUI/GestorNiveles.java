package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GestorNiveles {
	private static int nivelActual = 1;

	public static void cargarNivel(JFrame ventana) {
	    if (ventana instanceof NivelBase) {
	        ((NivelBase) ventana).cerrarNivel();
	    } else {
	        ventana.dispose();
	    }

	    SwingUtilities.invokeLater(() -> {
	        JFrame nuevoNivel;
	        switch (nivelActual) {
	            case 1:
	                nuevoNivel = new Nivel1(); break;
	            case 2:
	                nuevoNivel = new Nivel2(); break;
	            case 3:
	                nuevoNivel = new Nivel3(); break;
	            default:
	                nuevoNivel = new Nivel1(); break;
	        }
	        nuevoNivel.setVisible(true);
	    });
	}


    public static void avanzarNivel(JFrame ventana) {
        nivelActual++;
        cargarNivel(ventana);
    }
    
}
