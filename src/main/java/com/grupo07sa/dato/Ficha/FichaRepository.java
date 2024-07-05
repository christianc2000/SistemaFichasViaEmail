/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupo07sa.dato.Ficha;

import com.grupo07sa.dato.Ficha.dto.DiaDTO;
import com.grupo07sa.dato.Ficha.dto.FichaDTO;
import com.grupo07sa.dato.Ficha.dto.HorarioDTO;
import com.grupo07sa.dato.Ficha.dto.HorarioServicioDTO;
import java.util.List;

/**
 *
 * @author USER
 */
public interface FichaRepository {
     List<FichaDTO> getAllFichas();
     List<HorarioServicioDTO> getAllHorariosServicio();
     List<HorarioDTO> getAllHorarios();
     List<DiaDTO> getAllDias();
     
}
