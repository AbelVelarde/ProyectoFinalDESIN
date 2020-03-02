package es.abel.dam.models;

import java.util.Date;

public class MailInforme {

    private String asunto;
    private String remitente;
    private String contenido;
    private String fecha;
    private String carpeta;

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    public MailInforme(String asunto, String remitente, String contenido, String fecha) {
        this.asunto = asunto;
        this.remitente = remitente;
        this.contenido = contenido;
        this.fecha = fecha;
    }
}
