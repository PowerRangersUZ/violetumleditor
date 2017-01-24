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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.horstmann.violet.UMLEditorApplication;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.framework.dialog.DialogFactory;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.InjectedBean;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.framework.theme.ITheme;
import com.horstmann.violet.framework.theme.ThemeInfo;
import com.horstmann.violet.framework.theme.ThemeManager;
import com.horstmann.violet.framework.util.ResourceManager;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.editorpart.IEditorPart;

/**
 * View menu
 * 
 * @author Alexandre de Pellegrin
 * 
 */
@ResourceBundleBean(resourceReference = MenuFactory.class)
public class ViewMenu extends JMenu
{

    /**
     * Default constructor
     * 
     * @param mainFrame where this menu is attached
     */
@ResourceBundleBean(key = "view")
    public ViewMenu(MainFrame mainFrame)
    {
        BeanInjector.getInjector().inject(this);
        ResourceBundleInjector.getInjector().inject(this);
        this.mainFrame = mainFrame;
        this.createMenu();
    }

    /**
     * Initializes menu
     */
    private void createMenu()
    {

        zoomOut.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                performZoomOut();
            }
        });
        this.add(zoomOut);

        zoomIn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                performZoomIn();
            }
        });
        this.add(zoomIn);

        growDrawingArea.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                performGrowDrawingArea();
            }
        });
        this.add(growDrawingArea);

        clipDrawingArea.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                performClipDrawingArea();
            }
        });
        this.add(clipDrawingArea);

        smallerGrid.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                performDisplaySmallerGrid();
            }
        });
        this.add(smallerGrid);

        largerGrid.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                performDisplayLargerGrid();
            }
        });
        this.add(largerGrid);

        hideGridItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                performHideGrid(event);
            }
        });

        this.addMenuListener(new MenuListener()
        {
            public void menuSelected(MenuEvent event)
            {
                if (mainFrame.getWorkspaceList().size() == 0) return;
                IWorkspace activeWorkspace = mainFrame.getActiveWorkspace();
                IEditorPart activeEditor = activeWorkspace.getEditorPart();
                hideGridItem.setSelected(!activeEditor.getGrid().isVisible());
            }

            public void menuDeselected(MenuEvent event)
            {
            }

            public void menuCanceled(MenuEvent event)
            {
            }
        });

        ButtonGroup lookAndFeelButtonGroup = new ButtonGroup();
        String preferedLafName = this.themeManager.getPreferedLookAndFeel();
        List<ITheme> themes = this.themeManager.getInstalledThemes();
        for (ITheme aTheme : themes)
        {
            ThemeInfo themeInfo = aTheme.getThemeInfo();
        	String themeName = themeInfo.getName();
            final String themeClassName = themeInfo.getThemeClass().getName();
            JMenuItem lafMenu = new JCheckBoxMenuItem(themeName);

            lafMenu.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    performChangeLookAndFeel(themeClassName);
                }
            });

            changeLookAndFeelMenu.add(lafMenu);
            lookAndFeelButtonGroup.add(lafMenu);
            if (themeClassName.equals(preferedLafName))
            {
                lafMenu.setSelected(true);
            }

        }
        this.add(changeLookAndFeelMenu);

        performButtonGroup();

        this.add(language);

         langEnglish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              performEnglishLanguage();

            }
        });
        language.add(langEnglish);

        langFrench.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        language.add(langFrench);

        langGreman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        language.add(langGreman);

        langPolish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        language.add(langPolish);


    }

    /**
     * Performs zoom out action
     */
    private void performZoomOut()
    {
        if (mainFrame.getWorkspaceList().size() == 0) return;
        IWorkspace workspace = mainFrame.getActiveWorkspace();
        workspace.getEditorPart().changeZoom(-1);
    }

    /**
     * Performs zoom in action
     */
    private void performZoomIn()
    {
        if (mainFrame.getWorkspaceList().size() == 0) return;
        IWorkspace workspace = mainFrame.getActiveWorkspace();
        workspace.getEditorPart().changeZoom(1);
    }

    /**
     * Performs gros drawing area action
     */
    private void performGrowDrawingArea()
    {
        if (mainFrame.getWorkspaceList().size() == 0) return;
        IWorkspace workspace = mainFrame.getActiveWorkspace();
        workspace.getEditorPart().growDrawingArea();
    }

    /**
     * Performs clip drawing area action
     */
    private void performClipDrawingArea()
    {
        if (mainFrame.getWorkspaceList().size() == 0) return;
        IWorkspace workspace = mainFrame.getActiveWorkspace();
        workspace.getEditorPart().clipDrawingArea();
    }


    /**
     * Performs display smaller grid action
     */
    private void performDisplaySmallerGrid()
    {
        if (mainFrame.getWorkspaceList().size() == 0) return;
        IWorkspace workspace = mainFrame.getActiveWorkspace();
        IEditorPart editorPart = workspace.getEditorPart();
        editorPart.getGrid().changeGridSize(-1);
        editorPart.getSwingComponent().repaint();
    }

    /**
     * Performs display larger grid action
     */
    private void performDisplayLargerGrid()
    {
        if (mainFrame.getWorkspaceList().size() == 0) return;
        IWorkspace workspace = mainFrame.getActiveWorkspace();
        IEditorPart editorPart = workspace.getEditorPart();
        editorPart.getGrid().changeGridSize(1);
        editorPart.getSwingComponent().repaint();
    }

    /**
     * Performs hist grid action
     * 
     * @param event that handle the checkbox menu item to know if the hide sould be shown or not
     */
    private void performHideGrid(ActionEvent event)
    {
        if (mainFrame.getWorkspaceList().size() == 0) return;
        IWorkspace workspace = mainFrame.getActiveWorkspace();
        boolean isHidden = hideGridItem.isSelected();
        IEditorPart editorPart = workspace.getEditorPart();
        if (isHidden)
        {
            editorPart.getGrid().setVisible(false);
        }
        else
        {
            editorPart.getGrid().setVisible(true);
        }
        editorPart.getSwingComponent().repaint();
    }

    /**
     * Performs look and feel change
     * 
     * @param className look and feel or pgs theme class name
     */
    private void performChangeLookAndFeel(String className)
    {
        if (mainFrame.getWorkspaceList().size() == 0) return;
        this.themeManager.setPreferedLookAndFeel(className);
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(changeLAFDialogMessage);
        optionPane.setIcon(changeLAFDialogIcon);

    }

    /**
     * Performs creation of leanguage button group
     */
    private void performButtonGroup(){
        ButtonGroup langButtonGroup = new ButtonGroup();

        langButtonGroup.add(langEnglish);
        langButtonGroup.add(langFrench);
        langButtonGroup.add(langGreman);
        langButtonGroup.add(langPolish);
    }


    /**
     * Performs change language to english
     *
     */
    private void performEnglishLanguage(){
        if (mainFrame.getWorkspaceList().size() == 0) return;
        IWorkspace workspace = mainFrame.getActiveWorkspace();
        IEditorPart editorPart = workspace.getEditorPart();
        performLocale("fr","FR");
        editorPart.getSwingComponent().repaint();

    }

    /**
     * Performs locale set
     * @param country
     * @param region
     */
    private void performLocale(String country,String region){
        Locale locale = new Locale(country,region);
        setDefaultLocale(locale);
        BeanInjector.getInjector().inject(this);
        ResourceBundleInjector.getInjector().inject(this);
    }


    private MainFrame mainFrame;

    @ResourceBundleBean(key = "view.zoom_out")
    private JMenuItem zoomOut;

    @ResourceBundleBean(key = "view.zoom_in")
    private JMenuItem zoomIn;

    @ResourceBundleBean(key = "view.grow_drawing_area")
    private JMenuItem growDrawingArea;

    @ResourceBundleBean(key = "view.clip_drawing_area")
    private JMenuItem clipDrawingArea;

    @ResourceBundleBean(key = "view.smaller_grid")
    private JMenuItem smallerGrid;

    @ResourceBundleBean(key = "view.larger_grid")
    private JMenuItem largerGrid;

    @ResourceBundleBean(key = "view.langEnglish")
    private JCheckBoxMenuItem langEnglish;

    @ResourceBundleBean(key = "view.langPolish")
    private JCheckBoxMenuItem langPolish;

    @ResourceBundleBean(key = "view.langFrench")
    private JCheckBoxMenuItem langFrench;

    @ResourceBundleBean(key = "view.langGerman")
    private JCheckBoxMenuItem langGreman;

    @ResourceBundleBean(key = "view.hide_grid")
    private JCheckBoxMenuItem hideGridItem;

    @ResourceBundleBean(key = "view.change_laf")
    private JMenu changeLookAndFeelMenu;

    @ResourceBundleBean(key = "view.language")
    private JMenu language;

    @ResourceBundleBean(key = "dialog.change_laf.title")
    private String changeLAFDialogTitle;

    @ResourceBundleBean(key = "dialog.change_laf.ok")
    private String changeLAFDialogMessage;

    @ResourceBundleBean(key = "dialog.change_laf.icon")
    private ImageIcon changeLAFDialogIcon;
    
    @InjectedBean
    private DialogFactory dialogFactory;
    
    @InjectedBean
    private ThemeManager themeManager;

}
