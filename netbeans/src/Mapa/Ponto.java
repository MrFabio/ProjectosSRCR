/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Prolog.Propriedades;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

/**
 *
 * @author Chalkos
 */
public class Ponto extends Figura{
    private Propriedades propriedades = null;
    
    protected double originalX;
    protected double originalY;
    
    private double x = 0;
    private double y = 0;
    
    private final double diametro = 20;
    
    
    public Ponto(double x, double y){
        super();
        originalX = x;
        originalY = y;
        this.x = x - diametro / 2;
        this.y = y - diametro / 2;
    }

    public Ponto(double x, double y, Color c) {
        this(x, y);
        this.color = c;
    }

    public Ponto(String nome, double x, double y){
        this(x,y);
        propriedades = new Propriedades(nome);
    }

    @Override
    public void desenhar(Graphics g) {
        g.setColor(color);
        g.fillOval(getX(x), getY(y), getWidth(diametro), getHeigth(diametro));

        /*if (x + diametro / 2 == -10 && y + diametro / 2 == -10) {
         System.out.println(getX(x) + " - " + getY(y) + " - " + getWidth(diametro) + " - " + getHeigth(diametro));
         }*/
    }
    
    public String getNome(){
        if( propriedades != null)
            return propriedades.getNome();
        return null;
    }
    
    public void setPropriedades(HashMap<String, String> p){
        propriedades.setPropriedades(p);
    }
    
    public HashMap<String, String> getPropriedades(){
        return propriedades.getPropriedades();
    }

    double getCenterY() {
        return (y + diametro / 2.0);
    }

    double getCenterX() {
        return (x + diametro / 2.0);
    }

    double getDiametro() {
        return diametro;
    }
}
