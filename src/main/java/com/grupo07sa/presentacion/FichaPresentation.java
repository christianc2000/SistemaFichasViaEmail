/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.presentacion;

/**
 *
 * @author USER
 */
public class FichaPresentation {
    
    public String listarFichaHTML(String title, String[][] dato) {
        String listar = "<style>\n"
                + "    .container-title {\n"
                + "        display:flex;\n"
                + "        justify-content: center;\n"
                + "        align-content: center;\n"
                + "    }\n"
                + "    table {\n"
                + "        width: 100%;\n"
                + "        border-collapse: collapse;\n"
                + "    }\n"
                + "    th, td {\n"
                + "        border: 1px solid black;\n"
                + "        padding: 15px;\n"
                + "        text-align: left;\n"
                + "    }\n"
                + "    th {\n"
                + "        background-color: #4682B4;\n"
                + "        color: white;\n"
                + "    }\n"
                + "    .container {\n"
                + "        display: flex;\n"
                + "        justify-content: space-around;\n"
                + "        align-content: center;\n"
                + "        padding: 20px;\n"
                + "    }\n"
                + "    .container-title {\n"
                + "        display:flex;\n"
                + "        justify-content: center;\n"
                + "        align-content: center;\n"
                + "    }\n"
                + "    .button {\n"
                + "        margin-top: 20px;\n"
                + "        padding: 5px 20px;\n"
                + "        font-size: 16px;\n"
                + "        text-align: center;\n"
                + "        cursor: pointer;\n"
                + "        outline: none;\n"
                + "        color: #ffffff;\n"
                + "        background-color: #4682B4;\n"
                + "        border: none;\n"
                + "        border-radius: 15px;\n"
                + "        text-decoration: none;\n"
                + "        margin-bottom: 20px;\n"
                + "    }\n"
                + "    .button:hover{\n"
                + "        background-color: #0080FF\n"
                + "    }\n"
                + "</style>\n"
                + "<div class=\"container-title\">\n"
                + "    <h1>" + title + "</h1>\n"
                + "</div>\n"
                +"<div style=\"margin-top: 10px\">\n"
                + "    <table>\n"
                + "  <thead>\n";
        listar = listar + "<tr>\n";
        for (int j = 0; j < dato[0].length; j++) {
            listar = listar + "<th>\n" + dato[0][j] + "</th>\n";
        }
        listar = listar + "</tr>\n";
        listar = listar + "</thead>\n";
        listar = listar + "<tbody>\n";
        for (int i = 1; i < dato.length; i++) {
            listar = listar + "<tr>\n";
            for (int j = 0; j < dato[i].length; j++) {
                listar = listar + "<td>\n" + dato[i][j] + "</td>\n";
            }
            listar = listar + "</tr>\n";
        }
        listar = listar + "</tbody>\n";
        listar = listar + "    </table>\n"
                + "</div>";

        return listar;
    }
}
