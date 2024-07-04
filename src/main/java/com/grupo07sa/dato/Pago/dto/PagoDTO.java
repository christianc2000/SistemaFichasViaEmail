/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Pago.dto;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author USER
 */
public class PagoDTO {

    private int id;
    private String nit;
    private String razon_social;
    private String email;
    private String celular;
    private String trabajador;
    private String servicio;
    private String horario;
    private String tipo_servicio;
    private float costo;
    private String forma_pago;
    private String qr;
    private String estado;
    private Timestamp fecha_pago;
    private String fecha_expiracion;
    private int paciente_id;
    private int ficha_id;
    private Timestamp created_at;
    private Timestamp updated_at;

    public PagoDTO() {

    }

    public String getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(String fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public Timestamp getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Timestamp fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    //Validación para crear
    public void validateCreate() throws IllegalArgumentException {
        if (nit == null || nit.isEmpty()) {
            throw new IllegalArgumentException("El campo 'nit' es obligatorio.");
        }
        if (razon_social == null || razon_social.isEmpty()) {
            throw new IllegalArgumentException("El campo 'razon_social' es obligatorio.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El campo 'email' es obligatorio.");
        }
        if (celular == null || celular.isEmpty()) {
            throw new IllegalArgumentException("El campo 'celular' es obligatorio.");
        }
        if (trabajador == null || trabajador.isEmpty()) {
            throw new IllegalArgumentException("El campo 'trabajador' es obligatorio.");
        }
        if (servicio == null || servicio.isEmpty()) {
            throw new IllegalArgumentException("El campo 'servicio' es obligatorio.");
        }
        if (horario == null || horario.isEmpty()) {
            throw new IllegalArgumentException("El campo 'horario' es obligatorio.");
        }
        if (tipo_servicio == null || tipo_servicio.isEmpty()) {
            throw new IllegalArgumentException("El campo 'tipo_servicio' es obligatorio.");
        }
        if (forma_pago == null || forma_pago.isEmpty()) {
            throw new IllegalArgumentException("El campo 'forma_pago' es obligatorio.");
        }
        if (costo <= 0) {
            throw new IllegalArgumentException("El campo 'costo' debe ser mayor a 0.");
        }
        if (paciente_id <= 0) {
            throw new IllegalArgumentException("El campo 'paciente_id' es obligatorio.");
        }
        if (ficha_id <= 0) {
            throw new IllegalArgumentException("El campo 'ficha_id' es obligatorio.");
        }
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
            throw new IllegalArgumentException("Error setting attribute: " + e.getMessage());
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
        } else if (fieldType.equals(Timestamp.class)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(value, formatter);
            return Timestamp.valueOf(localDateTime);
        }
        throw new IllegalArgumentException("Unsupported field type: " + fieldType);
    }

    //validar para actualizar
    public void validateUpdate() throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("El campo 'id' es obligatorio y debe ser mayor a 0.");
        }
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

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(String trabajador) {
        this.trabajador = trabajador;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(int paciente_id) {
        this.paciente_id = paciente_id;
    }

    public int getFicha_id() {
        return ficha_id;
    }

    public void setFicha_id(int ficha_id) {
        this.ficha_id = ficha_id;
    }

    public String[][] UserToMatriz() {
        String[][] matriz = new String[2][18];
        matriz[0][0] = "id";
        matriz[0][1] = "nit";
        matriz[0][2] = "razon social";
        matriz[0][3] = "email";
        matriz[0][4] = "celular";
        matriz[0][5] = "trabajador";
        matriz[0][6] = "servicio";
        matriz[0][7] = "horario";
        matriz[0][8] = "tipo de servicio";
        matriz[0][9] = "costo";
        matriz[0][10] = "forma de pago";
        matriz[0][11] = "qr";
        matriz[0][12] = "estado";
        matriz[0][13] = "fecha de pago";
        matriz[0][14] = "paciente id";
        matriz[0][15] = "ficha id";
        matriz[0][16] = "fecha de creación";
        matriz[0][17] = "fecha de actualización";
        matriz[1][0] = String.valueOf(this.id);
        matriz[1][1] = this.nit;
        matriz[1][2] = this.razon_social;
        matriz[1][3] = this.email;
        matriz[1][4] = this.celular;
        matriz[1][5] = this.trabajador;
        matriz[1][6] = this.servicio;
        matriz[1][7] = this.horario;
        matriz[1][8] = this.tipo_servicio;
        matriz[1][9] = String.valueOf(this.costo);
        matriz[1][10] = this.forma_pago;
        matriz[1][11] = this.qr;
        matriz[1][12] = this.estado;
        matriz[1][13] = String.valueOf(this.fecha_pago);
        matriz[1][14] = String.valueOf(this.paciente_id);
        matriz[1][15] = String.valueOf(this.ficha_id);
        matriz[1][16] = String.valueOf(this.created_at);
        matriz[1][17] = String.valueOf(this.updated_at);
        return matriz;
    }
}
