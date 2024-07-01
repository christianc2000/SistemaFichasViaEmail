/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.User;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        this.ci = null;
        this.name = null;
        this.lastname = null;
        this.gender = null;
        this.celular = null;
        this.fecha_nacimiento = null;
        this.foto = null;
        this.direccion = null;
        this.email = null;
        this.password = null;
        this.nit = null;
        this.razon_social = null;
    }
    // Métodos de validación

    public boolean isValidId() {
        return id != null && !id.trim().isEmpty();
    }

    public boolean isValidCi() {
        return ci != null && !ci.trim().isEmpty() && ci.matches("\\d+");
    }

    public boolean isValidName() {
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Z]+");
    }

    public boolean isValidLastname() {
        return lastname != null && !lastname.trim().isEmpty() && lastname.matches("[a-zA-Z]+");
    }

    public boolean isValidGender() {
        return gender != null && (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"));
    }

    public boolean isValidCelular() {
        return celular != null && !celular.trim().isEmpty() && celular.matches("\\d{8,15}");
    }

    public boolean isValidEmail() {
        return email != null && !email.trim().isEmpty() && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public boolean isValidPassword() {
        return password != null && !password.trim().isEmpty() && password.length() >= 6;
    }

    public boolean isValidFechaNacimiento() {
        if (fecha_nacimiento == null || fecha_nacimiento.trim().isEmpty()) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(fecha_nacimiento, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isValidFoto() {
        return foto != null && !foto.trim().isEmpty();
    }

    public boolean isValidDireccion() {
        return direccion != null && !direccion.trim().isEmpty();
    }

    public boolean isValidNit() {
        return (nit != null) ? !nit.trim().isEmpty() : true;
    }

    public boolean isValidRazonSocial() {
        return (razon_social != null) ? !razon_social.trim().isEmpty() : true;
    }

    // Método para validar todos los atributos
    public boolean isValidCreate() {
        boolean b = true;
        if (!isValidCi()) {
            System.out.println("CI invalido");
            b = false;
        }
        if (!isValidName()) {
            System.out.println("NAME invalido");
            b = false;
        }
        if (!isValidLastname()) {
            System.out.println("LASTNAME invalido");
            b = false;
        }
        if (!isValidGender()) {
            System.out.println("GENDER invalido");
            b = false;
        }
        if (!isValidCelular()) {
            System.out.println("CELULAR invalido");
            b = false;
        }
        if (!isValidEmail()) {
            System.out.println("EMAIL invalido");
            b = false;
        }
        if (!isValidPassword()) {
            System.out.println("PASSWORD invalido");
            b = false;
        }
        if (!isValidFechaNacimiento()) {
            System.out.println("FECHA DE NACIMIENTO invalido");
            b = false;
        }
        if (!isValidFoto()) {
            System.out.println("FOTO invalido");
            b = false;
        }
        if (!isValidDireccion()) {
            System.out.println("DIRECCION invalido");
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

        if (this.ci != null) {
            if (this.ci.isEmpty()) {
                System.out.println("Error: CI no debe ser vacío");
                return false;
            }
        }

        if (this.name != null) {
            if (this.name.isEmpty()) {
                System.out.println("Error: NOMBRE no debe estar vacío");
                return false;
            }
        }

        if (this.lastname != null) {
            if (this.lastname.isEmpty()) {
                System.out.println("ERROR: APELLIDO no debe estar vacío");
                return false;
            }
        }

        if (this.gender != null) {
            if (this.gender.isEmpty()) {
                System.out.println("ERROR: GÉNERO no debe estar vacío");
                return false;
            }
        }

        if (this.celular != null) {
            if (this.celular.isEmpty()) {
                System.out.println("ERROR: CELULAR no debe estar vacío");
                return false;
            }
        }

        if (this.email != null) {
            if (this.email.isEmpty()) {
                System.out.println("ERROR: CORREO no debe estar vacío");
                return false;
            }
        }

        if (this.password != null) {
            if (this.password.isEmpty()) {
                System.out.println("ERROR: PASSWORD no debe estar vacío");
                return false;
            }
        }

        if (this.fecha_nacimiento != null) {
            if (this.fecha_nacimiento.isEmpty()) {
                System.out.println("ERROR: FECHA DE NACIMIENTO no debe estar vacío");
                return false;
            }
        }

        if (this.foto != null) {
            if (this.foto.isEmpty()) {
                System.out.println("ERROR: FOTO no debe estar vacío");
                return false;
            }
        }

        if (this.direccion != null) {
            if (this.direccion.isEmpty()) {
                System.out.println("ERROR: DIRECCIÓN no debe estar vacío");
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

    public String[][] UserToMatriz() {
        String[][] matriz = new String[2][13];
        matriz[0][0] = "id";
        matriz[0][1] = "ci";
        matriz[0][2] = "name";
        matriz[0][3] = "lastname";
        matriz[0][4] = "fecha_nacimiento";
        matriz[0][5] = "foto";
        matriz[0][6] = "direccion";
        matriz[0][7] = "gender";
        matriz[0][8] = "celular";
        matriz[0][9] = "email";
        matriz[0][10] = "password";
        matriz[0][11] = "nit";
        matriz[0][12] = "razon_social";
        matriz[1][0] = this.id;
        matriz[1][1] = this.ci;
        matriz[1][2] = this.name;
        matriz[1][3] = this.lastname;
        matriz[1][4] = this.fecha_nacimiento;
        matriz[1][5] = this.foto;
        matriz[1][6] = this.direccion;
        matriz[1][7] = this.gender;
        matriz[1][8] = this.celular;
        matriz[1][9] = this.email;
        matriz[1][10] = this.password;
        matriz[1][11] = this.nit;
        matriz[1][12] = this.razon_social;
        return matriz;
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

    public String toString() {
        return "id: " + this.id + ", ci" + this.ci + ", name: " + this.name + ", lastname: " + this.lastname + ", fecha nacimiento: " + this.fecha_nacimiento + ", foto: " + this.foto + ", direccion: " + this.direccion + ", genero: " + this.gender + ", celular: " + this.celular + ", email: " + this.email + ", password: " + this.password + ", nit: " + this.nit + ", razon social: " + this.razon_social;
    }
}
