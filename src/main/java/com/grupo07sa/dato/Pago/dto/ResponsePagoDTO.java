/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Pago.dto;

/**
 *
 * @author USER
 */
public class ResponsePagoDTO {
    private boolean error;
    private boolean status;
    private String message;
    private boolean messageMostrar;
    private String messageSistema;
    private ValuePagoDTO values;
    private String tagImage;

    public String getTagImage() {
        return tagImage;
    }

    public void setTagImage(String tagImage) {
        this.tagImage = tagImage;
    }
    
    
    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getMessageMostrar() {
        return messageMostrar;
    }

    public void setMessageMostrar(boolean messageMostrar) {
        this.messageMostrar = messageMostrar;
    }

    public String getMessageSistema() {
        return messageSistema;
    }

    public void setMessageSistema(String messageSistema) {
        this.messageSistema = messageSistema;
    }

    public ValuePagoDTO getValues() {
        return values;
    }

    public void setValues(ValuePagoDTO values) {
        this.values = values;
    }
    
    
}
