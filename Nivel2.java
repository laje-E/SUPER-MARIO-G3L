package GUI;

import javax.swing.ImageIcon;

public class Nivel2 extends NivelBase {
	public Nivel2() {
	}

    private static final long serialVersionUID = 1L;

    @Override
    protected void construirNivel() {
        Sonido.reproducirMusicaLoop("sonidos/temasInGame/tema2/tema2.wav");


       
        ImageIcon matecininiIcon = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/matecinini.png"));
        Enemigo matecinini1 = new Enemigo(700, 391, 48, 45, 400, 700, matecininiIcon, false, 3, this, false);
        matecinini1.tipo = "matecinini";
        contentPane.add(matecinini1);
        enemigos.add(matecinini1);
        matecinini1.patrullar();

        Enemigo matecinini2 = new Enemigo(1500, 391, 48, 45, 1100, 1400, matecininiIcon, false, 3, this, false);
        matecinini2.tipo = "matecinini";
        contentPane.add(matecinini2);
        enemigos.add(matecinini2);
        matecinini2.patrullar();

        Enemigo matecinini3 = new Enemigo(1700, 391, 48, 45, 1700, 2000, matecininiIcon, false, 3, this, false);
        matecinini3.tipo = "matecinini";
        contentPane.add(matecinini3);
        enemigos.add(matecinini3);
        matecinini3.patrullar();
        
        Enemigo matecinini4 = new Enemigo(2000, 391, 48, 45, 1700, 3000, matecininiIcon, false, 3, this, false);
        matecinini4.tipo = "carbon";
        contentPane.add(matecinini4);
        enemigos.add(matecinini4);
        matecinini4.patrullar();

        Enemigo matecinini5 = new Enemigo(2288, 160, 48, 45, 1700, 3000, matecininiIcon, false, 3, this, false);
        matecinini5.tipo = "matecinini";
        contentPane.add(matecinini5);
        enemigos.add(matecinini5);
        matecinini5.patrullar();
        
        Enemigo matecinini6 = new Enemigo(2350, 391, 48, 45, 1700, 3000, matecininiIcon, false, 3, this, false);
        matecinini6.tipo = "matecinini";
        contentPane.add(matecinini6);
        enemigos.add(matecinini6);
        matecinini6.patrullar();

        Enemigo matecinini7 = new Enemigo(2650, 391, 48, 45, 1700, 3000, matecininiIcon, false, 3, this, false);
        matecinini7.tipo = "matecinini";
        contentPane.add(matecinini7);
        enemigos.add(matecinini7);
        matecinini7.patrullar();
        
        ImageIcon mateciniIcon = new ImageIcon(getClass().getResource("/img/personajes/enemigos/yaEscalados/matecini.png"));
        Enemigo matecini = new Enemigo(2800, 386, 102, 153, 2500, 3000, mateciniIcon, true, 1, this, false);
        contentPane.add(matecini);
        enemigos.add(matecini);
        matecini.empezarADisparar(player);
        matecini.movimientoVerical(5);

       
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

        // plataformas
        ImageIcon metalIcon = new ImageIcon(getClass().getResource("/img/pisos/metal.png"));
        ImageIcon ladrilloIcon = new ImageIcon(getClass().getResource("/img/pisos/ladrillo.png"));

        
        Obstaculo plataformaMetal1 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal1.setBounds(500, 350, 80, 10);
        contentPane.add(plataformaMetal1);
        obstaculos.add(plataformaMetal1);

        Obstaculo plataformaMetal2 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal2.setBounds(600, 300, 80, 10);
        contentPane.add(plataformaMetal2);
        obstaculos.add(plataformaMetal2);

        Obstaculo plataformaMetal3 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal3.setBounds(700, 350, 80, 10);
        contentPane.add(plataformaMetal3);
        obstaculos.add(plataformaMetal3);

        
        Obstaculo plataformaMetal4 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal4.setBounds(800, 300, 80, 10);
        contentPane.add(plataformaMetal4);
        obstaculos.add(plataformaMetal4);

        Obstaculo plataformaMetal5 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal5.setBounds(900, 250, 80, 10);
        contentPane.add(plataformaMetal5);
        obstaculos.add(plataformaMetal5);


        
        Obstaculo plataformaMetal6 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal6.setBounds(1450, 300, 80, 10);
        contentPane.add(plataformaMetal6);
        obstaculos.add(plataformaMetal6);

        Obstaculo plataformaMetal7 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal7.setBounds(1600, 230, 80, 10);
        contentPane.add(plataformaMetal7);
        obstaculos.add(plataformaMetal7);
        
        Obstaculo plataformaMetal8 = new Obstaculo(obstaculos, metalIcon, true);
        plataformaMetal8.setBounds(1750, 160, 80, 10);
        contentPane.add(plataformaMetal8);
        obstaculos.add(plataformaMetal8);

        

      
        Obstaculo bloque1 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque1.setBounds(1100, 280, 48, 48);
        contentPane.add(bloque1);
        obstaculos.add(bloque1);

        Obstaculo bloque2 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque2.setBounds(1200, 280, 48, 48);
        contentPane.add(bloque2);
        obstaculos.add(bloque2);

        Obstaculo bloque3 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque3.setBounds(1300, 280, 48, 48);
        contentPane.add(bloque3);
        obstaculos.add(bloque3);
        
        Obstaculo bloque4 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque4.setBounds(1948, 200, 48, 48);
        contentPane.add(bloque4);
        obstaculos.add(bloque4);
        
        Obstaculo bloque5 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque5.setBounds(1988, 200, 48, 48);
        contentPane.add(bloque5);
        obstaculos.add(bloque5);
        
        Obstaculo bloque6 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque4.setBounds(2028, 200, 48, 48);
        contentPane.add(bloque6);
        obstaculos.add(bloque6);
        
        Obstaculo bloque7 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque7.setBounds(2048, 200, 48, 48);
        contentPane.add(bloque7);
        obstaculos.add(bloque7);
        
        Obstaculo bloque8 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque8.setBounds(2088, 200, 48, 48);
        contentPane.add(bloque8);
        obstaculos.add(bloque8);
        
        Obstaculo bloque9 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque9.setBounds(2088, 200, 48, 48);
        contentPane.add(bloque9);
        obstaculos.add(bloque9);

        Obstaculo bloque10 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque10.setBounds(2128, 200, 48, 48);
        contentPane.add(bloque10);
        obstaculos.add(bloque10);

        Obstaculo bloque11 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque11.setBounds(2168, 200, 48, 48);
        contentPane.add(bloque11);
        obstaculos.add(bloque11);

        Obstaculo bloque12 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque12.setBounds(2208, 200, 48, 48);
        contentPane.add(bloque12);
        obstaculos.add(bloque12);

        Obstaculo bloque13 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque13.setBounds(2248, 200, 48, 48);
        contentPane.add(bloque13);
        obstaculos.add(bloque13);

        Obstaculo bloque14 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque14.setBounds(2288, 200, 48, 48);
        contentPane.add(bloque14);
        obstaculos.add(bloque14);

        Obstaculo bloque15 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque15.setBounds(2328, 200, 48, 48);
        contentPane.add(bloque15);
        obstaculos.add(bloque15);

        Obstaculo bloque16 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque16.setBounds(2368, 200, 48, 48);
        contentPane.add(bloque16);
        obstaculos.add(bloque16);
        
        Obstaculo bloque17 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque17.setBounds(2408, 200, 48, 48);
        contentPane.add(bloque17);
        obstaculos.add(bloque17);

        Obstaculo bloque18 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque18.setBounds(2448, 200, 48, 48);
        contentPane.add(bloque18);
        obstaculos.add(bloque18);

        Obstaculo bloque19 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque19.setBounds(2488, 200, 48, 48);
        contentPane.add(bloque19);
        obstaculos.add(bloque19);

        Obstaculo bloque20 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque20.setBounds(2528, 200, 48, 48);
        contentPane.add(bloque20);
        obstaculos.add(bloque20);

        Obstaculo bloque21 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque21.setBounds(2568, 200, 48, 48);
        contentPane.add(bloque21);
        obstaculos.add(bloque21);

        Obstaculo bloque22 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque22.setBounds(2608, 200, 48, 48);
        contentPane.add(bloque22);
        obstaculos.add(bloque22);

        Obstaculo bloque23 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque23.setBounds(2648, 200, 48, 48);
        contentPane.add(bloque23);
        obstaculos.add(bloque23);

        Obstaculo bloque24 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque24.setBounds(2688, 200, 48, 48);
        contentPane.add(bloque24);
        obstaculos.add(bloque24);

        Obstaculo bloque25 = new Obstaculo(obstaculos, ladrilloIcon, false);
        bloque25.setBounds(2728, 200, 48, 48);
        contentPane.add(bloque25);
        obstaculos.add(bloque25);

       
    }

    @Override
    protected int getNumeroNivel() {
        return 1;
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Nivel1 nivel1 = new Nivel1();
            nivel1.setVisible(true);
        });
    }
}
