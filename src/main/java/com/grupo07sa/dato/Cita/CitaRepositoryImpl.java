/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Cita;

import com.grupo07sa.client.ClientDB;
import com.grupo07sa.dato.User.UserDTO;
import com.grupo07sa.help.MyException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isais
 */
public class CitaRepositoryImpl implements CitaRepository {

    private ClientDB db;

    public CitaRepositoryImpl() {
        this.db = new ClientDB();
    }

    @Override
    public List<CitaDTO> getAllCitas() {
        Connection conn = null;
        List<CitaDTO> citaList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM citas";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CitaDTO cita = new CitaDTO();
                cita.setId(rs.getString("id"));
                cita.setFechaHora(rs.getString("fecha_hora"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setPacienteId(rs.getString("paciente_id"));
                cita.setTrabajadorId(rs.getString("trabajador_id"));
                cita.setSalaId(rs.getString("sala_id"));
                cita.setPagoId(rs.getString("pago_id"));
                citaList.add(cita);
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            int errorCode = e.getErrorCode(); // Implement this method to map SQL error codes
            throw new MyException("Error al obtener citas de la base de datos", errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
        System.out.println("cantidad de registro de citas: " + citaList.size());
        return citaList;
    }

    @Override
    public CitaDTO findCita(String id) {
        Connection conn = null;
        CitaDTO cita = null;
        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM citas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cita = new CitaDTO();
                cita.setId(rs.getString("id"));
                cita.setFechaHora(rs.getString("fecha_hora"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setPacienteId(rs.getString("paciente_id"));
                cita.setTrabajadorId(rs.getString("trabajador_id"));
                cita.setSalaId(rs.getString("sala_id"));
                cita.setPagoId(rs.getString("pago_id"));
            }
            return cita;
        } catch (SQLException e) {
            throw new MyException("Error al buscar la cita: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public CitaDTO createCita(String fecha_hora, String motivo, String paciente_id, String trabajador_id, String sala_id, String pago_id) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "INSERT INTO citas (fecha_hora, motivo, paciente_id, trabajador_id, sala_id, pago_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Convertir la cadena fecha_nacimiento a java.sql.Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(fecha_hora, formatter);
            Date sqlDate = Date.valueOf(localDate);

            // Configura los valores de los parámetros
            stmt.setDate(1, sqlDate);
            stmt.setString(2, motivo);
            stmt.setInt(3, Integer.parseInt(paciente_id));
            stmt.setInt(4, Integer.parseInt(trabajador_id));
            stmt.setInt(5, Integer.parseInt(sala_id));
            stmt.setInt(6, Integer.parseInt(pago_id));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Ahora buscar el usuario con el ID más alto
                String findSql = "SELECT id, fecha_hora, motivo, paciente_id, trabajador_id, sala_id, pago_id FROM citas ORDER BY id DESC LIMIT 1";
                PreparedStatement findStmt = conn.prepareStatement(findSql);
                ResultSet rs = findStmt.executeQuery();
                if (rs.next()) {
                    // Construir y retornar el objeto CitaDTO
                    CitaDTO cita = new CitaDTO();
                    cita.setId(rs.getString("id"));
                    cita.setFechaHora(rs.getString("fecha_hora"));
                    cita.setMotivo(rs.getString("motivo"));
                    cita.setPacienteId(rs.getString("paciente_id"));
                    cita.setTrabajadorId(rs.getString("trabajador_id"));
                    cita.setSalaId(rs.getString("sala_id"));
                    cita.setPagoId(rs.getString("pago_id"));
                    return cita;
                } else {
                    throw new MyException("Failed to retrieve the new cita", 0);
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
    public CitaDTO updateCita(CitaDTO cita) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "UPDATE citas SET fecha_hora = ?, motivo = ?, paciente_id = ?, trabajador_id = ?, sala_id = ?, pago_id = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Convertir la cadena fecha_nacimiento a java.sql.Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(cita.getFechaHora(), formatter);
            Date sqlDate = Date.valueOf(localDate);

            // Configura los valores de los parámetros
            stmt.setDate(1, sqlDate);
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, Integer.parseInt(cita.getPacienteId()));
            stmt.setInt(4, Integer.parseInt(cita.getTrabajadorId()));
            stmt.setInt(5, Integer.parseInt(cita.getSalaId()));
            stmt.setInt(6, Integer.parseInt(cita.getPagoId()));
            stmt.setInt(7, Integer.parseInt(cita.getId()));
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return cita; // Retorna el objeto UserDTO actualizado
            } else {
                return null; // Retorna null si no se actualizó ningún registro
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            throw new MyException("Error al actualizar el cita: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public List<String[]> getAtrCitas(String[] atributos, String atrs) {
        Connection conn = null;
        List<String[]> resultList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT " + atrs + " FROM citas";
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
            System.err.println("Error al obtener citas de la base de datos (Código de error: " + errorCode + ")");
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }

        return resultList;
    }

    @Override
    public List<String[]> getAtrPagos(String[] atributos, String atrs) {
        Connection conn = null;
        List<String[]> resultList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT \n"
            + "    EXTRACT(MONTH FROM fecha_pago) AS mes,\n"
            + "    SUM(costo) AS total_pago\n"
            + "FROM \n"
            + "    pagos\n"
            + "GROUP BY \n"
            + "    EXTRACT(YEAR FROM fecha_pago), EXTRACT(MONTH FROM fecha_pago)\n"
            + "ORDER BY \n"
            + "    mes;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] valores = new String[2];
                    valores[0] = rs.getString("mes");
                    valores[1] = rs.getString("total_pago");
                    
                resultList.add(valores);
            }
        } catch (SQLException e) {
            // Maneja la excepción aquí (puedes loguearla o tomar otras acciones)
            int errorCode = e.getErrorCode(); // Implementa la lógica para mapear códigos de error
            System.err.println("Error al obtener los pagos por mes de la base de datos (Código de error: " + errorCode + ")");
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }

        return resultList;
    }
    
    @Override
    public List<String[]> getAtrMedicos(String[] atributos, String atrs) {
        Connection conn = null;
        List<String[]> resultList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT "
           + "t.especialidad AS trabajador, "
           + "COUNT(c.id) AS total_citas "
           + "FROM trabajadors t "
           + "JOIN citas c ON t.id = c.trabajador_id "
           + "GROUP BY t.especialidad "
           + "ORDER BY total_citas DESC;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] valores = new String[2];
                    valores[0] = rs.getString("trabajador");
                    valores[1] = rs.getString("total_citas");
                    
                resultList.add(valores);
            }
        } catch (SQLException e) {
            // Maneja la excepción aquí (puedes loguearla o tomar otras acciones)
            int errorCode = e.getErrorCode(); // Implementa la lógica para mapear códigos de error
            System.err.println("Error al obtener los medicos por mes de la base de datos (Código de error: " + errorCode + ")");
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }

        return resultList;
    }

    @Override
    public boolean deleteCitaById(String id) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "DELETE FROM citas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            throw new MyException("Error al eliminar la cita: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }
}
