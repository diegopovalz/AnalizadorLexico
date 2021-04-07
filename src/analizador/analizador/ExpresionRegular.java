/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.analizador;

/**
 *
 * @author Poveda
 */
public enum ExpresionRegular {
    /**
     * Identifica si la entrada es una letra
     */
    LETRA("[A-Za-z]"),
    /**
     * Identifica si la entrada es alfanumérica (letras o números)
     */
    ALFA_NUMERICO("[0-9A-Za-z]"),
    /**
     * Identifica si la entrada es un número
     */
    NUMERO_ENTERO("[0-9]");
    
    private final String regExp;
    
    public String getExpresionRegular(){
        return regExp;
    }
    
    private ExpresionRegular(String regExp) {
        this.regExp = regExp;
    }
    
    public static ExpresionRegular fromRegExp(String regExp){ 
        for(ExpresionRegular exp : values()){
            if(exp.name().equals(regExp)){
                return exp;
            }
        }
        return null;
    }
}
