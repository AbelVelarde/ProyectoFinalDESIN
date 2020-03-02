package es.abel.dam;

import java.util.Date;

public class EmailInforme {
    private String asunto;
    private String remitente;
    private String contenido;
    private String fecha;
    private String file;

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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public EmailInforme(String asunto, String remitente, String contenido, String fecha) {
        this.asunto = asunto;
        this.remitente = remitente;
        this.contenido = contenido;
        this.fecha = fecha;
    }
}
