/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Consulta;

import com.grupo07sa.client.ClientDB;
import com.grupo07sa.help.MyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isais
 */
public class ConsultaRepositoryImpl implements ConsultaRepository {
    private ClientDB db;

    public ConsultaRepositoryImpl() {
        this.db = new ClientDB();
    }

    @Override
    public List<ConsultaDTO> getAllConsultas() {
        Connection conn = null;
        List<ConsultaDTO> ConsultaList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM consultas";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ConsultaDTO consulta = new ConsultaDTO();
                consulta.setId(rs.getString("id"));
                consulta.setDiagnostico(rs.getString("diagnostico"));
                consulta.setTratamiento(rs.getString("tratamiento"));
                consulta.setObservaciones(rs.getString("observaciones"));
                consulta.setCitaId(rs.getString("cita_id"));
                consulta.setHistorialMedicoId(rs.getString("historial_medico_id"));
                ConsultaList.add(consulta);
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            int errorCode = e.getErrorCode(); // Implement this method to map SQL error codes
            throw new MyException("Error al obtener consultas de la base de datos", errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
        System.out.println("cantidad de registro de consultas: " + ConsultaList.size());
        return ConsultaList;
    }

    @Override
    public ConsultaDTO findConsulta(String id) {
        Connection conn = null;
        ConsultaDTO consulta = null;
        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM consultas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                consulta = new ConsultaDTO();
                consulta.setId(rs.getString("id"));
                consulta.setDiagnostico(rs.getString("diagnostico"));
                consulta.setTratamiento(rs.getString("tratamiento"));
                consulta.setObservaciones(rs.getString("observaciones"));
                consulta.setCitaId(rs.getString("cita_id"));
                consulta.setHistorialMedicoId(rs.getString("historial_medico_id"));
            }
            return consulta;
        } catch (SQLException e) {
            throw new MyException("Error al buscar la consulta: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public ConsultaDTO createConsulta(String diagnostico, String tratamiento, String observaciones, String cita_id, String historial_medico_id) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "INSERT INTO consultas (diagnostico, tratamiento, observaciones, cita_id, historial_medico_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Configura los valores de los parámetros
            stmt.setString(1, diagnostico);
            stmt.setString(2, tratamiento);
            stmt.setString(3, observaciones);
            stmt.setInt(4, Integer.parseInt(cita_id));
            stmt.setInt(5, Integer.parseInt(historial_medico_id));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Ahora buscar el usuario con el ID más alto
                String findSql = "SELECT id, diagnostico, tratamiento, observaciones, cita_id, historial_medico_id FROM consultas ORDER BY id DESC LIMIT 1";
                PreparedStatement findStmt = conn.prepareStatement(findSql);
                ResultSet rs = findStmt.executeQuery();
                if (rs.next()) {
                    // Construir y retornar el objeto ConsultaDTO
                    ConsultaDTO consulta = new ConsultaDTO();
                    consulta.setId(rs.getString("id"));
                    consulta.setDiagnostico(rs.getString("diagnostico"));
                    consulta.setTratamiento(rs.getString("tratamiento"));
                    consulta.setObservaciones(rs.getString("observaciones"));
                    consulta.setCitaId(rs.getString("cita_id"));
                    consulta.setHistorialMedicoId(rs.getString("historial_medico_id"));
                    return consulta;
                } else {
                    throw new MyException("Failed to retrieve the new consulta", 0);
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
    public ConsultaDTO updateConsulta(ConsultaDTO consulta) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "UPDATE consultas SET diagnostico = ?, tratamiento = ?, observaciones = ?, cita_id = ?, historial_medico_id = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Configura los valores de los parámetros
            stmt.setString(1, consulta.getDiagnostico());
            stmt.setString(2, consulta.getTratamiento());
            stmt.setString(3, consulta.getObservaciones());
            stmt.setInt(4, Integer.parseInt(consulta.getCitaId()));
            stmt.setInt(5, Integer.parseInt(consulta.getHistorialMedicoId()));
            stmt.setInt(6, Integer.parseInt(consulta.getId()));
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return consulta; // Retorna el objeto ConsultaDTO actualizado
            } else {
                return null; // Retorna null si no se actualizó ningún registro
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            throw new MyException("Error al actualizar la consulta: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public List<String[]> getAtrConsultas(String[] atributos, String atrs) {
        Connection conn = null;
        List<String[]> resultList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT " + atrs + " FROM consultas";
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
            System.err.println("Error al obtener consultas de la base de datos (Código de error: " + errorCode + ")");
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }

        return resultList;
    }

    @Override
    public boolean deleteConsultaById(String id) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "DELETE FROM consultas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            throw new MyException("Error al eliminar la consulta: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }
}
