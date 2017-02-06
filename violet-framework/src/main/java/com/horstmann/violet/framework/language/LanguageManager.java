package com.horstmann.violet.framework.language;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Marcin on 10.01.2017.
 */
public class LanguageManager {

    /**
     * Default constructor
     */
    public LanguageManager() {
        loadAvailableLanguage();
    }

    private List<Language> languages = new ArrayList<Language>();

    /**
     * Get languages List
     *
     * @return languages
     */
    public List<Language> getLanguages() {
        return languages;
    }

    /**
     * Load languages and add to list
     */
    private void loadAvailableLanguage() {


        String[] languages = Locale.getISOLanguages();
        for (String countryCode : languages) {

            String path = "Language_" + countryCode + ".properties";
            URL file = ClassLoader.getSystemResource("com/horstmann/violet/framework/language/" + path);

            if (file != null) {

                Locale locale = new Locale(countryCode);
                String languageName = locale.getDisplayLanguage(locale);
                this.languages.add(new Language(countryCode, languageName));

            }

        }


    }


}
