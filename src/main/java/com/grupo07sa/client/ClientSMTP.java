/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.client;

import com.grupo07sa.dato.CredentialDTO;
import com.grupo07sa.dato.ResponseDTO;
import com.grupo07sa.dato.User.UserRepositoryImpl;
import com.grupo07sa.service.UserService;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author USER
 */
public class ClientSMTP {

    private CredentialDTO credencial;

    public ClientSMTP(CredentialDTO credencial) {
        this.credencial = credencial;
    }
    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", credencial.getMail_host()); // Cambia esto al servidor SMTP que desees usar
        props.put("mail.smtp.port", credencial.getPort_smtp()); // Cambia esto al puerto adecuado

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(credencial.getMail_user(), credencial.getMail_pass()); // Cambia esto a tus credenciales
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(credencial.getMail_email())); // Tu dirección de correo
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setContent(cuerpo, "text/html; charset=utf-8"); // Establecer el contenido como HTML

            Transport.send(message);
            System.out.println("Correo enviado con éxito.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
