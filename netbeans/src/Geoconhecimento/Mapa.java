/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Geoconhecimento;

import Forma.Arco;
import Forma.Desenho;
import Forma.Ponto;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Chalkos
 */
public class Mapa {
    private ArrayList<Desenho> shapes = new ArrayList<>();
    private JPanel panel;
    private Graphics g;

    public Mapa(JPanel panel) {
        this.panel = panel;
        this.g = panel.getGraphics();
        shapes.add(new Ponto(-10,-10));
        shapes.add(new Ponto(-10,10));
        shapes.add(new Ponto(10,10));
        shapes.add(new Ponto(10,-10));
        shapes.add(new Arco(0,20,0,-20));
        shapes.add(new Arco(20,0,-20,0));
        shapes.add(new Ponto(0,0, new Color(255, 0, 0)));
    }
    
    public void desenharPonto(int x, int y){
        g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
        //g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        new Ponto(x, y).desenhar(g);
    }
    
    public void desenharTudo(){
        this.g = panel.getGraphics();
        g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
        //g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        
        Desenho.setMeio(panel.getWidth()/2, panel.getHeight()/2);
        for(Desenho s : shapes)
            s.desenhar(g);
    }
}
