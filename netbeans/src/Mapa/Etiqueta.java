/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Mapa;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Chalkos
 */
public class Etiqueta extends Figura{
    private static final Color borderColor = new Color(0x000000);
    private static final Color bgColor = new Color(255,255,225);
    
    private String texto = "label de teste";
    
    private int x = 0;
    private int y = 0;
    private int width = 50;
    private int heigth = 20;
    private boolean active = false;
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void setText(String text){
        this.texto = text;
    }
    
    public void activate(){
        this.active = true;
    }
    
    public void deactivate(){
        this.active = false;
    }
    
    @Override
    public void desenhar(Graphics g) {
        if(!active) return;
        
        g.setColor(bgColor);
        g.fillRect(x+1,y+1-heigth, width, heigth);
        g.setColor(borderColor);
        g.drawRect(x,y-heigth, width, heigth);
        g.drawString(texto, x+4, y-4);
    }
}
