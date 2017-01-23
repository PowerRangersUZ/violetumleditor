package com.horstmann.violet.framework.language;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Marcin on 10.01.2017.
 */
public class LanguageManager {

    public LanguageManager() {
        loadAvailableLang();
    }

    private List<Language> languages = new ArrayList<Language>();

    /**
     * Get language interval
     *
     * @return language interval
     */
    public List<Language> getLanguages() {
        return languages;
    }

    /**
     * Load languages and add to list
     */
    public void loadAvailableLang() {


        String[] languages = Locale.getISOLanguages();
        for (String countryCode : languages) {

            String path = "Language_" + countryCode + ".properties";
            URL file = ClassLoader.getSystemResource("com/horstmann/violet/framework/language/" + path);

            if (file != null) {

                Locale deLocale = new Locale(countryCode);
                String languageName = deLocale.getDisplayLanguage(deLocale);
                this.languages.add(new Language(countryCode, languageName));

            }

        }


    }


}
