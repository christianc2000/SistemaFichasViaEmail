/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.service;

import com.grupo07sa.dato.ResponseDTO;
import com.grupo07sa.dato.User.UserDTO;
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
            data = new String[users.size() + 1][8];  // Incrementa el tamaño para la fila de encabezado

            // Llena la primera fila con los nombres de los atributos
            data[0][0] = "ID";
            data[0][1] = "Nombre";
            data[0][2] = "Apellido";
            data[0][3] = "Email";
            data[0][4] = "Contraseña";
            data[0][5] = "Fecha de Nacimiento";
            data[0][6] = "Foto";
            data[0][7] = "Dirección";
            
             // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < users.size(); i++) {
                UserDTO user = users.get(i);
                data[i + 1][0] = user.getId();
                data[i + 1][1] = user.getName();
                data[i + 1][2] = user.getLastname();
                data[i + 1][3] = user.getEmail();
                data[i + 1][4] = user.getPassword();
                data[i + 1][5] = user.getFecha_nacimiento();
                data[i + 1][6] = user.getFoto();
                data[i + 1][7] = user.getDireccion();
            }
        }

        return new ResponseDTO(title, data, error);
    }
}
