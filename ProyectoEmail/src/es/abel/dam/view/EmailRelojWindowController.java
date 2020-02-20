package es.abel.dam.view;

import es.abel.reloj.Tarea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class EmailRelojWindowController extends BaseController implements Initializable {

    @FXML
    private CheckBox checkFormato;

    @FXML
    private TableView<Tarea> tablaTareas;

    @FXML
    private TextField tfContexto;

    @FXML
    private ComboBox<Integer> cbhora;

    @FXML
    private ComboBox<Integer> cbMins;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private Button btnAddTarea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
