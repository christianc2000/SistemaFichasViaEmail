/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupo07sa.dato.User;

import com.grupo07sa.dato.ResponseDTO;
import java.util.List;

/**
 *
 * @author USER
 */
public interface UserRepository{
    List<UserDTO> getAllUsers();
    List<String[]> getAtrUsers(String[] atributos, String atrs);
    UserDTO createUser(String ci, String name, String lastname, String fecha_nacimiento, String foto, String direccion, String gender, String celular, String email, String password, String nit, String razon_social);
    UserDTO findUser(String id);
    UserDTO updateUser(UserDTO user);
    boolean deleteUserById(String id);
}
