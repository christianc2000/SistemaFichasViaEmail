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

import com.grupo07sa.help.Lexer;
import com.grupo07sa.service.CitaService;
import com.grupo07sa.service.ConsultaService;
import com.grupo07sa.service.HistorialMedicoService;
import com.grupo07sa.service.UserService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtils;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;

/**
 *
 * @author USER
 */
public class HanddlerMessage extends Thread {

    private MessageDTO mensajeEmisor;
    private CredentialDTO credencial;
    private UserService userService;
    private CitaService citaService;
    private HistorialMedicoService historialService;
    private ConsultaService consultaService;
    private ResponseDTO response;
    private ClientSMTP clientSMTP;

    public HanddlerMessage(CredentialDTO credencial, MessageDTO mensajeEmisor) {
        this.mensajeEmisor = mensajeEmisor;
        this.credencial = credencial;
        this.userService = new UserService();
        this.citaService = new CitaService();
        this.historialService = new HistorialMedicoService();
        this.consultaService = new ConsultaService();
        this.clientSMTP = new ClientSMTP(credencial);
    }

    public String startHTML() {
        return """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Consultorio San Santiago</title>
                </head>
                <body>
                    <div style="text-align: center; margin: 20px;">
                        <h1>Consultorio San Santiago</h1>
                    </div>
                    <div style="text-align: center; padding: 20px;">
                        <a href="mailto:grupo07sa@tecnoweb.org.bo?subject=Listar&body=LIST[users];" style="display: inline-block; margin: 5px; padding: 15px 30px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 5px; text-decoration: none;">Gestionar Usuario</a>
                        <a href="mailto:grupo07sa@tecnoweb.org.bo?subject=Listar&body=LIST[servicios];" style="display: inline-block; margin: 5px; padding: 15px 30px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 5px; text-decoration: none;">Gestionar Servicios</a>
                        <a href="mailto:grupo07sa@tecnoweb.org.bo?subject=Listar&body=LIST[citas];" style="display: inline-block; margin: 5px; padding: 15px 30px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 5px; text-decoration: none;">Gestionar Citas</a>
                        <a href="mailto:grupo07sa@tecnoweb.org.bo?subject=Listar&body=LIST[turnos];" style="display: inline-block; margin: 5px; padding: 15px 30px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 5px; text-decoration: none;">Gestionar Turnos</a>
                    </div>
                    <div style="text-align: center; padding: 20px;">
                        <a href="mailto:grupo07sa@tecnoweb.org.bo?subject=Listar&body=LIST[ordens];" style="display: inline-block; margin: 5px; padding: 15px 30px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 5px; text-decoration: none;">Gestionar Pagos</a>
                        <a href="mailto:grupo07sa@tecnoweb.org.bo?subject=Listar&body=LIST[consultas];" style="display: inline-block; margin: 5px; padding: 15px 30px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 5px; text-decoration: none;">Gestionar Consultas</a>
                        <a href="mailto:grupo07sa@tecnoweb.org.bo?subject=Listar&body=LIST[historial_medicos];" style="display: inline-block; margin: 5px; padding: 15px 30px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 5px; text-decoration: none;">Gestionar Historial</a>
                        <a href="mailto:grupo07sa@tecnoweb.org.bo?subject=ATENTIONS[];&body= REPORTES Y ESTADISTICAS;" style="display: inline-block; margin: 5px; padding: 15px 30px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 5px; text-decoration: none;">Gestionar Reportes y Estad\u00edsticas</a>
                    </div>
                </body>
                </html>""";
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
        if (dato != null) {
            for (int j = 0; j < dato[0].length; j++) {
                listar = listar + "<th>\n" + dato[0][j] + "</th>\n";
            }
        }
        listar = listar + "</tr>\n";
        listar = listar + "</thead>\n";
        listar = listar + "<tbody>\n";
        if (dato != null) {
            for (int i = 1; i < dato.length; i++) {
                listar = listar + "<tr>\n";
                for (int j = 0; j < dato[i].length; j++) {
                    listar = listar + "<td>\n" + dato[i][j] + "</td>\n";
                }
                listar = listar + "</tr>\n";
            }
        }
        listar = listar + "</tbody>\n";
        listar = listar + "    </table>\n"
                + "</div>";

        return listar;
    }
    


