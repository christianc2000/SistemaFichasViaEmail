/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.service;

import com.grupo07sa.dato.ResponseDTO;
import com.grupo07sa.dato.User.dto.UserDTO;
import com.grupo07sa.dato.User.UserRepositoryImpl;
import com.grupo07sa.dato.User.dto.AdministradorDTO;
import com.grupo07sa.dato.User.dto.PacienteDTO;
import com.grupo07sa.dato.User.dto.TrabajadorDTO;
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

    public ResponseDTO allAdmin() {
        List<AdministradorDTO> administradors = null;
        String error = null;
        try {
            administradors = userRepositoryImpl.getAllAdministrador();
        } catch (Exception e) {
            error = "Error al obtener los admin: " + e.getMessage();
        }
        String title = "Lista de Administradores";
        String[][] data = null;

        if (administradors != null) {
            data = new String[administradors.size() + 1][15];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de administradores: " + administradors.size());
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
            data[0][13] = "FECHA DE CREACION";
            data[0][14] = "FECHA DE ACTUALIZACION";
            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < administradors.size(); i++) {
                AdministradorDTO admin = administradors.get(i);
                data[i + 1][0] = admin.getId();
                data[i + 1][1] = admin.getCi();
                data[i + 1][2] = admin.getName();
                data[i + 1][3] = admin.getLastname();
                data[i + 1][4] = admin.getFecha_nacimiento();
                data[i + 1][5] = admin.getFoto();
                data[i + 1][6] = admin.getDireccion();
                data[i + 1][7] = admin.getGender();
                data[i + 1][8] = admin.getCelular();
                data[i + 1][9] = admin.getEmail();
                data[i + 1][10] = admin.getPassword();
                data[i + 1][11] = admin.getNit();
                data[i + 1][12] = admin.getRazon_social();
                data[i + 1][13] = String.valueOf(admin.getCreated_at());
                data[i + 1][14] = String.valueOf(admin.getUpdated_at());

            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO allPacientes() {
        List<PacienteDTO> pacientes = null;
        String error = null;
        try {
            pacientes = userRepositoryImpl.getAllPaciente();
        } catch (Exception e) {
            error = "Error al obtener los pacientes: " + e.getMessage();
        }
        String title = "Lista de Pacientes";
        String[][] data = null;

        if (pacientes != null) {
            data = new String[pacientes.size() + 1][16];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de pacientes: " + pacientes.size());
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
            data[0][13] = "PROFESION";
            data[0][14] = "FECHA DE CREACION";
            data[0][15] = "FECHA DE ACTUALIZACION";
            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < pacientes.size(); i++) {
                PacienteDTO p = pacientes.get(i);
                data[i + 1][0] = p.getId();
                data[i + 1][1] = p.getCi();
                data[i + 1][2] = p.getName();
                data[i + 1][3] = p.getLastname();
                data[i + 1][4] = p.getFecha_nacimiento();
                data[i + 1][5] = p.getFoto();
                data[i + 1][6] = p.getDireccion();
                data[i + 1][7] = p.getGender();
                data[i + 1][8] = p.getCelular();
                data[i + 1][9] = p.getEmail();
                data[i + 1][10] = p.getPassword();
                data[i + 1][11] = p.getNit();
                data[i + 1][12] = p.getRazon_social();
                data[i + 1][13] = p.getProfesion();
                data[i + 1][14] = String.valueOf(p.getCreated_at());
                data[i + 1][15] = String.valueOf(p.getUpdated_at());

            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO allTrabajadores() {
        List<TrabajadorDTO> trabajadores = null;
        String error = null;
        try {
            trabajadores = userRepositoryImpl.getAllTrabajador();
        } catch (Exception e) {
            error = "Error al obtener los trabajadores: " + e.getMessage();
        }
        String title = "Lista de Trabajadores";
        String[][] data = null;

        if (trabajadores != null) {
            data = new String[trabajadores.size() + 1][17];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de trabajadores: " + trabajadores.size());
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
            data[0][13] = "ESPECIALIDAD";
            data[0][14] = "TIPO";
            data[0][15] = "FECHA DE CREACION";
            data[0][16] = "FECHA DE ACTUALIZACION";
            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < trabajadores.size(); i++) {
                TrabajadorDTO trabajador = trabajadores.get(i);
                data[i + 1][0] = trabajador.getId();
                data[i + 1][1] = trabajador.getCi();
                data[i + 1][2] = trabajador.getName();
                data[i + 1][3] = trabajador.getLastname();
                data[i + 1][4] = trabajador.getFecha_nacimiento();
                data[i + 1][5] = trabajador.getFoto();
                data[i + 1][6] = trabajador.getDireccion();
                data[i + 1][7] = trabajador.getGender();
                data[i + 1][8] = trabajador.getCelular();
                data[i + 1][9] = trabajador.getEmail();
                data[i + 1][10] = trabajador.getPassword();
                data[i + 1][11] = trabajador.getNit();
                data[i + 1][12] = trabajador.getRazon_social();
                data[i + 1][13] = trabajador.getEspecialidad();
                data[i + 1][14] = trabajador.getTipo() == "M" ? "Médico" : "Enfermera(o)";
                data[i + 1][15] = String.valueOf(trabajador.getCreated_at());
                data[i + 1][16] = String.valueOf(trabajador.getUpdated_at());

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
            user = null;
        }
        return new ResponseDTO("Mostrar usuario", user != null ? user.UserToMatriz() : null, error);
    }

    public ResponseDTO findAdmin(String[][] atributosValor) {
        AdministradorDTO admin = new AdministradorDTO();

        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            System.out.println("Seteando atributo: " + atributosValor[i][0] + " con valor: " + atributosValor[i][1]);
            admin.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (admin.getId() == null) {
            System.out.println("ID NO VALIDO");
            error = "Error de validación";
            return new ResponseDTO("Mostrar Administrador", null, error);
        }
        try {
            System.out.println("id: " + admin.getId());
            admin = userRepositoryImpl.findAdministrador(admin.getId());
        } catch (Exception e) {
            error = "Error: " + e;
            admin = null;
        }
        return new ResponseDTO("Mostrar Administrador", admin != null ? admin.UserToMatriz() : null, error);
    }

    public ResponseDTO findPaciente(String[][] atributosValor) {
        PacienteDTO paciente = new PacienteDTO();

        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            System.out.println("Seteando atributo: " + atributosValor[i][0] + " con valor: " + atributosValor[i][1]);
            paciente.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (paciente.getId() == null) {
            System.out.println("ID NO VALIDO");
            error = "Error de validación";
            return new ResponseDTO("Mostrar Paciente", null, error);
        }
        try {
            System.out.println("id: " + paciente.getId());
            paciente = userRepositoryImpl.findPaciente(paciente.getId());
        } catch (Exception e) {
            error = "Error: " + e;
            paciente = null;
        }
        return new ResponseDTO("Mostrar Paciente", paciente != null ? paciente.UserToMatriz() : null, error);
    }

    public ResponseDTO findTrabajador(String[][] atributosValor) {
        TrabajadorDTO trabajador = new TrabajadorDTO();

        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            System.out.println("Seteando atributo: " + atributosValor[i][0] + " con valor: " + atributosValor[i][1]);
            trabajador.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (trabajador.getId() == null) {
            System.out.println("ID NO VALIDO");
            error = "Error de validación";
            return new ResponseDTO("Mostrar Trabajador", null, error);
        }
        try {
            System.out.println("id: " + trabajador.getId());
            trabajador = userRepositoryImpl.findTrabajador(trabajador.getId());
        } catch (Exception e) {
            error = "Error: " + e;
            trabajador = null;
        }
        return new ResponseDTO("Mostrar Trabajador", trabajador != null ? trabajador.UserToMatriz() : null, error);
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

    public ResponseDTO createAdmin(String[][] atributosValor) {
        AdministradorDTO admin = new AdministradorDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            admin.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        try {
            if (admin.getId() != null) {
                admin = userRepositoryImpl.createAdministrador(admin.getId());
            } else {
                admin = null;
                error = "Error: Datos invalidos para crear un usuario";
            }

        } catch (Exception e) {
            error = "Error: " + e;
        }

        return new ResponseDTO("Crear Administrador", admin != null ? admin.UserToMatriz() : null, error);
    }

    public ResponseDTO createPaciente(String[][] atributosValor) {
        PacienteDTO paciente = new PacienteDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            paciente.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        try {
            if (paciente.getId() != null) {
                paciente = userRepositoryImpl.createPaciente(paciente.getId(), paciente.getProfesion());
            } else {
                paciente = null;
                error = "Error: Datos invalidos para crear un usuario";
            }

        } catch (Exception e) {
            error = "Error: " + e;
        }

        return new ResponseDTO("Crear Paciente", paciente != null ? paciente.UserToMatriz() : null, error);
    }

    public ResponseDTO createTrabajador(String[][] atributosValor) {
        TrabajadorDTO trabajador = new TrabajadorDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            trabajador.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        try {
            if (trabajador.getId() != null) {
                trabajador = userRepositoryImpl.createTrabajador(trabajador.getId(), trabajador.getEspecialidad(), trabajador.getTipo());
            } else {
                trabajador = null;
                error = "Error: Datos invalidos para crear un usuario";
            }

        } catch (Exception e) {
            error = "Error: " + e;
        }

        return new ResponseDTO("Crear Trabajador", trabajador != null ? trabajador.UserToMatriz() : null, error);
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
                /* if (user == null) {
                    error = "Error al actualizar el usuario";
                }*/
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

    public ResponseDTO deleteAdmin(String[][] atributosValor) {
        AdministradorDTO admin = new AdministradorDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            System.out.println("Seteando atributo: " + atributosValor[i][0] + " con valor: " + atributosValor[i][1]);
            admin.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (admin.getId() == null) {
            error = "Error de validación";
            return new ResponseDTO("Buscar el usuario admin", null, error);
        }
        boolean isDeleted = false;
        try {
            isDeleted = userRepositoryImpl.deleteUserById(admin.getId());
            if (!isDeleted) {
                error = "Error al intentar eliminar al usuario admin";
            }
        } catch (Exception e) {
            error = "Error al eliminar el usuario admin: " + e.getMessage();
        }
        return new ResponseDTO("Eliminar usuario admin", null, error);
    }

    public ResponseDTO deletePaciente(String[][] atributosValor) {
        PacienteDTO paciente = new PacienteDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            System.out.println("Seteando atributo: " + atributosValor[i][0] + " con valor: " + atributosValor[i][1]);
            paciente.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (paciente.getId() == null) {
            error = "Error de validación";
            return new ResponseDTO("Mostrar el usuario paciente", null, error);
        }
        boolean isDeleted = false;
        try {
            isDeleted = userRepositoryImpl.deletePacienteUserById(paciente.getId());
            if (!isDeleted) {
                error = "Error al intentar eliminar al usuario paciente";
            }
        } catch (Exception e) {
            error = "Error al eliminar el usuario paciente: " + e.getMessage();
        }
        return new ResponseDTO("Eliminar usuario paciente", null, error);
    }

    public ResponseDTO deleteTrabajador(String[][] atributosValor) {
        TrabajadorDTO trabajador = new TrabajadorDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            System.out.println("Seteando atributo: " + atributosValor[i][0] + " con valor: " + atributosValor[i][1]);
            trabajador.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (trabajador.getId() == null) {
            error = "Error de validación";
            return new ResponseDTO("Mostrar el usuario trabajador", null, error);
        }
        boolean isDeleted = false;
        try {
            isDeleted = userRepositoryImpl.deleteTrabajadorUserById(trabajador.getId());
            if (!isDeleted) {
                error = "Error al intentar eliminar al usuario trabajador";
            }
        } catch (Exception e) {
            error = "Error al eliminar el usuario trabajador: " + e.getMessage();
        }
        return new ResponseDTO("Eliminar usuario trabajador", null, error);
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
