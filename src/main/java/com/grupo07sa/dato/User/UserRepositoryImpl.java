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
import java.util.Map;

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

    public boolean createUser(String atributos, String[] valores) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "INSERT INTO users (" + atributos + ") VALUES (?, ?, ?, ?, ?)"; // Ajusta según la cantidad de columnas
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Configura los valores de los parámetros
            for (int i = 0; i < valores.length; i++) {
                stmt.setString(i + 1, valores[i]); // Ajusta según los tipos de datos (setString, setInt, etc.)
            }

            int rowsAffected = stmt.executeUpdate();
            // Si se inserta correctamente, retorna true
            return rowsAffected > 0;
        } catch (SQLException e) {
            int errorCode = e.getErrorCode(); // Implement this method to map SQL error codes
            throw new MyException("Error al inserta el usuario", errorCode);
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

}
