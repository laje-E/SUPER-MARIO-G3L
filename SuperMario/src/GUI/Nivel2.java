package GUI;

import java.awt.Color;
import javax.swing.ImageIcon;

public class Nivel2 extends NivelBase {

    @Override
    protected void construirNivel() {
        // Agregar enemigo
        Enemigo enemigo = new Enemigo(500, 410, 30, 30, 510, 785);
        enemigo.setBackground(Color.RED);
        contentPane.add(enemigo);
        enemigos.add(enemigo);

        // Agregar tiles
        ImageIcon pastoIcon = new ImageIcon(getClass().getResource("/img/pisos/pastoFixed.png"));
        ImageIcon tierraIcon = new ImageIcon(getClass().getResource("/img/pisos/tierraFixed.png"));

        int sueloY = 500;
        int alturaVentana = 600;
        int tileSize = 64;
        int tilesX = anchoMapa / tileSize;
        int tilesAbajo = (alturaVentana - sueloY) / tileSize;

        // Capa de pasto
        for (int i = 0; i < tilesX; i++) {
            Obstaculo pasto = new Obstaculo(obstaculos, pastoIcon, false);
            pasto.setBounds(i * tileSize, sueloY - tileSize, tileSize, tileSize);
            contentPane.add(pasto);
            obstaculos.add(pasto);
        }

        // Capa de tierra
        for (int y = 0; y < tilesAbajo; y++) {
            for (int i = 0; i < tilesX; i++) {
                Obstaculo tierra = new Obstaculo(obstaculos, tierraIcon, false);
                tierra.setBounds(i * tileSize, sueloY + y * tileSize, tileSize, tileSize);
                contentPane.add(tierra);
                obstaculos.add(tierra);
            }
        }

        // Obstáculos especiales
        int[][] nivel2 = {
            {50, 400, 80, 10},  
            {170, 350, 20, 20}, 
            {250, 370, 80, 10}, 
            {500, 390, 10, 50}, 
            {795, 390, 10, 50}
        };

        int contador = 1;
        for (int[] bloque : nivel2) {
            Obstaculo o = new Obstaculo(obstaculos);
            o.setBackground(Color.PINK);
            o.setBounds(bloque[0], bloque[1], bloque[2], bloque[3]);
            contentPane.add(o);
            obstaculos.add(o);

            if (contador == 2) { // obstáculo especial
                o.traspasable = false;
                o.setBackground(Color.BLACK);
            }
            contador++;
        }
    }
}
