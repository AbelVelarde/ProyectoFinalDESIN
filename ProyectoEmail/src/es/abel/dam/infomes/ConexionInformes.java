package es.abel.dam.infomes;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConexionInformes {
    private Connection con = null;

    public ConexionInformes(){
        loadConnection();
    }

    private void loadConnection(){
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/test", "sa", "");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void generarInforme(){
       //Map<String, Object> parametros = new HashMap<>();

        try{
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/es/abel/hsqldb/informes/"), null, con);
        }catch(JRException e){
            e.printStackTrace();
        }
    }

}
