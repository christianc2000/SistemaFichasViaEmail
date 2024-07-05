/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.service;

import com.grupo07sa.dato.Cita.CitaDTO;
import com.grupo07sa.dato.Cita.CitaRepositoryImpl;
import com.grupo07sa.dato.HistorialMedico.HistorialMedicoDTO;
import com.grupo07sa.dato.HistorialMedico.HistorialMedicoRepositoryImpl;
import com.grupo07sa.dato.ResponseDTO;
import java.util.List;

/**
 *
 * @author isais
 */
public class HistorialMedicoService {
     private HistorialMedicoRepositoryImpl historialMedicoRepositoryImpl;

    public HistorialMedicoService() {
        this.historialMedicoRepositoryImpl = new HistorialMedicoRepositoryImpl();
    }

    public ResponseDTO all() {
        List<HistorialMedicoDTO> historials = null;
        String error = null;
        try {
            historials = historialMedicoRepositoryImpl.getAllHistorialMedicos();
        } catch (Exception e) {
            error = "Error al obtener los historiales : " + e.getMessage();
        }
        String title = "Lista de Historial";
        String[][] data = null;

        if (historials != null) {
            data = new String[historials.size() + 1][4];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de historial: " + historials.size());
            // Llena la primera fila con los nombres de los atributos
            data[0][0] = "ID";
            data[0][1] = "FECHA CREACION";
            data[0][2] = "OBSERVACIONES GENERALES";
            data[0][3] = "PACIENTE ID";
            // Llena las filas siguientes con los datos de las citas
            for (int i = 0; i < historials.size(); i++) {
                HistorialMedicoDTO historial = historials.get(i);
                data[i + 1][0] = historial.getId();
                data[i + 1][1] = historial.getFechaCreacion();
                data[i + 1][2] = historial.getObservaciones();
                data[i + 1][3] = historial.getPacienteId();
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO listAtr(String[] atributos, String atrs) {
        List<String[]> historials = null;
        String error = null;
        try {
            historials = historialMedicoRepositoryImpl.getAtrHistorialMedicos(atributos, atrs);
        } catch (Exception e) {
            error = "Error al obtener los historiales : " + e.getMessage();
        }
        String title = "Lista de Historial por atributo";
        String[][] data = null;

        if (historials != null) {
            data = new String[historials.size() + 1][atributos.length];  // Incrementa el tamaño para la fila de encabezado

            // Llena la primera fila con los nombres de los atributos
            for (int i = 0; i < atributos.length; i++) {
                data[0][i] = atributos[i].toUpperCase();
            }

            // Llena las filas siguientes con los datos de las citas
            for (int i = 0; i < historials.size(); i++) {
                String[] cita = historials.get(i);
                for (int j = 0; j < cita.length; j++) {
                    data[i + 1][j] = cita[j];
                }
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO find(String[][] atributosValor) {
        HistorialMedicoDTO historial = new HistorialMedicoDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            historial.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (!historial.isValidId()) {
            error = "Error de validación";
            return new ResponseDTO("Buscar cita", null, error);
        }
        try {
            historial = historialMedicoRepositoryImpl.findHistorialMedico(historial.getId());
        } catch (Exception e) {
            error = "Error: " + e;
        }
        return new ResponseDTO("Mostrar historial", historial.HistorialMedicoToMatriz(), error);
    }

    public ResponseDTO create(String[][] atributosValor) {
        HistorialMedicoDTO historial = new HistorialMedicoDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            historial.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        try {
            if (historial.isValidCreate()) {
                historial = historialMedicoRepositoryImpl.createHistorialMedico(historial.getFechaCreacion(), historial.getObservaciones(), historial.getPacienteId());
            } else {
                error = "Error: Datos invalidos para crear un historial ";
            }

        } catch (Exception e) {
            error = "Error: " + e;
        }

        return new ResponseDTO("Crear historial", historial.HistorialMedicoToMatriz(), error);
    }

    public ResponseDTO update(String[][] atributosValor) {
        String error = null;
        HistorialMedicoDTO historialMedicoRequest = new HistorialMedicoDTO();
        HistorialMedicoDTO historial = new HistorialMedicoDTO();
        for (int i = 0; i < atributosValor.length; i++) {
            historialMedicoRequest.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (historialMedicoRequest.isValidUpdate()) {
            try {
                historial = historialMedicoRepositoryImpl.findHistorialMedico(historialMedicoRequest.getId());
                if (historial == null) {
                    error = "Error: Historial no encontrado";
                    return new ResponseDTO("Actualizar Historial", null, error);
                }
                historial.setFechaCreacion(historialMedicoRequest.getFechaCreacion()!= null ? historialMedicoRequest.getFechaCreacion(): historial.getFechaCreacion());
                historial.setObservaciones(historialMedicoRequest.getObservaciones()!= null ? historialMedicoRequest.getObservaciones(): historial.getObservaciones());
                historial.setPacienteId(historialMedicoRequest.getPacienteId()!= null ? historialMedicoRequest.getPacienteId() : historial.getPacienteId());
                historial = historialMedicoRepositoryImpl.updateHistorialMedico(historial);
                if (historial == null) {
                    error = "Error al actualizar el historial";
                }
            } catch (Exception e) {
                error = "Error: " + e;
                return new ResponseDTO("Actualizar historial", null, error);
            }
            return new ResponseDTO("Actualizar historial", historial.HistorialMedicoToMatriz(), error);
        } else {
            error = "Error: Error de validación";
            return new ResponseDTO("Actualizar Historial", null, error);
        }
    }

    public ResponseDTO delete(String[][] atributosValor) {
        String error = null;
        HistorialMedicoDTO historial = new HistorialMedicoDTO();
        for (int i = 0; i < atributosValor.length; i++) {
            historial.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (!historial.isValidId()) {
            error = "Error de validación";
            return new ResponseDTO("Buscar historial", null, error);
        }
        boolean isDeleted = false;
        try {
            isDeleted = historialMedicoRepositoryImpl.deleteHistorialMedicoById(historial.getId());
            if (!isDeleted) {
                error = "Error al intentar eliminar  el historial";
            }
        } catch (Exception e) {
            error = "Error al eliminar el historial : " + e.getMessage();
        }
        return new ResponseDTO("Eliminar historial", null, error);
    }
}
