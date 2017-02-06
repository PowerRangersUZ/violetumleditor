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

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.help.AboutDialog;
import com.horstmann.violet.application.help.HelpManager;
import com.horstmann.violet.application.help.ShortcutDialog;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.classes.node.ClassNode;
import com.horstmann.violet.product.diagram.property.text.LineText;
import com.horstmann.violet.product.diagram.property.text.SingleLineText;
import com.horstmann.violet.workspace.IWorkspace;

/**
 * Help menu
 *
 * @author Alexandre de Pellegrin
 *
 */
@ResourceBundleBean(resourceReference = MenuFactory.class)
public class SettingMenu extends JMenu
{
    @ResourceBundleBean(key = "setting")
    public SettingMenu(MainFrame mainFrame)
    {
        ResourceBundleInjector.getInjector().inject(this);
        this.mainFrame = mainFrame;
        this.createMenu();
    }

    /**
     * Initializes menu
     */

    private void createMenu() {
        toUpper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toUpper.isSelected()) {
                    ClassNode.capitalizeLetter = true;
                } else {
                    ClassNode.capitalizeLetter = false;
                }
            }
        });
        this.add(toUpper);
    }


    /**
     * Main app frame where this menu is attached to
     */
    private JFrame mainFrame;
    @ResourceBundleBean(key = "toUpper")
    private JCheckBoxMenuItem toUpper;





}
