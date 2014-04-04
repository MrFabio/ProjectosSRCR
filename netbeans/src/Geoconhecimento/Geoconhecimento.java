/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geoconhecimento;

import Mapa.Arco;
import Mapa.Ponto;
import Prolog.Prolog;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import Prolog.Parser;
import java.util.ArrayList;

/**
 *
 * @author Chalkos
 */
public class Geoconhecimento {
    private Prolog conhecimento;
    private Parser parser;
    
    
    public Geoconhecimento(String filename) {
        conhecimento = new Prolog(filename);
        parser = new Parser(conhecimento);
    }
    
    public Geoconhecimento(){
        conhecimento = new Prolog("..\\pontos.pl");
        parser = new Parser(conhecimento);
        
        //parser.distanciasDoMelhorCaminho("p1","p5");
    }
    
    public ArrayList<Ponto> getPontos(){
        return parser.todosOsPontos();
    }
    
    public ArrayList<Arco> getArcos(){
        return parser.todosOsArcos();
    }
    
    public void actualizarPropriedades(Ponto p){
        parser.setPropriedadesDoPonto(p);
    }
    
    public ArrayList<String> pontosDoCaminhoMaisCurto(Ponto origem, Ponto destino){
        return parser.pontosDoMelhorCaminho(origem.getNome(), destino.getNome());
    }
    public ArrayList<Double> distanciasDoCaminhoMaisCurto(Ponto origem, Ponto destino){
        return parser.distanciasDoMelhorCaminho(origem.getNome(), destino.getNome());
    }
}
