/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa;

import com.grupo07sa.client.ClientPOP3;
import com.grupo07sa.client.ClientSMTP;
import com.grupo07sa.dato.CredentialDTO;
import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author USER
 */
public class PruebaEnvio {

    public static void main(String[] args) {

        CredentialDTO credencial = new CredentialDTO();
        credencial.setMail_email("grupo24sa@tecnoweb.org.bo");
        credencial.setMail_user("grupo24sa");
        credencial.setMail_pass("grup024grup024");
        ClientSMTP clientSMTP = new ClientSMTP(credencial);
        clientSMTP.enviarCorreo("grupo07sa@tecnoweb.org.bo", "INSERT[users:ci=1729121,name=string,lastname=string,fecha_nacimiento=1999-02-02,foto=string,direccion=string,gender=M,celular=77678654,email=prueba@gmail.com,password=string,nit=string,razon_social=string];", "Insertar usuarios...");
    }
}
