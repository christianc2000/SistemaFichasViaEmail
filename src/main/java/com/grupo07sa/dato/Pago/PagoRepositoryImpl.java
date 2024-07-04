/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Pago;

import com.grupo07sa.client.ClientDB;
import com.grupo07sa.dato.Pago.dto.PagoDTO;
import com.grupo07sa.help.MyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class PagoRepositoryImpl implements PagoRepository {

    private ClientDB db;

    public PagoRepositoryImpl() {
        this.db = new ClientDB();
    }

    @Override
    public PagoDTO createPago(PagoDTO pago) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "INSERT INTO pagos (nit,razon_social,email,celular,trabajador,servicio,horario,tipo_servicio,costo,forma_pago,qr,paciente_id,ficha_id,fecha_expiracion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Convertir la cadena fecha_nacimiento a java.sql.Date
            // Configura los valores de los parámetros
            stmt.setString(1, pago.getNit());
            stmt.setString(2, pago.getRazon_social());
            stmt.setString(3, pago.getEmail());
            stmt.setString(4, pago.getCelular());
            stmt.setString(5, pago.getTrabajador());
            stmt.setString(6, pago.getServicio());
            stmt.setString(7, pago.getHorario());
            stmt.setString(8, pago.getTipo_servicio());
            stmt.setFloat(9, pago.getCosto());
            stmt.setString(10, pago.getForma_pago());
            stmt.setString(11, pago.getQr());
            stmt.setInt(12, pago.getPaciente_id());
            stmt.setInt(13, pago.getFicha_id());
            stmt.setString(14, pago.getFecha_expiracion());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Ahora buscar el usuario con el ID más alto
                String findSql = "SELECT * FROM pagos ORDER BY id DESC LIMIT 1";
                PreparedStatement findStmt = conn.prepareStatement(findSql);
                ResultSet rs = findStmt.executeQuery();
                if (rs.next()) {
                    // Construir y retornar el objeto UserDTO
                    PagoDTO pagoD = new PagoDTO();
                    pagoD.setId(rs.getInt("id"));
                    pagoD.setNit(rs.getString("nit"));
                    pagoD.setRazon_social(rs.getString("razon_social"));
                    pagoD.setEmail(rs.getString("email"));
                    pagoD.setCelular(rs.getString("celular"));
                    pagoD.setTrabajador(rs.getString("trabajador"));
                    pagoD.setServicio(rs.getString("servicio"));
                    pagoD.setHorario(rs.getString("horario"));
                    pagoD.setTipo_servicio(rs.getString("tipo_servicio"));
                    pagoD.setCosto(rs.getFloat("costo"));
                    pagoD.setForma_pago(rs.getString("forma_pago"));
                    pagoD.setQr(rs.getString("qr"));
                    pagoD.setEstado(rs.getString("estado"));
                    pagoD.setFecha_pago(rs.getTimestamp("fecha_pago"));
                    pagoD.setPaciente_id(rs.getInt("paciente_id"));
                    pagoD.setFicha_id(rs.getInt("ficha_id"));
                    pagoD.setCreated_at(rs.getTimestamp("created_at"));
                    pagoD.setUpdated_at(rs.getTimestamp("updated_at"));
                    //Crear Administrador
                    return pagoD;
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

   
}
