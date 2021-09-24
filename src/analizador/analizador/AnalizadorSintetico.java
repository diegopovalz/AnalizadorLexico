/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.analizador;

import analizador.token.Token;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Poveda
 */
public class AnalizadorSintetico {
    public String analizarTokens(ArrayList<Token[]> tokens) {
        ArrayList<Token> tokensParaAnalizar = new ArrayList<>();
        int abreLlaves = 0, cierraLlaves = 0;        
        for(Token[] t : tokens) {
            for(Token t1 : t) {
                if(t1.getTipo().equals("ERROR    ")) return "Error, hay un token inválido en el código";
                if(t1.getLexema().equals("{")) abreLlaves++;
                if(t1.getLexema().equals("}")) cierraLlaves++;
            }
            tokensParaAnalizar.addAll(Arrays.asList(t));
        }
        
        if(abreLlaves != cierraLlaves) {
            return "No hay la misma cantidad de llaves\nque abren '{', y llaves que cierran '}'";
        }
        
        return ConvertidorSintetico.analizar(tokensParaAnalizar);
    }
}
