package es.abel.dam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FactoriaEmails {

    public static List<EmailInformeTres> crearListaEmails() throws ParseException {
        EmailInformeTres email = new EmailInformeTres( "Saludo", "Paco@gmail.com", "Hola", "13/01/1997", "INBOX");
        EmailInformeTres email2 = new EmailInformeTres("Despedida", "Maria@gmail.com", "Adios", "14/02/2020", "Borrados");

        List<EmailInformeTres> emails = new ArrayList<>();
        emails.add(email);
        emails.add(email2);

        return emails;
    }
}
