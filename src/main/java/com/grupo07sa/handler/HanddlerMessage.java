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
import com.grupo07sa.service.UserService;
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
    private ResponseDTO response;

    public HanddlerMessage(CredentialDTO credencial, MessageDTO mensajeEmisor) {
        this.mensajeEmisor = mensajeEmisor;
        this.credencial = credencial;
        this.userService = new UserService();
    }

    @Override
    public void run() {
        Lexer sintaxis = new Lexer();
        CommandDTO comando = sintaxis.analizarMensaje(this.mensajeEmisor.getAsunto());
        switch (comando.getCommand()) {
            case "START"://Empezar, mandar la vista de los casos de uso del sistema

                break;
            case "HELP"://Enviar la vista con los comandos

                break;
            case "LIST":
                String tabla = comando.getNameTable();
                if (comando.getParameters().length == 1) {//Si es un list sin atributos, realizar un all de la tabla
                    switch (tabla) {//Verificamos que tabla vamos a Listar de la base de datos
                        case "users":
                            response = userService.all();
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de usuarios
                                String registro = "";
                                for (int i = 0; i < response.getData().length; i++) {
                                    for (int j = 0; j < response.getData()[i].length; j++) {
                                        registro = registro + "\t|\t" + response.getData()[i][j];
                                    }
                                    registro = registro + "\n";
                                }
                                System.out.println("\t" + response.getTitle() + "\n" + registro);
                                //ENVIAR LA RESPUESTA
                                /*String htmlContent = new String(Files.readAllBytes(Paths.get("C:\\Users\\Christian\\Documents\\NetBeansProjects\\SistemaFichasViaEmail\\src\\main\\java\\com\\grupo07sa\\presentacion\\list.html")));
                                    String insert = "INSERT[users:name=string,lastname=string,birth_date=date,genero=string,celular=number,tipo=string,residencia_actual=string,email=string,password=string,url_foto=string,sueldo=number,formacion=string,nit=string,razon_social=string];";
                                    String update = "UPDATE[users:id=number,name=string,lastname=string,fecha_nacimiento=date,nit=string,razon_social=string]; COLOQUE TODOS LOS CAMPOS QUE REQUIERA ACTUALIZAR";
                                    String show = "SHOW[users:id=number];";
                                    String delete = "DELETE[users:id=number];";
                                    htmlContent = succesView(htmlContent, response.getTitle(), response.getData(), insert, update, show, delete);
                                 */
                                ClientSMTP clientSMTP = new ClientSMTP(credencial);
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), registro);//ENVIANDO RESPUESTA
                            } else {//Enviar una vista de error
                                System.out.println(response.getError());
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                } else {//Si es un list con atributos, realizar un select con los atributos 
                    switch (tabla) {//Verificamos que tabla vamos a Listar de la base de datos
                        case "users":
                            System.out.println("getAttributesName: " + comando.getAttributesName().length);
                            response = userService.listAtr(comando.getAttributesName(), comando.getAttributesNameToString());
                            if (response.getError() == null) {//Enviar por SMTP una vista con la lista de usuarios
                                System.out.println("CREANDO RESPUESTA...");
                                String registro = "";
                                for (int i = 0; i < response.getData().length; i++) {
                                    for (int j = 0; j < response.getData()[i].length; j++) {
                                        registro = registro + "\t|\t" + response.getData()[i][j];
                                    }
                                    registro = registro + "\n";
                                }
                                System.out.println("\t" + response.getTitle() + "\n" + registro);
                                //ENVIAR LA RESPUESTA
                                ClientSMTP clientSMTP = new ClientSMTP(credencial);
                                clientSMTP.enviarCorreo(mensajeEmisor.getCorreo(), response.getTitle(), registro);//ENVIANDO RESPUESTA
                            } else {//Enviar una vista de error
                                System.out.println("ERROR*********************" + response.getError());
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                }
                break;
            case "INSERT":

                break;
            case "UPDATE":

                break;
            case "SHOW":

                break;
            case "DELETE":

                break;
            default:
                throw new AssertionError();
        }
        System.out.println("FIN DEL HILO");
    }
}
