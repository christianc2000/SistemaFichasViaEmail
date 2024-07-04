/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.handler;

import com.grupo07sa.client.ClientSMTP;
import com.grupo07sa.dato.CommandDTO;
import com.grupo07sa.dato.CredentialDTO;
import com.grupo07sa.dato.MessageDTO;
import com.grupo07sa.dato.ResponseDTO;
import com.grupo07sa.dato.User.dto.UserDTO;
import com.grupo07sa.help.Lexer;
import com.grupo07sa.service.PagoService;
import com.grupo07sa.service.UserService;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author USER
 */
public class HanddlerMessage extends Thread {

    private MessageDTO mensajeEmisor;
    private CredentialDTO credencial;
    private UserService userService;
    private PagoService pagoService;
    private ResponseDTO response;
    private ClientSMTP clientSMTP;

    public HanddlerMessage(CredentialDTO credencial, MessageDTO mensajeEmisor) {
        this.mensajeEmisor = mensajeEmisor;
        this.credencial = credencial;
        this.userService = new UserService();
        this.pagoService = new PagoService();
        this.clientSMTP = new ClientSMTP(credencial);
    }

    public String startHTML() {
        return "<style>\n"
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
                + "        margin: 5px;\n"
                + "        padding: 30px 60px;\n"
                + "        font-size: 20px;\n"
                + "        text-align: center;\n"
                + "        cursor: pointer;\n"
                + "        outline: none;\n"
                + "        color: #ffffff;\n"
                + "        background-color: #4682B4;\n"
                + "        border: none;\n"
                + "        border-radius: 15px;\n"
                + "\n"
                + "        text-decoration: none;\n"
                + "    }\n"
                + "    .button:hover {\n"
                + "        background-color: #0080FF\n"
                + "    }\n"
                + "\n"
                + "</style>\n"
                + "<div class=\"container-title\">\n"
                + "    <h1>Consultorio San Santiago</h1>\n"
                + "</div>\n"
                + "\n"
                + "<div class=\"container\">\n"
                + "\n"
                + "    <a href=\"mailto:grupo07sa@tecnoweb.org.bo?subject=LIST[users];&body=LIST[users];\" class=\"button\">Gestionar Usuario</a>\n"
                + "    <a href=\"mailto:grupo07sa@tecnoweb.org.bo?subject=LIST[servicios];&body=LIST[servicios];\" class=\"button\">Gestionar Servicios</a>\n"
                + "    <a href=\"mailto:grupo07sa@tecnoweb.org.bo?subject=LIST[citas];&body=LIST[citas];\" class=\"button\">Gestionar Citas</a>\n"
                + "    <a href=\"mailto:grupo07sa@tecnoweb.org.bo?subject=LIST[turnos];&body=LIST[turnos];\" class=\"button\">Gestionar Turnos</a>\n"
                + "</div>\n"
                + "\n"
                + "<!-- Aquí puedes poner el contenido principal de tu página -->\n"
                + "\n"
                + "<div class=\"container\">\n"
                + "    <a href=\"mailto:grupo07sa@tecnoweb.org.bo?subject=LIST[ordens];&body=LIST[ordens];\" class=\"button\">Gestionar Pagos</a>\n"
                + "    <a href=\"mailto:grupo07sa@tecnoweb.org.bo?subject=Listar&body=LIST[consultas];\" class=\"button\">Gestionar Consultas</a>\n"
                + "    <a href=\"mailto:grupo07sa@tecnoweb.org.bo?subject=Listar&body=LIST[historials];\" class=\"button\">Gestionar Historial</a>\n"
                + "    <a href=\"mailto:grupo07sa@tecnoweb.org.bo?subject=Listar&body=LIST[atencions];\" class=\"button\">Gestionar Reportes y Estadísticas</a>\n"
                + "</div>";
    }

    public String errorHTML(String error) {
        return "<style>\n"
                + "    .container-title {\n"
                + "        display:flex;\n"
                + "        justify-content: center;\n"
                + "        align-content: center;\n"
                + "        color: red;\n"
                + "    }\n"
                + "    .error {\n"
                + "        width: 100%;\n"
                + "        margin:10px;\n"
                + "        padding: 20px;\n"
                + "        border: 1px solid red;\n"
                + "        color: red;\n"
                + "        font-weight: bold;\n"
                + "        text-align: center;\n"
                + "        margin-top: 20px;\n"
                + "    }\n"
                + "    .container-subtitle {\n"
                + "        margin: 10px;\n"
                + "        font-weight: 400px;\n"
                + "        color: red;\n"
                + "    }\n"
                + "</style>\n"
                + "<div class=\"container-title\">\n"
                + "    <h1>Error</h1>\n"
                + "</div>\n"
                + "<div class=\"error\">\n"
                + "    " + error + "\n"
                + "</div>\n"
                + "<p class=\"container-subtitle\">Puede ver la ayuda de los comandos colocando <strong>HELP[];</strong> o puede iniciar con las opciones usando el comando <strong>START[];</strong></p>\n"
                + "";
    }

