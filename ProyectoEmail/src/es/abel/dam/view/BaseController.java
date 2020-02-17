package es.abel.dam.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseController {

    private Stage stage;

    protected Stage getStage(){
        return stage;
    }

    protected BaseController cargarVentana(String fxml, String titulo){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = fxmlLoader.load();
            BaseController controller = fxmlLoader.getController();
            controller.cargarStage(root, titulo);
            return controller;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void abrirVentana(boolean wait){
        if(stage != null){
            if (wait) {
                stage.showAndWait();
            } else {
                stage.show();
            }
        }
        else{
            throw new IllegalStateException("Hay que llamar a cargarVentana primero.");
        }
    }

    private void cargarStage(Parent root, String titulo){
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(titulo);
        stage.setScene(new Scene(root));
    }

}
