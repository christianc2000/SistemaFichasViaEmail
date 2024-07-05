/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.service;


import com.grupo07sa.dato.Consulta.ConsultaDTO;
import com.grupo07sa.dato.Consulta.ConsultaRepositoryImpl;
import com.grupo07sa.dato.ResponseDTO;
import java.util.List;

/**
 *
 * @author isais
 */
public class ConsultaService {
     private ConsultaRepositoryImpl consultaRepositoryImpl;

    public ConsultaService() {
        this.consultaRepositoryImpl = new ConsultaRepositoryImpl();
    }

    public ResponseDTO all() {
        List<ConsultaDTO> consultas = null;
        String error = null;
        try {
            consultas = consultaRepositoryImpl.getAllConsultas();
        } catch (Exception e) {
            error = "Error al obtener las consultas: " + e.getMessage();
        }
        String title = "Lista de Consultas";
        String[][] data = null;

        if (consultas != null) {
            data = new String[consultas.size() + 1][6];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de consultas: " + consultas.size());
            // Llena la primera fila con los nombres de los atributos
            data[0][0] = "ID";
            data[0][1] = "DIAGNOSTICO";
            data[0][2] = "TRATAMIENTO";
            data[0][3] = "OBSERVACIONES";
            data[0][4] = "CITA ID";
            data[0][5] = "HISTORIAL MEDICO ID";
            // Llena las filas siguientes con los datos de las citas
            for (int i = 0; i < consultas.size(); i++) {
                ConsultaDTO consulta = consultas.get(i);
                data[i + 1][0] = consulta.getId();
                data[i + 1][1] = consulta.getDiagnostico();
                data[i + 1][2] = consulta.getTratamiento();
                data[i + 1][3] = consulta.getObservaciones();
                data[i + 1][4] = consulta.getCitaId();
                data[i + 1][5] = consulta.getHistorialMedicoId();
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO listAtr(String[] atributos, String atrs) {
        List<String[]> consultas = null;
        String error = null;
        try {
            consultas = consultaRepositoryImpl.getAtrConsultas(atributos, atrs);
        } catch (Exception e) {
            error = "Error al obtener las consultas: " + e.getMessage();
        }
        String title = "Lista de Consultas por atributo";
        String[][] data = null;

        if (consultas != null) {
            data = new String[consultas.size() + 1][atributos.length];  // Incrementa el tamaño para la fila de encabezado

            // Llena la primera fila con los nombres de los atributos
            for (int i = 0; i < atributos.length; i++) {
                data[0][i] = atributos[i].toUpperCase();
            }

            // Llena las filas siguientes con los datos de las citas
            for (int i = 0; i < consultas.size(); i++) {
                String[] consulta = consultas.get(i);
                for (int j = 0; j < consulta.length; j++) {
                    data[i + 1][j] = consulta[j];
                }
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO find(String[][] atributosValor) {
        ConsultaDTO consulta = new ConsultaDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            consulta.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (!consulta.isValidId()) {
            error = "Error de validación";
            return new ResponseDTO("Buscar cita", null, error);
        }
        try {
            consulta = consultaRepositoryImpl.findConsulta(consulta.getId());
        } catch (Exception e) {
            error = "Error: " + e;
        }
        return new ResponseDTO("Mostrar consulta", consulta.ConsultaToMatriz(), error);
    }

    public ResponseDTO create(String[][] atributosValor) {
        ConsultaDTO consulta = new ConsultaDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            consulta.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        try {
            if (consulta.isValidCreate()) {
                consulta = consultaRepositoryImpl.createConsulta(consulta.getDiagnostico(), consulta.getTratamiento(), consulta.getObservaciones(), consulta.getCitaId(), consulta.getHistorialMedicoId());
            } else {
                error = "Error: Datos invalidos para crear una consulta ";
            }

        } catch (Exception e) {
            error = "Error: " + e;
        }

        return new ResponseDTO("Crear consulta", consulta.ConsultaToMatriz(), error);
    }

    public ResponseDTO update(String[][] atributosValor) {
        String error = null;
        ConsultaDTO consultaRequest = new ConsultaDTO();
        ConsultaDTO consulta = new ConsultaDTO();
        for (int i = 0; i < atributosValor.length; i++) {
            consultaRequest.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (consultaRequest.isValidUpdate()) {
            try {
                consulta = consultaRepositoryImpl.findConsulta(consultaRequest.getId());
                if (consulta == null) {
                    error = "Error: Consulta no encontrado";
                    return new ResponseDTO("Actualizar consulta", null, error);
                }
                consulta.setDiagnostico(consultaRequest.getDiagnostico()!= null ? consultaRequest.getDiagnostico() : consulta.getDiagnostico());
                consulta.setTratamiento(consultaRequest.getTratamiento()!= null ? consultaRequest.getTratamiento() : consulta.getTratamiento());
                consulta.setObservaciones(consultaRequest.getObservaciones()!= null ? consultaRequest.getObservaciones() : consulta.getObservaciones());
                consulta.setCitaId(consultaRequest.getCitaId()!= null ? consultaRequest.getCitaId() : consulta.getCitaId());
                consulta.setHistorialMedicoId(consultaRequest.getHistorialMedicoId()!= null ? consultaRequest.getHistorialMedicoId() : consulta.getHistorialMedicoId());
               
                consulta = consultaRepositoryImpl.updateConsulta(consulta);
                if (consulta == null) {
                    error = "Error al actualizar la consulta";
                }
            } catch (Exception e) {
                error = "Error: " + e;
                return new ResponseDTO("Actualizar consulta", null, error);
            }
            return new ResponseDTO("Actualizar consulta", consulta.ConsultaToMatriz(), error);
        } else {
            error = "Error: Error de validación";
            return new ResponseDTO("Actualizar consulta", null, error);
        }
    }

    public ResponseDTO delete(String[][] atributosValor) {
        String error = null;
        ConsultaDTO consulta = new ConsultaDTO();
        for (int i = 0; i < atributosValor.length; i++) {
            consulta.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (!consulta.isValidId()) {
            error = "Error de validación";
            return new ResponseDTO("Buscar consulta", null, error);
        }
        boolean isDeleted = false;
        try {
            isDeleted = consultaRepositoryImpl.deleteConsultaById(consulta.getId());
            if (!isDeleted) {
                error = "Error al intentar eliminar a la consulta";
            }
        } catch (Exception e) {
            error = "Error al eliminar la consulta: " + e.getMessage();
        }
        return new ResponseDTO("Eliminar consulta", null, error);
    }
}
