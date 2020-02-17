package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.MailAccount;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.util.ResourceBundle;

public class EmailLoginWindowController extends BaseController implements Initializable {

    @FXML
    private Button btnLoginAccept;
    @FXML
    private TextField tfCorreo;
    @FXML
    private PasswordField pfPassword;

    private MailAccount mailAccount;

    public MailAccount getMailAccount(){
        return mailAccount;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        tfCorreo.setText("sandierparapromociones@gmail.com");
        pfPassword.setText("abelvelarde97");

        String emailRegex= "^(.+)@(.+)$";

        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(tfCorreo, Validator.createEmptyValidator("El campo no puede estar vacío"));
        validationSupport.registerValidator(tfCorreo, Validator.createRegexValidator("Correo no valido", emailRegex, Severity.ERROR));
        validationSupport.registerValidator(pfPassword, Validator.createEmptyValidator("El campo no puede estar vacío"));

        validationSupport.invalidProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {
                btnLoginAccept.setDisable(newValue);
            }
        });
    }

    @FXML
    public void añadirMail(){
        mailAccount = new MailAccount(tfCorreo.getText(), pfPassword.getText());
        Logica.getInstance().setAccount(mailAccount);
        getStage().close();
    }

    @FXML
    private void cancelar(){
        getStage().close();
    }

}