    public String helpHTML() {
        return "<style>\n"
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
                + "\n"
                + "</style>\n"
                + "<div class=\"container-title\">\n"
                + "    <h1>Comandos de ayuda - Consultorio San Santiago</h1>\n"
                + "</div>\n"
                + "<div class=\"\">\n"
                + "    <table>\n"
                + "        <tr>\n"
                + "            <th style=\"width: 100px\">Tabla</th>\n"
                + "            <th>\n"
                + "                Comando\n"
                + "            </th> \n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Gestionar usuario</td>\n"
                + "            <td>\n"
                + "                <p>LIST[users];</p>\n"
                + "                <p>LIST[users:id,ci,name,lastname,fecha_nacimiento,foto,direccion,gender,celular,email,password,nit,razon_social];</p>\n"
                + "                <p>INSERT[users:ci=string,name=string,lastname=string,fecha_nacimiento=date,foto=string,direccion=string,gender=string,celular=string,email=string,password=string,nit=string,razon_social=string];</p>\n"
                + "                <p>UPDATE[users:id=number,ci=string,name=string,lastname=string,fecha_nacimiento=date,foto=string,direccion=string,gender=string,celular=string,email=string,password=string,nit=string,razon_social=string];</p>\n"
                + "                <p>SHOW[users:id=number];</p>\n"
                + "                <p>DELETE[users:id=number];</p>\n"
                + "            </td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Dia</td>\n"
                + "            <td>Comando 1</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Horario</td>\n"
                + "            <td>Comando 2</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Turno</td>\n"
                + "            <td>Comando 1</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Servicio</td>\n"
                + "            <td>Comando 2</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Sala</td>\n"
                + "            <td>Comando 1</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Atencion</td>\n"
                + "            <td>Comando 2</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Cita</td>\n"
                + "            <td>Comando 1</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Ficha</td>\n"
                + "            <td>Comando 2</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Forma Pago</td>\n"
                + "            <td>Comando 1</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Orden</td>\n"
                + "            <td>Comando 2</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Historial</td>\n"
                + "            <td>Comando 1</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Dato Medico</td>\n"
                + "            <td>Comando 2</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Consulta</td>\n"
                + "            <td>Comando 1</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Examen Fisico</td>\n"
                + "            <td>Comando 2</td>\n"
                + "\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Tratamiento</td>\n"
                + "            <td>Comando 1</td>\n"
                + "\n"
                + "        </tr>\n"
                + "    </table>\n"
                + "</div>";
    }

