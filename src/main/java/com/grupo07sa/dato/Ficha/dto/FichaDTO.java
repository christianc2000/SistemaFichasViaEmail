/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Ficha.dto;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author USER
 */
public class FichaDTO {
    private int id;
    private int cantidad;
    private Date fecha;
    private int cantidad_vendido;
    private int horario_servicio_id;
    private HorarioServicioDTO horario_servicio;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad_vendido() {
        return cantidad_vendido;
    }

    public void setCantidad_vendido(int cantidad_vendido) {
        this.cantidad_vendido = cantidad_vendido;
    }

    public int getHorario_servicio_id() {
        return horario_servicio_id;
    }

    public void setHorario_servicio_id(int horario_servicio_id) {
        this.horario_servicio_id = horario_servicio_id;
    }

    public HorarioServicioDTO getHorario_servicio() {
        return horario_servicio;
    }

    public void setHorario_servicio(HorarioServicioDTO horario_servicio) {
        this.horario_servicio = horario_servicio;
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
