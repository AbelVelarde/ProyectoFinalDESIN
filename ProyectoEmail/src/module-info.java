module ProyectoEmail {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires ProyectoReloj;

    requires hsqldb;
    requires java.sql;
    requires jasperreports;

    requires commons.email;
    requires javax.mail;

    requires org.docgene.help.jfx;

    requires java.desktop;

    exports es.abel.dam;
    exports es.abel.dam.view;
    exports es.abel.dam.models;
    exports es.abel.dam.logica;
    exports es.abel.dam.servicios;
    exports es.abel.dam.utils;
    exports es.abel.dam.infomes;

    opens es.abel.dam.view to javafx.fxml,  org.controlsfx.controls, javafx.graphics, javafx.base;
    opens es.abel.dam to org.controlsfx.controls;

}