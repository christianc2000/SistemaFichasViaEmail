/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Servicio.dto;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author USER
 */
public class ServicioDTO {

    private int id;
    private String nombre;
    private String foto;
    private float costo;
    private int tipo_servicio_id;
    private int sala_id;
    private TipoServicioDTO tipo_servicio;
    private SalaDTO sala;
    private Timestamp created_at;
    private Timestamp updated_at;

    public int getSala_id() {
        return sala_id;
    }

    public void setSala_id(int sala_id) {
        this.sala_id = sala_id;
    }

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public TipoServicioDTO getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(TipoServicioDTO tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getTipo_servicio_id() {
        return tipo_servicio_id;
    }

    public void setTipo_servicio_id(int tipo_servicio_id) {
        this.tipo_servicio_id = tipo_servicio_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String[][] ServicioToMatriz() {
        String[][] matriz = new String[2][9];
        matriz[0][0] = "Id";
        matriz[0][1] = "Nombre";
        matriz[0][2] = "Foto";
        matriz[0][3] = "Costo";
        matriz[0][4] = "Id tipo de servicio";
        matriz[0][5] = "Tipo de servicio";
        matriz[0][6] = "Sala";
        matriz[0][7] = "Fecha de creación";
        matriz[0][8] = "Fecha de actualización";
        matriz[1][0] = String.valueOf(this.id);
        matriz[1][1] = this.nombre;
        matriz[1][2] = this.foto;
        matriz[1][3] = String.valueOf(this.costo);
        matriz[1][4] = String.valueOf(this.tipo_servicio_id);
        matriz[1][5] = this.tipo_servicio.getNombre();
        matriz[1][6] = this.sala.getNombre();
        matriz[1][7] = String.valueOf(this.created_at);
        matriz[1][8] = String.valueOf(this.updated_at);
        return matriz;
    }

    public void setAttribute(String attributeName, String value) {
        try {
            Field field = this.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            Object convertedValue = convertToFieldType(fieldType, value);
            field.set(this, convertedValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object convertToFieldType(Class<?> fieldType, String value) {
        if (fieldType.equals(String.class)) {
            return value;
        } else if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
            return Integer.valueOf(value);
        } else if (fieldType.equals(double.class) || fieldType.equals(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.equals(float.class) || fieldType.equals(Float.class)) {
            return Float.valueOf(value);
        } else if (fieldType.equals(boolean.class) || fieldType.equals(Boolean.class)) {
            return Boolean.valueOf(value);
        } else if (fieldType.equals(long.class) || fieldType.equals(Long.class)) {
            return Long.valueOf(value);
        } else if (fieldType.equals(short.class) || fieldType.equals(Short.class)) {
            return Short.valueOf(value);
        } else if (fieldType.equals(byte.class) || fieldType.equals(Byte.class)) {
            return Byte.valueOf(value);
        } else if (fieldType.equals(char.class) || fieldType.equals(Character.class)) {
            return value.charAt(0);
        }
        // Añadir más conversiones según sea necesario
        throw new IllegalArgumentException("Unsupported field type: " + fieldType);
    }
}
