package com.horstmann.violet.application.menu;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.InjectedBean;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.framework.language.Language;
import com.horstmann.violet.framework.language.LanguageManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@ResourceBundleBean(resourceReference = MenuFactory.class)
public class SettingMenu extends JMenu
{
    @ResourceBundleBean(key = "setting")
    public SettingMenu(final MainFrame mainFrame)
    {
        ResourceBundleInjector.getInjector().inject(this);
        this.mainFrame = mainFrame;
        this.createMenu();
    }

    /**
     * Initializes menu
     */
    private void createMenu()
    {
        ButtonGroup languageButtonGroup = new ButtonGroup();
        languageManager = new LanguageManager();

        String preferedLanguage = "";
        preferedLanguage = this.languageManager.getPreferedLanguage();

        List<Language> languageList = this.languageManager.getLanguageList();

        for (final Language language : languageList)
        {
            final String languageName = language.getName();
            JMenuItem languageMenuItem = new JCheckBoxMenuItem(language.getLanguage().getDisplayLanguage());
            languageMenuItem.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    changeLanguage(languageName);
                }
            });
            changeLanguageMenu.add(languageMenuItem);
            languageButtonGroup.add(languageMenuItem);

            if (language.getName().equals(preferedLanguage))
            {
                languageMenuItem.setSelected(true);
            }

        }
        this.add(changeLanguageMenu);

    }

    /**
     * Set prefered language
     */
    private void changeLanguage(String languageName)
    {
        showAlert();
        languageManager.setPreferedLanguage(languageName);
    }

    /**
     * Showing save alert
     */
    private void showAlert()
    {
        String[] options = {"OK"};

        JOptionPane.showOptionDialog(null, changeLanguageDialogMessage, changeLanguageDialogTitle,
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
    }

    private MainFrame mainFrame;

    @InjectedBean
    private LanguageManager languageManager;

    @ResourceBundleBean(key = "setting.langauge")
    private JMenu changeLanguageMenu;

    @ResourceBundleBean(key = "dialog.change_language.title")
    private String changeLanguageDialogTitle;

    @ResourceBundleBean(key = "dialog.change_language.ok")
    private String changeLanguageDialogMessage;


}