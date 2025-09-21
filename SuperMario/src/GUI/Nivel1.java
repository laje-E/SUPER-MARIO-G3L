//        Enemigo enemigo = new Enemigo(500, 386, 37, 50, 500, 801, enemigoIcon); // Matecinini --> mirar tamaños!!
////    Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
////    Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
////    Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
////    Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
////    Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
package GUI;

import javax.swing.ImageIcon;

public class Nivel1 extends NivelBase {

    private static final long serialVersionUID = 1L;

    @Override
    protected void construirNivel() {
        ImageIcon carbIcon = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/carboncini.png"));
        Enemigo carboncini = new Enemigo(500, 391, 48, 45, 400, 700, carbIcon, false, 3, this);
        contentPane.add(carboncini);
        enemigos.add(carboncini);
        carboncini.patrullar();
        
       
        ImageIcon carbIcon2 = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/carboncini.png"));
        Enemigo carboncini2 = new Enemigo(900, 391, 48, 45, 700, 1000, carbIcon, false, 10, this);
        contentPane.add(carboncini2);
        enemigos.add(carboncini2);
        carboncini2.movimientoJefe(player);
        
        ImageIcon mateIcon = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/matecinini.png"));
        Enemigo matecinini = new Enemigo(4000, 386, 37, 50, 500, 801, mateIcon, true, 1, this);
        contentPane.add(matecinini);
        enemigos.add(matecinini);
        matecinini.empezarADisparar(player);
        matecinini.movimientoVerical(5);
        

        ImageIcon pastoIcon = new ImageIcon(getClass().getResource("/img/pisos/pastoFixed.png"));
        ImageIcon tierraIcon = new ImageIcon(getClass().getResource("/img/pisos/tierraFixed.png"));

        int sueloY = 500;
        int alturaVentana = 600;
        int tileSize = 64;
        int tilesX = anchoMapa / tileSize;
        int tilesAbajo = (alturaVentana - sueloY) / tileSize;

        for (int i = 0; i < tilesX; i++) {
            Obstaculo pasto = new Obstaculo(obstaculos, pastoIcon, false);
            pasto.setBounds(i * tileSize, sueloY - tileSize, tileSize, tileSize);
            contentPane.add(pasto);
            obstaculos.add(pasto);
        }

        for (int y = 0; y < tilesAbajo; y++) {
            for (int i = 0; i < tilesX; i++) {
                Obstaculo tierra = new Obstaculo(obstaculos, tierraIcon, false);
                tierra.setBounds(i * tileSize, sueloY + y * tileSize, tileSize, tileSize);
                contentPane.add(tierra);
                obstaculos.add(tierra);
            }
        }

        ImageIcon metalIcon = new ImageIcon(getClass().getResource("/img/pisos/metal.png"));
        ImageIcon ladrilloIcon = new ImageIcon(getClass().getResource("/img/pisos/ladrillo.png"));

        Obstaculo plataformaMetal1 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal1.setBounds(200, 350, 128, 16);
        contentPane.add(plataformaMetal1);
        obstaculos.add(plataformaMetal1);

        Obstaculo plataformaMetal2 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal2.setBounds(600, 310, 128, 16);
        contentPane.add(plataformaMetal2);
        obstaculos.add(plataformaMetal2);
        
        Obstaculo plataformaMetal3 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal3.setBounds(4000, 200, 128, 16);
        contentPane.add(plataformaMetal3);
        obstaculos.add(plataformaMetal3);
        
        Obstaculo plataformaMetal4 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal4.setBounds(4000, 280, 128, 16);
        contentPane.add(plataformaMetal4);
        obstaculos.add(plataformaMetal4);
        
        Obstaculo plataformaMetal5 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal5.setBounds(4000, 360, 128, 16);
        contentPane.add(plataformaMetal5);
        obstaculos.add(plataformaMetal5);

        Obstaculo bloqueLadrillo1 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloqueLadrillo1.setBounds(400, 250, 48, 48);
        contentPane.add(bloqueLadrillo1);
        obstaculos.add(bloqueLadrillo1);

        Obstaculo bloqueLadrillo2 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloqueLadrillo2.setBounds(448, 250, 48, 48);
        contentPane.add(bloqueLadrillo2);
        obstaculos.add(bloqueLadrillo2);
    }

    @Override
    protected int getNumeroNivel() {
        return 1;
    }
}
