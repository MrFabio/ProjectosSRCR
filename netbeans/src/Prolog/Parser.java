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
import se.sics.jasper.Query;
import se.sics.jasper.SICStus;
import se.sics.jasper.SPException;

/**
 *
 * @author duarteduarte
 */
public class Parser {

    private SICStus sp;

    public Parser(Prolog p) {
        this.sp = p.sp;
    }
    
    /**
     * Obtém a distância entre dois pontos
     * @param origem ponto origem
     * @param destino ponto destino
     * @return a distancia entre os pontos
     */
    public float distanciaEntrePontos(String origem, String destino) {
        float res = -1;
        String resultado = "";
        StringBuilder resultadoManipulado = new StringBuilder();
        HashMap map = new HashMap();
        
        // distancia(p1,p2).
        String queryS = "distancia(" + origem + "," + destino + ", X ).";

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
        
        // resposta:
        // X = 2938.23
        int i = 0;
        for (i = 3; i < resultado.length() - 1; i++) {
            resultadoManipulado.append(resultado.charAt(i));
        }
        res = Float.parseFloat(resultadoManipulado.toString());
        //System.out.println(res);
        return res;
    }
    
    /**
     * Obtém o custo do melhor caminho entre dois pontos
     * @param origem ponto de partida
     * @param destino ponto de chegada
     * @return o melhor caminho entre os pontos especificados
     */
    public float custoMelhorCaminho(String origem, String destino) {
        float res = -1;
        String resultado = "";
        StringBuilder resultadoManipulado = new StringBuilder();
        HashMap map = new HashMap();
        
        String queryS = "melhorCaminho(" + origem + "," + destino + ", X).";

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
        
        // X = 23746.3234
        int i = 0;
        for (i = 3; i < resultado.length() - 1; i++) {
            resultadoManipulado.append(resultado.charAt(i));
        }
        if (!resultadoManipulado.toString().equals("")) {
            res = Float.parseFloat(resultadoManipulado.toString());
        }
        //System.out.println(res);
        return res;
    }
    
    /**
     * Obtém todos os pontos
     * @return todos os pontos
     */
    public ArrayList<Ponto> todosOsPontos() {
        ArrayList<Ponto> res = new ArrayList<Ponto>();
        String resultado = "";
        StringBuilder resultadoManipulado = new StringBuilder();
        HashMap map = new HashMap();

        String queryS = "findall((N,X,Y),posicao(N,X,Y),Bag).";

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
        for (i = 5; i < resultado.length() - 1; i++) {
            if (resultado.charAt(i) != '.' && resultado.charAt(i) != '(' && resultado.charAt(i) != ')'
                    && resultado.charAt(i) != '[' && resultado.charAt(i) != ']') {
                resultadoManipulado.append(resultado.charAt(i));
            }
        }

        String[] r = resultadoManipulado.toString().split(",");
        String tmp = "";
        int x, y;
        i = 0;
        while (i < r.length) {

            if (r[i].startsWith("p")) {
                //System.out.println(r[i]);
                tmp = r[i];
                i += 2;
                x = Integer.parseInt(r[i]);
                i++;
                y = Integer.parseInt(r[i]);
                Ponto p = new Ponto(tmp, x, y);
                res.add(p);
                //System.out.println("x:" + x + " y:" + y);
            }
            i++;
        }
        //System.out.println(res);
        return res;
    }

    public void setPropriedadesDoPonto(Ponto ponto) {
        String resultado = "";
        StringBuilder resultadoManipulado = new StringBuilder();
        HashMap map = new HashMap();

        String queryS = "findall((A,B),propriedade(" + ponto.getNome() + ",A,B),Bag).";

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

        //  {Bag=.(,(empresa,mcDonalds),.(,(servico,restauracao),[])), A=_142, B=_162}
        HashMap<String, String> propriedades = parserPropriedades(resultado);
        ponto.setPropriedades(propriedades);
    }

    private static HashMap<String, String> parserPropriedades(String toParser) {
        HashMap<String, String> res = new HashMap<>();
        if (toParser == null || toParser.equals("")) {
            return null;
        }
        String[] r = toParser.toString().split(",");
        String tmp = "";
        String tmp1 = "";
        String tmp2 = "";
        int x, y;
        int i = 0;
        while (i < r.length) {

            if (!r[i].startsWith("{") && !r[i].startsWith(",") && !r[i].startsWith(".") && !r[i].startsWith("[")
                    && !r[i].startsWith("A") && !r[i].startsWith("B")) {
                //System.out.println(r[i]);
                tmp = r[i];
                if (r[i].charAt(0) == '(') {
                    tmp1 = tmp.substring(1);
                }
                if (r[i].charAt(r[i].length() - 1) == ')') {
                    tmp1 = tmp.substring(0, tmp.length() - 1);
                }
                i += 1;
                tmp = r[i];
                if (r[i].charAt(0) == '(') {
                    tmp2 = tmp.substring(1);
                }
                if (r[i].charAt(r[i].length() - 1) == ')') {
                    tmp2 = tmp.substring(0, tmp.length() - 1);
                }
                
                if( tmp1.length() > 0 && tmp2.length() > 0 )
                    res.put(tmp1, tmp2);
                //System.out.println("x:" + x + " y:" + y);
            }
            i++;
        }
        return res;
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

}
