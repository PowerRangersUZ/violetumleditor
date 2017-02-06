package com.horstmann.violet.application.menu;

import com.horstmann.violet.product.diagram.classes.node.ClassNode;

import java.io.*;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by Marcin on 12.01.2017.
 */
public class SettingProperties {

    private static final File PROPERTIES_FILE = new File(System.getProperty("user.home") + File.separator + "user.properties");
    private static final String LANGUAGE_PROPERTIES = "language";
    private String selectedLanguage;
    private static final String classNameProperties = "StartFromBig";
    private String selectedClassNameOption = "disabled";

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

            if (getSelectedClassNameOption().equals("enabled")) {
                ClassNode.startClassNameFromBig = true;
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

    public String getSelectedClassNameOption() {
        return selectedClassNameOption;
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
     * Set classname option
     *
     * @param selectedClassNameOption
     */

    public void setSelectedClassNameOption(String selectedClassNameOption) {
        this.selectedClassNameOption = selectedClassNameOption;
    }

    /**
     * Check is properties file exist
     *
     * @return boolean
     */
    private boolean IsPropertiesFileExist() {
        return PROPERTIES_FILE.exists() && !PROPERTIES_FILE.isDirectory();

    }

    /**
     * Create properties file
     */
    private void createPropertiesFile() {
        try {

            OutputStream out = new FileOutputStream(PROPERTIES_FILE);
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
                OutputStream outputStream = new FileOutputStream(PROPERTIES_FILE);
                properties.setProperty(LANGUAGE_PROPERTIES, selectedLanguage);
                properties.setProperty(classNameProperties, selectedClassNameOption);
                properties.store(outputStream, "User properties");
                outputStream.close();

            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else

            createPropertiesFile();

    }

    /**
     * Load properties from file to variables
     */
    private void loadProperties() {

        try {

            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream(PROPERTIES_FILE);
            properties.load(inputStream);
            selectedLanguage = properties.getProperty(LANGUAGE_PROPERTIES);
            selectedClassNameOption = properties.getProperty(classNameProperties);
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
