package GUI;

import javax.swing.ImageIcon;

public class Nivel1 extends NivelBase {

    private static final long serialVersionUID = 1L;

    @Override
    protected void construirNivel() {
        Sonido.reproducirMusicaLoop("sonidos/temasInGame/tema1/tema1.wav");

       
        ImageIcon carbIcon = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/carboncini.png"));
        Enemigo carboncini1 = new Enemigo(500, 391, 48, 45, 400, 700, carbIcon, false, 3, this, false);
        carboncini1.tipo = "carbon";
        contentPane.add(carboncini1);
        enemigos.add(carboncini1);
        carboncini1.patrullar();

        Enemigo carboncini2 = new Enemigo(1200, 391, 48, 45, 1100, 1400, carbIcon, false, 3, this, false);
        carboncini2.tipo = "carbon";
        contentPane.add(carboncini2);
        enemigos.add(carboncini2);
        carboncini2.patrullar();

        Enemigo carboncini3 = new Enemigo(1800, 391, 48, 45, 1700, 2000, carbIcon, false, 3, this, false);
        carboncini3.tipo = "carbon";
        contentPane.add(carboncini3);
        enemigos.add(carboncini3);
        carboncini3.patrullar();
        
        Enemigo carboncini4 = new Enemigo(2000, 391, 48, 45, 1700, 3000, carbIcon, false, 3, this, false);
        carboncini4.tipo = "carbon";
        contentPane.add(carboncini4);
        enemigos.add(carboncini4);
        carboncini4.patrullar();

        ImageIcon parrilleroIcon = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/parrilleroInfernal.png"));
        Enemigo parrillero = new Enemigo(2800, 386, 102, 153, 2500, 3000, parrilleroIcon, true, 1, this, false);
        contentPane.add(parrillero);
        enemigos.add(parrillero);
        parrillero.empezarADisparar(player);
        parrillero.movimientoVerical(5);

       
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

        // plataformas
        ImageIcon metalIcon = new ImageIcon(getClass().getResource("/img/pisos/plataformaMetal2.png"));
        ImageIcon ladrilloIcon = new ImageIcon(getClass().getResource("/img/pisos/ladrillo.png"));

        // 3 plataformas iniciales
        Obstaculo plataformaMetal1 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal1.setBounds(500, 350, 80, 10);
        contentPane.add(plataformaMetal1);
        obstaculos.add(plataformaMetal1);

        Obstaculo plataformaMetal2 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal2.setBounds(700, 300, 80, 10);
        contentPane.add(plataformaMetal2);
        obstaculos.add(plataformaMetal2);

        Obstaculo plataformaMetal3 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal3.setBounds(900, 250, 80, 10);
        contentPane.add(plataformaMetal3);
        obstaculos.add(plataformaMetal3);

        // 3 plataformas en el medio del nivel
        Obstaculo plataformaMetal4 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal4.setBounds(1400, 350, 80, 10);
        contentPane.add(plataformaMetal4);
        obstaculos.add(plataformaMetal4);

        Obstaculo plataformaMetal5 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal5.setBounds(1600, 300, 80, 10);
        contentPane.add(plataformaMetal5);
        obstaculos.add(plataformaMetal5);

        Obstaculo plataformaMetal6 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal6.setBounds(1800, 250, 80, 10);
        contentPane.add(plataformaMetal6);
        obstaculos.add(plataformaMetal6);

        // 3 plataformas para poder derrotar al jefe del nivel
        Obstaculo plataformaMetal7 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal7.setBounds(2300, 320, 80, 10);
        contentPane.add(plataformaMetal7);
        obstaculos.add(plataformaMetal7);

        Obstaculo plataformaMetal8 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal8.setBounds(2500, 270, 80, 10);
        contentPane.add(plataformaMetal8);
        obstaculos.add(plataformaMetal8);

        Obstaculo plataformaMetal9 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal9.setBounds(2700, 220, 80, 10);
        contentPane.add(plataformaMetal9);
        obstaculos.add(plataformaMetal9);

      
        Obstaculo bloque1 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque1.setBounds(1100, 280, 48, 48);
        contentPane.add(bloque1);
        obstaculos.add(bloque1);

        Obstaculo bloque2 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque2.setBounds(1148, 280, 48, 48);
        contentPane.add(bloque2);
        obstaculos.add(bloque2);

        Obstaculo bloque3 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque3.setBounds(1900, 200, 48, 48);
        contentPane.add(bloque3);
        obstaculos.add(bloque3);

        Obstaculo bloque4 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque4.setBounds(1948, 200, 48, 48);
        contentPane.add(bloque4);
        obstaculos.add(bloque4);
    }

    @Override
    protected int getNumeroNivel() {
        return 1;
    }
}
