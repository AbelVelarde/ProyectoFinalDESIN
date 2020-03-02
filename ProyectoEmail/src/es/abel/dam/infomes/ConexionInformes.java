package es.abel.dam.infomes;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Mail;
import es.abel.dam.models.MailInforme;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.mail.Folder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConexionInformes {

    public void generarInforme(Mail mail){
        try{
            JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(Logica.getInstance().getReportMail(mail));
            Map<String, Object> param = new HashMap<>();
            File fichero = new File("informes/informeEmail/InformeEmail.jasper");
            System.out.println(fichero);
            JasperPrint print = JasperFillManager.fillReport(new FileInputStream(fichero),
                    param, jr);

            JasperExportManager.exportReportToPdfFile(print, "informes/informeEmail/InformeEmails.pdf");
        }catch(JRException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void generarInforme(Folder folder){
        try{
            ArrayList<MailInforme> mailList = (ArrayList<MailInforme>) Logica.getInstance().getReportList(folder);
            JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(mailList);
            File fichero = new File("informes/informeListaEmails/InformeListaEmail.jasper");

            Map<String, Object> param = new HashMap<>();
            param.put("carpeta", mailList.get(0).getCarpeta());

            JasperPrint print = JasperFillManager.fillReport(new FileInputStream(fichero),
                    param, jr);

            JasperExportManager.exportReportToPdfFile(print, "informes/informeListaEmails/InformeListaEmails.pdf");
        }catch(JRException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
