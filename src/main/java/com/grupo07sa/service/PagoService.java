/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.service;

import com.grupo07sa.dato.CredentialPagoFacilDTO;
import com.grupo07sa.dato.Pago.PagoRepositoryImpl;
import com.grupo07sa.dato.Pago.dto.PagoDTO;
import com.grupo07sa.dato.Pago.dto.ResponsePagoDTO;
import com.grupo07sa.dato.Pago.dto.ValuePagoDTO;
import com.grupo07sa.dato.PagoData;
import com.grupo07sa.dato.PedidoDetalle;
import com.grupo07sa.dato.ResponseDTO;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

/**
 *
 * @author USER
 */
public class PagoService {

    private PagoRepositoryImpl pagoRepositoryImpl;
    private final OkHttpClient client;
    private CredentialPagoFacilDTO credencialPF;
    private String COMMERCE_ID;
    private String URL_QR;
    private String URL_TIGO_MONEY;

    public PagoService() {
        pagoRepositoryImpl = new PagoRepositoryImpl();
        client = new OkHttpClient();
        credencialPF = new CredentialPagoFacilDTO();
        COMMERCE_ID = credencialPF.getCOMMERCEID();
        URL_QR = "https://serviciostigomoney.pagofacil.com.bo/api/servicio/generarqrv2";
        URL_TIGO_MONEY = "https://serviciostigomoney.pagofacil.com.bo/api/servicio/realizarpagotigomoneyv2";
    }

    public ResponseDTO create(String[][] atributosValor) {
        PagoDTO pago = new PagoDTO();
        String error = null;
        for (int i = 0; i < atributosValor.length; i++) {
            pago.setAttribute(atributosValor[i][0], atributosValor[i][1]);
        }
        try {
            try {
                pago.validateCreate();
            } catch (Exception e) {
                error = "Error de validación para crear el pago";
            }
            System.out.println("PAGO ESTÁ VALIDADO");
            //REALIZAR LO DE PAGO FÁCIL AQUÍ
            Random random = new Random();
            PagoData pagoData = new PagoData();
            List<PedidoDetalle> detalles = new ArrayList<>();
            int randomNumber2 = 1000 + random.nextInt(9000);
            detalles.add(new PedidoDetalle(String.valueOf(randomNumber2), "servicio", "1", "50", "99", "50"));
            pagoData.setCommerceID(COMMERCE_ID);
            pagoData.setMoneda(2);
            pagoData.setTelefono(pago.getCelular());
            pagoData.setNombre_usuario(pago.getRazon_social());
            pagoData.setCi_nit(pago.getNit());
            int randomNumber = 1000 + random.nextInt(9000);
            pagoData.setNro_pago("GRUPO7-SA-" + randomNumber);
            pagoData.setMonto_cliente_empresa(String.valueOf(pago.getCosto()));
            pagoData.setCorreo(pago.getEmail());
            pagoData.setUrl_call_back("http://locashost:8000");
            pagoData.setUrl_return("http://locashost:8000");
            pagoData.setTipo_servicio(1);
            pagoData.setPedidoDetalles(detalles);
            try {
                ResponsePagoDTO response_pago = procesarPago(pagoData);
                pago.setQr(response_pago.getValues().getQrImage());
                pago.setFecha_expiracion(response_pago.getValues().getExpirationDate());
                pago = pagoRepositoryImpl.createPago(pago);
            } catch (Exception e) {
                error = "Error: " + e;
            }

        } catch (Exception e) {
            error = "Error: " + e;
        }

        return new ResponseDTO("Crear Pago", pago.UserToMatriz(), error);
    }

    public ResponsePagoDTO procesarPago(PagoData pago) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = pago.getTipo_servicio() == 1 ? URL_QR : URL_TIGO_MONEY;

        JSONObject body = new JSONObject();
        body.put("tcCommerceID", pago.getCommerceID());
        body.put("tnMoneda", pago.getMoneda());
        body.put("tnTelefono", pago.getTelefono());
        body.put("tcNombreUsuario", pago.getNombre_usuario());
        body.put("tnCiNit", pago.getCi_nit());
        body.put("tcNroPago", pago.getNro_pago());
        body.put("tnMontoClienteEmpresa", pago.getMonto_cliente_empresa());
        body.put("tcCorreo", pago.getCorreo());
        body.put("tcUrlCallBack", pago.getUrl_call_back());
        body.put("tcUrlReturn", pago.getUrl_return());

        JSONArray pedidoDetalleArray = new JSONArray();
        for (PedidoDetalle detalle : pago.getPedidoDetalles()) {
            JSONObject detalleJson = new JSONObject();
            detalleJson.put("Serial", detalle.getSerial());
            detalleJson.put("Producto", detalle.getProducto());
            detalleJson.put("Cantidad", detalle.getCantidad());
            detalleJson.put("Precio", detalle.getPrecio());
            detalleJson.put("Descuento", detalle.getDescuento());
            detalleJson.put("Total", detalle.getTotal());
            pedidoDetalleArray.put(detalleJson);
        }
        body.put("taPedidoDetalle", pedidoDetalleArray);

