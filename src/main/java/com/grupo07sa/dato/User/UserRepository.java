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
    ResponseDTO createUser(String name, String email, String password);
}
