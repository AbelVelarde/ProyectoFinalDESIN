package es.abel.dam;

import es.abel.dam.models.Mail;

import java.util.ArrayList;
import java.util.List;

public class FactoriaEmails {

    public static List<FactoryEmail> crearListaEmails(){
        FactoryEmail email = new FactoryEmail(true, "Saludo", "Paco@gmail.com", "Hola", "13-01-1997");
        FactoryEmail email2 = new FactoryEmail(false, "Despedida", "Maria@gmail.com", "Adios", "14-02-2020");

        List<FactoryEmail> emails = new ArrayList<>();
        emails.add(email);
        emails.add(email2);

        return emails;
    }

}
