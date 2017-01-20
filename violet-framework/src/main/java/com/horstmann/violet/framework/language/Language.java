package com.horstmann.violet.framework.language;

/**
 * Created by Marcin on 10.01.2017.
 */
public class Language {

    public String shortcut;
    public String name;

    /**
     * Constructor Language
     *
     * @param shortcut ,name
     */
    public Language(String shortcut, String name) {
        this.shortcut = shortcut;
        this.name = name;
    }

    /**
     * Get name interval
     *
     * @return name interval
     */
    public String getName() {
        return name;
    }

    /**
     * Set name interval
     *
     * @param name interval
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get shortcut interval
     *
     * @return shortcut interval
     */
    public String getShortcut() {
        return shortcut;
    }

    /**
     * Set shortcut interval
     *
     * @param shortcut interval
     */
    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }
}
