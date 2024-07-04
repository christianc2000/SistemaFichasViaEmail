/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.service;

import com.grupo07sa.dato.ResponseDTO;
import com.grupo07sa.dato.User.dto.UserDTO;
import com.grupo07sa.dato.User.UserRepositoryImpl;
import java.util.List;

/**
 *
 * @author USER
 */
public class UserService {

    private UserRepositoryImpl userRepositoryImpl;

    public UserService() {
        this.userRepositoryImpl = new UserRepositoryImpl();
    }

    public ResponseDTO all() {
        List<UserDTO> users = null;
        String error = null;
        try {
            users = userRepositoryImpl.getAllUsers();
        } catch (Exception e) {
            error = "Error al obtener los usuarios: " + e.getMessage();
        }
        String title = "Lista de Usuarios";
        String[][] data = null;

        if (users != null) {
            data = new String[users.size() + 1][13];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de usuarios: " + users.size());
            // Llena la primera fila con los nombres de los atributos
            data[0][0] = "ID";
            data[0][1] = "CI";
            data[0][2] = "NOMBRE";
            data[0][3] = "APELLIDO";
            data[0][4] = "FECHA DE NACIMIENTO";
            data[0][5] = "FOTO";
            data[0][6] = "DIRECCION";
            data[0][7] = "GÉNERO";
            data[0][8] = "CELULAR";
            data[0][9] = "EMAIL";
            data[0][10] = "CONTRASEÑA";
            data[0][11] = "NIT";
            data[0][12] = "RAZON SOCIAL";
            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < users.size(); i++) {
                UserDTO user = users.get(i);
                data[i + 1][0] = user.getId();
                data[i + 1][1] = user.getCi();
                data[i + 1][2] = user.getName();
                data[i + 1][3] = user.getLastname();
                data[i + 1][4] = user.getFecha_nacimiento();
                data[i + 1][5] = user.getFoto();
                data[i + 1][6] = user.getDireccion();
                data[i + 1][7] = user.getGender();
                data[i + 1][8] = user.getCelular();
                data[i + 1][9] = user.getEmail();
                data[i + 1][10] = user.getPassword();
                data[i + 1][11] = user.getNit();
                data[i + 1][12] = user.getRazon_social();
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO listAtr(String[] atributos, String atrs) {
        List<String[]> users = null;
        String error = null;
        try {
            users = userRepositoryImpl.getAtrUsers(atributos, atrs);
        } catch (Exception e) {
            error = "Error al obtener los usuarios: " + e.getMessage();
        }
        String title = "Lista de Usuarios por atributo";
        String[][] data = null;

        if (users != null) {
            data = new String[users.size() + 1][atributos.length];  // Incrementa el tamaño para la fila de encabezado

            // Llena la primera fila con los nombres de los atributos
            for (int i = 0; i < atributos.length; i++) {
                data[0][i] = atributos[i].toUpperCase();
            }

            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < users.size(); i++) {
                String[] user = users.get(i);
                for (int j = 0; j < user.length; j++) {
                    data[i + 1][j] = user[j];
                }
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO find(String[][] atributosValor) {
        UserDTO user = new UserDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            user.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (!user.isValidId()) {
            error = "Error de validación";
            return new ResponseDTO("Buscar usuario", null, error);
        }
        try {
            user = userRepositoryImpl.findUser(user.getId());
        } catch (Exception e) {
            error = "Error: " + e;
        }
        return new ResponseDTO("Mostrar usuario", user.UserToMatriz(), error);
    }

    public ResponseDTO create(String[][] atributosValor) {
        UserDTO user = new UserDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            user.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        try {
            if (user.isValidCreate()) {
                user = userRepositoryImpl.createUser(user.getCi(), user.getName(), user.getLastname(), user.getFecha_nacimiento(), user.getFoto(), user.getDireccion(), user.getGender(), user.getCelular(), user.getEmail(), user.getPassword(), user.getNit(), user.getRazon_social());
            } else {
                error = "Error: Datos invalidos para crear un usuario";
            }

        } catch (Exception e) {
            error = "Error: " + e;
        }

        return new ResponseDTO("Crear usuario", user.UserToMatriz(), error);
    }

    public ResponseDTO update(String[][] atributosValor) {
        String error = null;
        UserDTO userRequest = new UserDTO();
        UserDTO user = new UserDTO();
        for (int i = 0; i < atributosValor.length; i++) {
            userRequest.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (userRequest.isValidUpdate()) {
            try {
                user = userRepositoryImpl.findUser(userRequest.getId());
                if (user == null) {
                    error = "Error: Usuario no encontrado";
                    return new ResponseDTO("Actualizar usuario", null, error);
                }
                user.setCi(userRequest.getCi() != null ? userRequest.getCi() : user.getCi());
                user.setName(userRequest.getName() != null ? userRequest.getName() : user.getName());
                user.setLastname(userRequest.getLastname() != null ? userRequest.getLastname() : user.getLastname());
                user.setFecha_nacimiento(userRequest.getFecha_nacimiento() != null ? userRequest.getFecha_nacimiento() : user.getFecha_nacimiento());
                user.setFoto(userRequest.getFoto() != null ? userRequest.getFoto() : user.getFoto());
                user.setDireccion(userRequest.getDireccion() != null ? userRequest.getDireccion() : user.getDireccion());
                user.setGender(userRequest.getGender() != null ? userRequest.getGender() : user.getGender());
                user.setCelular(userRequest.getCelular() != null ? userRequest.getCelular() : user.getCelular());
                user.setEmail(userRequest.getEmail() != null ? userRequest.getEmail() : user.getEmail());
                user.setPassword(userRequest.getPassword() != null ? userRequest.getPassword() : user.getPassword());
                user.setNit(userRequest.getNit() != null ? userRequest.getNit() : user.getNit());
                user.setRazon_social(userRequest.getRazon_social() != null ? userRequest.getRazon_social() : user.getRazon_social());

                user = userRepositoryImpl.updateUser(user);
                if (user == null) {
                    error = "Error al actualizar el usuario";
                }
            } catch (Exception e) {
                error = "Error: " + e;
                return new ResponseDTO("Actualizar usuario", null, error);
            }
            return new ResponseDTO("Actualizar usuario", user.UserToMatriz(), error);
        } else {
            error = "Error: Error de validación";
            return new ResponseDTO("Actualizar usuario", null, error);
        }
    }

    public ResponseDTO delete(String[][] atributosValor) {
        String error = null;
        UserDTO user = new UserDTO();
        for (int i = 0; i < atributosValor.length; i++) {
            user.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (!user.isValidId()) {
            error = "Error de validación";
            return new ResponseDTO("Buscar usuario", null, error);
        }
        boolean isDeleted = false;
        try {
            isDeleted = userRepositoryImpl.deleteUserById(user.getId());
            if (!isDeleted) {
                error = "Error al intentar eliminar al usuario";
            }
        } catch (Exception e) {
            error = "Error al eliminar el usuario: " + e.getMessage();
        }
        return new ResponseDTO("Eliminar usuario", null, error);
    }

    //public static void main(String[] args) {
    // Crear instancia del servicio de usuarios
    //UserService userService = new UserService();
    /*ResponseDTO response = response = userService.find("1");

        if (response.getError() == null) {
            System.out.println("Usuario encontrado");
            System.out.println(response.MatrizToString());
        } else {
            System.out.println("Error: " + response.getError());
        }*/
    // Definir los atributos y valores del usuario a crear
    //String[][] atributosValor = {
    //    {"id", "15"},
    //{"ci", "12345678"},
    //{"name", "Juan Actualizado"},
    //{"lastname", "Perez Actualizado"}, //{"fecha_nacimiento", "1990-01-01"},
    //{"foto", "juan.jpg"},
    //{"direccion", "Calle Falsa 123"},
    //{"gender", "M"},
    //{"celular", "987654321"},
    //    {"email", "juan@example.com"},
    //    {"password", "12345678"},
    //    {"nit", "11223344"},
    //    {"razon_social", "Ana Rodriguez Actualizado"}
    //};
    //String atributos = "ci, name, lastname, fecha_nacimiento, foto, direccion, gender, celular, email, nit, razon_social";
    //ResponseDTO response = new ResponseDTO(null, null, null);
    // Llamar al método create del servicio de usuarios
    //response = userService.create(atributosValor, atributos);
    //System.out.println("Atributos Valor: " + atributosValor.length);
    //response = userService.delete("10");
    // Imprimir el resultado
    //if (response.getError() == null) {
    //  System.out.println("Usuario ELIMINADO exitosamente.");
    //} else {
    //  System.out.println(response.getError());
    //}
    // }
}
