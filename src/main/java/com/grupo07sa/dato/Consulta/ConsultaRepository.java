/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupo07sa.dato.Consulta;

import java.util.List;

/**
 *
 * @author isais
 */
public interface ConsultaRepository {
    List<ConsultaDTO> getAllConsultas();
    List<String[]> getAtrConsultas(String[] atributos, String atrs);
    ConsultaDTO createConsulta(String diagnostico, String tratamiento, String observaciones, String cita_id, String historial_medico_id);
    ConsultaDTO findConsulta(String id);
    ConsultaDTO updateConsulta(ConsultaDTO consulta);
    boolean deleteConsultaById(String id);
}
