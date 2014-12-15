/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Mapa;

import java.awt.Graphics;

/**
 *
 * @author Chalkos
 */
public class Arco extends Figura{
    protected double x1 = 0;
    protected double x2 = 0;
    protected double y1 = 0;
    protected double y2 = 0;
    
    public Arco(){
        super();
        this.color = Mapa.normalLinhas;
    }
    
    public Arco(double x1, double y1, double x2, double y2) {
        this();
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    
    @Override
    public void desenhar(Graphics g) {
        g.setColor(color);
        
        g.drawLine(getX(x1), getY(y1), getX(x2), getY(y2));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        
        final Arco other = (Arco) obj;
        
        // iguais
        if( this.x1 == other.x1 &&
            this.x2 == other.x2 &&
            this.y1 == other.y1 &&
            this.y2 == other.y2)
            return true;
        
        // sentidos opostos
        if( this.x1 == other.x2 &&
            this.x2 == other.x1 &&
            this.y1 == other.y2 &&
            this.y2 == other.y1)
            return true;
        
        return false;
    }
    
    
}
