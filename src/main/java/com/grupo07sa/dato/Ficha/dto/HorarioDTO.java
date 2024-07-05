/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Ficha.dto;

import java.lang.reflect.Field;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author USER
 */
public class HorarioDTO {

    private int id;
    private Time hora_inicio;
    private Time hora_fin;
    private boolean estado;
    private int dia_id;
    private DiaDTO dia;
    Timestamp created_at;
    Timestamp updated_at;

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

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getDia_id() {
        return dia_id;
    }

    public void setDia_id(int dia_id) {
        this.dia_id = dia_id;
    }

    public DiaDTO getDia() {
        return dia;
    }

    public void setDia(DiaDTO dia) {
        this.dia = dia;
    }

    public void setAttribute(String attributeName, String value) {
        try {
            Field field = getField(attributeName);
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            Object convertedValue = convertToFieldType(fieldType, value);
            field.set(this, convertedValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Field getField(String attributeName) throws NoSuchFieldException {
        Field field;
        try {
            field = this.getClass().getDeclaredField(attributeName);
        } catch (NoSuchFieldException e) {
            field = this.getClass().getSuperclass().getDeclaredField(attributeName);
        }
        return field;
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
