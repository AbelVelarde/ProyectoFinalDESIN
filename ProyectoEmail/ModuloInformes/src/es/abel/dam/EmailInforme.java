package es.abel.dam;

public class EmailInforme {
    private boolean isRead;
    private String asunto;
    private String remitente;
    private String contenido;
    private String fecha;

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

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


    public EmailInforme(boolean isRead, String asunto, String remitente, String contenido, String fecha) {
        this.isRead = isRead;
        this.asunto = asunto;
        this.remitente = remitente;
        this.contenido = contenido;
        this.fecha = fecha;
    }
}
