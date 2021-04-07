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
     * Variable en notación camello: camelCase
     */
    VARIABLE("([a-z][a-zA-Z0-9]+)+"),
    /**
     * Constante caracter: 'a'
     */
    CHAR_CONST("([\'][A-Za-z0-9]{1}[\'])+"),
    /**
     * Constante hilera: "abc"
     */
    STR_CONST("([\"][A-Za-z0-9]+[\"])+");
    
    private final String regExp;
    
    public String getRegExp(){
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
