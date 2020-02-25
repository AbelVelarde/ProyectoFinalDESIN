package es.abel.dam;

import es.abel.dam.logica.Logica;
import es.abel.dam.logica.Logica_Reloj;
import es.abel.dam.models.MailAccount;
import es.abel.dam.view.EmailMainWindowController;
import es.abel.reloj.RelojDigital;
import javafx.application.Application;
import javafx.css.Size;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.ArrayList;

public class Launcher extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/EmailMainWindow.fxml"));
        Parent root = fxmlLoader.load();
        EmailMainWindowController controller = fxmlLoader.getController();
        stage.setTitle("Email Controller");
        stage.setScene(new Scene(root, 1024, 720));
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Logica.getInstance().saveListaCuentas();
                Logica_Reloj.getInstance().saveListaTareas();
                controller.getReloj().getTimer().cancel();
                controller.getReloj().getTimer().purge();
            }
        });
        stage.show();
    }

}
