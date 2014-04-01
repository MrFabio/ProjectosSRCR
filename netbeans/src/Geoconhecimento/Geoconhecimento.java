/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Geoconhecimento;

import Prolog.Parser;
import Prolog.Prolog;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Chalkos
 */
public class Geoconhecimento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Prolog conhecimento = new Prolog("..\\pontos.pl");
        Parser parses = null;
        System.out.println(conhecimento.getResults("melhorCaminho(p1,p7,X)."));
       // System.out.println(parses.melhorCaminho("caminhos2(p1,p7,X)."));
        // System.out.println(conhecimento.check("arco(p1,p7)."));
        // System.out.println(conhecimento.check("arco(p2,p7)."));
        // System.out.println(conhecimento.check("arco(p3,p7)."));
        // System.out.println(conhecimento.check("arcos(p4,p7)."));
        // System.out.println(conhecimento.check("melhorCaminho(p1,p7,X)."));
        //String res = parses.melhorCaminho("melhorCaminho(p1,p7,X).");
        //System.out.println(res);
        
    }
    
}
