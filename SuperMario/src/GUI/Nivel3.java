package GUI;

import javax.swing.ImageIcon;

public class Nivel3 extends NivelBase {

    private static final long serialVersionUID = 1L;

    @Override
    protected void construirNivel() {
        // Enemigos
        ImageIcon mateIcon = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/matecinini.png"));
        Enemigo matecinini = new Enemigo(500, 386, 37, 50, 500, 801, mateIcon, true);
        contentPane.add(matecinini);
        enemigos.add(matecinini);
        matecinini.empezarADisparar(player);

        ImageIcon carbIcon = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/carboncini.png"));
        Enemigo carboncini = new Enemigo(1000, 391, 48, 45, 950, 1300, carbIcon, false);
        contentPane.add(carboncini);
        enemigos.add(carboncini);
        carboncini.patrullar();

        // Tiles del piso
        ImageIcon metalIcon = new ImageIcon(getClass().getResource("/img/pisos/metal.png"));
        ImageIcon ladrilloPiedraIcon = new ImageIcon(getClass().getResource("/img/pisos/ladrilloPiedra.png"));

        int sueloY = 500;
        int alturaVentana = 600;
        int tileSize = 64;
        int tilesX = anchoMapa / tileSize;
        int tilesAbajo = (alturaVentana - sueloY) / tileSize;

        for (int i = 0; i < tilesX; i++) {
            Obstaculo sueloMetal = new Obstaculo(obstaculos, metalIcon, false);
            sueloMetal.setBounds(i * tileSize, sueloY - tileSize, tileSize, tileSize);
            contentPane.add(sueloMetal);
            obstaculos.add(sueloMetal);
        }

        for (int y = 0; y < tilesAbajo; y++) {
            for (int i = 0; i < tilesX; i++) {
                Obstaculo ladrilloPiedra = new Obstaculo(obstaculos, ladrilloPiedraIcon, false);
                ladrilloPiedra.setBounds(i * tileSize, sueloY + y * tileSize, tileSize, tileSize);
                contentPane.add(ladrilloPiedra);
                obstaculos.add(ladrilloPiedra);
            }
        }

        // Plataformas extra
        ImageIcon ladrilloIcon = new ImageIcon(getClass().getResource("/img/pisos/ladrillo.png"));

        Obstaculo p1 = new Obstaculo(obstaculos, metalIcon, true);
        p1.setBounds(200, 400, 100, 16);
        contentPane.add(p1);
        obstaculos.add(p1);

        Obstaculo p2 = new Obstaculo(obstaculos, ladrilloIcon, false);
        p2.setBounds(450, 350, 32, 32);
        contentPane.add(p2);
        obstaculos.add(p2);

        Obstaculo p3 = new Obstaculo(obstaculos, metalIcon, true);
        p3.setBounds(700, 300, 120, 16);
        contentPane.add(p3);
        obstaculos.add(p3);

        Obstaculo p4 = new Obstaculo(obstaculos, ladrilloIcon, false);
        p4.setBounds(950, 250, 32, 32);
        contentPane.add(p4);
        obstaculos.add(p4);

        Obstaculo p5 = new Obstaculo(obstaculos, metalIcon, true);
        p5.setBounds(1250, 350, 120, 16);
        contentPane.add(p5);
        obstaculos.add(p5);
    }

    @Override
    protected int getNumeroNivel() {
        return 3;
    }
}
