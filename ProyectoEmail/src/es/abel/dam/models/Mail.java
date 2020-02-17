package es.abel.dam.models;

import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.util.MimeMessageParser;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mail{

    private Message message;

    public Message getMessage(){
        return message;
    }

    public boolean isRead(){
        try{
            return message.isSet(Flags.Flag.SEEN);
        }catch(MessagingException e){
            e.printStackTrace();
        }
        return true;
    }

    public String getAsunto(){
        try {
            return message.getSubject();
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getRemitente(){
        try {
            return message.getFrom()[0].toString();
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getContenido(){
        MimeMessageParser mimeParser = new MimeMessageParser((MimeMessage) message);
        try {
            mimeParser.parse();
            if(mimeParser.getHtmlContent() != null){
                return mimeParser.getHtmlContent();
            }
            else{
                return mimeParser.getPlainContent();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = message.getReceivedDate();
            return sdf.format(date);
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] getDestinatario(){
        String[] dest = null;
        try {
            dest = new String[message.getAllRecipients().length];
            for(int i=0; i<message.getAllRecipients().length;i++){
                dest[i] = message.getAllRecipients()[i].toString();
            }
            return dest;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Mail(Message message){
        this.message = message;
    }
}
