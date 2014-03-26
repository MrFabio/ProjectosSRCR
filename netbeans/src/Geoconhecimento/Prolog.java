/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Geoconhecimento;

/**
 *
 * @author Chalkos
 */
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.sics.jasper.Query;
import se.sics.jasper.SICStus;
import se.sics.jasper.SPException;

public class Prolog{
    //Java Object to Interact with SICStus virtual Machine 
    SICStus sp; 
    
    //Construtor
    public Prolog(String pathToFile){
        try {
            loadSICStus();
            loadSICStusScrpt(pathToFile);
        } catch (SPException ex) {
            Logger.getLogger(Prolog.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

    Prolog() {
        
    }
    
    //Initalize SICStus virtual machine 
    private void loadSICStus() throws SPException { 
        sp = new SICStus(); 
    }
    
    public Boolean check(String queryS){
        try {
            //String query = “predicate(‘term’,’term2’).”;
            
            HashMap map = new HashMap(); 
            return sp.query(queryS,map);
        } catch (SPException ex) {
            Logger.getLogger(Prolog.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
            return false;
        }
    }
    
    public String getResults(String queryS){
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

    //Load SICStus script 
    private void loadSICStusScrpt(String pathToFile) throws SPException { 
        sp.load(pathToFile); 
    } 
}



    
    