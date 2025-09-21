package GUI;

import javax.swing.ImageIcon;

public class Nivel3 extends NivelBase {

    private static final long serialVersionUID = 1L;

    @Override
    protected void construirNivel() {

       
        agregarPiso();

       
        agregarPlataformasHorizontales();

      
        agregarObstaculosVerticales();
    }

    private void agregarPiso() {
        ImageIcon metalIcon = new ImageIcon(getClass().getResource("/img/pisos/metal.png"));
        ImageIcon ladrilloPiedraIcon = new ImageIcon(getClass().getResource("/img/pisos/ladrilloPiedra.png"));

        int sueloY = 500;
        int alturaVentana = 600;
        int tileSize = 64;
        int tilesX = anchoMapa / tileSize;
        int tilesAbajo = (alturaVentana - sueloY) / tileSize;

        // Piso superior
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
    }

    private void agregarPlataformasHorizontales() {
        ImageIcon ladrilloIcon = new ImageIcon(getClass().getResource("/img/pisos/ladrillo.png"));
        ImageIcon metalIcon = new ImageIcon(getClass().getResource("/img/pisos/metal.png"));

 
        int[][] plataformas = {
            {180, 380, 120, 16},   // plataforma baja
            {400, 320, 100, 16},
            {650, 280, 120, 16},
            {800, 350, 100, 16},
            {1200, 300, 140, 16},
            {1500, 400, 120, 16},
            {1750, 360, 100, 16},
            {2000, 310, 140, 16},
            {2250, 380, 120, 16},
            {2500, 340, 100, 16}
        };

        for (int[] p : plataformas) {
            Obstaculo plat;
            if (p[3] <= 32) {
                plat = new Obstaculo(obstaculos, ladrilloIcon, false); //ladrillo
            } else {
                plat = new Obstaculo(obstaculos, metalIcon, true); //  metal
            }
            plat.setBounds(p[0], p[1], p[2], p[3]);
            contentPane.add(plat);
            obstaculos.add(plat);
        }
    }

    private void agregarObstaculosVerticales() {
        ImageIcon ladrilloIcon = new ImageIcon(getClass().getResource("/img/pisos/ladrillo.png"));
        
        int[][] columnas = {
            {600, 380, 32, 100}, {1000, 370, 32, 120}, {1400, 390, 32, 90},
            {1800, 360, 32, 130}, {2200, 380, 32, 110}, {2600, 400, 32, 100}
        };

        for (int[] c : columnas) {
            Obstaculo col = new Obstaculo(obstaculos, ladrilloIcon, false);
            col.setBounds(c[0], c[1], c[2], c[3]);
            contentPane.add(col);
            obstaculos.add(col);
        }
    }

    @Override
    protected int getNumeroNivel() {
        return 3;
    }
}
