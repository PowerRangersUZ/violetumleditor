package com.horstmann.violet.application.menu;

import com.horstmann.violet.product.diagram.classes.node.ClassNode;

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
    private static final String classNameProperties = "StartFromBig";
    private String selectedClassName = "disabled";

    /**
     * Default constructor
     */
    public SettingProperties() {
        if (IsPropertiesFileExist()) {
            loadProperties();
            if (getSelectedLanguage() != null) {
                Locale locale = new Locale(getSelectedLanguage());
                Locale.setDefault(locale);
            }

            if (getSelectedClassName().equals("enabled")) {
                ClassNode.nameChange = true;
            }

        }
        setSelectedLanguage(Locale.getDefault().getLanguage());

    }

    /**
     * Get selectedLanguage
     *
     * @return selectedLanguage
     */
    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    /**
     * Get SelectedClassName(
     *
     * @return selectedClassName
     */

    public String getSelectedClassName() {
        return selectedClassName;
    }

    /**
     * Set selectedLanguage
     *
     * @param selectedLanguage
     */
    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    /**
     * Set selectedClassName
     *
     * @param selectedClassName
     */
    public void setSelectedClassName(String selectedClassName) {
        this.selectedClassName = selectedClassName;
    }

    /**
     * Check is properties file exist
     *
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
     * Save properties to file
     */
    public void savePropertiesToFile() {

        if (IsPropertiesFileExist()) {
            try {

                Properties properties = new Properties();
                OutputStream outputStream = new FileOutputStream(propertiesFile);
                properties.setProperty(languageProperties, selectedLanguage);
                properties.store(outputStream, "User properties");
                outputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else

            createPropertiesFile();

    }

    /**
     * Load properties from file to variables
     */
    public void loadProperties() {

        try {

            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream(propertiesFile);
            properties.load(inputStream);
            selectedLanguage = properties.getProperty(languageProperties);
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
