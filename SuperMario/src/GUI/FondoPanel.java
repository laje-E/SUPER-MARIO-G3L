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
    private Font marioFont;
    
    public FondoPanel() {
        fondo = new ImageIcon(getClass().getResource("/img/fondo/fondoLejanoPrimerNivel.png")).getImage();
        
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

        
        // Edificios: se mueven un poco más rápido que el fondo
        g.drawImage(edificios[0], 270 - desplazamiento / 2, 236, 122, 200, this);
        g.drawImage(edificios[1], 700 - desplazamiento / 2, 206, 117, 230, this);
        g.drawImage(edificios[2], 840 - desplazamiento / 2, 221, 125, 215, this);
        
     // Casa de Mirtha Legrand:
        g.drawImage(mirthaCasa, 150 - desplazamiento, 266, 200, 170, this);
        
        g.drawImage(obelisco, 4155 - desplazamiento, 234, 50, 202, this); // obelisco dibujado, se mueve en primer plano como si fuera un obstáculo solo que sin las colisiones.
        
        if (nivelSuperado) {
            g.setFont(marioFont);
            g.setColor(Color.RED);
            g.drawString("Nivel Superado !", 200, 200);
        }
        
        
    }    
    
}
