/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.token;

/**
 *
 * @author Poveda
 */
public class Token {
    private String tipo;
    private String lexema;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public Token(String tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
    }
}