    public String HTMLReportesEstadistica(String servicios, String medicos, String pagos, String[][] dato) {
        String base64Image = "";
        if (dato != null) {
            // Crear el dataset
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            
            // Crear un HashMap para almacenar los totales por mes
            HashMap<String, Integer> totalPorMes = new HashMap<>();

            // Llenar el HashMap con los datos existentes
            for (int i = 1; i < dato[0].length; i++) {
                totalPorMes.put(dato[i][0], Integer.valueOf(dato[i][1]));
            }

            // Iterar sobre todos los meses del año (de 1 a 12)
            for (int i = 1; i <= 12; i++) {
                String mes = Integer.toString(i);
                int total = totalPorMes.getOrDefault(mes, 0); // Obtener el total o 0 si no está presente

                // Aquí puedes usar 'total' como necesites, por ejemplo, para agregarlo a un dataset
                //System.out.println("Mes: " + mes + ", Total: " + total);
                dataset.addValue(total, "Ingresos", mes);

            }

            // Crear el gráfico
            JFreeChart barChart = ChartFactory.createBarChart(
                    "Ingresos por Mes",
                    "Mes",
                    "Ingresos",
                    dataset
            );

            // Guardar el gráfico como imagen en un ByteArrayOutputStream
            int width = 640;
            int height = 480;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                ChartUtils.writeChartAsPNG(baos, barChart, width, height);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Codificar la imagen en Base64
            base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());
            //System.out.println("data:image/png;base64," + base64Image);
        }
        // Crear el contenido del correo
        String listar
                = "<!DOCTYPE html>\n"
                + "<html lang=\"es\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Consultorio San Santiago</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div style=\"text-align: center;\">\n"
                + "        <h1>Consultorio San Santiago</h1>\n"
                + "        <h3>Reportes y Estadisticas </h3>\n"
                + "    </div>\n"
                + "    <div style=\"text-align: center; padding: 20px;\">\n"
                + "        <a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + servicios + "\" style=\"display: inline-block; margin: 10px; padding: 10px 20px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 15px; text-decoration: none;\">SERVICIOS</a>\n"
                + "        <a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + medicos + "\" style=\"display: inline-block; margin: 10px; padding: 10px 20px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 15px; text-decoration: none;\">MEDICOS</a>\n"
                + "        <a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + pagos + "\" style=\"display: inline-block; margin: 10px; padding: 10px 20px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 15px; text-decoration: none;\">INGRESOS</a>\n"
                + "    </div>\n";
        if (dato != null) {
            listar = listar
                    + "    <div style=\"text-align: center; margin-top: 10px;\">\n"
                    + "        <img src='data:image/jpeg;base64," + base64Image + "' alt='Gráfico de Ejemplo' style='width: 100%; max-width: 600px; height: auto;'>\n"
                    + "    </div>\n";
        }

        listar = listar + "</body>\n"
                + "</html>";

