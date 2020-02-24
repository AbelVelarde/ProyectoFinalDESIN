package es.abel.dam.view;

import es.abel.dam.utils.DateUtils;
import es.abel.reloj.OnTimeReach;
import es.abel.reloj.RelojDigital;
import es.abel.reloj.Tarea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;

public class EmailRelojWindowController extends BaseController implements Initializable {

    @FXML
    private CheckBox checkFormato;

    @FXML
    private TableView<Tarea> tablaTareas;

    @FXML
    private TextField tfContexto;

    @FXML
    private ComboBox<String> cbhora;

    @FXML
    private ComboBox<String> cbMins;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private Button btnAddTarea;

    @FXML
    private ComboBox<String> formatoHora;

    private RelojDigital reloj;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> formatos = FXCollections.observableArrayList();
        formatos.add("a.m.");
        formatos.add("p.m.");
        formatoHora.setItems(formatos);

        ObservableList<Tarea> tareas = FXCollections.observableArrayList();
        tablaTareas.setItems(tareas);


        checkFormato.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean valorFinal) {
                reloj.setFormato24h(valorFinal);
                cbhora.setItems(setListaHoras());
                cbMins.setItems(setListaMinutos());

                formatoHora.setVisible(!valorFinal);
            }
        });

        btnAddTarea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Calendar calendar = Calendar.getInstance();

                String texto = tfContexto.getText();
                String hora = cbhora.getSelectionModel().getSelectedItem();
                String min = cbMins.getSelectionModel().getSelectedItem();
                Date fecha = DateUtils.convertToDate(dpFecha.getValue());
                calendar.setTime(fecha);

                if(reloj.isFormato24h()){
                    calendar.set(Calendar.HOUR, Integer.valueOf(hora));
                }
                else{
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hora));
                }
                calendar.set(Calendar.MINUTE, Integer.valueOf(min));
                calendar.set(Calendar.SECOND, 0);
                Date date = calendar.getTime();

                System.out.println(date.toString());

                Tarea tarea = new Tarea(texto, date);

                reloj.registrarTarea(tarea);
                tareas.add(tarea);

                reloj.setOnTimeReach(new OnTimeReach() {
                    @Override
                    public void execute(Tarea tarea) {
                        Alerts.alertaAlarma(tarea);
                        tarea.setCompletada(true);
                        tareas.remove(tarea);
                    }
                });
            }
        });


    }

    private ObservableList<String> setListaHoras() {
        ObservableList<String> listaHoras = FXCollections.observableArrayList();
        int formato = 0;
        if(checkFormato.isSelected()){
            formato = 24;
        }
        else{
            formato = 12;
        }

        for (int i = 0; i < formato; i++) {
            String hora = String.valueOf(i);
            if (i < 10){
                hora = "0" + hora;
            }
            listaHoras.add(hora);
        }
        return listaHoras;
    }

    private ObservableList<String> setListaMinutos() {
        ObservableList<String> listaMins = FXCollections.observableArrayList();
        for (int i = 0; i < 60; i++) {
            String min = String.valueOf(i);
            if (i < 10){
                min = "0" + min;
            }
            listaMins.add(min);
        }
        return listaMins;
    }

    public void setReloj(RelojDigital reloj){
        this.reloj = reloj;
        checkFormato.setSelected(reloj.isFormato24h());
        System.out.println(reloj.isFormato24h());
    }
}
