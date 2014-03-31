/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Prolog;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jregex.Matcher;
import jregex.Pattern;
import jregex.RETokenizer;

/**
 *
 * @author Chalkos
 */
public class Parser {
    public static final int TIPO_UNKNOWN = 0;
    public static final int TIPO_BOOLEAN = 1;
    public static final int TIPO_LISTA = 2;
    
    // testes
    public static void main(String[] args){
        ArrayList<String> testes = new ArrayList<>();
        
        testes.add("[]");
        testes.add("[1,2]");
        testes.add("[1]");
        testes.add("[1,2,[34,45],4]");
        testes.add("[1,2,[34,45],4,[34,45],5]");
        testes.add("[1,2,[34,45]]");
        
        //Pattern p = new Pattern("\\[(.*)\\]"); //a word pattern
        //Pattern p = new Pattern("(?<!\"\\]),(?!\"\\[)");
        Pattern p = new Pattern(",");
        
        for( String test_original : testes ){
            String test = test_original.substring(1, test_original.length()-1);
            RETokenizer tok=new RETokenizer(p,test);
            System.out.println("String: " + test);
            while(tok.hasMore())
                System.out.print("\n   "+tok.nextToken());
            System.out.println("");
        }
        
        exit(0);
        
        for( String test : testes ){
            Matcher m = p.matcher(test);
            if(m.matches()){
                System.out.print("matches " + test + "\n   matches: ");
                for(String group : m.groups())
                    System.out.print(group + "\n   ");
                System.out.println("");
            }else{
                System.out.println("failed " + test);
            }
        }
    }
    
    public static String[] parseList(String input){
        // falhar caso não seja uma lista
        if( !input.startsWith("[") || !input.endsWith("]")){
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, "Not a list.");
            return null;
        }
        
        ArrayList<String> elementos = new ArrayList<>();
        
        //encontrar todos os elementos da lista
        
        
        return (String[])elementos.toArray();
    }
    
    
}
