/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato;

/**
 *
 * @author USER
 */
public class AttributeDTO {
    String name;
    String value;
    String type;
    String nullable;
    String constrained;
    
    public AttributeDTO(String name, String value, String type, String nullable, String constrained) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.nullable = nullable;
        this.constrained = constrained;
    }

    public String getConstrained() {
        return constrained;
    }

    public void setConstrained(String constrained) {
        this.constrained = constrained;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }
    
    /*public boolean isTypeDate(){
        String valor=this.value;
        switch (this.type) {
            case "String":
                
                break;
            case "Integer":
                
                break;
            case "Float":
                
                break;
            case "Date":
                
                break;
            case "Boolean":
                
                break;
            default:
                throw new AssertionError();
        }
    }*/
}
