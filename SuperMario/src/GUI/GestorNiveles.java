package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GestorNiveles {
    private static int nivelActual = 1;
    private static ControladorJuego controlador;

    public static void setControlador(ControladorJuego c) {
        controlador = c;
    }

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
                    nuevoNivel = new SuperMario(); break;
                case 2:
                    nuevoNivel = new Nivel2(controlador); break;
                default:
                    nuevoNivel = new Nivel2(controlador); break;
            }
            nuevoNivel.setVisible(true);
        });
    }

    public static void avanzarNivel(JFrame ventana) {
        nivelActual++;
        cargarNivel(ventana);
    }
}