        RequestBody requestBody = RequestBody.create(
                body.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();

            JSONObject jsonResponse = new JSONObject(responseBody);
            ResponsePagoDTO response_pago = new ResponsePagoDTO();
            ValuePagoDTO values_pago = new ValuePagoDTO();
            System.out.println(jsonResponse);
            response_pago.setError(jsonResponse.getInt("error") != 0);
            response_pago.setStatus(jsonResponse.getInt("status") != 0);
            response_pago.setMessage(jsonResponse.getString("message"));
            response_pago.setMessageMostrar(jsonResponse.getInt("messageMostrar") != 0);
            response_pago.setMessageSistema(jsonResponse.getString("messageSistema"));

            Object values = jsonResponse.get("values");
            values_pago.extraerValue(values);
            //System.out.println("cantidad de letras en qr: " + values_pago.getQrImage().length());
            response_pago.setValues(values_pago);

            //System.out.println("value: " + response_pago.getValues().getId());
            //String qrImageBase64 = response_pago.getValues().getQrImage();
            //qrImageBase64 = "data:image/png;base64," + qrImageBase64;
            //qrImageBase64 = "<img src='" + qrImageBase64 + "' alt='Imagen base64'>";
            //System.out.println(qrImageBase64);
            //response_pago.setTagImage(qrImageBase64);
            return response_pago;
        }
    }

    public ResponseDTO allPagos() {
        List<PagoDTO> pagos = null;
        String error = null;
        try {
            pagos = pagoRepositoryImpl.getAllPagos();
        } catch (Exception e) {
            error = "Error al obtener los pagos: " + e.getMessage();
        }
        String title = "Lista de Pagos";
        String[][] data = null;

        if (pagos != null) {
            data = new String[pagos.size() + 1][19];  // Incrementa el tamaño para la fila de encabezado
            System.out.println("cantidad de pagos: " + pagos.size());
            // Llena la primera fila con los nombres de los atributos
            data[0][0] = "ID";
            data[0][1] = "NIT";
            data[0][2] = "RAZON SOCIAL";
            data[0][3] = "CORREO";
            data[0][4] = "CELULAR";
            data[0][5] = "TRABAJADOR";
            data[0][6] = "SERVICIO";
            data[0][7] = "HORARIO";
            data[0][8] = "TIPO DE SERVICIO";
            data[0][9] = "COSTO";
            data[0][10] = "FORMA DE PAGO";
            data[0][11] = "QR";
            data[0][12] = "ESTADO";
            data[0][13] = "FECHA DE PAGO";
            data[0][14] = "FECHA DE EXPIRACIÓN";
            data[0][15] = "PACIENTE ID";
            data[0][16] = "FICHA ID";
            data[0][17] = "FECHA DE CREACIÓN";
            data[0][18] = "FECHA DE ACTUALIZACIÓN";
            // Llena las filas siguientes con los datos de los usuarios
            for (int i = 0; i < pagos.size(); i++) {
                PagoDTO pago = pagos.get(i);
                data[i + 1][0] = String.valueOf(pago.getId());
                data[i + 1][1] = pago.getNit();
                data[i + 1][2] = pago.getRazon_social();
                data[i + 1][3] = pago.getEmail();
                data[i + 1][4] = pago.getCelular();
                data[i + 1][5] = pago.getTrabajador();
                data[i + 1][6] = pago.getServicio();
                data[i + 1][7] = pago.getHorario();
                data[i + 1][8] = pago.getTipo_servicio();
                data[i + 1][9] = String.valueOf(pago.getCosto());
                data[i + 1][10] = pago.getForma_pago();
                data[i + 1][11] = pago.getQr();
                data[i + 1][12] = pago.getEstado();
                data[i + 1][13] = String.valueOf(pago.getFecha_pago());
                data[i + 1][14] = pago.getFecha_expiracion();
                data[i + 1][15] = String.valueOf(pago.getPaciente_id());
                data[i + 1][16] = String.valueOf(pago.getFicha_id());
                data[i + 1][17] = String.valueOf(pago.getCreated_at());
                data[i + 1][18] = String.valueOf(pago.getUpdated_at());

            }
        }

        return new ResponseDTO(title, data, error);
    }
    /*public static void main(String[] args) {
        //Crear instancia del servicio de usuarios
        PagoService pagoService = new PagoService();
        /*ResponseDTO response = response = userService.find("1");

        if (response.getError() == null) {
            System.out.println("Usuario encontrado");
            System.out.println(response.MatrizToString());
        } else {
            System.out.println("Error: " + response.getError());
        }*/
    //Definir los atributos y valores del usuario a crear
    /*    String[][] atributosValor = {
            {"nit", "1578217"},
            {"razon_social", "Christian Mamani"},
            {"email", "christian@gmail.com"},
            {"celular", "77382831"}, //{"fecha_nacimiento", "1990-01-01"},
            {"trabajador", "Dr Ventura"},
            {"servicio", "Ecografía Abdominal"},
            {"horario", "08:30-12:00"},
            {"tipo_servicio", "Médico"},
            {"costo", "0.01"},
            {"forma_pago", "QR"},
            {"paciente_id", "7"},
            {"ficha_id", "1"}
        };
        String atributos = "nit,razon_social,email,celular,trabajador,servicio,horario,tipo_servicio,costo,forma_pago,paciente_id,ficha_id";
        ResponseDTO response = new ResponseDTO(null, null, null);
        //Llamar al método create del servicio de usuarios
        response = pagoService.create(atributosValor);
        //Imprimir el resultado
        if (response.getError() == null) {
            System.out.println(response.MatrizToString());
            System.out.println("Pago generado exitosamente.");

        } else {
            System.out.println(response.getError());
        }
    }*/
}
