package es.abel.dam.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alerts {

    /**
     * Alerta para confirmar el borrado
     * @return true si se presiona el boton "aceptar"
     */
    public static boolean alertaBorradoConfim() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("");
        alert.setContentText("¿Desea borrar este partido? No podrá recuperarlo más adelante.");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Alerta de error de borrado o edicion cuando no se selecciona una fila de la tabla
     */
    public static void alertaNoSelec() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Error, hay que seleccionar un partido primero.");
        alert.showAndWait();
    }

    /**
     * Alerta cuando las credenciales no coinciden
     */
    public static void alertaCredencialesEmail(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Las credenciales no coinciden");
        alert.showAndWait();
    }

}
