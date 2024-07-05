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
import java.util.ArrayList;
import java.util.List;

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

    /*@Override
    public List<PagoDTO> getAllPagos() {
        Connection conn = null;
        List<PagoDTO> pagoList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT *  FROM pagos, pacientes, users WHERE users.id=pacientes.id AND pagos.paciente_id=pacientes.id;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TrabajadorDTO trabajador = new TrabajadorDTO();
                trabajador.setId(rs.getString("id"));
                trabajador.setCi(rs.getString("ci"));
                trabajador.setName(rs.getString("name"));
                trabajador.setLastname(rs.getString("lastname"));
                trabajador.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                trabajador.setFoto(rs.getString("foto"));
                trabajador.setDireccion(rs.getString("direccion"));
                trabajador.setGender(rs.getString("gender"));
                trabajador.setCelular(rs.getString("celular"));
                trabajador.setEmail(rs.getString("email"));
                trabajador.setPassword(rs.getString("password"));
                trabajador.setNit(rs.getString("nit"));
                trabajador.setRazon_social(rs.getString("razon_social"));
                trabajador.setEspecialidad(rs.getString("especialidad"));
                trabajador.setTipo(rs.getString("tipo"));
                trabajador.setCreated_at(rs.getTimestamp("created_at_t"));
                trabajador.setUpdated_at(rs.getTimestamp("updated_at_t"));
                trabajadorList.add(trabajador);
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
        System.out.println("cantidad de registro de usuarios: " + trabajadorList.size());
        return trabajadorList;
    }*/
    @Override
    public List<PagoDTO> getAllPagos() {
        Connection conn = null;
        List<PagoDTO> pagoList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM pagos;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PagoDTO pago = new PagoDTO();
                pago.setId(rs.getInt("id"));
                pago.setNit(rs.getString("nit"));
                pago.setRazon_social(rs.getString("razon_social"));
                pago.setEmail(rs.getString("email"));
                pago.setCelular(rs.getString("celular"));
                pago.setTrabajador(rs.getString("trabajador"));
                pago.setServicio(rs.getString("servicio"));
                pago.setHorario(rs.getString("horario"));
                pago.setTipo_servicio(rs.getString("tipo_servicio"));
                pago.setCosto(rs.getFloat("costo"));
                pago.setForma_pago(rs.getString("forma_pago"));
                pago.setQr(rs.getString("qr"));
                pago.setEstado(rs.getString("estado"));
                pago.setFecha_pago(rs.getTimestamp("fecha_pago"));
                pago.setFecha_expiracion(rs.getString("fecha_expiracion"));
                pago.setPaciente_id(rs.getInt("paciente_id"));
                pago.setFicha_id(rs.getInt("ficha_id"));
                pago.setCreated_at(rs.getTimestamp("created_at"));
                pago.setUpdated_at(rs.getTimestamp("updated_at"));
                pagoList.add(pago);
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            int errorCode = e.getErrorCode(); // Implement this method to map SQL error codes
            throw new MyException("Error al obtener pagos de la base de datos", errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
        System.out.println("cantidad de registro de pagos: " + pagoList.size());
        return pagoList;
    }

}
