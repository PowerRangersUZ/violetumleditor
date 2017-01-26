package com.horstmann.violet.framework.language;

import java.util.Locale;

public class Language {
    private String name;
    private Locale language;

    public Language(String name, Locale language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public Locale getLanguage() {
        return language;
    }
}
