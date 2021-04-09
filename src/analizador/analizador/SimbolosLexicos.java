/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.analizador;

/**
 *
 * @author Poveda & Saldarriaga
 */
public enum SimbolosLexicos {
    VAR("var"),
    LET("let"),
    CONST("const"),
    FUNCTION("function"),
    OPEN_PAR("("),
    CLOSE_PAR(")"),
    PRINT("print"),
    MINUS("-"),
    PLUS("+"),
    TIMES("*"),
    DIVIDED("/"),
    EQUALS("="),
    IF("if"),
    ELSE("else"),
    AND("&&"),
    OR("||"),
    OPEN_BRAC("{"),
    CLOSE_BRAC("}"),
    SEMICOLON(";");

    private final String lexema;

    private SimbolosLexicos(String lexema) {
        this.lexema = lexema;
    }

    public static SimbolosLexicos fromLexema(String lexema) {
        for (SimbolosLexicos simb : values()) {
            if (simb.name().equals(lexema)) {
                return simb;
            }
        }
        return null;
    }

    public String getLexema() {
        return lexema;
    }
}
