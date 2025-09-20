package GUI;

import javax.swing.ImageIcon;
import java.awt.Color;

public class Nivel1 extends NivelBase {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void construirNivel() {
        // Agregar enemigo
        ImageIcon enemigoIcon = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/carboncini.png"));
        Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801, enemigoIcon); //carboncini --> mirar tamaños!!
        
//        Enemigo enemigo = new Enemigo(500, 386, 37, 50, 500, 801, enemigoIcon); // Matecinini --> mirar tamaños!!
////    Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
////    Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
////    Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
////    Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
////    Enemigo enemigo = new Enemigo (500, 391, 48, 45, 500, 801);
        
        contentPane.add(enemigo);
        enemigos.add(enemigo);
        enemigo.patrullar();

        // Agregar tiles
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

        // Obstáculos del nivel
        int[][] nivel1 = {
            {170, 350, 20, 20},
            {250, 370, 80, 10},
            {500, 390, 10, 50},
            {795, 390, 10, 50}
        };

        for (int[] bloque : nivel1) {
            Obstaculo o = new Obstaculo(obstaculos);
            o.setBackground(Color.GREEN);
            o.setBounds(bloque[0], bloque[1], bloque[2], bloque[3]);
            contentPane.add(o);
            obstaculos.add(o);
        }
    }
}
