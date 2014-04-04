/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Chalkos
 */
public abstract class Figura {

    protected static double zoom = 1;
    protected static int meioX = 0;
    protected static int meioY = 0;
    protected static double offsetX = 0;
    protected static double offsetY = 0;
    protected Color color = Mapa.normal;

    public abstract void desenhar(Graphics g);

    public static void setZoom(int zoom) {
        Figura.zoom = ((double) zoom) / 100;
    }

    public static void setMeio(int x, int y) {
        meioX = x;
        meioY = y;
    }

    protected int getX(double original) {
        return (int) ((original + offsetX) * zoom + meioX);
    }

    protected int getY(double original) {
        return (int) ((original + offsetY) * zoom + meioY);
    }

    protected int getWidth(double original) {
        return (int) (original * zoom);
    }

    protected int getHeigth(double original) {
        return (int) (original * zoom);
    }

    public static int mouseXToCoords(int val) {
        return (int) (val / zoom - meioX - offsetX);
    }

    public static int mouseYToCoords(int val) {
        return (int) (val / zoom - meioY - offsetY);
    }

    public static void mouseMoved(int fromX, int fromY, int toX, int toY) {
        double novoOffsetX = toX - fromX;
        double novoOffsetY = toY - fromY;

        novoOffsetX /= zoom;
        novoOffsetY /= zoom;

        offsetX += novoOffsetX;
        offsetY += novoOffsetY;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
