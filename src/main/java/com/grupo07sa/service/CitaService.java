/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.service;

import com.grupo07sa.dato.Cita.CitaDTO;
import com.grupo07sa.dato.Cita.CitaRepositoryImpl;
import com.grupo07sa.dato.ResponseDTO;
import java.util.List;

/**
 *
 * @author isais
 */
public class CitaService {
    private CitaRepositoryImpl citaRepositoryImpl;

    public CitaService() {
        this.citaRepositoryImpl = new CitaRepositoryImpl();
    }

    public ResponseDTO all() {
        List<CitaDTO> citas = null;
        String error = null;
        try {
            citas = citaRepositoryImpl.getAllCitas();
        } catch (Exception e) {
            error = "Error al obtener las citas: " + e.getMessage();
        }
        String title = "Lista de Citas";
        String[][] data = null;

        if (citas != null) {
            data = new String[citas.size() + 1][7];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de citas: " + citas.size());
            // Llena la primera fila con los nombres de los atributos
            data[0][0] = "ID";
            data[0][1] = "FECHA HORA";
            data[0][2] = "MOTIVO";
            data[0][3] = "PACIENTE ID";
            data[0][4] = "TRABAJADOR ID";
            data[0][5] = "SALA ID";
            data[0][6] = "PAGO ID";
            // Llena las filas siguientes con los datos de las citas
            for (int i = 0; i < citas.size(); i++) {
                CitaDTO cita = citas.get(i);
                data[i + 1][0] = cita.getId();
                data[i + 1][1] = cita.getFechaHora();
                data[i + 1][2] = cita.getMotivo();
                data[i + 1][3] = cita.getPacienteId();
                data[i + 1][4] = cita.getTrabajadorId();
                data[i + 1][5] = cita.getSalaId();
                data[i + 1][6] = cita.getPagoId();
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO listAtr(String[] atributos, String atrs) {
        List<String[]> citas = null;
        String error = null;
        try {
            citas = citaRepositoryImpl.getAtrCitas(atributos, atrs);
        } catch (Exception e) {
            error = "Error al obtener las citas: " + e.getMessage();
        }
        String title = "Lista de Citas por atributo";
        String[][] data = null;

        if (citas != null) {
            data = new String[citas.size() + 1][atributos.length];  // Incrementa el tamaño para la fila de encabezado

            // Llena la primera fila con los nombres de los atributos
            for (int i = 0; i < atributos.length; i++) {
                data[0][i] = atributos[i].toUpperCase();
            }

            // Llena las filas siguientes con los datos de las citas
            for (int i = 0; i < citas.size(); i++) {
                String[] cita = citas.get(i);
                for (int j = 0; j < cita.length; j++) {
                    data[i + 1][j] = cita[j];
                }
            }
        }

        return new ResponseDTO(title, data, error);
    }
    
    public ResponseDTO pagos(String[] atributos, String atrs)
    {
        List<String[]> pagos = null;
        String error = null;
        try {
            pagos = citaRepositoryImpl.getAtrPagos(atributos, atrs);
        } catch (Exception e) {
            error = "Error al obtener las pagos: " + e.getMessage();
        }
        String title = "Reporte de pagos";
        String[][] data = null;

        if (pagos != null) {
            data = new String[pagos.size() + 1][2];  // Incrementa el tamaño para la fila de encabezado

            // Llena la primera fila con los nombres de los atributos
            
            data[0][0] = "MES";
            data[0][1] = "FECHA_PAGO";
            

            // Llena las filas siguientes con los datos de las citas
            for (int i = 0; i < pagos.size(); i++) {
                String[] pago = pagos.get(i);
                for (int j = 0; j < pago.length; j++) {
                    data[i + 1][j] = pago[j];
                }
            }
        }

        return new ResponseDTO(title, data, error);
    }
    
     public ResponseDTO medicos(String[] atributos, String atrs)
    {
        List<String[]> pagos = null;
        String error = null;
        try {
            pagos = citaRepositoryImpl.getAtrMedicos(atributos, atrs);
        } catch (Exception e) {
            error = "Error al obtener los medicos: " + e.getMessage();
        }
        String title = "Reporte de medicos";
        String[][] data = null;

        if (pagos != null) {
            data = new String[pagos.size() + 1][2];  // Incrementa el tamaño para la fila de encabezado

            // Llena la primera fila con los nombres de los atributos
            
            data[0][0] = "TRABAJADOR";
            data[0][1] = "TOTAL CITAS";
            

            // Llena las filas siguientes con los datos de las citas
            for (int i = 0; i < pagos.size(); i++) {
                String[] pago = pagos.get(i);
                for (int j = 0; j < pago.length; j++) {
                    data[i + 1][j] = pago[j];
                }
            }
        }

        return new ResponseDTO(title, data, error);
    }

    public ResponseDTO find(String[][] atributosValor) {
        CitaDTO cita = new CitaDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            cita.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (!cita.isValidId()) {
            error = "Error de validación";
            return new ResponseDTO("Buscar cita", null, error);
        }
        try {
            cita = citaRepositoryImpl.findCita(cita.getId());
        } catch (Exception e) {
            error = "Error: " + e;
        }
        return new ResponseDTO("Mostrar cita", cita.CitaToMatriz(), error);
    }

    public ResponseDTO create(String[][] atributosValor) {
        CitaDTO cita = new CitaDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            cita.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        try {
            if (cita.isValidCreate()) {
                cita = citaRepositoryImpl.createCita(cita.getFechaHora(), cita.getMotivo(), cita.getPacienteId(), cita.getTrabajadorId(), cita.getSalaId(), cita.getPagoId());
            } else {
                error = "Error: Datos invalidos para crear una cita ";
            }

        } catch (Exception e) {
            error = "Error: " + e;
        }

        return new ResponseDTO("Crear cita", cita.CitaToMatriz(), error);
    }

    public ResponseDTO update(String[][] atributosValor) {
        String error = null;
        CitaDTO citaRequest = new CitaDTO();
        CitaDTO cita = new CitaDTO();
        for (int i = 0; i < atributosValor.length; i++) {
            citaRequest.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (citaRequest.isValidUpdate()) {
            try {
                cita = citaRepositoryImpl.findCita(citaRequest.getId());
                if (cita == null) {
                    error = "Error: Cita no encontrado";
                    return new ResponseDTO("Actualizar cita", null, error);
                }
                cita.setFechaHora(citaRequest.getFechaHora()!= null ? citaRequest.getFechaHora() : cita.getFechaHora());
                cita.setMotivo(citaRequest.getMotivo()!= null ? citaRequest.getMotivo() : cita.getMotivo());
                cita.setPacienteId(citaRequest.getPacienteId()!= null ? citaRequest.getPacienteId() : cita.getPacienteId());
                cita.setTrabajadorId(citaRequest.getTrabajadorId()!= null ? citaRequest.getTrabajadorId() : cita.getTrabajadorId());
                cita.setSalaId(citaRequest.getSalaId()!= null ? citaRequest.getSalaId() : cita.getSalaId());
                cita.setPagoId(citaRequest.getPagoId()!= null ? citaRequest.getPagoId() : cita.getPagoId());
               
                cita = citaRepositoryImpl.updateCita(cita);
                if (cita == null) {
                    error = "Error al actualizar la cita";
                }
            } catch (Exception e) {
                error = "Error: " + e;
                return new ResponseDTO("Actualizar cita", null, error);
            }
            return new ResponseDTO("Actualizar cita", cita.CitaToMatriz(), error);
        } else {
            error = "Error: Error de validación";
            return new ResponseDTO("Actualizar cita", null, error);
        }
    }

    public ResponseDTO delete(String[][] atributosValor) {
        String error = null;
        CitaDTO cita = new CitaDTO();
        for (int i = 0; i < atributosValor.length; i++) {
            cita.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        if (!cita.isValidId()) {
            error = "Error de validación";
            return new ResponseDTO("Buscar cita", null, error);
        }
        boolean isDeleted = false;
        try {
            isDeleted = citaRepositoryImpl.deleteCitaById(cita.getId());
            if (!isDeleted) {
                error = "Error al intentar eliminar a la cita";
            }
        } catch (Exception e) {
            error = "Error al eliminar la cita: " + e.getMessage();
        }
        return new ResponseDTO("Eliminar cita", null, error);
    }
    
   
}
