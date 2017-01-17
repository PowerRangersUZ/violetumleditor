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

package com.horstmann.violet.application.menu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.horstmann.violet.application.gui.*;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.framework.language.LanguageManager;


@ResourceBundleBean(resourceReference = MenuFactory.class)
public class SettingMenu extends JMenu {
    LanguageManager languageManager = new LanguageManager();
    private SettingProperties settingPropertis = new SettingProperties();
    private MainFrame mainFrame;


    @ResourceBundleBean(key = "setting")
    public SettingMenu(final MainFrame mainFrame) {
        ResourceBundleInjector.getInjector().inject(this);
        this.mainFrame = mainFrame;
        this.createMenu();

    }


    @ResourceBundleBean(key = "setting.langauge")
    private JMenu settingItemMenuLanguage;

    @ResourceBundleBean(key = "setting.nameofclass")
    private JCheckBoxMenuItem settingItemMenuUpperNameClass;

    @ResourceBundleBean(key = "dialog.change_laf.title")
    private String changeLAFDialogTitle;

    @ResourceBundleBean(key = "setting.dialog.change_language")
    private String changeLAFDialogMessage;


    private void createMenu() {
        this.add(settingItemMenuLanguage);

        languageManager.loadAllAvibleLang();

        for (int x = 0; x < languageManager.getLanguageArrayList().size(); x++) {
            final int index = x;
            JMenuItem menuLangSelect = new JMenuItem(languageManager.getLanguageArrayList().get(x).name);
            settingItemMenuLanguage.add(menuLangSelect);
            menuLangSelect.addActionListener(new ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent e) {
                                                     if (settingPropertis.IsPropertiesFileExist()) {
                                                         settingPropertis.reoladProperties();
                                                     }

                                                     settingPropertis.setLangauge(languageManager.getLanguageArrayList().get(index).shortcut);
                                                     settingPropertis.savePropertiesToFile();
                                                     languageChangeAlert();
                                                 }
                                             }
            );

        }

     }


    private void languageChangeAlert() {

        Object[] options = {"OK"};

        JOptionPane.showOptionDialog(null, changeLAFDialogMessage, changeLAFDialogTitle,

                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

                null, options, options[0]);
    }


}
