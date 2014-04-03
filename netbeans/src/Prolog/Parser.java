/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prolog;

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
                res.put(tmp1, tmp2);
                //System.out.println("x:" + x + " y:" + y);
            }
            i++;
        }
        return res;
    }

}
