/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.HistorialMedico;

import com.grupo07sa.client.ClientDB;
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
public class HistorialMedicoRepositoryImpl implements HistorialMedicoRepository {
    private ClientDB db;

    public HistorialMedicoRepositoryImpl() {
        this.db = new ClientDB();
    }

    @Override
    public List<HistorialMedicoDTO> getAllHistorialMedicos() {
        Connection conn = null;
        List<HistorialMedicoDTO> historialList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM historial_medicos";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HistorialMedicoDTO historial = new HistorialMedicoDTO();
                historial.setId(rs.getString("id"));
                historial.setFechaCreacion(rs.getString("fecha_creacion"));
                historial.setObservaciones(rs.getString("observaciones_generales"));
                historial.setPacienteId(rs.getString("paciente_id"));
                historialList.add(historial);
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            int errorCode = e.getErrorCode(); // Implement this method to map SQL error codes
            throw new MyException("Error al obtener historiales de la base de datos", errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
        System.out.println("cantidad de registro de historial: " + historialList.size());
        return historialList;
    }

    @Override
    public HistorialMedicoDTO findHistorialMedico(String id) {
        Connection conn = null;
        HistorialMedicoDTO historial = null;
        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM historial_medicos WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                historial = new HistorialMedicoDTO();
                historial.setId(rs.getString("id"));
                historial.setFechaCreacion(rs.getString("fecha_creacion"));
                historial.setObservaciones(rs.getString("observaciones_generales"));
                historial.setPacienteId(rs.getString("paciente_id"));
            }
            return historial;
        } catch (SQLException e) {
            throw new MyException("Error al buscar el historial: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public HistorialMedicoDTO createHistorialMedico(String fecha_creacion, String observaciones_generales, String paciente_id) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "INSERT INTO historial_medicos (fecha_creacion, observaciones_generales, paciente_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Convertir la cadena fecha_nacimiento a java.sql.Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(fecha_creacion, formatter);
            Date sqlDate = Date.valueOf(localDate);

            // Configura los valores de los parámetros
            stmt.setDate(1, sqlDate);
            stmt.setString(2, observaciones_generales);
            stmt.setInt(3, Integer.parseInt(paciente_id));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Ahora buscar el usuario con el ID más alto
                String findSql = "SELECT id, fecha_creacion, observaciones_generales, paciente_id FROM historial_medicos ORDER BY id DESC LIMIT 1";
                PreparedStatement findStmt = conn.prepareStatement(findSql);
                ResultSet rs = findStmt.executeQuery();
                if (rs.next()) {
                    // Construir y retornar el objeto CitaDTO
                    HistorialMedicoDTO historial = new HistorialMedicoDTO();
                    historial.setId(rs.getString("id"));
                    historial.setFechaCreacion(rs.getString("fecha_creacion"));
                    historial.setObservaciones(rs.getString("observaciones_generales"));
                    historial.setPacienteId(rs.getString("paciente_id"));
                    return historial;
                } else {
                    throw new MyException("Failed to retrieve the new historial", 0);
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
    public HistorialMedicoDTO updateHistorialMedico(HistorialMedicoDTO historial) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "UPDATE historial_medicos SET fecha_creacion = ?, observaciones_generales = ?, paciente_id = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Convertir la cadena fecha_nacimiento a java.sql.Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(historial.getFechaCreacion(), formatter);
            Date sqlDate = Date.valueOf(localDate);

            // Configura los valores de los parámetros
            stmt.setDate(1, sqlDate);
            stmt.setString(2, historial.getObservaciones());
            stmt.setInt(3, Integer.parseInt(historial.getPacienteId()));
            stmt.setInt(4, Integer.parseInt(historial.getId()));
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return historial; // Retorna el objeto UserDTO actualizado
            } else {
                return null; // Retorna null si no se actualizó ningún registro
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            throw new MyException("Error al actualizar el historial : " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public List<String[]> getAtrHistorialMedicos(String[] atributos, String atrs) {
        Connection conn = null;
        List<String[]> resultList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT " + atrs + " FROM historial_medicos";
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
            System.err.println("Error al obtener historial de la base de datos (Código de error: " + errorCode + ")");
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }

        return resultList;
    }

    @Override
    public boolean deleteHistorialMedicoById(String id) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "DELETE FROM historial_medicos WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            throw new MyException("Error al eliminar el historial: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }
}
