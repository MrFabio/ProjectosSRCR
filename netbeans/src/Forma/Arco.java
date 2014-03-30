/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Forma;

import java.awt.Graphics;

/**
 *
 * @author Chalkos
 */
public class Arco extends Desenho{
    private int x1 = 0;
    private int x2 = 0;
    private int y1 = 0;
    private int y2 = 0;

    public Arco(int x1, int y1, int x2, int y2) {
        super();
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
    
}
