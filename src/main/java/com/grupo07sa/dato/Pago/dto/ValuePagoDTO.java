/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Pago.dto;

import java.sql.Timestamp;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
public class ValuePagoDTO {

    private String id;
    private String qrImage;
    private String expirationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQrImage() {
        return qrImage;
    }

    public void setQrImage(String qrImage) {
        this.qrImage = qrImage;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public ValuePagoDTO extraerValue(Object values) {
        if (values instanceof String) {
            String valuesStr = (String) values;
            String[] parts = valuesStr.split(";");
            if (parts.length == 2) {
                String qrDetailsStr = parts[1];
                JSONObject qrDetails = new JSONObject(qrDetailsStr);
                System.out.println("id: " + qrDetails.get("id"));
                System.out.println("qrImage: " + qrDetails.get("qrImage"));
                System.out.println("expirationDate: " + qrDetails.get("expirationDate"));
                this.id = qrDetails.getString("id");
                this.qrImage = qrDetails.getString("qrImage");
                this.expirationDate = qrDetails.getString("expirationDate");
            }
        }
        return this;
    }
}
