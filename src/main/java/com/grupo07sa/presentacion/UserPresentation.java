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
    private String listAdmin;
    private String insertAdmin;
    private String updateAdmin;
    private String showAdmin;
    private String deleteAdmin;
    private String listTrabajador;
    private String insertTrabajador;
    private String updateTrabajador;
    private String showTrabajador;
    private String deleteTrabajador;
    private String listPaciente;
    private String insertPaciente;
    private String updatePaciente;
    private String showPaciente;
    private String deletePaciente;

    public UserPresentation() {
        listUser = "subject=LIST[users];&body=Listar usuario";
        listAtrUser = "subject=LIST[users:id,ci,name,lastname,fecha_nacimiento,foto,direccion,gender,celular,email,password,nit,razon_social];&body=Listar usuario por atributo";
        insertUser = "subject=INSERT[users:ci=string,name=string,lastname=string,fecha_nacimiento=date,foto=string,direccion=string,gender=string,celular=string,email=string,password=string,nit=string,razon_social=string];&body=Insertar usuario";
        updateUser = "subject=UPDATE[users:id=number,ci=string,name=string,lastname=string,fecha_nacimiento=date,foto=string,direccion=string,gender=string,celular=string,email=string,password=string,nit=string,razon_social=string];&body=Actualizar usuario";
        showUser = "subject=SHOW[users:id=number];&body=Mostrar usuario";
        deleteUser = "subject=DELETE[users:id=number];&body=Eliminar usuario";
        //ADMIN
        listAdmin = "subject=LIST[administradors];&body=Listar Administradores";
        insertAdmin = "subject=INSERT[administradors:id=number];&body=Insertar Administrador";
        showAdmin = "subject=SHOW[administradors:id=number];&body=Mostrar Administrador";
        deleteAdmin = "subject=DELETE[administradors:id=number];&body=Eliminar Aministrador";

        //TRABAJADOR
        listTrabajador = "subject=LIST[trabajadors];&body=Listar Trabajadores";
        insertTrabajador = "subject=INSERT[trabajadors:id=number,especialidad=string,tipo=string];&body=Insertar Trabajador";
        updateTrabajador = "subject=UPDATE[trabajadors:id=number,especialidad=string,tipo=string];&body=Actualizar Trabajador";
        showTrabajador = "subject=SHOW[trabajadors:id=number];&body=Mostrar Trabajador";
        deleteTrabajador = "subject=DELETE[trabajadors:id=number];&body=Eliminar Trabajador";
        //PACIENTE
        listPaciente = "subject=LIST[pacientes];&body=Listar Pacientes";
        insertPaciente = "subject=INSERT[pacientes:id=number,profesion=string];&body=Insertar paciente";
        updatePaciente = "subject=UPDATE[pacientes:id=number,profesion=string];&body=Actualizar paciente";
        showPaciente = "subject=SHOW[pacientes:id=number];&body=Mostrar paciente";
        deletePaciente = "subject=DELETE[pacientes:id=number];&body=Eliminar paciente";
    }

    //ADMIN
    public String listarAdminHTML(String title, String[][] dato) {
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
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + listAdmin + "\" class=\"button\">Listar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + insertAdmin + "\" class=\"button\">Insertar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + showAdmin + "\" class=\"button\">Mostrar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + deleteAdmin + "\" class=\"button\">Eliminar</a>\n"
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

    public String listarTrabajadorHTML(String title, String[][] dato) {
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
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + listTrabajador + "\" class=\"button\">Listar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + insertTrabajador + "\" class=\"button\">Insertar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + showTrabajador + "\" class=\"button\">Mostrar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + deleteTrabajador + "\" class=\"button\">Eliminar</a>\n"
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

    public String listarPacienteHTML(String title, String[][] dato) {
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
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + listPaciente + "\" class=\"button\">Listar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + insertPaciente + "\" class=\"button\">Insertar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + showPaciente + "\" class=\"button\">Mostrar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + deletePaciente + "\" class=\"button\">Eliminar</a>\n"
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

    public String listarUserHTML(String title, String[][] dato) {
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
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + listUser + "\" class=\"button\">Listar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + listAtrUser + "\" class=\"button\">Listar por atributo</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + insertUser + "\" class=\"button\">Insertar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + updateUser + "\" class=\"button\">Actualizar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + showUser + "\" class=\"button\">Mostrar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + deleteUser + "\" class=\"button\">Eliminar</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + listAdmin + "\" class=\"button\">Listar Admin</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + listTrabajador + "\" class=\"button\">Listar Trabajador</a>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + listPaciente + "\" class=\"button\">Listar Pacientes</a>\n"
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
