package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FondoPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Image fondo;
    private Image obelisco;
    private Image mirthaCasa;
    private Image[] edificios;
    public int desplazamiento = 0;
    private boolean nivelSuperado = false;
    private int nivel;
    private Font marioFont;
    
    public FondoPanel(int nivel) {
    	 this.nivel = nivel;
    	
    	 switch (nivel) {
         case 1:
             fondo = new ImageIcon(getClass().getResource("/img/fondo/fondoLejanoPrimerNivel.png")).getImage();
             break;
         case 2:
             fondo = new ImageIcon(getClass().getResource("/img/fondo/fondoLejanoSegundoNivel.png")).getImage();
             break;
         case 3:
             fondo = new ImageIcon(getClass().getResource("/img/fondo/fondoLejanoTercerNivel.png")).getImage();
             break;
         default:
             fondo = new ImageIcon(getClass().getResource("/img/fondo/fondoLejanoPrimerNivel.png")).getImage();
             break;
     }
        
        mirthaCasa = new ImageIcon(getClass().getResource("/img/edificios/mirthaCasa.png")).getImage();
        
        edificios = new Image[3];
        edificios[0] = new ImageIcon(getClass().getResource("/img/edificios/edif1.png")).getImage();
        edificios[1] = new ImageIcon(getClass().getResource("/img/edificios/edif2.png")).getImage();
        edificios[2] = new ImageIcon(getClass().getResource("/img/edificios/edif3.png")).getImage();
        
        
        obelisco = new ImageIcon(getClass().getResource("/img/edificios/obelisco.png")).getImage();
        
        try {
        	 marioFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fuentes/SuperMario64.ttf")).deriveFont(48f);
        }
        catch (FontFormatException | IOException e) {
            marioFont = new Font("Arial", Font.BOLD, 48);
        }
        
        
    }
    
    public void setearNivelSuperado(boolean estado) {
        this.nivelSuperado = estado;
        repaint();
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int x = -desplazamiento / 4; x < getWidth(); x += fondo.getWidth(null)) {
            g.drawImage(fondo, x, 0, null);
        }


        int espacio= 200;      // se dibujan edificios cada 200px, usando siempre sus tamaños originales

        for (int x = 64; x < 3200; x += espacio) {
            Image edificio = edificios[(x / espacio) % edificios.length]; // se asegura que sea 0, 1, o 2.

            int width = edificio.getWidth(this);
            int height = edificio.getHeight(this);

            if (width <= 0) width = 120;   // fallback si no cargó bien
            if (height <= 0) height = 200;

            int baseY = getHeight() - height - 164; // se apoyan sobre el suelo, con un poco de margen

            g.drawImage(edificio, x - desplazamiento / 2, baseY, width, height, this);
        }

        
        
     // Casa de Mirtha Legrand:
        
        if (nivel == 1) {
            g.drawImage(mirthaCasa, 150 - desplazamiento, 266, 200, 170, this);
        }
        
        g.drawImage(obelisco, 3000 - desplazamiento, 234, 50, 202, this); // obelisco dibujado, se mueve en primer plano como si fuera un obstáculo solo que sin las colisiones.
        
        if (nivelSuperado) {
        	if(nivel == 1) {
        		g.setFont(marioFont);
                g.setColor(Color.RED);
                g.drawString("Nivel Superado !", 200, 200);
        	}
        	else if(nivel == 2) {
        		g.setFont(marioFont);
        		g.setColor(Color.WHITE);
                g.drawString("Nivel Superado !", 200, 200);
        	}
            
        }
        
        
    }    
    
}
