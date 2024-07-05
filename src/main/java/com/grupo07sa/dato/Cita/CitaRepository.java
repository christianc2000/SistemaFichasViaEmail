/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Cita;


import java.util.List;

/**
 *
 * @author isais
 */
public interface CitaRepository{
    List<CitaDTO> getAllCitas();
    List<String[]> getAtrCitas(String[] atributos, String atrs);
    List<String[]> getAtrPagos(String[] atributos, String atrs);
    List<String[]> getAtrMedicos(String[] atributos, String atrs);
    CitaDTO createCita(String fecha_hora, String motivo, String paciente_id, String trabajador_id, String sala_id, String pago_id);
    CitaDTO findCita(String id);
    CitaDTO updateCita(CitaDTO cita);
    boolean deleteCitaById(String id);
}