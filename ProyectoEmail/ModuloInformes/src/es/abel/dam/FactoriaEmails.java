package es.abel.dam;

import java.util.ArrayList;
import java.util.List;

public class FactoriaEmails {

    public static List<EmailInforme> crearListaEmails(){
        EmailInforme email = new EmailInforme(true, "Saludo", "Paco@gmail.com", "Hola", "13-01-1997");
        EmailInforme email2 = new EmailInforme(false, "Despedida", "Maria@gmail.com", "Adios", "14-02-2020");

        List<EmailInforme> emails = new ArrayList<>();
        emails.add(email);
        emails.add(email2);

        return emails;
    }

    public static EmailInforme crearEmail(){
        EmailInforme email = new EmailInforme(true, "Saludo", "Paco@gmail.com", "Hola", "13-01-1997");

        return email;
    }

}
