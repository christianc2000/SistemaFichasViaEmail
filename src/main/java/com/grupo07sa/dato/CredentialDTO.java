/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author USER
 */
public class CredentialDTO {

    private String mail_host;
    private String mail_email;
    private String mail_user;
    private String mail_pass;
    private int port_pop3;
    private int port_smtp;

    public CredentialDTO() {
        Dotenv dotenv = Dotenv.load();
        this.mail_host = dotenv.get("MAIL_HOST");
        this.mail_email = dotenv.get("MAIL_EMAIL");
        this.mail_user = dotenv.get("MAIL_USER");
        this.mail_pass = dotenv.get("MAIL_PASS");
        this.port_pop3 = Integer.parseInt(dotenv.get("MAIL_PORT_POP3"));
        this.port_smtp = Integer.parseInt(dotenv.get("MAIL_PORT_SMTP"));

    }

    public String getMail_host() {
        return mail_host;
    }

    public void setMail_host(String mail_host) {
        this.mail_host = mail_host;
    }

    public String getMail_email() {
        return mail_email;
    }

    public void setMail_email(String mail_email) {
        this.mail_email = mail_email;
    }

    public String getMail_user() {
        return mail_user;
    }

    public void setMail_user(String mail_user) {
        this.mail_user = mail_user;
    }

    public String getMail_pass() {
        return mail_pass;
    }

    public void setMail_pass(String mail_pass) {
        this.mail_pass = mail_pass;
    }

    public int getPort_pop3() {
        return port_pop3;
    }

    public void setPort_pop3(int port_pop3) {
        this.port_pop3 = port_pop3;
    }

    public int getPort_smtp() {
        return port_smtp;
    }

    public void setPort_smtp(int port_smtp) {
        this.port_smtp = port_smtp;
    }
}
