/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Mapa;

import Mapa.Arco;
import Mapa.Figura;
import Mapa.Ponto;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Chalkos
 */
public class Mapa {
    private ArrayList<Figura> shapes = new ArrayList<>();
    private JPanel panel;
    private Graphics g;
    
    public final Etiqueta activeLabel = new Etiqueta();

    public Mapa(JPanel panel, ArrayList<Ponto> pontos) {
        this.panel = panel;
        this.g = panel.getGraphics();
        
        shapes.addAll(pontos);
        
        shapes.add(activeLabel);
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
        
        Figura.setMeio(panel.getWidth()/2, panel.getHeight()/2);
        for(Figura s : shapes)
            if(s != null)
                s.desenhar(g);
    }
}
