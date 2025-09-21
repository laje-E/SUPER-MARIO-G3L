package GUI;

import javax.swing.ImageIcon;

public class Nivel2 extends NivelBase {

    private static final long serialVersionUID = 1L;

    @Override
    protected void construirNivel() {
        ImageIcon mateIcon = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/matecinini.png"));
        Enemigo matecinini = new Enemigo(500, 386, 37, 50, 500, 801, mateIcon, true);
        contentPane.add(matecinini);
        enemigos.add(matecinini);
        matecinini.empezarADisparar(player);

        ImageIcon carbIcon = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/carboncini.png"));
        Enemigo carboncini = new Enemigo(300, 391, 48, 45, 250, 600, carbIcon, false);
        contentPane.add(carboncini);
        enemigos.add(carboncini);
        carboncini.patrullar();

        ImageIcon troncoIcon = new ImageIcon(getClass().getResource("/img/pisos/tronco.png"));
        ImageIcon ladrilloPiedraIcon = new ImageIcon(getClass().getResource("/img/pisos/ladrilloPiedra.png"));

        int sueloY = 500;
        int alturaVentana = 600;
        int tileSize = 64;
        int tilesX = anchoMapa / tileSize;
        int tilesAbajo = (alturaVentana - sueloY) / tileSize;

        for (int i = 0; i < tilesX; i++) {
            Obstaculo tronco = new Obstaculo(obstaculos, troncoIcon, false);
            tronco.setBounds(i * tileSize, sueloY - tileSize, tileSize, tileSize);
            contentPane.add(tronco);
            obstaculos.add(tronco);
        }

        for (int y = 0; y < tilesAbajo; y++) {
            for (int i = 0; i < tilesX; i++) {
                Obstaculo ladrilloPiedra = new Obstaculo(obstaculos, ladrilloPiedraIcon, false);
                ladrilloPiedra.setBounds(i * tileSize, sueloY + y * tileSize, tileSize, tileSize);
                contentPane.add(ladrilloPiedra);
                obstaculos.add(ladrilloPiedra);
            }
        }

        ImageIcon metalIcon = new ImageIcon(getClass().getResource("/img/pisos/metal.png"));
        ImageIcon ladrilloIcon = new ImageIcon(getClass().getResource("/img/pisos/ladrillo.png"));

        Obstaculo plataformaMetal1 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal1.setBounds(200, 400, 128, 16);
        contentPane.add(plataformaMetal1);
        obstaculos.add(plataformaMetal1);

        Obstaculo plataformaMetal2 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal2.setBounds(500, 300, 128, 16);
        contentPane.add(plataformaMetal2);
        obstaculos.add(plataformaMetal2);

        Obstaculo plataformaMetal3 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal3.setBounds(800, 250, 128, 16);
        contentPane.add(plataformaMetal3);
        obstaculos.add(plataformaMetal3);

        Obstaculo bloqueLadrillo1 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloqueLadrillo1.setBounds(400, 200, 48, 48);
        contentPane.add(bloqueLadrillo1);
        obstaculos.add(bloqueLadrillo1);

        Obstaculo bloqueLadrillo2 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloqueLadrillo2.setBounds(448, 200, 48, 48);
        contentPane.add(bloqueLadrillo2);
        obstaculos.add(bloqueLadrillo2);

        Obstaculo bloqueLadrillo3 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloqueLadrillo3.setBounds(600, 150, 48, 48);
        contentPane.add(bloqueLadrillo3);
        obstaculos.add(bloqueLadrillo3);
    }

    @Override
    protected int getNumeroNivel() {
        return 2;
    }
}
