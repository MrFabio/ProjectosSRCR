/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prolog;

import Mapa.Arco;
import Mapa.Ponto;
import Prolog.Propriedades;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jregex.Matcher;
import jregex.Pattern;
import se.sics.jasper.Query;
import se.sics.jasper.SICStus;
import se.sics.jasper.SPException;

/**
 *
 * @author duarteduarte
 */
public class Parser {

    private SICStus sp;
    private Prolog prolog;

    public Parser(Prolog p) {
        this.sp = p.sp;
        this.prolog = p;
    }
    
    /**
     * Obtém todos os pontos
     * @return todos os pontos
     */
    public ArrayList<Ponto> todosOsPontos() {
        ArrayList<Ponto> res = new ArrayList<Ponto>();
        
        String answer = prolog.getStringResults("findall((N,X,Y),posicao(N,X,Y),Bag).");
        
        Pattern pattern=new Pattern("\\(,\\((.*?),,\\((.*?),(.*?)\\)\\)"); //a word pattern
        
        Matcher matcher=pattern.matcher(answer);
        while(matcher.find()){
            
            res.add(new Ponto(
                   matcher.group(1),
                   Integer.parseInt(matcher.group(2)),
                   Integer.parseInt(matcher.group(3))
            ));
        }
        
        
        //System.out.println(res);
        return res;
    }

    public void setPropriedadesDoPonto(Ponto ponto) {
        HashMap<String, String> res = new HashMap<>();
        
        Pattern pattern = new Pattern("\\(,\\((.*?),(.*?)\\),");
        
        String answer = prolog.getStringResults("findall((A,B),propriedade(" + ponto.getNome() + ",A,B),Bag).");

        Matcher matcher=pattern.matcher(answer);
        while(matcher.find())
            res.put(matcher.group(1), matcher.group(2));

        ponto.setPropriedades(res);
    }
    
    public ArrayList<Arco> todosOsArcos() {
        ArrayList<Arco> res = new ArrayList<>();
        
        Pattern pattern = new Pattern("\\(,\\((.*?),,\\((.*?),,\\((.*?),(.*?)\\)\\)\\),");
        
        String answer = prolog.getStringResults("predicadoTotalArcos(X).");

        Matcher matcher=pattern.matcher(answer);
        while(matcher.find()){
            
            Arco novo = new Arco(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4))
            );
            
            Boolean existe = false;
            for(Arco a : res){
                if( a.equals(novo) ){
                    existe = true;
                    break;
                }
            }
            
            if( !existe )
                res.add(novo);
        }

        return res;
    }
    
    public ArrayList<String> pontosDoMelhorCaminho(String origem, String destino){
        ArrayList<String> res = new ArrayList<>();
        
        String answer = prolog.getStringResults("melhorPercursoPontos(" + origem + "," + destino + ",X).");
        
        Pattern p=new Pattern("\\((.*?),"); //a word pattern
        
        Matcher m=p.matcher(answer);
        while(m.find()){
            res.add( m.toString().substring(1, m.toString().length()-1) );
        }
        
        return res;
    }
    
    public ArrayList<Double> distanciasDoMelhorCaminho(String origem, String destino){
        ArrayList<Double> res = new ArrayList<>();
        
        String answer = prolog.getStringResults("melhorPercursoDistancias(" + origem + "," + destino + ",X).");
        
        Pattern p=new Pattern("\\((.*?),"); //a word pattern
        
        Matcher m=p.matcher(answer);
        while(m.find()){
            res.add( Double.parseDouble(m.toString().substring(1, m.toString().length()-1)) );
        }
        
        return res;
    }
}
