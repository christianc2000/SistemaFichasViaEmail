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
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                user.setFoto(rs.getString("foto"));
                user.setDireccion(rs.getString("direccion"));

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
        System.out.println("cantidad de registro de usuarios: "+userList.size());
        return userList;
    }

    @Override
    public ResponseDTO createUser(String name, String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
