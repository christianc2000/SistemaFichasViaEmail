/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato;

import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class PagoData {
    private String commerceID;
    private int moneda;
    private String telefono;
    private String nombre_usuario;
    private String ci_nit;
    private String nro_pago;
    private String monto_cliente_empresa;
    private String correo;
    private String url_call_back;
    private String url_return;
    private int tipo_servicio;
    private List<PedidoDetalle> pedidoDetalles;
    
    public PagoData(){
        
    }

    public String getCommerceID() {
        return commerceID;
    }

    public void setCommerceID(String commerceID) {
        this.commerceID = commerceID;
    }

    public int getMoneda() {
        return moneda;
    }

    public void setMoneda(int moneda) {
        this.moneda = moneda;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getCi_nit() {
        return ci_nit;
    }

    public void setCi_nit(String ci_nit) {
        this.ci_nit = ci_nit;
    }

    public String getNro_pago() {
        return nro_pago;
    }

    public void setNro_pago(String nro_pago) {
        this.nro_pago = nro_pago;
    }

    public String getMonto_cliente_empresa() {
        return monto_cliente_empresa;
    }

    public void setMonto_cliente_empresa(String monto_cliente_empresa) {
        this.monto_cliente_empresa = monto_cliente_empresa;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUrl_call_back() {
        return url_call_back;
    }

    public void setUrl_call_back(String url_call_back) {
        this.url_call_back = url_call_back;
    }

    public String getUrl_return() {
        return url_return;
    }

    public void setUrl_return(String url_return) {
        this.url_return = url_return;
    }

    public int getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(int tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public List<PedidoDetalle> getPedidoDetalles() {
        return pedidoDetalles;
    }

    public void setPedidoDetalles(List<PedidoDetalle> pedidoDetalles) {
        this.pedidoDetalles = pedidoDetalles;
    }
    
    

}
