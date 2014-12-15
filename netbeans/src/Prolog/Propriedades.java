/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Prolog;

import java.util.HashMap;

/**
 *
 * @author Chalkos
 */
public class Propriedades {
    private HashMap<String, String> propriedades;
    private String nome;

    public Propriedades(String nomeDoPonto) {
        this.nome = nomeDoPonto;
    }
    
    public HashMap<String, String> getPropriedades() {
        return propriedades;
    }

    public void setPropriedades(HashMap<String, String> propriedades) {
        this.propriedades = propriedades;
    }
    
    public String getNome(){
        return nome;
    }
}
