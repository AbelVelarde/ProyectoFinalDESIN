package es.abel.dam.view;

import es.abel.dam.infomes.ConexionInformes;
import es.abel.dam.logica.Logica;
import es.abel.dam.logica.Logica_Reloj;
import es.abel.dam.models.Mail;
import es.abel.dam.models.MailAccount;
import es.abel.dam.models.MailTreeItem;
import es.abel.dam.servicios.GetMailsService;
import es.abel.reloj.RelojDigital;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import org.docgene.help.JavaHelpFactory;
import org.docgene.help.gui.jfx.JFXHelpContentViewer;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Service;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmailMainWindowController extends BaseController implements Initializable {

    @FXML
    private TableView<Mail> tablaMails;
    @FXML
    private TreeView<String> treeViewMail;
    @FXML
    private WebView wvMail;
    @FXML
    private ProgressIndicator mainProgress;
    @FXML
    private Button btnMgResponder;
    @FXML
    private Button btnMgReenviar;
    @FXML
    private Button btnMgBorrar;
    @FXML
    private MenuItem menuBorrarCorreo;
    @FXML
    private MenuItem menuEditarCuenta;
    @FXML
    private RelojDigital reloj;
    @FXML
    private MenuItem acercaDe;

    private TreeItem root;

    JFXHelpContentViewer viewer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acercaDe.setDisable(true);

        inicializarAyuda();

        boolean hayCuentas = Logica.getInstance().loadListaCuentas();
        if(hayCuentas){
            treeViewMail.setRoot(Logica.getInstance().getRootPrincipal());
            treeViewMail.setShowRoot(false);
        }

        btnMgResponder.setDisable(true);
        btnMgReenviar.setDisable(true);
        btnMgBorrar.setDisable(true);
        menuBorrarCorreo.setDisable(true);
        menuEditarCuenta.setDisable(true);
        mainProgress.setVisible(false);

        treeViewMail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> oldValue, TreeItem<String> newValue) {
                MailTreeItem newmti = (MailTreeItem)newValue;
                MailTreeItem oldmti = (MailTreeItem)oldValue;

                if(oldmti != null && oldmti.getFolder().isOpen()){
                    try {
                        oldmti.getFolder().close(true);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                if(newmti!=null){
                    refrescarTabla(newmti);
                }
            }
        });

        tablaMails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mail>() {
            @Override
            public void changed(ObservableValue<? extends Mail> observableValue, Mail mail, Mail newValue) {
                WebEngine webEngine = null;
                String mensaje = "";
                if(newValue!=null){
                    btnMgResponder.setDisable(false);
                    btnMgReenviar.setDisable(false);
                    btnMgBorrar.setDisable(false);
                    menuBorrarCorreo.setDisable(false);
                    mensaje = newValue.getContenido();
                    webEngine = wvMail.getEngine();
                    webEngine.loadContent(mensaje);
                }
                else{
                    btnMgResponder.setDisable(true);
                    btnMgReenviar.setDisable(true);
                    btnMgBorrar.setDisable(true);
                    menuBorrarCorreo.setDisable(true);
                }
            }
        });

        pasarNegrita();
    }

    @FXML
    private void cargarLogin(){
        BaseController controller = cargarVentana("EmailLoginWindow.fxml", "Login");
        controller.abrirVentana(true);

//      tablaMails.setItems(Logica.getInstance().getDefaultMails());
        root = Logica.getInstance().getRootPrincipal();
        treeViewMail.setRoot(root);
        treeViewMail.setShowRoot(false);
        root.setExpanded(true);
    }

    @FXML
    private void eliminarCuenta(){
        BaseController controller = cargarVentana("EmailDeleteWindow.fxml", "Borrar Cuenta");
        controller.abrirVentana(true);

        treeViewMail.setRoot(Logica.getInstance().getRootPrincipal());
        treeViewMail.setShowRoot(false);
    }

    @FXML
    private void enviarCorreo(){

        //añadir a la configuracion de la VM
        //--add-opens=javafx.graphics/javafx.scene=org.controlsfx.controls
        //para evitar error al inicar la ventana

        BaseController controller = cargarVentana("EmailMensajeWindow.fxml", "Correo");
        controller.abrirVentana(true);
    }

    @FXML
    private void reenviarCorreo(){
        MailAccount a = ((MailTreeItem)treeViewMail.getSelectionModel().getSelectedItem()).getMailAccount();
        Mail m = tablaMails.getSelectionModel().getSelectedItem();

        BaseController controller = cargarVentana("EmailMensajeWindow.fxml", "Correo");
        ((EmailMensajeWindowController)controller).reenviar(m,a);
        controller.abrirVentana(true);
    }

    @FXML
    private void responderCorreo(){
        MailAccount a = ((MailTreeItem)treeViewMail.getSelectionModel().getSelectedItem()).getMailAccount();
        Mail m = tablaMails.getSelectionModel().getSelectedItem();

        BaseController controller = cargarVentana("EmailMensajeWindow.fxml", "Correo");
        ((EmailMensajeWindowController)controller).responder(m,a);
        controller.abrirVentana(true);
    }

    @FXML
    private void borrarCorreo(){
        MailTreeItem mti = (MailTreeItem)treeViewMail.getSelectionModel().getSelectedItem();
        Mail mail = tablaMails.getSelectionModel().getSelectedItem();
        Folder folder = mti.getFolder();
        MailAccount mailAccount = mti.getMailAccount();

        Logica.getInstance().deleteMail(mail, folder, mailAccount);

        refrescarTabla(mti);
    }

    @FXML
    private void cambiarTemas(){
        BaseController controller = cargarVentana("EmailTemasWindow.fxml", "Temas");
        controller.abrirVentana(true);
    }

    @FXML
    private void configuracionReloj(){
        Logica_Reloj.getInstance().setReloj(reloj);
        BaseController controller = cargarVentana("EmailRelojWindow.fxml", "Configuracion Reloj");
        controller.abrirVentana(false);
    }

    private void refrescarTabla(MailTreeItem mti){
        GetMailsService gms = new GetMailsService(mti.getFolder());
        gms.start();
        gms.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                mainProgress.setVisible(true);
            }
        });
        gms.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                tablaMails.setItems(gms.getValue());
                mainProgress.setVisible(false);
            }
        });
    }

    private void pasarNegrita(){
        tablaMails.setRowFactory(new Callback<TableView<Mail>, TableRow<Mail>>() {
            @Override
            public TableRow<Mail> call(TableView<Mail> mailTableView) {
                return new TableRow<>(){
                    @Override
                    protected void updateItem(Mail mail, boolean b){
                        super.updateItem(mail, b);
                        if(mail!=null){
                            if(!mail.isRead()){
                                setStyle("-fx-font-weight:bold");
                            }
                            else{
                                setStyle("");
                            }
                        }
                    }
                };
            }
        });
    }

    public RelojDigital getReloj(){
        return reloj;
    }

    @FXML
    private void generarInformeEmail(){
        ConexionInformes ci = new ConexionInformes();
        ci.generarInforme(getStage(), tablaMails.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void generarListaEmails(){
        ConexionInformes ci = new ConexionInformes();
        ci.generarInforme(getStage(), ((MailTreeItem) treeViewMail.getSelectionModel().getSelectedItem()).getFolder());
    }

    @FXML
    private void generarInformeCuenta(){
        ConexionInformes ci = new ConexionInformes();
        ci.generarInforme(getStage(), ((MailTreeItem) treeViewMail.getSelectionModel().getSelectedItem()).getMailAccount());
    }

    private void inicializarAyuda(){
        try {
            URL url = new File("ayuda/articles.zip").toURI().toURL();
            JavaHelpFactory factory = new JavaHelpFactory(url);
            factory.create();
            viewer = new JFXHelpContentViewer();
            factory.install(viewer);
            viewer.getHelpWindow(getStage(), "Help Content", 600, 700);
        }catch (Throwable e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarAyuda(){
        viewer.showHelpDialog(0,0);
    }


}
