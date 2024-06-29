/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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
public class SistemaFichasViaEmail {

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        //ClientPOP3 clientePOP3= new ClientPOP3();
        //clientePOP3.iniciar();
        CredentialDTO credentialDTO=new CredentialDTO();
        //SMTP G24
        ClientPOP3 clientePOP3= new ClientPOP3(credentialDTO);
        clientePOP3.iniciar();
        //clientSMTP clientSMTP = new ClientSMTP();
        //clientSMTP.enviarCorreo("grupo24sa@tecnoweb.org.bo", "Probando envio de correo", "LIST[users];");
    }
}
