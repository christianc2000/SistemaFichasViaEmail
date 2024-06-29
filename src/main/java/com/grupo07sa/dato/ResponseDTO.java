/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato;

/**
 *
 * @author USER
 */
public class ResponseDTO {
    private String title;
    private String[][] data;
    private String error;

    public ResponseDTO(String title, String[][] data, String error) {
        this.title = title;
        this.data = data;
        this.error = error;
    }

    public ResponseDTO(){
        
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
