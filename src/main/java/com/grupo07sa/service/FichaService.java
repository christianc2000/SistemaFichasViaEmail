/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.service;

import com.grupo07sa.dato.Ficha.FichaRepositoryImpl;
import com.grupo07sa.dato.Ficha.dto.DiaDTO;
import com.grupo07sa.dato.Ficha.dto.FichaDTO;
import com.grupo07sa.dato.Ficha.dto.HorarioDTO;
import com.grupo07sa.dato.Ficha.dto.HorarioServicioDTO;
import com.grupo07sa.dato.ResponseDTO;
import java.util.List;

/**
 *
 * @author USER
 */
public class FichaService {

    private FichaRepositoryImpl fichaRepositoryImpl;

    public FichaService() {
        this.fichaRepositoryImpl = new FichaRepositoryImpl();
    }

    public ResponseDTO allFichas() {
        List<FichaDTO> fichas = null;
        String error = null;
        try {
            fichas = fichaRepositoryImpl.getAllFichas();
        } catch (Exception e) {
            error = "Error al obtener los fichas: " + e.getMessage();
        }
        String title = "Lista de Fichas";
        String[][] data = null;

        if (fichas != null) {
            data = new String[fichas.size() + 1][7];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de fichas: " + fichas.size());
            // Llena la primera fila con los nombres de los atributos
            data[0][0] = "ID";
            data[0][1] = "CANTIDAD";
            data[0][2] = "FECHA";
            data[0][3] = "CANTIDAD VENDIDO";
            data[0][4] = "HORARIO SERVICIO ID";
            data[0][5] = "FECHA DE CREACION";
            data[0][6] = "FECHA DE ACTUALIZACION";
            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < fichas.size(); i++) {
                FichaDTO ficha = fichas.get(i);
                data[i + 1][0] = String.valueOf(ficha.getId());
                data[i + 1][1] = String.valueOf(ficha.getCantidad());
                data[i + 1][2] = String.valueOf(ficha.getFecha());
                data[i + 1][3] = String.valueOf(ficha.getCantidad_vendido());
                data[i + 1][4] = String.valueOf(ficha.getHorario_servicio_id());
                data[i + 1][5] = String.valueOf(ficha.getCreated_at());
                data[i + 1][6] = String.valueOf(ficha.getUpdated_at());
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO allHorarioServicios() {
        List<HorarioServicioDTO> horarios = null;
        String error = null;
        try {
            horarios = fichaRepositoryImpl.getAllHorariosServicio();
        } catch (Exception e) {
            error = "Error al obtener los horario servicios: " + e.getMessage();
        }
        String title = "Lista de Horario Servicio";
        String[][] data = null;

        if (horarios != null) {
            data = new String[horarios.size() + 1][7];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de horario servicio: " + horarios.size());
            // Llena la primera fila con los nombres de los atributos
            data[0][0] = "ID";
            data[0][1] = "ESTADO";
            data[0][2] = "SERVICIO ID";
            data[0][3] = "HORARIO ID";
            data[0][4] = "TRABAJADOR ID";
            data[0][5] = "FECHA DE CREACION";
            data[0][6] = "FECHA DE ACTUALIZACION";
            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < horarios.size(); i++) {
                HorarioServicioDTO horario = horarios.get(i);
                data[i + 1][0] = String.valueOf(horario.getId());
                data[i + 1][1] = horario.isEstado() ? "Habilitado" : "Inhabilitado";
                data[i + 1][2] = String.valueOf(horario.getServicio_id());
                data[i + 1][3] = String.valueOf(horario.getHorario_id());
                data[i + 1][4] = String.valueOf(horario.getTrabajador_id());
                data[i + 1][5] = String.valueOf(horario.getCreated_at());
                data[i + 1][6] = String.valueOf(horario.getUpdated_at());
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO allHorarios() {
        List<HorarioDTO> horarios = null;
        String error = null;
        try {
            horarios = fichaRepositoryImpl.getAllHorarios();
        } catch (Exception e) {
            error = "Error al obtener los horarios: " + e.getMessage();
        }
        String title = "Lista de Horarios";
        String[][] data = null;

        if (horarios != null) {
            data = new String[horarios.size() + 1][7];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de horario servicio: " + horarios.size());
            // Llena la primera fila con los nombres de los atributos
            data[0][0] = "ID";
            data[0][1] = "ESTADO";
            data[0][2] = "HORA DE INICIO";
            data[0][3] = "HORA DE FINALIZACIÓN";
            data[0][4] = "DIA ID";
            data[0][5] = "FECHA DE CREACION";
            data[0][6] = "FECHA DE ACTUALIZACION";
            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < horarios.size(); i++) {
                HorarioDTO horario = horarios.get(i);
                data[i + 1][0] = String.valueOf(horario.getId());
                data[i + 1][1] = horario.isEstado() ? "Habilitado" : "Inhabilitado";
                data[i + 1][2] = String.valueOf(horario.getHora_inicio());
                data[i + 1][3] = String.valueOf(horario.getHora_fin());
                data[i + 1][4] = String.valueOf(horario.getDia_id());
                data[i + 1][5] = String.valueOf(horario.getCreated_at());
                data[i + 1][6] = String.valueOf(horario.getUpdated_at());
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO allDias() {
        List<DiaDTO> dias = null;
        String error = null;
        try {
            dias = fichaRepositoryImpl.getAllDias();
        } catch (Exception e) {
            error = "Error al obtener los dias: " + e.getMessage();
        }
        String title = "Lista de Días";
        String[][] data = null;

        if (dias != null) {
            data = new String[dias.size() + 1][2];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de dias: " + dias.size());
            // Llena la primera fila con los nombres de los atributos
            data[0][0] = "ID";
            data[0][1] = "DÍA";

            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < dias.size(); i++) {
                DiaDTO dia = dias.get(i);
                data[i + 1][0] = String.valueOf(dia.getId());
                data[i + 1][1] = dia.getNombre();
            }
        }

        return new ResponseDTO(title, data, error);
    }
}
