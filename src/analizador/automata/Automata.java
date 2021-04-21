/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.automata;

import analizador.expresion.ExpresionRegular;

/**
 *
 * @author Poveda & Saldarriaga
 */
public class Automata {

    public static boolean lexemaEsIdentificador(String lexema) {
        String letra = ExpresionRegular.LETRA.getExpresionRegular();
        String digitoLetra = ExpresionRegular.ALFA_NUMERICO.getExpresionRegular();
        String caracter = "";
        String estado = "L";
        for (int i = 0; i < lexema.length(); i++) {
            caracter = lexema.charAt(i) + "";
            switch (estado) {
                case "L":
                    if (caracter.matches(letra)) {
                        estado = "DL";
                    } else {
                        estado = "O";
                    }
                    break;
                case "DL":
                    if (caracter.matches(digitoLetra)) {
                        estado = "DL";
                    } else {
                        estado = "O";
                    }
                    break;
            }
        }
        return !estado.equals("O");
    }

    public static boolean lexemaEsNumero(String lexema) {
        String digito = ExpresionRegular.NUMERO_ENTERO.getExpresionRegular();
        String caracter = "";
        String estado = "NUM";
        for (int i = 0; i < lexema.length(); i++) {
            caracter = lexema.charAt(i) + "";
            switch (estado) {
                case "NUM":
                    if (caracter.matches(digito)) {
                        estado = "NUM";
                    } else {
                        estado = "NO_NUM";
                    }
                    break;
            }
        }
        return !estado.equals("NO_NUM");
    }
}
