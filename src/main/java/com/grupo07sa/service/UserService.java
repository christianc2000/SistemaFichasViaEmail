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
            data = new String[users.size() + 1][13];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de usuarios: "+users.size());
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
        String title = "Lista de Usuarios";
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

    public ResponseDTO create(String atributo, String valor) {

        return new ResponseDTO(null, null, null);
    }
}
