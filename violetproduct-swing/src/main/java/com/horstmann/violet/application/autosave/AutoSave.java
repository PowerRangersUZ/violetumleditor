package com.horstmann.violet.application.autosave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import javax.swing.Timer;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.workspace.IWorkspace;

public class AutoSave implements ActionListener
{
	private MainFrame mainFrame;
	private Timer saveTimer;
	private int saveInterval;
	private boolean autoSaveEnabled;
	private String autoSaveDirectory;

    /**
     * Constructor AutoSave
     *
     * @param mainFrame where is attached this menu
     */
    public AutoSave(MainFrame mainFrame)
    {
		BeanInjector.getInjector().inject(this);
        AutosaveSettings settings = new AutosaveSettings();
        if (settings.isEnableAutosave()) {
            loadSettings();

		if (mainFrame != null)
			{
				this.mainFrame = mainFrame;
				if (createVioletDirectory())
				{
					openAutoSaveDirectory();
					initializeTimer();
				}
			}
        }
	}

    public String getAutoSaveDirectory()
    {
        return autoSaveDirectory;
    }

    /**
     * Create Violet directory
     *
     * @return true if path was created
     */
    private boolean createVioletDirectory()
    {
        File directory = new File(autoSaveDirectory);
        return directory.isDirectory() || directory.mkdir();
    }

    /**
     * Get autosave file in directory, if exist initialize AutoSaveRecover frame
     */
    private void openAutoSaveDirectory()
    {
        File directory = new File(autoSaveDirectory);
        emptyFileRemove();
        if (directory.isDirectory())
        {
            File[] files = directory.listFiles();
            if (files.length == 0)
                return;

            new AutoSaveRecover(mainFrame);

        }
    }

    /**
     * Remove Violet empty saves
     */
    private void emptyFileRemove()
    {
        File directory = new File(autoSaveDirectory);

        File[] files = directory.listFiles();

        for (File file : files)
        {
            if (file.length() == 0)
                file.delete();

        }
    }

    /**
     * Initialize Timer
     */
    private void initializeTimer()
    {
        saveTimer = new Timer(saveInterval, (ActionListener) this);
        saveTimer.setInitialDelay(0);
        saveTimer.start();
    }

    /**
     * Action Performed
     *
     * @param actionEvent event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        for (IWorkspace workspace : mainFrame.getWorkspaceList())
        {
            IGraphFile graphFile = workspace.getGraphFile();
            if (graphFile.isSaveRequired())
            {
                graphFile.autoSave(autoSaveDirectory);
            }
        }
    }
    
    public void reloadSettings() {
    	loadSettings();
    	if (mainFrame != null) {
			for (IWorkspace workspace : mainFrame.getWorkspaceList())
	        {
	            IGraphFile graphFile = workspace.getGraphFile();
	            graphFile.autoSaveSettingsWasChanged();
	        }
    	}
    }
        
	private void loadSettings() {
		AutosaveSettings settings = new AutosaveSettings();
		if (settings.isEnableAutosave()) {
			saveInterval = settings.getAutosaveInterval();
			autoSaveDirectory = settings.getAutosavePath();

			initializeTimer();
		} else {
			if (saveTimer != null) {
				saveTimer.stop();
				saveTimer = null;
			}
		}
		
	}

}