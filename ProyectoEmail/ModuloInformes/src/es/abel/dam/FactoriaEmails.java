package es.abel.dam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FactoriaEmails {

    public static List<EmailInforme> crearListaEmails() throws ParseException {
        EmailInforme email = new EmailInforme( "Saludo", "Paco@gmail.com", "Hola", "13/01/1997");
        EmailInforme email2 = new EmailInforme("Despedida", "Maria@gmail.com", "Adios", "14/02/2020");

        List<EmailInforme> emails = new ArrayList<>();
        emails.add(email);
        emails.add(email2);

        return emails;
    }
}
