package com.horstmann.violet.application.menu;

import java.io.*;
import java.util.Properties;

/**
 * Created by Marcin on 12.01.2017.
 */
public class SettingProperties {

    private static final String languageProperties = "language";
    private String selectedLanguage;

    /**
     * Get selectedLanguage interval
     * @return selectedLanguage interval
     */
    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    /**
     * Set autosave interval
     * @param selectedLanguage interval
     */
    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    /**
     * Check is properties file exist
     * @return boolean
     */
    public boolean IsPropertiesFileExist() {
        File f = new File(System.getProperty("user.home") + File.separator + "user.properties");
        if (f.exists() && !f.isDirectory()) {

            return true;
        }

        return false;
    }

    /**
     * Create properties file
     */
    private void createPropertiesFile() {
        try {

            OutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + File.separator + "user.properties"));
            out.close();
            savePropertiesToFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Save properties
     */
    public void savePropertiesToFile() {

        if (IsPropertiesFileExist()) {
            try {
                Properties props = new Properties();
                OutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + File.separator + "user.properties"));
                props.setProperty(languageProperties, selectedLanguage);
                props.store(out, "User properties");
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else

            createPropertiesFile();

    }


    /**
     * Load properties
     */
    public void loadProperties() {

        try {

            Properties props = new Properties();
            InputStream in = new FileInputStream(new File(System.getProperty("user.home") + File.separator + "user.properties"));
            props.load(in);
            selectedLanguage = props.getProperty(languageProperties);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
