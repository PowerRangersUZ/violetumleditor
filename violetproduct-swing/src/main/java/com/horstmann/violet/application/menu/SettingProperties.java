package com.horstmann.violet.application.menu;

import java.io.*;
import java.util.Properties;

/**
 * Created by Marcin on 12.01.2017.
 */
public class SettingProperties {

    private String Langauge = "";

    public String getLangauge() {
        return Langauge;
    }

    public void setLangauge(String langauge) {
        Langauge = langauge;
    }

    public  boolean IsPropertiesFileExist() {
        File f = new File(System.getProperty("user.home") + File.separator + "user.properties");
        if (f.exists() && !f.isDirectory()) {

            return true;
        }

        return false;
    }


    public void createPropertiesFile() {
        try {

            OutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + File.separator + "user.properties"));
            out.close();
            savePropertiesToFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void savePropertiesToFile() {

        if (IsPropertiesFileExist()) {
            try {
                Properties props = new Properties();
                OutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + File.separator + "user.properties"));
                props.setProperty("language", Langauge);
                props.store(out, "User properties");
                out.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else

            createPropertiesFile();

    }



    public void reoladProperties() {


        try {
            String Langfromconf = "language";
            Properties props = new Properties();
            InputStream in = new FileInputStream(new File(System.getProperty("user.home") + File.separator + "user.properties"));
            props.load(in);
            Langauge = props.getProperty(Langfromconf);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
