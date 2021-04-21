/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.analizador;

import analizador.automata.Automata;
import analizador.simbolo.SimboloLexico;
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
        boolean esHileraOCaracter = false;
        String simboloHileraOCaracter = "";
        for (int i = 0; i < caracteres.length; i++) {
            String caracter = caracteres[i] + "";
            if (esHileraOCaracter) {
                if (caracter.equalsIgnoreCase("\"")) {
                    //Es el segundo "
                    esHileraOCaracter = false;
                    lexema += caracter;
                    
                    //Si el simbolo final no es el mismo que el del inicio, ERROR
                    if(!caracter.equalsIgnoreCase(simboloHileraOCaracter)) {
                        tokens.add(new Token("ERROR     ", lexema));
                        lexema = "";
                        simboloHileraOCaracter = "";
                        continue;
                    }
                    
                    tokens.add(new Token("STRING    ", lexema));
                    lexema = "";
                    simboloHileraOCaracter = "";
                    continue;
                } else if (caracter.equalsIgnoreCase("'")) {
                    //Es el segundo '
                    esHileraOCaracter = false;
                    lexema += caracter;
                    
                    //Si el simbolo final no es el mismo que el del inicio, ERROR
                    if(!caracter.equalsIgnoreCase(simboloHileraOCaracter)) {
                        tokens.add(new Token("ERROR     ", lexema));
                        lexema = "";
                        simboloHileraOCaracter = "";
                        continue;
                    }
                    
                    if (lexema.length() == 3) {
                        tokens.add(new Token("CARACTER", lexema));
                    } else {
                        //Si el "caracter" tiene más de 3 caracteres, ERROR
                        tokens.add(new Token("ERROR     ", lexema));
                    }
                    lexema = "";
                    simboloHileraOCaracter = "";
                    continue;
                }
                lexema += caracter;
                if (i == caracteres.length - 1) {
                    if ((!caracter.equalsIgnoreCase("\"") || !caracter.equalsIgnoreCase("'"))) {
                        esHileraOCaracter = false;
                        tokens.add(new Token("ERROR     ", lexema));
                        lexema = "";
                    }
                }
                continue;
            }
            if (SimboloLexico.esSimbolo(caracter)) {
                //Es el primer " o '
                if (caracter.equalsIgnoreCase("\"") || caracter.equalsIgnoreCase("'")) {
                    if (i == caracteres.length - 1) {
                        tokens.add(new Token("ERROR     ", caracter));
                        continue;
                    } else {
                        esHileraOCaracter = true;
                    }
                    simboloHileraOCaracter = caracter;
                }
                if (!lexema.isEmpty()) {
                    tokens.add(analizar(lexema));
                }
                if (!caracter.equalsIgnoreCase("\"") && !caracter.equalsIgnoreCase("'")) {
                    tokens.add(analizar(caracter));
                    lexema = "";
                } else {
                    lexema += caracter;
                }
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
        if (simbolo != null) {
            token = new Token(simbolo.name(), simbolo.getLexema());
        } else {
            if (Automata.lexemaEsIdentificador(lexema)) {
                token = new Token("NOMB_VAR", lexema);
            } else if (Automata.lexemaEsNumero(lexema)) {
                token = new Token("NUMBER   ", lexema);
            }
        }

        if (token == null) {
            token = new Token("ERROR    ", lexema);
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
            i++;
        }
        return salida;
    }
}
