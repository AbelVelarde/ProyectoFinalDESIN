package es.abel.dam.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtils {

    //TODO: Comentar codigo

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Convierte una fecha en formato LocalDate a formato Date.
     * @param localDate fecha en formato LocalDate.
     * @return fecha en formato Date.
     */
    public static Date convertToDate(LocalDate localDate){
        Date fecha = null;
        try {
            fecha = sdf.parse(localDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
    }

    /**
     * Convierte una fecha en formato Date en una fecha en formato LocalDate.
     * @param date fecha en formato Date,
     * @return fecha convertida a LocalDate.
     */
    public static LocalDate convertToLocalDate(Date date){
        String fecha = sdf.format(date);
        return LocalDate.parse(fecha);
    }

}
