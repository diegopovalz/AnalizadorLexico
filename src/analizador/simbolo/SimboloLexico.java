/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.simbolo;

/**
 *
 * @author Poveda & Saldarriaga
 */
public enum SimboloLexico {
    //Identificadores de variables
    VARIABLE("var"),
    CONSTANT("const"),
    LET_VAR("let"),
    //Identificador de funciones
    FUNCTION("function"),
    PRINT("print"),
    //Identificadores de operaciones
    MINUS("-"),
    PLUS("+"),
    TIMES("*"),
    DIVIDED("/"),
    //Identificadores de relación/condición
    EQUALS("="),
    IF_COND("if"),
    ELSE_COND("else"),
    AND_OPER("&&"),
    OR_OPER("||"),
    //Identificadores de separador
    OPEN_BRAC("{"),
    CLOSE_BRAC("}"),
    OPEN_PAR("("),
    CLOSE_PAR(")"),
    SEMICOLON(";"),
    SEP_COMMA(","),
    //Identificadores de texto
    DOUB_QUOTE("\""),
    SING_QUOTE("\'");

    private final String lexema;

    private SimboloLexico(String lexema) {
        this.lexema = lexema;
    }

    public static SimboloLexico desdeLexema(String lexema) {
        for (SimboloLexico simb : values()) {
            if (simb.getLexema().equals(lexema)) {
                return simb;
            }
        }
        return null;
    }
    
    public static boolean esSimbolo(String lexema) {
        for (SimboloLexico simb : values()) {
            if (simb.getLexema().equals(lexema)) {
                return true;
            }
        }
        return false;
    }

    public String getLexema() {
        return lexema;
    }
}
