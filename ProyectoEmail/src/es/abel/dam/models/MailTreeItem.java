package es.abel.dam.models;

import javafx.scene.control.TreeItem;

import javax.mail.Folder;

public class MailTreeItem extends TreeItem<String> {

    private String nombre;
    private MailAccount mailAccount;
    private Folder folder;

    public Folder getFolder() {
        return folder;
    }
    public void setFolder(Folder folder) {
        this.folder = folder;
    }
    public MailAccount getMailAccount(){
        return mailAccount;
    }

    public MailTreeItem(String name, MailAccount mailAccount, Folder folder){
        super(name);
        nombre = name;
        this.mailAccount = mailAccount;
        this.folder = folder;
    }

    @Override
    public String toString(){
        return nombre;
    }

}