    public String generarPagoHTML(String title, String[][] dato, String insert) {
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
                + "    .button:hover {\n"
                + "        background-color: #0080FF\n"
                + "    }\n"
                + "</style>\n"
                + "<div class=\"container-title\">\n"
                + "    <h1>" + title + "</h1>\n"
                + "</div>\n"
                + "<a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + insert + "\" class=\"button\">Insertar</a>\n"
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
                if (j == 11) {
                    listar = listar + "<td>\n<img src='data:image/png;base64," + dato[i][j] + "' alt='Imagen base64'></td>\n";
                } else {
                    listar = listar + "<td>\n" + dato[i][j] + "</td>\n";
                }
            }
            listar = listar + "</tr>\n";
        }
        listar = listar + "</tbody>\n";
        listar = listar + "    </table>\n"
                + "</div>";

        return listar;
    }

    public String listarHTML(String title, String[][] dato, String list, String listAtr, String insert, String update, String show, String delete) {
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

    @Override
    public void run() {
        Lexer sintaxis = new Lexer();
        CommandDTO comando = sintaxis.analizarMensaje(this.mensajeEmisor.getAsunto());
        String tabla = "";
        String htmlContent;
        //users
        String listUser = "subject=LIST[users];&body=Listar usuario";
        String listAtrUser = "subject=LIST[users:id,ci,name,lastname,fecha_nacimiento,foto,direccion,gender,celular,email,password,nit,razon_social];&body=Listar usuario por atributo";
        String insertUser = "subject=INSERT[users:ci=string,name=string,lastname=string,fecha_nacimiento=date,foto=string,direccion=string,gender=string,celular=string,email=string,password=string,nit=string,razon_social=string];&body=Insertar usuario";
        String updateUser = "subject=UPDATE[users:ci=string,name=string,lastname=string,fecha_nacimiento=date,foto=string,direccion=string,gender=string,celular=string,email=string,password=string,nit=string,razon_social=string];&body=Actualizar usuario";
        String showUser = "subject=SHOW[users:id=number];&body=Mostrar usuario";
        String deleteUser = "subject=DELETE[users:id=number];&body=Eliminar usuario";
        //pagos
        String insertPago = "subject=INSERT[pagos:nit=string,razon_social=string,email=string,celular=string,trabajador=string,servicio=string,horario=string,tipo_servicio=string,costo=float,forma_pago=string,paciente_id=number,ficha_id=number];&body=Insertar pago por qr";
        switch (comando.getCommand()) {
            case "START"://Empezar, mandar la vista de los casos de uso del sistema
                System.out.println("Mostrar opciones de inicio");
                this.clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), "Empezando...", startHTML());
                break;
            case "HELP"://Enviar la vista con los comandos
                System.out.println("Mostrar help");
                this.clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), mensajeEmisor.getAsunto(), helpHTML());
                break;
            case "LIST":
                tabla = comando.getNameTable();
                if (comando.getParameters().length == 1) {//Si es un list sin atributos, realizar un all de la tabla
                    switch (tabla) {//Verificamos que tabla vamos a Listar de la base de datos
                        case "users":
                            response = userService.all();
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de usuarios
                                htmlContent = listarHTML(response.getTitle(), response.getData(), listUser, listAtrUser, insertUser, updateUser, showUser, deleteUser);
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                            } else {//Enviar una vista de error
                                htmlContent = errorHTML(response.getError());
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                            }
                            break;

                        default:
                            htmlContent = errorHTML("Error la tabla '" + tabla + "' no existe");
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), "Error", htmlContent);
                    }
                } else {//Si es un list con atributos, realizar un select con los atributos 
                    switch (tabla) {//Verificamos que tabla vamos a Listar de la base de datos
                        case "users":
                            System.out.println("getAttributesName: " + comando.getAttributesName().length);
                            response = userService.listAtr(comando.getAttributesName(), comando.getAttributesNameToString());
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de usuarios
                                htmlContent = listarHTML(response.getTitle(), response.getData(), listUser, listAtrUser, insertUser, updateUser, showUser, deleteUser);
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                            } else {//Enviar una vista de error
                                htmlContent = errorHTML(response.getError());
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                            }
                            break;
                        default:
                            htmlContent = errorHTML("Error la tabla '" + tabla + "' no existe");
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), "Error", htmlContent);
                    }
                }
                break;
            case "INSERT":
                tabla = comando.getNameTable();
                switch (tabla) {
                    case "users":
                        response = userService.create(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listUser, listAtrUser, insertUser, updateUser, showUser, deleteUser);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;
                    case "pagos":
                        response = pagoService.create(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = generarPagoHTML(response.getTitle(), response.getData(), insertPago);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;
                    default:
                        htmlContent = errorHTML("Error la tabla '" + tabla + "' no existe");
                        clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), "Error", htmlContent);
                }
                break;
            case "UPDATE":
                tabla = comando.getNameTable();
                switch (tabla) {
                    case "users":
                        response = userService.update(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listUser, listAtrUser, insertUser, updateUser, showUser, deleteUser);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;
                    default:
                        htmlContent = errorHTML("Error la tabla '" + tabla + "' no existe");
                        clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), "Error", htmlContent);
                }
                break;
            case "SHOW":
                tabla = comando.getNameTable();
                switch (tabla) {
                    case "users":
                        response = userService.find(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listUser, listAtrUser, insertUser, updateUser, showUser, deleteUser);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;
                    default:
                        htmlContent = errorHTML("Error la tabla '" + tabla + "' no existe");
                        clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), "Error", htmlContent);
                }
                break;
            case "DELETE":
                tabla = comando.getNameTable();
                switch (tabla) {
                    case "users":
                        response = userService.delete(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listUser, listAtrUser, insertUser, updateUser, showUser, deleteUser);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;
                    default:
                        htmlContent = errorHTML("Error la tabla '" + tabla + "' no existe");
                        clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), "Error", htmlContent);
                }
                break;
            default:
                htmlContent = errorHTML("Error sintaxis no válida");
                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), "Error", htmlContent);
        }
        System.out.println("FIN DEL HILO");
    }
}
