package com.horstmann.violet.application.gui;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.injection.bean.ManiocFramework;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.framework.userpreferences.UserPreferencesService;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RecentProjectScreen extends JFrame{

    public RecentProjectScreen(MainFrame mainFrame)
    {
        ManiocFramework.BeanInjector.getInjector().inject(this);
        ResourceBundleInjector.getInjector().inject(this);

        if (mainFrame != null)
        {
            this.mainFrame = mainFrame;
            createRecentProjectsFrame();
        }
    }


    private void createRecentProjectsFrame()
    {
        final JFrame recentlyProjectsFrame = new JFrame(screenTitle);

        setRecentProjectsFrameProperties(recentlyProjectsFrame);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());

        recentlyProjectsFrame.add(jPanel);
        recentlyProjectsFrame.setAlwaysOnTop(true);

        this.setEnabled(false);

        addRecentFiles(recentlyProjectsFrame, jPanel);

        recentlyProjectsFrame.setResizable(false);
        recentlyProjectsFrame.setVisible(true);
        toFront();
    }

    private void setRecentProjectsFrameProperties(JFrame recentlyProjectsFrame) {
        final int SCREEN_WIDTH = 600;
        final int SCREEN_HEIGHT = 100;

        Dimension frameDimension = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
        recentlyProjectsFrame.setSize(frameDimension);

        recentlyProjectsFrame.setIconImage(appIcon);
        recentlyProjectsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Dimension mainFrameDimension = Toolkit.getDefaultToolkit().getScreenSize();
        setCenterPosition(recentlyProjectsFrame, mainFrameDimension);
    }

    private void setCenterPosition(JFrame recentlyProjectsFrame, Dimension mainFrameDimension) {
        final int CENTER_X = mainFrameDimension.width/2-recentlyProjectsFrame.getSize().width/2;
        final int CENTER_Y = mainFrameDimension.height/2-recentlyProjectsFrame.getSize().height/2;
        recentlyProjectsFrame.setLocation(CENTER_X, CENTER_Y);
    }

    private void addRecentFiles(final JFrame recentlyProjectsFrame, JPanel jPanel) {
        for (final IFile aFile : userPreferencesService.getRecentFiles())
        {

            String name = aFile.getFilename();
            JButton jButton = new JButton(name);
            jPanel.add(jButton);
            jButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    try
                    {
                        recentlyProjectsFrame.setVisible(false);
                        recentlyProjectsFrame.dispose();

                        IGraphFile graphFile = new GraphFile(aFile);
                        IWorkspace workspace = new Workspace(graphFile);
                        mainFrame.addWorkspace(workspace);

                        userPreferencesService.addOpenedFile(aFile);
                        userPreferencesService.addRecentFile(aFile);
                    }
                    catch (IOException e)
                    {
                        userPreferencesService.removeOpenedFile(aFile);
                    }finally {
                        setEnabled(true);
                        toFront();
                    }
                }
            });
        }
    }

    private MainFrame mainFrame;

    @ManiocFramework.InjectedBean
    private UserPreferencesService userPreferencesService;

    @ResourceBundleBean(key="recentProjectScreen.title")
    private String screenTitle;

    @ResourceBundleBean(key="app.icon")
    private Image appIcon;
}
