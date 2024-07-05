/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.HistorialMedico;

import java.lang.reflect.Field;

/**
 *
 * @author isais
 */
public class HistorialMedicoDTO {
    private String id;
    private String fecha_creacion;
    private String observaciones_generales;
    private String paciente_id;   
    

    public HistorialMedicoDTO() {
        this.id = null;
        this.fecha_creacion = null;
        this.observaciones_generales = null;
        this.paciente_id = null;
        
    }
    // Métodos de validación

    public boolean isValidId() {
        return id != null && !id.trim().isEmpty();
    }

    public boolean isValidFechaCreacion() {
        return fecha_creacion != null && !fecha_creacion.trim().isEmpty() ;
    }

    public boolean isValidObservaciones() {
        return observaciones_generales != null && !observaciones_generales.trim().isEmpty();
    }

    public boolean isValidPacienteId() {
        return paciente_id != null && !paciente_id.trim().isEmpty();
    }

    // Método para validar todos los atributos
    public boolean isValidCreate() {
        boolean b = true;
        if (!isValidFechaCreacion()) {
            System.out.println("FECHA_CREACION invalido");
            b = false;
        }
        if (!isValidObservaciones()) {
            System.out.println("OBSERVACIONES invalido");
            b = false;
        }
        if (!isValidPacienteId()) {
            System.out.println("PACIENTE_ID invalido");
            b = false;
        }
      
        return b;
    }

    public boolean isValidUpdate() {
        if (this.id == null) {
            System.out.println("Error: ID no debe ser null");
            return false;
        } else if (this.id.isEmpty()) {
            System.out.println("Error: ID no debe estar vacío");
            return false;
        }

        if (this.fecha_creacion != null) {
            if (this.fecha_creacion.isEmpty()) {
                System.out.println("Error: FECHA_CREACION no debe ser vacío");
                return false;
            }
        }

        if (this.observaciones_generales != null) {
            if (this.observaciones_generales.isEmpty()) {
                System.out.println("Error: OBSERVACIONES no debe estar vacío");
                return false;
            }
        }

        if (this.paciente_id != null) {
            if (this.paciente_id.isEmpty()) {
                System.out.println("ERROR: PACIENTE_ID no debe estar vacío");
                return false;
            }
        }
        
        return true;
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

    public String getFechaCreacion() {
        return fecha_creacion;
    }

    public void setFechaCreacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getObservaciones() {
        return observaciones_generales;
    }

    public void setObservaciones(String observaciones_generales) {
        this.observaciones_generales = observaciones_generales;
    }

    public String getPacienteId() {
        return paciente_id;
    }

    public void setPacienteId(String paciente_id) {
        this.paciente_id = paciente_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[][] HistorialMedicoToMatriz() {
        String[][] matriz = new String[2][4];
        matriz[0][0] = "id";
        matriz[0][1] = "fecha_creacion";
        matriz[0][2] = "observaciones_generales";
        matriz[0][3] = "paciente_id";
        matriz[1][0] = this.id;
        matriz[1][1] = this.fecha_creacion;
        matriz[1][2] = this.observaciones_generales;
        matriz[1][3] = this.paciente_id;
        return matriz;
    }

    public void insertAtrVal(String atr, String val) {
        switch (atr.toLowerCase()) {
            case "id":
                setId(val);
                break;
            case "fecha_creacion":
                setFechaCreacion(val);
                break;
            case "motivo":
                setObservaciones(val);
                break;
            case "paciente_id":
                setPacienteId(val);
                break;
            default:
                // Manejar caso de atributo desconocido
                System.out.println("Atributo desconocido: " + atr);
                break;
        }
    }

    public String toString() {
        return "id: " + this.id + ", fecha_creacion" + this.fecha_creacion + ", observaciones: " + this.observaciones_generales + ", paciente_id: " + this.paciente_id;
    }
}
