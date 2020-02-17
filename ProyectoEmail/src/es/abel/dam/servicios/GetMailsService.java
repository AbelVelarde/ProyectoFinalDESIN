package es.abel.dam.servicios;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Mail;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;

public class GetMailsService extends Service<ObservableList<Mail>> {

    Folder carpeta;

    public GetMailsService(Folder folder){
        carpeta = folder;
    }

    @Override
    protected Task<ObservableList<Mail>> createTask() {
        return new Task<ObservableList<Mail>>() {
            @Override
            protected ObservableList<Mail> call() throws Exception {
                return Logica.getInstance().getMailList(carpeta);
            }
        };
    }
}
