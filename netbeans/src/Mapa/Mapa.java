/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Mapa.Arco;
import Mapa.Figura;
import static Mapa.Figura.*;
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

    public Mapa(JPanel panel) {
        this.panel = panel;
        this.g = panel.getGraphics();
        shapes.add(new Ponto(-10, -10));
        shapes.add(new Ponto(-10, 10));
        shapes.add(new Ponto(10, 10));
        shapes.add(new Ponto(10, -10));
        shapes.add(new Arco(0, 20, 0, -20));
        shapes.add(new Arco(20, 0, -20, 0));
        shapes.add(new Ponto(0, 0, new Color(255, 0, 0)));

        shapes.add(activeLabel);
    }

    public int mouseXtoMapX(int mouseX) {
        return (int) ((mouseX - meioX) / zoom - offsetX);
    }

    public int mouseYtoMapY(int mouseY) {
        return (int) ((mouseY - meioY) / zoom - offsetY);
    }

    public void desenharPonto(int x, int y) {
        g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
        //g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        new Ponto(x, y).desenhar(g);
    }

    public void desenharTudo() {
        this.g = panel.getGraphics();
        g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
        //g.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        Figura.setMeio(panel.getWidth() / 2, panel.getHeight() / 2);
        for (Figura s : shapes) {
            if (s != null) {
                s.desenhar(g);
            }
        }
    }

    public void checkIntersect(int x, int y) {

        x = mouseXtoMapX(x);
        y = mouseYtoMapY(y);
        //System.out.println(shapes.size());
        for (Figura s : shapes) {
            if (s != null && s.getClass() == Ponto.class) {
                Ponto p = (Ponto) s;

                if (p.getDiametro() / 2 >= Math.sqrt(Math.pow(p.getCenterX() - x, 2) + Math.pow(p.getCenterY() - y, 2))) {
                    System.out.println("TÁ LÁ DENTRO !!!! diam=" + p.getDiametro() + " x=" + x + " y=" + y);
                }
            }
        }

    }
}
