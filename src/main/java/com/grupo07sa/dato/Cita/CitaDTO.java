/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Cita;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author isais
 */
public class CitaDTO {
    
    private String id;
    private String fecha_hora;
    private String motivo;
    private String paciente_id;
    private String trabajador_id;
    private String sala_id;
    private String pago_id;
    
    
    //ResponseBD response = new ResponseBD(null, null, null);

    public CitaDTO() {
        this.id = null;
        this.fecha_hora = null;
        this.motivo = null;
        this.paciente_id = null;
        this.trabajador_id = null;
        this.sala_id = null;
        this.pago_id = null;
        
    }
    // Métodos de validación

    public boolean isValidId() {
        return id != null && !id.trim().isEmpty();
    }

    public boolean isValidFechaHora() {
        return fecha_hora != null && !fecha_hora.trim().isEmpty() ;
    }

    public boolean isValidMotivo() {
        return motivo != null && !motivo.trim().isEmpty();
    }

    public boolean isValidPacienteId() {
        return paciente_id != null && !paciente_id.trim().isEmpty();
    }

    public boolean isValidTrabajadorId() {
        return trabajador_id != null && !trabajador_id.trim().isEmpty();
    }

    public boolean isValidSalaId() {
        return sala_id != null && !(sala_id.trim().isEmpty() );
    }

    public boolean isValidPagoId() {
        return pago_id != null && !(pago_id.trim().isEmpty() );
    }

    // Método para validar todos los atributos
    public boolean isValidCreate() {
        boolean b = true;
        if (!isValidFechaHora()) {
            System.out.println("FECHA_HORA invalido");
            b = false;
        }
        if (!isValidMotivo()) {
            System.out.println("MOTIVO invalido");
            b = false;
        }
        if (!isValidPacienteId()) {
            System.out.println("PACIENTE_ID invalido");
            b = false;
        }
        if (!isValidTrabajadorId()) {
            System.out.println("TRABAJADOR_ID invalido");
            b = false;
        }
        if (!isValidSalaId()) {
            System.out.println("SALA_ID invalido");
            b = false;
        }
        if (!isValidPagoId()) {
            System.out.println("PAGO_ID invalido");
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

        if (this.fecha_hora != null) {
            if (this.fecha_hora.isEmpty()) {
                System.out.println("Error: FECHA_HORA no debe ser vacío");
                return false;
            }
        }

        if (this.motivo != null) {
            if (this.motivo.isEmpty()) {
                System.out.println("Error: MOTIVO no debe estar vacío");
                return false;
            }
        }

        if (this.paciente_id != null) {
            if (this.paciente_id.isEmpty()) {
                System.out.println("ERROR: PACIENTE_ID no debe estar vacío");
                return false;
            }
        }

        if (this.trabajador_id != null) {
            if (this.trabajador_id.isEmpty()) {
                System.out.println("ERROR: TRABAJADOR_ID no debe estar vacío");
                return false;
            }
        }

        if (this.sala_id != null) {
            if (this.sala_id.isEmpty()) {
                System.out.println("ERROR: SALA_ID no debe estar vacío");
                return false;
            }
        }

        if (this.pago_id != null) {
            if (this.pago_id.isEmpty()) {
                System.out.println("ERROR: PAGO_ID no debe estar vacío");
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

    public String getFechaHora() {
        return fecha_hora;
    }

    public void setFechaHora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPacienteId() {
        return paciente_id;
    }

    public void setPacienteId(String paciente_id) {
        this.paciente_id = paciente_id;
    }

    public String getTrabajadorId() {
        return trabajador_id;
    }

    public void setTrabajadorId(String trabajador_id) {
        this.trabajador_id = trabajador_id;
    }

    public String getSalaId() {
        return sala_id;
    }

    public void setSalaId(String sala_id) {
        this.sala_id = sala_id;
    }
    
     public String getPagoId() {
        return pago_id;
    }

    public void setPagoId(String pago_id) {
        this.pago_id = pago_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[][] CitaToMatriz() {
        String[][] matriz = new String[2][7];
        matriz[0][0] = "id";
        matriz[0][1] = "fecha_hora";
        matriz[0][2] = "motivo";
        matriz[0][3] = "paciente_id";
        matriz[0][4] = "trabajador_id";
        matriz[0][5] = "sala_id";
        matriz[0][6] = "pago_id";
        matriz[1][0] = this.id;
        matriz[1][1] = this.fecha_hora;
        matriz[1][2] = this.motivo;
        matriz[1][3] = this.paciente_id;
        matriz[1][4] = this.trabajador_id;
        matriz[1][5] = this.sala_id;
        matriz[1][6] = this.pago_id;
        return matriz;
    }

    public void insertAtrVal(String atr, String val) {
        switch (atr.toLowerCase()) {
            case "id":
                setId(val);
                break;
            case "fecha_hora":
                setFechaHora(val);
                break;
            case "motivo":
                setMotivo(val);
                break;
            case "paciente_id":
                setPacienteId(val);
                break;
            case "trabajador_id":
                setTrabajadorId(val);
                break;
            case "sala_id":
                setSalaId(val);
                break;
            case "pago_id":
                setPagoId(val);
                break;
            default:
                // Manejar caso de atributo desconocido
                System.out.println("Atributo desconocido: " + atr);
                break;
        }
    }

    public String toString() {
        return "id: " + this.id + ", fecha_hora" + this.fecha_hora + ", motivo: " + this.motivo + ", paciente_id: " + this.paciente_id + ", trabajador_id: " + this.trabajador_id + ", sala_id: " + this.sala_id + ", pago_id: " + this.pago_id ;
    }
}
