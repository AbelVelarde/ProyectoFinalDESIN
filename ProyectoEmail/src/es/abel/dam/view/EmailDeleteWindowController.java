package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.MailAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EmailDeleteWindowController extends BaseController implements Initializable{

    @FXML
    private ComboBox<MailAccount> cbCuentas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbCuentas.setItems(Logica.getInstance().getAccountList());
    }

    @FXML
    private void borrarCuenta(){
        MailAccount mailAccount = cbCuentas.getSelectionModel().getSelectedItem();
        Logica.getInstance().deleteAccount(mailAccount);
        getStage().close();
    }
}
