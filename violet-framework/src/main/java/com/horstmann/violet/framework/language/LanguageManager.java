/*
 Violet - A program for editing UML diagrams.

 Copyright (C) 2007 Cay S. Horstmann (http://horstmann.com)
 Alexandre de Pellegrin (http://alexdp.free.fr);

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.horstmann.violet.framework.language;

import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.InjectedBean;
import com.horstmann.violet.framework.userpreferences.UserPreferencesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LanguageManager
{
    public LanguageManager()
    {
        BeanInjector.getInjector().inject(this);
        InitLanguages();
    }

    /**
     * Initiates languages.
     *
     */
    private void InitLanguages()
    {
        Language polishLanguage = new Language("Polish", new Locale("pl"));
        Language englishLanguage = new Language("English", Locale.ENGLISH);
        Language frenchLanguage = new Language("French", Locale.FRENCH);
        Language germanLanguage = new Language("German", Locale.GERMAN);

        languageList.add(polishLanguage);
        languageList.add(englishLanguage);
        languageList.add(frenchLanguage);
        languageList.add(germanLanguage);
    }

    /**
     * @return language list.
     *
     */
    public List<Language> getLanguageList()
    {
        return languageList;
    }

    /**
     * Applies prefered language.
     *
     */
    public void applyPreferedLanguage()
    {
        String languageName = getPreferedLanguage();
        switchToLanguage(languageName);
    }

    public void setPreferedLanguage(String language)
    {
        this.userPreferencesServices.setPreferedLanguage(language);
    }

    public String getPreferedLanguage()
    {
        return this.userPreferencesServices.getPreferedLanguage();
    }

    /**
     * Switch to a specific language
     *
     * @param languageName
     */
    private void switchToLanguage(String languageName)
    {
        for(Language language : this.languageList)
        {
            if (languageName.equals(language.getName()))
            {
                Locale selectedDLanguage = language.getLanguage();
                Locale.setDefault(selectedDLanguage);
                return;
            }
        }

        Locale.setDefault(Locale.ENGLISH);
        throw new IllegalArgumentException("No language named: "+languageName);


        for(Language language : this.languageList)
        {
            if (languageName.equals(language.getName()))
            {
                Locale selectedDLanguage = language.getLanguage();
                Locale.setDefault(selectedDLanguage);
                return;
            } else
            {
                Locale.setDefault(Locale.ENGLISH);

            }
        }
    }

    private List<Language> languageList = new ArrayList<Language>();

    @InjectedBean
    private UserPreferencesService userPreferencesServices;

}