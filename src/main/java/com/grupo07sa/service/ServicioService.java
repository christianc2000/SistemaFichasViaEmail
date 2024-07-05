/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.service;

import com.grupo07sa.dato.ResponseDTO;
import com.grupo07sa.dato.Servicio.ServicioRepositoryImpl;
import com.grupo07sa.dato.Servicio.dto.ServicioDTO;
import java.util.List;

/**
 *
 * @author USER
 */
public class ServicioService {

    private ServicioRepositoryImpl servicioRepositoryImpl;

    public ServicioService() {
        this.servicioRepositoryImpl = new ServicioRepositoryImpl();
    }

    public ResponseDTO all() {
        List<ServicioDTO> servicios = null;
        String error = null;
        try {
            servicios = servicioRepositoryImpl.getAllservicios();
        } catch (Exception e) {
            error = "Error al obtener los servicios: " + e.getMessage();
        }
        String title = "Lista de Servicios";
        String[][] data = null;

        if (servicios != null) {
            data = new String[servicios.size() + 1][10];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de servicios: " + servicios.size());
            // Llena la primera fila con los nombres de los atributos
            data[0][0] = "ID";
            data[0][1] = "NOMBRE";
            data[0][2] = "FOTO";
            data[0][3] = "COSTO";
            data[0][4] = "TIPO SERVICIO ID";
            data[0][5] = "TIPO SERVICIO";
            data[0][6] = "SALA ID";
            data[0][7] = "SALA";
            data[0][8] = "FECHA DE CREACIÓN";
            data[0][9] = "FECHA DE ACTUALIZACIÓN";
            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < servicios.size(); i++) {
                ServicioDTO servicio = servicios.get(i);
                data[i + 1][0] = String.valueOf(servicio.getId());
                data[i + 1][1] = servicio.getNombre();
                data[i + 1][2] = servicio.getFoto();
                data[i + 1][3] = String.valueOf(servicio.getCosto());
                data[i + 1][4] = String.valueOf(servicio.getTipo_servicio_id());
                data[i + 1][5] = servicio.getTipo_servicio().getNombre();
                data[i + 1][6] = String.valueOf(servicio.getSala_id());
                data[i + 1][7] = servicio.getSala().getNombre();
                data[i + 1][8] = String.valueOf(servicio.getCreated_at());
                data[i + 1][9] = String.valueOf(servicio.getUpdated_at());
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO listAtr(String[] atributos, String atrs) {
        List<String[]> servicios = null;
        String error = null;
        try {
            servicios = servicioRepositoryImpl.getAtrServicios(atributos, atrs);
        } catch (Exception e) {
            error = "Error al obtener los servicios: " + e.getMessage();
        }
        String title = "Lista de Servicio por atributo";
        String[][] data = null;

        if (servicios != null) {
            data = new String[servicios.size() + 1][atributos.length];  // Incrementa el tamaño para la fila de encabezado

            // Llena la primera fila con los nombres de los atributos
            for (int i = 0; i < atributos.length; i++) {
                data[0][i] = atributos[i].toUpperCase();
            }

            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < servicios.size(); i++) {
                String[] user = servicios.get(i);
                for (int j = 0; j < user.length; j++) {
                    data[i + 1][j] = user[j];
                }
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO find(String[][] atributosValor) {
        ServicioDTO servicio = new ServicioDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            servicio.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (servicio.getId() == 0) {
            error = "Error de validación";
            return new ResponseDTO("Buscar servicio", null, error);
        }
        try {
            servicio = servicioRepositoryImpl.findServicio(servicio.getId());
        } catch (Exception e) {
            error = "Error: " + e;
            servicio=null;
        }
        return new ResponseDTO("Mostrar Servicio", servicio!=null?servicio.ServicioToMatriz():null, error);
    }

    /*public ResponseDTO create(String[][] atributosValor) {
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

*/
}
