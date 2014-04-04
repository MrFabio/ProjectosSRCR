/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Prolog;

/**
 *
 * @author Chalkos
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.sics.jasper.Query;
import se.sics.jasper.SICStus;
import se.sics.jasper.SPException;

public class Prolog{
    //Java Object to Interact with SICStus virtual Machine 
    protected SICStus sp; 
    
    //Construtor
    public Prolog(String pathToFile){
        try {
            loadSICStus();
            System.out.println(pathToFile);
            loadSICStusScrpt(pathToFile);
        } catch (SPException ex) {
            Logger.getLogger(Prolog.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
    //Initalize SICStus virtual machine 
    private void loadSICStus() throws SPException { 
        sp = new SICStus(); 
    }
    
    public Boolean check(String queryS){
        try {
            HashMap map = new HashMap(); 
            return sp.query(queryS,map);
        } catch (SPException ex) {
            //Logger.getLogger(Prolog.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public String getStringResults(String queryS){
       
        StringBuilder results = new StringBuilder();
        try {
            //String queryS = “predicate(‘term’,X).”;
            
            HashMap map = new HashMap();
            Query query = sp.openPrologQuery(queryS,map);
            while (query.nextSolution()) {
                //System.out.println(map.toString());
                
                results.append(map.toString());
            } 
            query.close();
        } catch (SPException ex) {
            Logger.getLogger(Prolog.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Prolog.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (Exception ex) {
            Logger.getLogger(Prolog.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        return results.toString();
    }
    
    
    public ArrayList<String> getResults(String queryS){
        ArrayList<String> resultados = new ArrayList<>();
        HashMap map = new HashMap();
        try {
            Query query = sp.openPrologQuery(queryS,map);
            while (query.nextSolution()) {
                //results.append(map.toString());
                resultados.add(map.values().toArray()[0].toString());
            } 
            query.close();
        } catch (SPException ex) {
            Logger.getLogger(Prolog.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Prolog.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (Exception ex) {
            Logger.getLogger(Prolog.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        return resultados;
    }

    //Load SICStus script 
    private void loadSICStusScrpt(String pathToFile) throws SPException { 
        sp.load(pathToFile); 
    } 
}



    
    