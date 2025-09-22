package GUI;

import javax.sound.sampled.*;

public class Sonido {
    private static Clip musicaFondo;  // loop (nivel)
    private static Clip efecto;       // efectos cortos (pop, game over, disparo, etc.)

    public static void reproducirMusicaLoop(String rutaClasspath) {
        try {
            detenerMusica();
            AudioInputStream in = AudioSystem.getAudioInputStream(
                Sonido.class.getResource("/" + rutaClasspath)
            );
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(in);
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void detenerMusica() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            musicaFondo.stop();
            musicaFondo.close();
            musicaFondo = null;
        }
    }

    public static void reproducirEfecto(String rutaClasspath) {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(
                Sonido.class.getResource("/" + rutaClasspath)
            );
            efecto = AudioSystem.getClip();
            efecto.open(in);
            efecto.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
