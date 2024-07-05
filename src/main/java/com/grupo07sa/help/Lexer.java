/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.help;

import com.grupo07sa.dato.CommandDTO;
import com.grupo07sa.dato.User.UserDTO;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author USER
 */
public class Lexer {

    private Pattern patronStart;
    private Pattern patronHelp;
    private Pattern patronList;
    private Pattern patronAtention;
    private Pattern patronListAttributes;
    private Pattern patronInsert;
    private Pattern patronUpdate;
    private Pattern patronShow;
    private Pattern patronDelete;

    public Lexer() {
        this.patronStart = Pattern.compile("^START\\[\\];$");
        this.patronHelp = Pattern.compile("^HELP\\[\\];$");
        this.patronAtention = Pattern.compile("^ATENTIONS\\[\\];$");
        this.patronList = Pattern.compile("^LIST\\[[a-zA-Z0-9_]+\\];$");
        this.patronListAttributes = Pattern.compile("^LIST\\[[a-zA-Z0-9_]+:(?:[a-zA-Z0-9_]+,)*[a-zA-Z0-9_]+\\];$");
        this.patronInsert = Pattern.compile("^INSERT\\[[a-zA-Z0-9_]+:(?:[a-zA-Z0-9_]+=[\\w\\-\\s@./:]+,)*[a-zA-Z0-9_]+=[\\w\\-\\s@./:]+\\];$");
        this.patronUpdate = Pattern.compile("^UPDATE\\[[a-zA-Z0-9_]+:(?:[a-zA-Z0-9_]+=[\\w\\-\\s@.]+,)*[a-zA-Z0-9_]+=[\\w\\-\\s@.]+\\];$");
        this.patronShow = Pattern.compile("^SHOW\\[[a-zA-Z0-9_]+:[a-zA-Z0-9_]+=\\w+\\];$");
        this.patronDelete = Pattern.compile("^DELETE\\[[a-zA-Z0-9_]+:[a-zA-Z0-9_]+=\\w+\\];$");
    }

    public CommandDTO analizarMensaje(String input) {
        if (patronStart.matcher(input).matches()) {
            return new CommandDTO("START", null);
        } else if (patronHelp.matcher(input).matches()) {
            return new CommandDTO("HELP", null);
        } else if (patronAtention.matcher(input).matches()) {
            return new CommandDTO("ATENTIONS", null);
        } else if (patronList.matcher(input).matches()) {
            String tableName = extractTableName(input);
            return new CommandDTO("LIST", new String[]{tableName});
        } else if (patronListAttributes.matcher(input).matches()) {
            String tableName = extractTableName(input);
            String[] attributes = extractAttributes(input);
            return new CommandDTO("LIST", new String[]{tableName, String.join(",", attributes)});
        } else if (patronInsert.matcher(input).matches()) {
            String tableName = extractTableName(input);
            String[] keyValues = extractKeyValues(input);
            return new CommandDTO("INSERT", new String[]{tableName, String.join(",", keyValues)});
        } else if (patronUpdate.matcher(input).matches()) {
            String tableName = extractTableName(input);
            String[] keyValues = extractKeyValues(input);
            return new CommandDTO("UPDATE", new String[]{tableName, String.join(",", keyValues)});
        } else if (patronShow.matcher(input).matches()) {
            String tableName = extractTableName(input);
            String condition = extractCondition(input);
            return new CommandDTO("SHOW", new String[]{tableName, condition});
        } else if (patronDelete.matcher(input).matches()) {
            String tableName = extractTableName(input);
            String condition = extractCondition(input);
            return new CommandDTO("DELETE", new String[]{tableName, condition});
        } else {
            return new CommandDTO("ERROR", null);
        }
    }

    private String extractTableName(String input) {
        int start = input.indexOf('[') + 1;
        int end = input.indexOf(':') != -1 ? input.indexOf(':') : input.indexOf(']');
        return input.substring(start, end);
    }

    private String[] extractAttributes(String input) {
        int start = input.indexOf(':') + 1;
        int end = input.indexOf(']');
        return input.substring(start, end).split(",");
    }

    private String[] extractKeyValues(String input) {
        int start = input.indexOf(':') + 1;
        int end = input.indexOf(']');
        return input.substring(start, end).split(",");
    }

    private String extractCondition(String input) {
        int start = input.indexOf(':') + 1;
        int end = input.indexOf(']');
        return input.substring(start, end);
    }

    /*public static void main(String[] args) {
        Lexer analizador = new Lexer();
        CommandDTO comando = analizador.analizarMensaje("INSERT[users:ci=1,name=christian celso,lastname=mamani soliz];");
        System.out.println("Comando: " + comando.getCommand());
        System.out.println("Parametro: " + comando.getParametersToString());
        System.out.println("tabla: " + comando.getNameTable());
        System.out.println("atributos: " + comando.getAttributesNameToString());
        System.out.println("atributos con valor: " + comando.getAttributesValueToString());
        System.out.println("filas: " + comando.getAttributesValue().length);
        System.out.println("columnas: " + comando.getAttributesValue()[0].length);

        UserDTO user = new UserDTO();
        for (int i = 0; i < comando.getAttributesValue().length; i++) {
            user.setAttribute(comando.getAttributesValue()[i][0], comando.getAttributesValue()[i][1]);
        }
        System.out.println("ci: "+user.getCi());
        System.out.println("nombre: "+user.getName());
    }*/
}
