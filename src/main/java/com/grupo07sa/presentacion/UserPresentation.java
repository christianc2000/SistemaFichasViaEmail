/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.presentacion;

/**
 *
 * @author USER
 */
public class UserPresentation {

    private String listUser;
    private String listAtrUser;
    private String insertUser;
    private String updateUser;
    private String showUser;
    private String deleteUser;

    public UserPresentation() {
        listUser = "subject=LIST[users];&body=Listar usuario";
        listAtrUser = "subject=LIST[users:id,ci,name,lastname,fecha_nacimiento,foto,direccion,gender,celular,email,password,nit,razon_social];&body=Listar usuario por atributo";
        insertUser = "subject=INSERT[users:ci=string,name=string,lastname=string,fecha_nacimiento=date,foto=string,direccion=string,gender=string,celular=string,email=string,password=string,nit=string,razon_social=string];&body=Insertar usuario";
        updateUser = "subject=UPDATE[users:ci=string,name=string,lastname=string,fecha_nacimiento=date,foto=string,direccion=string,gender=string,celular=string,email=string,password=string,nit=string,razon_social=string];&body=Actualizar usuario";
        showUser = "subject=SHOW[users:id=number];&body=Mostrar usuario";
        deleteUser = "subject=DELETE[users:id=number];&body=Eliminar usuario";
    }

    public String listarConFoto(String title, String[][] dato, String list, String listAtr, String insert, String update, String show, String delete) {
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
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + list + "\" class=\"button\">Listar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + listAtr + "\" class=\"button\">Listar por atributo</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + insert + "\" class=\"button\">Insertar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + update + "\" class=\"button\">Actualizar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + show + "\" class=\"button\">Mostrar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + delete + "\" class=\"button\">Eliminar</a>\n"
                + "<div style=\"margin-top: 10px\">\n"
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
