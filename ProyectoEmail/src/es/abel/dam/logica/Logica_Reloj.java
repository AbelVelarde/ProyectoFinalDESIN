package es.abel.dam.logica;

import es.abel.dam.models.MailAccount;
import es.abel.reloj.RelojDigital;
import es.abel.reloj.Tarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class Logica_Reloj {

    private static Logica_Reloj logica;
    private RelojDigital reloj;

    private ObservableList<Tarea> listaTareas;

    public Logica_Reloj(){
        listaTareas = loadListaTareas();
        if(listaTareas == null){
            listaTareas = FXCollections.observableArrayList();
        }
    }

    public void setReloj(RelojDigital reloj){
        this.reloj = reloj;
        for (Tarea tarea : listaTareas) {
            reloj.registrarTarea(tarea);
        }
    }

    public RelojDigital getReloj(){
        return reloj;
    }

    public static Logica_Reloj getInstance(){
        if(logica==null){
            logica = new Logica_Reloj();
        }
        return logica;
    }

    public ObservableList<Tarea> getListaTareas() {
        return listaTareas;
    }

    public void addTarea(Tarea tarea) {
        listaTareas.add(tarea);
    }

    public void removeTarea(Tarea tarea){
        listaTareas.remove(tarea);
    }

    public void saveListaTareas() {
        File file = new File("archivos/FicheroTareas.txt");
        ObjectOutputStream oos = null;
        try{
            ArrayList<Tarea> arrayList = new ArrayList<>(listaTareas);
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(arrayList);
        }catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ObservableList<Tarea> loadListaTareas(){
        File file = new File("archivos/FicheroTareas.txt");
        ObjectInputStream ois = null;
        try{
            if(file.exists()){
                ois = new ObjectInputStream(new FileInputStream(file));
                return FXCollections.observableArrayList((ArrayList<Tarea>)ois.readObject());
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
