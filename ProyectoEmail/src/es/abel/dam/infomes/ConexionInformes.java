package es.abel.dam.infomes;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Mail;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConexionInformes {

//    private void loadConnection(){
//        try {
//            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/test", "sa", "");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

    public void generarInforme(Mail mail){
        try{
            JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(Logica.getInstance().getReportList(mail));
            Map<String, Object> param = new HashMap<>();
            InputStream fichero = getClass().getResourceAsStream("InformeEmail.jasper");
            System.out.println(fichero);
            JasperPrint print = JasperFillManager.fillReport(fichero,
                    param, jr);
            JasperExportManager.exportReportToPdfFile(print, "informes/InformeEmails.pdf");
        }catch(JRException e){
            e.printStackTrace();
        }
    }

}
