package com.betek.demoday.actionfactory.dto;

public class DeviceCsvDto {
    private String imei;
    private String state;
    private String proveedor;
    private String puntaje;
    private String status;
    private String date;

    public DeviceCsvDto(String imei, String state, String proveedor, String puntaje, String status, String date) {
        this.imei = imei;
        this.state = state;
        this.proveedor = proveedor;
        this.puntaje = puntaje;
        this.status = status;
        this.date = date;
    }

    public DeviceCsvDto() {
    }

    public String getImei() {
        return imei;
    }

    public String getState() {
        return state;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DeviceCsvDto{" +
                "imei='" + imei + '\'' +
                ", state='" + state + '\'' +
                ", proveedor='" + proveedor + '\'' +
                ", puntaje='" + puntaje + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
