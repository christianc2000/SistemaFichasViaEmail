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
public class CredentialPagoFacilDTO {
    private String COMMERCEID;
    private String TOKEN_SERVICE;
    private String TOKEN_SECRET;
    
    public CredentialPagoFacilDTO(){
        Dotenv dotenv = Dotenv.load();
        COMMERCEID=dotenv.get("PAGO_FACIL_COMMERCID");
        TOKEN_SERVICE=dotenv.get("PAGO_FACIL_TOKEN_SERVICE");
        TOKEN_SECRET=dotenv.get("PAGO_FACIL_TOKEN_SECRET");
    }

    public String getCOMMERCEID() {
        return COMMERCEID;
    }

    public void setCOMMERCEID(String COMMERCEID) {
        this.COMMERCEID = COMMERCEID;
    }

    public String getTOKEN_SERVICE() {
        return TOKEN_SERVICE;
    }

    public void setTOKEN_SERVICE(String TOKEN_SERVICE) {
        this.TOKEN_SERVICE = TOKEN_SERVICE;
    }

    public String getTOKEN_SECRET() {
        return TOKEN_SECRET;
    }

    public void setTOKEN_SECRET(String TOKEN_SECRET) {
        this.TOKEN_SECRET = TOKEN_SECRET;
    }
    
    
}
