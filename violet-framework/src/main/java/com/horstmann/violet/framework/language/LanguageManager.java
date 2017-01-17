package com.horstmann.violet.framework.language;


import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Marcin on 10.01.2017.
 */
public class LanguageManager {


    List<Language> languageArrayList = new ArrayList<Language>();

    @ResourceBundleBean(key = "locale")
    private String changeLangMenu;


    public List<Language> getLanguageArrayList() {
        return languageArrayList;
    }


    public void loadAllAvibleLang() {


        String[] langs = Locale.getISOLanguages();
        for (String countryCode : langs) {

            String path = "Language_" + countryCode + ".properties";
            URL file = ClassLoader.getSystemResource("com/horstmann/violet/framework/language/" + path);

            if (file != null) {

                Locale deLocale = new Locale(countryCode);
                String languageName = deLocale.getDisplayLanguage(deLocale);
                languageArrayList.add(new Language(countryCode, languageName));

            }

        }


    }


}
