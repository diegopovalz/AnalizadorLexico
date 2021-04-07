/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.analizador;

import analizador.token.Token;
import java.util.ArrayList;

/**
 *
 * @author Poveda
 */
public class Analizador {
    public String analizar(String texto){
        ArrayList<Token[]> tokensFinales = new ArrayList<>();
        String[] lineas = convertirLineas(texto);
        for(String linea : lineas) {
            Token[] tokens = Convertidor.convertirATokens(linea);
            tokensFinales.add(tokens);
        }
        return convertirTokensATexto(tokensFinales);
    }
    
    private String[] convertirLineas(String texto) {
        String[] lineas = texto.split("\n");
        for(String linea : lineas) {
            linea = linea.trim();
        }
        return lineas;
    }
    
    private String convertirTokensATexto(ArrayList<Token[]> tokens) {
        return "";
    }
}