        return listar;
    }

    public String HTMLEstadisticaMedicos(String servicios, String medicos, String pagos, String[][] dato) {
        String base64Image = "";
        if (dato != null) {
            // Crear el dataset
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            
            for (int i = 1; i < dato[0].length; i++) {
                dataset.addValue(Integer.parseInt(dato[i][1]), "Especialidad", dato[i][0]);
            }

            // Crear el gráfico
            JFreeChart barChart = ChartFactory.createBarChart(
                    "Especialidades mas Solicitadas",
                    "Especialidad",
                    "Solicitudes",
                    dataset
            );

            // Guardar el gráfico como imagen en un ByteArrayOutputStream
            int width = 640;
            int height = 480;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                ChartUtils.writeChartAsPNG(baos, barChart, width, height);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Codificar la imagen en Base64
            base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());
            //System.out.println("data:image/png;base64," + base64Image);
        }
        // Crear el contenido del correo
        String listar
                = "<!DOCTYPE html>\n"
                + "<html lang=\"es\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Consultorio San Santiago</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div style=\"text-align: center;\">\n"
                + "        <h1>Consultorio San Santiago</h1>\n"
                + "        <h3>Reportes y Estadisticas </h3>\n"
                + "    </div>\n"
                + "    <div style=\"text-align: center; padding: 20px;\">\n"
                + "        <a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + servicios + "\" style=\"display: inline-block; margin: 10px; padding: 10px 20px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 15px; text-decoration: none;\">SERVICIOS</a>\n"
                + "        <a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + medicos + "\" style=\"display: inline-block; margin: 10px; padding: 10px 20px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 15px; text-decoration: none;\">MEDICOS</a>\n"
                + "        <a href=\"mailto:grupo07sa@tecnoweb.org.bo?" + pagos + "\" style=\"display: inline-block; margin: 10px; padding: 10px 20px; font-size: 16px; text-align: center; cursor: pointer; color: #ffffff; background-color: #4682B4; border: none; border-radius: 15px; text-decoration: none;\">INGRESOS</a>\n"
                + "    </div>\n";
        if (dato != null) {
            listar = listar
                    + "    <div style=\"text-align: center; margin-top: 10px;\">\n"
                    + "        <img src='data:image/jpeg;base64," + base64Image + "' alt='Gráfico de Ejemplo' style='width: 100%; max-width: 600px; height: auto;'>\n"
                    + "    </div>\n";
        }

        listar = listar + "</body>\n"
                + "</html>";

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

        //citas
        String listCita = "subject=LIST[citas];&body=Listar citas";
        String listAtrCita = "subject=LIST[citas:id,fecha_hora,motivo,paciente_id,trabajador_id,sala_id,pago_id];&body=Listar cita por Atributo";
        String insertCita = "subject=INSERT[citas:fecha_hora=date,motivo=string,paciente_id=int,trabajador_id=int,sala_id=int,pago_id=int];&body=Insertar cita";
        String updateCita = "subject=UPDATE[citas:id=int,fecha_hora=date,motivo=string,paciente_id=int,trabajador_id=int,sala_id=int,pago_id=int];&body=Actualizar cita";
        String showCita = "subject=SHOW[citas:id=number];&body=Mostrar cita";
        String deleteCita = "subject=DELETE[citas:id=number];&body=Eliminar cita";

        //historial
        String listHistorial = "subject=LIST[historial_medicos];&body=Listar Historial Medico";
        String listAtrHistorial = "subject=LIST[historial_medicos:id,fecha_creacion,observaciones_generales,paciente_id];&body=Listar Historial Medico por Atributo";
        String insertHistorial = "subject=INSERT[historial_medicos:fecha_creacion=date,observaciones_generales=string,paciente_id=number];&body=Insertar Historial Medico";
        String updateHistorial = "subject=UPDATE[historial_medicos:id=int,fecha_creacion=date,observaciones_generales=string,paciente_id=number];&body=Actualizar Historial Medico";
        String showHistorial = "subject=SHOW[historial_medicos:id=number];&body=Mostrar Historial Medico";
        String deleteHistorial = "subject=DELETE[historial_medicos:id=number];&body=Eliminar Historial Medico";

        //consulta
        String listConsulta = "subject=LIST[consultas];&body=Listar Consultas";
        String listAtrConsulta = "subject=LIST[consultas:id,diagnostico,tratamiento,observaciones,cita_id,historial_medico_id];&body=Listar Consulta por Atributo";
        String insertConsulta = "subject=INSERT[consultas:diagnostico=string,tratamiento=string,observaciones=string,cita_id=int,historial_medico_id=int];&body=Insertar Consulta";
        String updateConsulta = "subject=UPDATE[consultas:id=int,diagnostico=string,tratamiento=string,observaciones=string,cita_id=int,historial_medico_id=int];&body=Actualizar Consulta";
        String showConsulta = "subject=SHOW[consultas:id=number];&body=Mostrar Consulta";
        String deleteConsulta = "subject=DELETE[consultas:id=number];&body=Eliminar Consulta";

        //REPORTES Y ESTDISTICAS
        String ingresos = "subject=LIST[pagosreporte];&body=Reporte Pagos";
        String medicos = "subject=LIST[trabajadoresreporte];&body=Reporte Trabajadores";
        String servicios = "subject=LIST[serviciosreporte];&body=Reporte Servicios";
        System.out.println("Comando: " + comando.getCommand());
        System.out.println(comando.getParameters());

        switch (comando.getCommand()) {
            case "START"://Empezar, mandar la vista de los casos de uso del sistema
                System.out.println("Mostrar opciones de inicio");
                this.clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), "Empezando...", startHTML());
                break;
            case "HELP"://Enviar la vista con los comandos
                System.out.println("Mostrar help");
                this.clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), mensajeEmisor.getAsunto(), helpHTML());
                break;
            case "ATENTIONS"://Enviar la vista con los comandos
                System.out.println("Mostrar atentions");
                this.clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), mensajeEmisor.getAsunto(), HTMLReportesEstadistica(servicios, medicos, ingresos, null));
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
                        case "citas":
                            response = citaService.all();
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de citas
                                htmlContent = listarHTML(response.getTitle(), response.getData(), listCita, listAtrCita, insertCita, updateCita, showCita, deleteCita);
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                            } else {//Enviar una vista de error
                                htmlContent = errorHTML(response.getError());
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                            }
                            break;
                        case "historial_medicos":
                            response = historialService.all();
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de citas
                                htmlContent = listarHTML(response.getTitle(), response.getData(), listHistorial, listAtrHistorial, insertHistorial, updateHistorial, showHistorial, deleteHistorial);
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                            } else {//Enviar una vista de error
                                htmlContent = errorHTML(response.getError());
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                            }
                            break;

                        case "consultas":
                            response = consultaService.all();
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de citas
                                htmlContent = listarHTML(response.getTitle(), response.getData(), listConsulta, listAtrConsulta, insertConsulta, updateConsulta, showConsulta, deleteConsulta);
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                            } else {//Enviar una vista de error
                                htmlContent = errorHTML(response.getError());
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                            }
                            break;

                        case "pagosreporte":
                            response = citaService.pagos(null, null);
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de citas
                                htmlContent = HTMLReportesEstadistica(servicios, medicos, ingresos, response.getData());
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                            } else {//Enviar una vista de error
                                htmlContent = errorHTML(response.getError());
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                            }
                            break;
                            
                         case "trabajadoresreporte":
                            response = citaService.medicos(null, null);
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de citas
                                htmlContent = listarHTML(response.getTitle(), response.getData(), listHistorial, listAtrHistorial, insertHistorial, updateHistorial, showHistorial, deleteHistorial);
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
                        case "citas":
                            System.out.println("getAttributesName: " + comando.getAttributesName().length);
                            response = citaService.listAtr(comando.getAttributesName(), comando.getAttributesNameToString());
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de citas
                                htmlContent = listarHTML(response.getTitle(), response.getData(), listCita, listAtrCita, insertCita, updateUser, showCita, deleteCita);
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                            } else {//Enviar una vista de error
                                htmlContent = errorHTML(response.getError());
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                            }
                            break;

                        case "historial_medicos":
                            System.out.println("getAttributesName: " + comando.getAttributesName().length);
                            response = historialService.listAtr(comando.getAttributesName(), comando.getAttributesNameToString());
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de citas
                                htmlContent = listarHTML(response.getTitle(), response.getData(), listHistorial, listAtrHistorial, insertHistorial, updateHistorial, showHistorial, deleteHistorial);
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                            } else {//Enviar una vista de error
                                htmlContent = errorHTML(response.getError());
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                            }
                            break;

                        case "consultas":
                            System.out.println("getAttributesName: " + comando.getAttributesName().length);
                            response = consultaService.listAtr(comando.getAttributesName(), comando.getAttributesNameToString());
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de citas
                                htmlContent = listarHTML(response.getTitle(), response.getData(), listConsulta, listAtrConsulta, insertConsulta, updateConsulta, showConsulta, deleteConsulta);
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

                    case "citas":
                        response = citaService.create(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listCita, listAtrCita, insertCita, updateUser, showCita, deleteCita);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;

                    case "historial_medicos":
                        response = historialService.create(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listHistorial, listAtrHistorial, insertHistorial, updateHistorial, showHistorial, deleteHistorial);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;

                    case "consultas":
                        response = consultaService.create(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listConsulta, listAtrConsulta, insertConsulta, updateConsulta, showConsulta, deleteConsulta);
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

                    case "citas":
                        response = citaService.update(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listCita, listAtrCita, insertCita, updateUser, showCita, deleteCita);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;

                    case "historial_medicos":
                        response = historialService.update(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listHistorial, listAtrHistorial, insertHistorial, updateHistorial, showHistorial, deleteHistorial);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;

                    case "consultas":
                        System.out.println("Cuerpo consulta update: " + comando.getAttributesValueToString());
                        response = consultaService.update(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listConsulta, listAtrConsulta, insertConsulta, updateConsulta, showConsulta, deleteConsulta);
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
                    case "citas":
                        response = citaService.find(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listCita, listAtrCita, insertCita, updateUser, showCita, deleteCita);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;
                    case "historial_medicos":
                        response = historialService.find(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listHistorial, listAtrHistorial, insertHistorial, updateHistorial, showHistorial, deleteHistorial);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;

                    case "consultas":
                        response = consultaService.find(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listConsulta, listAtrConsulta, insertConsulta, updateConsulta, showConsulta, deleteConsulta);
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
                    case "citas":
                        response = citaService.delete(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listCita, listAtrCita, insertCita, updateUser, showCita, deleteCita);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;

                    case "historial_medicos":
                        response = historialService.delete(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listHistorial, listAtrHistorial, insertHistorial, updateHistorial, showHistorial, deleteHistorial);
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);//ENVIANDO RESPUESTA
                        } else {
                            htmlContent = errorHTML(response.getError());
                            clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), htmlContent);
                        }
                        break;

                    case "consultas":
                        response = consultaService.delete(comando.getAttributesValue());
                        System.out.println(response.getTitle());
                        if (response.getError() == null) {
                            htmlContent = listarHTML(response.getTitle(), response.getData(), listConsulta, listAtrConsulta, insertConsulta, updateConsulta, showConsulta, deleteConsulta);
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
