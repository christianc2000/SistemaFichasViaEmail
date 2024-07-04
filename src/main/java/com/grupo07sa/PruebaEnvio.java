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
        clientSMTP.enviarCorreo("grupo07sa@tecnoweb.org.bo", "INSERT[pagos:nit=1578217,razon_social=Christian Mamani,email=christian@gmail.com,celular=77382831,trabajador=Dr Ventura,servicio=Ecografia Abdominal,horario=08:30-12:00,tipo_servicio=Medico,costo=0.01,forma_pago=QR,paciente_id=7,ficha_id=1];", "Insertar usuarios...");
    }
}
