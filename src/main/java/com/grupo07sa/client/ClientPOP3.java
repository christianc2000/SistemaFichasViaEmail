/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.client;

import com.grupo07sa.dato.CredentialDTO;
import com.grupo07sa.dato.MessageDTO;
import com.grupo07sa.handler.HanddlerMessage;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author USER
 */
public class ClientPOP3 {

    private CredentialDTO credencial;
    private int previousMessageCount;

    public ClientPOP3(CredentialDTO credentialDTO) {
        this.credencial = credentialDTO;// El puerto predeterminado para POP3 es 110
        this.previousMessageCount = 0;
    }

    public void iniciar() {
        Properties properties = new Properties();
        properties.put("mail.pop3.host", credencial.getMail_host());
        properties.put("mail.pop3.port", credencial.getPort_pop3());
        properties.put("mail.pop3.auth", "true");

        Session session = Session.getDefaultInstance(properties);

        try {
            // Inicializa previousMessageCount con la cantidad actual de mensajes en el servidor
            Store store = session.getStore("pop3");
            store.connect(credencial.getMail_host(), credencial.getPort_pop3(), credencial.getMail_user(), credencial.getMail_pass());

            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();
            previousMessageCount = messages.length;

            folder.close(false);
            store.close();

            System.out.println("Cantidad inicial de mensajes: " + previousMessageCount);
            while (true) {
                store = session.getStore("pop3");
                store.connect(credencial.getMail_host(), credencial.getPort_pop3(), credencial.getMail_user(), credencial.getMail_pass());

                folder = store.getFolder("INBOX");
                folder.open(Folder.READ_ONLY);

                messages = folder.getMessages();
                int currentMessageCount = messages.length;
                if (currentMessageCount > previousMessageCount) {
                    System.out.println("Total mensajes: " + currentMessageCount);
                    for (int i = previousMessageCount; i < currentMessageCount; i++) {
                        Message message = messages[i];

                        String from = ((InternetAddress) message.getFrom()[0]).getAddress();
                        String subject = message.getSubject();
                        String body = getTextFromMessage(message);

                        System.out.println("Nuevo mensaje " + (i + 1) + ": " + subject);
                        System.out.println("De: " + from);
                        System.out.println("Cuerpo: " + body);

                        // Guardar en variables
                        String receptor = from;
                        String asunto = subject;
                        String cuerpo = body;
                        System.out.println("receptor: " + receptor);
                        System.out.println("asunto: " + asunto);
                        System.out.println("cuerpo: " + cuerpo);
                        MessageDTO mensaje = new MessageDTO(receptor, asunto);
                        Thread thread = new Thread(new HanddlerMessage(credencial, mensaje));
                        thread.start();
                    }

                    previousMessageCount = currentMessageCount;
                }

                folder.close(false);
                store.close();

                // Esperar 5 segundos antes de la siguiente comprobación
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) message.getContent();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    result.append(bodyPart.getContent().toString());
                }
            }
            return result.toString();
        }
        return "";
    }
}
