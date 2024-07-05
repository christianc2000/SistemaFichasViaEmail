/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupo07sa.dato.Servicio;

import com.grupo07sa.dato.Servicio.dto.ServicioDTO;
import com.grupo07sa.dato.User.dto.UserDTO;
import java.util.List;

/**
 *
 * @author USER
 */
public interface ServicioRepository {
    List<ServicioDTO> getAllservicios();
    List<String[]> getAtrServicios(String[] atributos, String atrs);
    ServicioDTO createServicio(ServicioDTO servicio);
    ServicioDTO findServicio(int id);
    ServicioDTO updateServicio(ServicioDTO servicioUpdated);
    boolean deleteServicioById(int id);
}
