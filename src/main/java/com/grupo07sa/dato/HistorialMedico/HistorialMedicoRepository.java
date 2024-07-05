/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupo07sa.dato.HistorialMedico;

import com.grupo07sa.dato.Cita.CitaDTO;
import java.util.List;

/**
 *
 * @author isais
 */
public interface HistorialMedicoRepository {
     List<HistorialMedicoDTO> getAllHistorialMedicos();
    List<String[]> getAtrHistorialMedicos(String[] atributos, String atrs);
    HistorialMedicoDTO createHistorialMedico(String fecha_creacion, String observaciones_generales, String paciente_id);
    HistorialMedicoDTO findHistorialMedico(String id);
    HistorialMedicoDTO updateHistorialMedico(HistorialMedicoDTO historial);
    boolean deleteHistorialMedicoById(String id);
}
