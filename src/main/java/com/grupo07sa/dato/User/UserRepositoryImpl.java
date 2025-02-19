/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.User;

import com.grupo07sa.client.ClientDB;
import com.grupo07sa.dato.ResponseDTO;
import com.grupo07sa.help.MyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.grupo07sa.help.MyException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author USER
 */
public class UserRepositoryImpl implements UserRepository {

    private ClientDB db;

    public UserRepositoryImpl() {
        this.db = new ClientDB();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        Connection conn = null;
        List<UserDTO> userList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM users";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setId(rs.getString("id"));
                user.setCi(rs.getString("ci"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                user.setFoto(rs.getString("foto"));
                user.setDireccion(rs.getString("direccion"));
                user.setGender(rs.getString("gender"));
                user.setCelular(rs.getString("celular"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNit(rs.getString("nit"));
                user.setRazon_social(rs.getString("razon_social"));
                userList.add(user);
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            int errorCode = e.getErrorCode(); // Implement this method to map SQL error codes
            throw new MyException("Error al obtener usuarios de la base de datos", errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
        System.out.println("cantidad de registro de usuarios: " + userList.size());
        return userList;
    }

    @Override
    public UserDTO findUser(String id) {
        Connection conn = null;
        UserDTO user = null;
        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new UserDTO();
                user.setId(rs.getString("id"));
                user.setCi(rs.getString("ci"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                user.setFoto(rs.getString("foto"));
                user.setDireccion(rs.getString("direccion"));
                user.setGender(rs.getString("gender"));
                user.setCelular(rs.getString("celular"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNit(rs.getString("nit"));
                user.setRazon_social(rs.getString("razon_social"));
            }
            return user;
        } catch (SQLException e) {
            throw new MyException("Error al buscar el usuario: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public UserDTO createUser(String ci, String name, String lastname, String fecha_nacimiento, String foto, String direccion, String gender, String celular, String email, String password, String nit, String razon_social) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "INSERT INTO users (ci, name, lastname, fecha_nacimiento, foto, direccion, gender, celular, email, password, nit, razon_social) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Convertir la cadena fecha_nacimiento a java.sql.Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(fecha_nacimiento, formatter);
            Date sqlDate = Date.valueOf(localDate);

            // Configura los valores de los parámetros
            stmt.setString(1, ci);
            stmt.setString(2, name);
            stmt.setString(3, lastname);
            stmt.setDate(4, sqlDate);
            stmt.setString(5, foto);
            stmt.setString(6, direccion);
            stmt.setString(7, gender);
            stmt.setString(8, celular);
            stmt.setString(9, email);
            stmt.setString(10, password);
            stmt.setString(11, nit);
            stmt.setString(12, razon_social);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Ahora buscar el usuario con el ID más alto
                String findSql = "SELECT id, ci, name, lastname, fecha_nacimiento, foto, direccion, gender, celular, email, password, nit, razon_social FROM users ORDER BY id DESC LIMIT 1";
                PreparedStatement findStmt = conn.prepareStatement(findSql);
                ResultSet rs = findStmt.executeQuery();
                if (rs.next()) {
                    // Construir y retornar el objeto UserDTO
                    UserDTO user = new UserDTO();
                    user.setId(rs.getString("id"));
                    user.setCi(rs.getString("ci"));
                    user.setName(rs.getString("name"));
                    user.setLastname(rs.getString("lastname"));
                    user.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                    user.setFoto(rs.getString("foto"));
                    user.setDireccion(rs.getString("direccion"));
                    user.setGender(rs.getString("gender"));
                    user.setCelular(rs.getString("celular"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setNit(rs.getString("nit"));
                    user.setRazon_social(rs.getString("razon_social"));
                    return user;
                } else {
                    throw new MyException("Failed to retrieve the new user", 0);
                }
            } else {
                throw new MyException("No rows affected", 0);
            }
        } catch (SQLException e) {
            int errorCode = e.getErrorCode();
            throw new MyException("" + e, errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "UPDATE users SET ci = ?, name = ?, lastname = ?, fecha_nacimiento = ?, foto = ?, direccion = ?, gender = ?, celular = ?, email = ?, password = ?, nit = ?, razon_social = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Convertir la cadena fecha_nacimiento a java.sql.Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(user.getFecha_nacimiento(), formatter);
            Date sqlDate = Date.valueOf(localDate);

            // Configura los valores de los parámetros
            stmt.setString(1, user.getCi());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getLastname());
            stmt.setDate(4, sqlDate);
            stmt.setString(5, user.getFoto());
            stmt.setString(6, user.getDireccion());
            stmt.setString(7, user.getGender());
            stmt.setString(8, user.getCelular());
            stmt.setString(9, user.getEmail());
            stmt.setString(10, user.getPassword());
            stmt.setString(11, user.getNit());
            stmt.setString(12, user.getRazon_social());
            stmt.setInt(13, Integer.parseInt(user.getId()));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return user; // Retorna el objeto UserDTO actualizado
            } else {
                return null; // Retorna null si no se actualizó ningún registro
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            throw new MyException("Error al actualizar el usuario: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public List<String[]> getAtrUsers(String[] atributos, String atrs) {
        Connection conn = null;
        List<String[]> resultList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT " + atrs + " FROM users";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] valores = new String[atributos.length];
                for (int i = 0; i < atributos.length; i++) {
                    valores[i] = rs.getString(atributos[i]);
                }
                resultList.add(valores);
            }
        } catch (SQLException e) {
            // Maneja la excepción aquí (puedes loguearla o tomar otras acciones)
            int errorCode = e.getErrorCode(); // Implementa la lógica para mapear códigos de error
            System.err.println("Error al obtener usuarios de la base de datos (Código de error: " + errorCode + ")");
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }

        return resultList;
    }

    @Override
    public boolean deleteUserById(String id) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            throw new MyException("Error al eliminar el usuario: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }
}
