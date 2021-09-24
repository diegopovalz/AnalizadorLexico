/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.analizador;

import analizador.token.Token;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Poveda
 */
public class ConvertidorSintetico {

    public static String analizar(ArrayList<Token> tokens) {
        List<String> primerTokenPermitido = Arrays.asList("FUNCTION", "IF_COND");
        
        if(!primerTokenPermitido.contains(tokens.get(0).getTipo())) {
            return "El código a analizar debe empezar con 'function' o 'if'";
        }
        
        String simbolo = ""; 
        for(int i = 0; i < tokens.size(); i++) {
            simbolo = tokens.get(i).getTipo();
            switch(simbolo) {
                case "IF_COND":
                    if(!tokens.get(i + 1).getTipo().equals("OPEN_PAR")) {
                        return "Error al rededor de token [" + simbolo.trim() + "]";
                    }
                    break;
                case "FUNCTION":
                    if(!tokens.get(i + 1).getTipo().equals("NOMB_VAR")) {
                        return "Error al rededor de token [" + simbolo.trim() + "]";
                    }
                    break;
                case "OPEN_PAR":
                    if((!tokens.get(i - 1).getTipo().equals("FUNCTION") && !tokens.get(i + 1).getTipo().equals("CLOSE_PAR"))
                        && (!tokens.get(i - 1).getTipo().equals("IF_COND") && !tokens.get(i + 1).getTipo().equals("NOMB_VAR"))) {
                        return "Error al rededor de token [" + simbolo.trim() + "]";
                    }
                    break;
                case "CLOSE_PAR":
                    if(!tokens.get(i + 1).getTipo().equals("OPEN_BRAC")) {
                        return "Error al rededor de token [" + simbolo.trim() + "]";
                    }
                    break;
                case "OPEN_BRAC":
                    if(!tokens.get(i + 1).getTipo().equals("VARIABLE")
                       && !tokens.get(i + 1).getTipo().equals("NOMB_VAR")
                       && !tokens.get(i - 1).getTipo().equals("CLOSE_PAR")) {
                        return "Error al rededor de token [" + simbolo.trim() + "]";
                    }
                    break;
                case "CLOSE_BRAC":
                    break;
                case "NOMB_VAR":
                    if((!tokens.get(i - 1).getTipo().equals("FUNCTION") && !tokens.get(i + 1).getTipo().equals("OPEN_PAR"))
                        && (!tokens.get(i - 1).getTipo().equals("VARIABLE") && !tokens.get(i + 1).getTipo().equals("EQUALS"))
                        && (!tokens.get(i - 1).getTipo().equals("OPEN_BRAC") && !tokens.get(i + 1).getTipo().equals("EQUALS"))) {
                        return "Error al rededor de token [" + simbolo.trim() + "]";
                    }
                    break;
                case "VARIABLE":
                    if(!tokens.get(i - 1).getTipo().equals("OPEN_BRAC")) {
                        return "Error al rededor de token [" + simbolo.trim() + "]";
                    }
                    break;
                case "NUMBER   ":
                case "STRING    ":
                case "CARACTER":
                    if(!tokens.get(i - 1).getTipo().equals("EQUALS")
                       || ( !tokens.get(i + 1).getTipo().equals("CLOSE_PAR")
                       && !tokens.get(i + 1).getTipo().equals("SEMICOLON"))) {
                        return "Error al rededor de token [" + simbolo.trim() + "]";
                    }
                    break;
                case "SEMICOLON":
                    if(!tokens.get(i - 1).getTipo().equals("NUMBER   ")
                       && !tokens.get(i - 1).getTipo().equals("STRING    ")
                       && !tokens.get(i - 1).getTipo().equals("CARACTER    ")) {
                        return "Error al rededor de token [" + simbolo.trim() + "]";
                    }
                    break;
                case "EQUALS":
                    if((!tokens.get(i - 1).getTipo().equals("EQUALS")
                       && !tokens.get(i - 1).getTipo().equals("NOMB_VAR"))
                       &&
                       (!tokens.get(i + 1).getTipo().equals("NUMBER   ")
                       && !tokens.get(i - 1).getTipo().equals("EQUALS")
                       && !tokens.get(i + 1).getTipo().equals("STRING    ")
                       && !tokens.get(i + 1).getTipo().equals("CARACTER    "))) {
                        return "Error al rededor de token [" + simbolo.trim() + "]";
                    }
                    break;
                default:
                    return "Error: Existe un token no reconocido";
            }
        }
        
        return "¡El código ingresado es correcto!";
    }
    
}
