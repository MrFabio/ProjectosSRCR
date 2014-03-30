/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Forma;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Chalkos
 */
public class Ponto extends Desenho{
    private double x = 0;
    private double y = 0;
    
    private double diametro = 10;

    public Ponto(int x, int y){
        super();
        this.x = x-diametro/2;
        this.y = y-diametro/2;
    }
    public Ponto(int x, int y, Color c){
        this(x,y);
        this.color = c;
    }
    
    @Override
    public void desenhar(Graphics g) {
        g.setColor(color);
        g.fillOval(getX(x), getY(y), getWidth(diametro), getHeigth(diametro) );
    }
    
}
