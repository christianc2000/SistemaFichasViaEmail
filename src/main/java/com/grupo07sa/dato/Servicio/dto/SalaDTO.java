/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Servicio.dto;

import java.sql.Timestamp;

/**
 *
 * @author USER
 */
public class SalaDTO {
    private int id;
    private String nombre;

    public SalaDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
