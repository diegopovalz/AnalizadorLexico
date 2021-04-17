/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.analizador;

import analizador.token.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Poveda & Saldarriaga
 */
public class Convertidor {

    public static Token[] convertirATokens(String linea) {
        List<Token> tokens = new ArrayList<>();
        char[] caracteres = linea.toCharArray();
        String lexema = "";
        boolean flagChar = false;
        for (int i = 0; i < caracteres.length; i++) {
            String caracter = caracteres[i] + "";
            if (flagChar) {
                if (caracter.equalsIgnoreCase("\"")) {
                    flagChar = false;
                    tokens.add(new Token("STRING", lexema));
                    tokens.add(analizar(caracter));
                    lexema = "";
                    continue;
                } else if (caracter.equalsIgnoreCase("'")) {
                    flagChar = false;
                    tokens.add(new Token("caracter", lexema));
                    tokens.add(analizar(caracter));
                    lexema = "";
                }
                lexema += caracter;
                continue;
            }
            if (SimboloLexico.esSimbolo(caracter)) {
                if (caracter.equalsIgnoreCase("\"")) {
                    flagChar = true;
                }
                if (!lexema.isEmpty()) {
                    tokens.add(analizar(lexema));
                }
                tokens.add(analizar(caracter));
                lexema = "";
            } else if (caracter.equalsIgnoreCase(" ")) {
                if (!lexema.isEmpty()) {
                    tokens.add(analizar(lexema));
                }
                lexema = "";
            } else {
                lexema += caracter;
            }
        }
        if (!lexema.isEmpty()) {
            tokens.add(analizar(lexema));
        }
        return tokens.toArray(new Token[tokens.size()]);
    }

    private static Token analizar(String lexema) {
        SimboloLexico simbolo = SimboloLexico.desdeLexema(lexema);
        Token token = null;
        if(simbolo != null) {
            token = new Token(simbolo.name(), simbolo.getLexema());
        } else {
            if (Automata.lexemaEsIdentificador(lexema)) {
                token = new Token("NOMB_VAR", lexema);
            } else if (Automata.lexemaEsNumero(lexema)) {
                token = new Token("NUM", lexema);
            }
        }
        
        if (token == null) {
            token = new Token("ERROR", lexema);
        }
        return token;
    }

    public static String convertirTokensATexto(ArrayList<Token[]> tokensFinales) {
        String salida = "";
        int i = 0;
        for (Token[] tokens : tokensFinales) {
            salida += "Línea " + (i + 1) + ":\n";
            for (Token token : tokens) {
                salida += "Token: " + token.getTipo() + "\tLexema: " + token.getLexema() + "\n";
            }
            salida += "\n";
        }
        return salida;
    }
}