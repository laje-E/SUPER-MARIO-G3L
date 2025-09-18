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
    private Image[] edificios;
    private Font marioFont;

    public int desplazamiento = 0;
    private boolean nivelSuperado = false;
    
    public FondoPanel() {
        fondo = new ImageIcon(getClass().getResource("/img/fondoPrueba2.png")).getImage();

        edificios = new Image[3];
        edificios[0] = new ImageIcon(getClass().getResource("/img/edif1.png")).getImage();
        edificios[1] = new ImageIcon(getClass().getResource("/img/edif2.png")).getImage();
        edificios[2] = new ImageIcon(getClass().getResource("/img/edif3.png")).getImage();
        
        obelisco = new ImageIcon(getClass().getResource("/img/obeliscoSolo.png")).getImage();
        
        try { // Es una promesa, que si no se cumple, o sea no carga la fuente, que use de manera forzada la default que es ARIAL.
            marioFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fuentes/SuperMario64.ttf"));
        }
        catch (FontFormatException | IOException e) { // Tira el error, y usa por default ARIAL.
        	System.err.println("Error al cargar la fuente: " + e.getMessage());
            marioFont = new Font("Arial", Font.BOLD, 48);
        }
    }
        
    
        
        

    
    
    public void setearNivelSuperado(boolean valor) {
        this.nivelSuperado = valor;
        repaint();
    }
    
    protected void paintComponent_mensaje(Graphics g) {
        if (nivelSuperado) { // verificamos la variable del fondo
            g.setFont(marioFont);
            g.setColor(Color.YELLOW);
            g.drawString("Nivel Superado!", getWidth() / 2 - 200, getHeight() / 2);
        }
    
        //    mensajePanel.setOpaque(false); // transparente
        //    mensajePanel.setBounds(0, 0, 800, 600);
        //    this.add(mensajePanel);

    
}
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int x = -desplazamiento / 4; x < getWidth(); x += fondo.getWidth(null)) {
            g.drawImage(fondo, x, 0, null);
        }

        // Edificios: se mueven un poco más rápido que el fondo
        g.drawImage(edificios[0], 370 - desplazamiento / 2, 236, 122, 200, this);
        g.drawImage(edificios[1], 500 - desplazamiento / 2, 206, 117, 230, this);
        g.drawImage(edificios[2], 640 - desplazamiento / 2, 221, 125, 215, this);
        
        g.drawImage(obelisco, 4155 - desplazamiento, 234, 50, 202, this); // obelisco dibujado, se mueve en primer plano como si fuera un obstáculo solo que sin las colisiones.
        
        
        paintComponent_mensaje(g);
    }    
    
}
