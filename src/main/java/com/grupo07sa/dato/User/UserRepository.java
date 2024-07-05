/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupo07sa.dato.User;

import com.grupo07sa.dato.User.dto.UserDTO;
import com.grupo07sa.dato.ResponseDTO;
import com.grupo07sa.dato.User.dto.AdministradorDTO;
import com.grupo07sa.dato.User.dto.PacienteDTO;
import com.grupo07sa.dato.User.dto.TrabajadorDTO;
import java.util.List;

/**
 *
 * @author USER
 */
public interface UserRepository {
    //USER
    List<UserDTO> getAllUsers();
    List<String[]> getAtrUsers(String[] atributos, String atrs);
    UserDTO createUser(String ci, String name, String lastname, String fecha_nacimiento, String foto, String direccion, String gender, String celular, String email, String password, String nit, String razon_social);
    UserDTO findUser(String id);
    UserDTO updateUser(UserDTO user);
    boolean deleteUserById(String id);

    //ADMINISTRADOR
    List<AdministradorDTO> getAllAdministrador();
    AdministradorDTO createAdministrador(String id);
    AdministradorDTO findAdministrador(String id);
    AdministradorDTO updateAdministrador(AdministradorDTO admin);
    boolean deleteAdministradorUserById(String id);

    //TRABAJADOR
    List<TrabajadorDTO> getAllTrabajador();
    TrabajadorDTO createTrabajador(String id, String especialidad, String tipo);
    TrabajadorDTO findTrabajador(String id);
    TrabajadorDTO updateTrabajador(TrabajadorDTO admin);
    boolean deleteTrabajadorUserById(String id);

    //PACIENTE
    List<PacienteDTO> getAllPaciente();
    PacienteDTO createPaciente(String id, String profesion);
    PacienteDTO findPaciente(String id);
    PacienteDTO updatePaciente(PacienteDTO admin);
    boolean deletePacienteUserById(String id);
}
