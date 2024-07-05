/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Pago;

import com.grupo07sa.dato.Pago.dto.PagoDTO;
import java.util.List;

/**
 *
 * @author USER
 */
public interface PagoRepository {
    List<PagoDTO> getAllPagos();
    PagoDTO createPago(PagoDTO pago);
}
