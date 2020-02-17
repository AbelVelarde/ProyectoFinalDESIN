module ProyectoEmail {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;

    requires commons.email;
    requires javax.mail;

    exports es.abel.dam;
    exports es.abel.dam.view;
    exports es.abel.dam.models;
    exports es.abel.dam.logica;
    exports es.abel.dam.servicios;

    opens es.abel.dam.view to javafx.fxml,  org.controlsfx.controls, javafx.graphics, javafx.base;
    opens es.abel.dam to org.controlsfx.controls;
}