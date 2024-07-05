/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Ficha;

import com.grupo07sa.client.ClientDB;
import com.grupo07sa.dato.Ficha.dto.DiaDTO;
import com.grupo07sa.dato.Ficha.dto.FichaDTO;
import com.grupo07sa.dato.Ficha.dto.HorarioDTO;
import com.grupo07sa.dato.Ficha.dto.HorarioServicioDTO;
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
public class FichaRepositoryImpl implements FichaRepository {

    private ClientDB db;

    public FichaRepositoryImpl() {
        this.db = new ClientDB();
    }

    @Override
    public List<FichaDTO> getAllFichas() {
        Connection conn = null;
        List<FichaDTO> trabajadorList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT *  FROM fichas;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FichaDTO fichas = new FichaDTO();
                fichas.setId(rs.getInt("id"));
                fichas.setCantidad(rs.getInt("cantidad"));
                fichas.setFecha(rs.getDate("fecha"));
                fichas.setCantidad_vendido(rs.getInt("cantidad_vendido"));
                fichas.setHorario_servicio_id(rs.getInt("horario_servicio_id"));
                fichas.setCreated_at(rs.getTimestamp("created_at"));
                fichas.setUpdated_at(rs.getTimestamp("updated_at"));
                trabajadorList.add(fichas);
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            int errorCode = e.getErrorCode(); // Implement this method to map SQL error codes
            throw new MyException("Error al obtener fichas de la base de datos", errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
        System.out.println("cantidad de registro de fichas: " + trabajadorList.size());
        return trabajadorList;
    }

    @Override
    public List<HorarioServicioDTO> getAllHorariosServicio() {
        Connection conn = null;
        List<HorarioServicioDTO> horarioServicioList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM horario_servicios;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HorarioServicioDTO horarioServicios = new HorarioServicioDTO();
                horarioServicios.setId(rs.getInt("id"));
                horarioServicios.setEstado(rs.getBoolean("estado"));
                horarioServicios.setServicio_id(rs.getInt("servicio_id"));
                horarioServicios.setHorario_id(rs.getInt("horario_id"));
                horarioServicios.setTrabajador_id(rs.getInt("trabajador_id"));
                horarioServicios.setCreated_at(rs.getTimestamp("created_at"));
                horarioServicios.setUpdated_at(rs.getTimestamp("updated_at"));
                horarioServicioList.add(horarioServicios);
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            int errorCode = e.getErrorCode(); // Implement this method to map SQL error codes
            throw new MyException("Error al obtener horario servicio de la base de datos", errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
        System.out.println("cantidad de registro de horario servicio: " + horarioServicioList.size());
        return horarioServicioList;
    }

    @Override
    public List<HorarioDTO> getAllHorarios() {
        Connection conn = null;
        List<HorarioDTO> HorarioList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM horarios;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HorarioDTO horario = new HorarioDTO();
                horario.setId(rs.getInt("id"));
                horario.setEstado(rs.getBoolean("estado"));
                horario.setHora_inicio(rs.getTime("hora_inicio"));
                horario.setHora_fin(rs.getTime("hora_fin"));
                horario.setDia_id(rs.getInt("dia_id"));
                horario.setCreated_at(rs.getTimestamp("created_at"));
                horario.setUpdated_at(rs.getTimestamp("updated_at"));
                HorarioList.add(horario);
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            int errorCode = e.getErrorCode(); // Implement this method to map SQL error codes
            throw new MyException("Error al obtener horario de la base de datos", errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
        System.out.println("cantidad de registro de horarios: " + HorarioList.size());
        return HorarioList;
    }

    @Override
    public List<DiaDTO> getAllDias() {
        Connection conn = null;
        List<DiaDTO> diaList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT * FROM dias;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DiaDTO dia = new DiaDTO();
                dia.setId(rs.getInt("id"));
                dia.setNombre(rs.getString("nombre"));

                diaList.add(dia);
            }
        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            int errorCode = e.getErrorCode(); // Implement this method to map SQL error codes
            throw new MyException("Error al obtener dias de la base de datos", errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
        System.out.println("cantidad de registro de dias: " + diaList.size());
        return diaList;
    }

}
