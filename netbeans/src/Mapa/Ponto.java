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
    
    private double x = 0;
    private double y = 0;
    
    private double diametro = 2;
    
    
    public Ponto(int x, int y){
        super();
        this.x = x - diametro / 2;
        this.y = y - diametro / 2;
    }

    public Ponto(int x, int y, Color c) {
        this(x, y);
        this.color = c;
    }

    public Ponto(String nome, int x, int y){
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

    int getCenterY() {

        return (int) (y + diametro / 2);
    }

    int getCenterX() {

        return (int) (x + diametro / 2);
    }
}
