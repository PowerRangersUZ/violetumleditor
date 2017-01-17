package com.horstmann.violet.framework.language;

/**
 * Created by Marcin on 10.01.2017.
 */
public class Language {
    public String shortcut;
    public String name;


    public Language(String shortcut,String name) {
        this.shortcut = shortcut;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }
}
