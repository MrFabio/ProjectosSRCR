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
public class Ponto extends Figura {

    private double x = 0;
    private double y = 0;

    private double diametro = 10;

    public Ponto(int x, int y) {
        super();
        this.x = x - diametro / 2;
        this.y = y - diametro / 2;
    }

    public Ponto(int x, int y, Color c) {
        this(x, y);
        this.color = c;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDiametro() {
        return diametro;
    }

    @Override
    public void desenhar(Graphics g) {
        g.setColor(color);
        g.fillOval(getX(x), getY(y), getWidth(diametro), getHeigth(diametro));

        /*if (x + diametro / 2 == -10 && y + diametro / 2 == -10) {
         System.out.println(getX(x) + " - " + getY(y) + " - " + getWidth(diametro) + " - " + getHeigth(diametro));
         }*/
    }

    int getCenterX() {

        return (int) (x + diametro / 2);
    }

    int getCenterY() {

        return (int) (y + diametro / 2);
    }

}
