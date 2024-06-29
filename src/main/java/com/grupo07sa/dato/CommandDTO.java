/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato;

import java.util.Map;

/**
 *
 * @author USER
 */
public class CommandDTO {

    private String command;
    private String[] parameters;

    public CommandDTO(String command, String[] parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public String getCommand() {
        return command;
    }

    public String[] getParameters() {
        return parameters;
    }

    public String getParametersToString() {
        String parametros = "[";
        for (int i = 0; i < this.parameters.length - 1; i++) {
            parametros = parametros + this.parameters[i] + ", ";
        }
        parametros = parametros + this.parameters[this.parameters.length - 1] + "]";
        return parametros;
    }

    public String getNameTable() {
        String tabla = this.parameters[0];
        return tabla;
    }

    public String[] getAttributesName() {
        String[] atributos = this.parameters[1].split(",");

        for (int i = 0; i < atributos.length; i++) {
            atributos[i] = ((atributos[i].split("=").length == 1) ? atributos[i] : atributos[i].split("=")[0]);
        }
        return atributos;
    }

    public String[][] getAttributesValue() {
        String[] atributos = this.parameters[1].split(",");
        String[][] atributosValor = new String[atributos.length][2];

        for (int i = 0; i < atributosValor.length; i++) {
            String[] av = atributos[i].split("=");
            atributosValor[i][0] = av[0];
            atributosValor[i][1] = av[1];
        }
        return atributosValor;
    }

    public String getAttributesNameToString() {
        String atr = "";
        String[] atributos = this.parameters[1].split(",");

        for (int i = 0; i < atributos.length-1; i++) {
            atr = atr + (atributos[i].split("=").length == 1 ? atributos[i] : atributos[i].split("=")[0]) + ", ";
        }
        atr=atr+(atributos[atributos.length-1].split("=").length == 1 ? atributos[atributos.length-1] : atributos[atributos.length-1].split("=")[0]);
        return atr;
    }

    public String getAttributesValueToString() {
        String[] atributos = this.parameters[1].split(",");
        String[][] atributosValor = new String[atributos.length][2];
        String atrv = "[\n";
        for (int i = 0; i < atributosValor.length; i++) {
            String[] av = atributos[i].split("=");
            atrv = atrv + "[atributo: " + av[0] + ", valor: " + av[1] + "]\n";
        }
        atrv = atrv + "]";
        return atrv;
    }
}
