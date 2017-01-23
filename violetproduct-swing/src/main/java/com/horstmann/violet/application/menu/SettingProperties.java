package com.horstmann.violet.application.menu;

import java.io.*;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by Marcin on 12.01.2017.
 */
public class SettingProperties {

    private static final File propertiesFile = new File(System.getProperty("user.home") + File.separator + "user.properties");
    private static final String languageProperties = "language";
    private String selectedLanguage;

    public SettingProperties() {
        if (IsPropertiesFileExist()) {
            loadProperties();

            if (getSelectedLanguage() != null) {
                Locale locale = new Locale(getSelectedLanguage());
                Locale.setDefault(locale);
            }


        }

    }

    /**
     * Get selectedLanguage
     * @return selectedLanguage
     */
    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    /**
     * Set selectedLanguage
     * @param selectedLanguage
     */
    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    /**
     * Check is properties file exist
     * @return boolean
     */
    public boolean IsPropertiesFileExist() {
        return propertiesFile.exists() && !propertiesFile.isDirectory();

    }

    /**
     * Create properties file
     */
    private void createPropertiesFile() {
        try {

            OutputStream out = new FileOutputStream(propertiesFile);
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
                OutputStream out = new FileOutputStream(propertiesFile);
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
            InputStream in = new FileInputStream(propertiesFile );
            props.load(in);
            selectedLanguage = props.getProperty(languageProperties);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
