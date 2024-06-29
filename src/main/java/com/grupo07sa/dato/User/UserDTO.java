/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.User;

/**
 *
 * @author USER
 */
public class UserDTO {

    private String id;
    private String ci;
    private String name;
    private String lastname;
    private String gender;
    private String celular;
    private String email;
    private String password;
    private String fecha_nacimiento;
    private String foto;
    private String direccion;
    private String nit;
    private String razon_social;
    //ResponseBD response = new ResponseBD(null, null, null);

    public UserDTO() {
        this.id = null;
        this.ci=null;
        this.name = null;
        this.lastname = null;
        this.gender=null;
        this.celular=null;
        this.fecha_nacimiento = null;
        this.foto = null;
        this.direccion = null;
        this.email = null;
        this.password = null;
        this.nit=null;
        this.razon_social=null;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void insertAtrVal(String atr, String val) {
        switch (atr.toLowerCase()) {
            case "id":
                setId(val);
                break;
            case "name":
                setName(val);
                break;
            case "lastname":
                setLastname(val);
                break;
            case "email":
                setEmail(val);
                break;
            case "password":
                setPassword(val);
                break;
            case "fecha_nacimiento":
                setFecha_nacimiento(val);
                break;
            case "foto":
                setFoto(val);
                break;
            case "direccion":
                setDireccion(val);
                break;
            default:
                // Manejar caso de atributo desconocido
                System.out.println("Atributo desconocido: " + atr);
                break;
        }
    }
}
