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
        String resultado = "";
        StringBuilder resultadoManipulado = new StringBuilder();
        HashMap map = new HashMap();

        String queryS = "predicadoTotalArcos(X).";

        Query query;
        try {
            query = sp.openPrologQuery(queryS, map);
            while (query.nextSolution()) {
                //System.out.println(map.toString());
                resultado = map.toString();
            }
            query.close();
        } catch (SPException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i = 0;

        String[] r = resultado.toString().split(",");
        String tmp1 = "";
        String tmp2 = "";
        String tmp3 = "";
        String tmp4 = "";
        int p1, p2, p3, p4;
        int x, y;
        i = 0;
        boolean encontrou = false;
        while (i < r.length) {
            if (r[i] != null && !r[i].equals("")) {
                if (Character.isDigit(r[i].charAt(0)) || Character.isDigit(r[i].charAt(1))) {
                    //System.out.println(r[i]);
                    tmp1 = "";
                    tmp1 = r[i];
                    while (tmp1.charAt(0) == '(') {
                        tmp1 = tmp1.substring(1);
                    }
                    while (!Character.isDigit(tmp1.charAt(tmp1.length() - 1))) {
                        tmp1 = tmp1.substring(0, tmp1.length() - 1);
                    }
                    i++;
                    encontrou = false;
                    while (i < r.length && !encontrou) {
                        if (!r[i].equals("") && r[i] != null) {
                            if (Character.isDigit(r[i].charAt(0)) || Character.isDigit(r[i].charAt(1))) {
                                encontrou = true;
                                i--;
                            }
                        }
                        i++;
                    }
                    tmp2 = "";
                    tmp2 = r[i];
                    if (tmp2.startsWith("(")) {
                        tmp2 = tmp2.substring(1, tmp2.length());
                    }
                    while (!Character.isDigit(tmp2.charAt(tmp2.length() - 1))) {
                        tmp2 = tmp2.substring(0, tmp2.length() - 1);
                    }
                    i++;
                    encontrou = false;
                    while (i < r.length && !encontrou) {
                        if (!r[i].equals("") && r[i] != null) {
                            if (Character.isDigit(r[i].charAt(0)) || Character.isDigit(r[i].charAt(1))) {
                                encontrou = true;
                                i--;
                            }
                        }
                        i++;
                    }
                    tmp3 = "";
                    tmp3 = r[i];
                    if (tmp3.startsWith("(")) {
                        tmp3 = tmp3.substring(1, tmp3.length());
                    }
                    while (!Character.isDigit(tmp3.charAt(tmp3.length() - 1))) {
                        tmp3 = tmp3.substring(0, tmp3.length() - 1);
                    }
                    i++;
                    encontrou = false;
                    while (i < r.length && !encontrou) {
                        if (!r[i].equals("") && r[i] != null) {
                            if (Character.isDigit(r[i].charAt(0)) || Character.isDigit(r[i].charAt(1))) {
                                encontrou = true;
                                i--;
                            }
                        }
                        i++;
                    }
                    tmp4 = "";
                    tmp4 = r[i];
                    if (tmp4.startsWith("(")) {
                        tmp4 = tmp4.substring(1, tmp4.length());
                    }
                    while (!Character.isDigit(tmp4.charAt(tmp4.length() - 1))) {
                        tmp4 = tmp4.substring(0, tmp4.length() - 1);
                    }
                    i++;
                    encontrou = false;
                    
                    
                    res.add( new Arco(
                            Integer.parseInt(tmp1),
                            Integer.parseInt(tmp2),
                            Integer.parseInt(tmp3),
                            Integer.parseInt(tmp4)));
                    
                    //System.out.printf("%s %s %s %s\n", tmp1, tmp2, tmp3, tmp4);
                }
            }
            i++;
        }
        //System.out.println(res);
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
