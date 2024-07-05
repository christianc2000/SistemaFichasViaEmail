/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupo07sa.dato.Consulta;

import java.lang.reflect.Field;

/**
 *
 * @author isais
 */
public class ConsultaDTO {
    private String id;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private String cita_id;
    private String historial_medico_id;
    

    public ConsultaDTO() {
        this.id = null;
        this.diagnostico = null;
        this.tratamiento = null;
        this.observaciones = null;
        this.cita_id = null;
        this.historial_medico_id = null;
    }
    // Métodos de validación

    public boolean isValidId() {
        return id != null && !id.trim().isEmpty();
    }

    public boolean isValidDiagnostico() {
        return diagnostico != null && !diagnostico.trim().isEmpty() ;
    }

    public boolean isValidTratamiento() {
        return tratamiento != null && !tratamiento.trim().isEmpty();
    }

    public boolean isValidObservaciones() {
        return observaciones != null && !observaciones.trim().isEmpty();
    }
    
    public boolean isValidCitaId() {
        return cita_id != null && !cita_id.trim().isEmpty();
    }
    
    public boolean isValidHistorialMedicoId() {
        return historial_medico_id != null && !historial_medico_id.trim().isEmpty();
    }

    // Método para validar todos los atributos
    public boolean isValidCreate() {
        boolean b = true;
        if (!isValidDiagnostico()) {
            System.out.println("DIAGNOSTICO invalido");
            b = false;
        }
        if (!isValidTratamiento()) {
            System.out.println("TRATAMIENTO invalido");
            b = false;
        }
        if (!isValidObservaciones()) {
            System.out.println("OBSERVACIONES invalido");
            b = false;
        }
        if (!isValidCitaId()) {
            System.out.println("CITA_ID invalido");
            b = false;
        }
        if (!isValidHistorialMedicoId()) {
            System.out.println("HISTORIAL_MEDICO_ID invalido");
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

        if (this.diagnostico != null) {
            if (this.diagnostico.isEmpty()) {
                System.out.println("Error: DIAGNOSTICO no debe ser vacío");
                return false;
            }
        }

        if (this.tratamiento != null) {
            if (this.tratamiento.isEmpty()) {
                System.out.println("Error: TRATAMIENTO no debe estar vacío");
                return false;
            }
        }

        if (this.observaciones != null) {
            if (this.observaciones.isEmpty()) {
                System.out.println("ERROR: OBSERVACIONES no debe estar vacío");
                return false;
            }
        }
        
        if (this.cita_id != null) {
            if (this.cita_id.isEmpty()) {
                System.out.println("ERROR: CITA_ID no debe estar vacío");
                return false;
            }
        }
        
        if (historial_medico_id != null) {
            if (this.historial_medico_id.isEmpty()) {
                System.out.println("ERROR: HISTORIAL_MEDICO_ID no debe estar vacío");
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

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String getCitaId() {
        return cita_id;
    }

    public void setCitaId(String cita_id) {
        this.cita_id = cita_id;
    }
    
    public String getHistorialMedicoId() {
        return historial_medico_id;
    }

    public void setHistorialMedicoId(String historial_medico_id) {
        this.historial_medico_id = historial_medico_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[][] ConsultaToMatriz() {
        String[][] matriz = new String[2][6];
        matriz[0][0] = "id";
        matriz[0][1] = "diagnostico";
        matriz[0][2] = "tratamiento";
        matriz[0][3] = "observaciones";
        matriz[0][4] = "cita_id";
        matriz[0][5] = "historial_medico_id";
        matriz[1][0] = this.id;
        matriz[1][1] = this.diagnostico;
        matriz[1][2] = this.tratamiento;
        matriz[1][3] = this.observaciones;
        matriz[1][4] = this.cita_id;
        matriz[1][5] = this.historial_medico_id;
        return matriz;
    }

    public void insertAtrVal(String atr, String val) {
        switch (atr.toLowerCase()) {
            case "id":
                setId(val);
                break;
            case "diagnostico":
                setDiagnostico(val);
                break;
            case "tratamiento":
                setTratamiento(val);
                break;
            case "observaciones":
                setObservaciones(val);
                break;
            case "cita_id":
                setCitaId(val);
                break;
            case "historial_medico_id":
                setHistorialMedicoId(val);
                break;
            default:
                // Manejar caso de atributo desconocido
                System.out.println("Atributo desconocido: " + atr);
                break;
        }
    }

    public String toString() {
        return "id: " + this.id + ", diagnostico" + this.diagnostico + ", tratamiento: " + this.tratamiento + ", observaciones: " + this.observaciones + ", cita_id: " + this.cita_id + ", historial_medico_id: " + this.historial_medico_id;
    }
}
