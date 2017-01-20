package com.horstmann.violet.application.autosave;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IFile;
import com.horstmann.violet.framework.file.LocalFile;
import com.horstmann.violet.framework.file.persistence.IFileReader;
import com.horstmann.violet.framework.file.persistence.JFileReader;
import com.horstmann.violet.framework.injection.bean.ManiocFramework;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

/**
 * Violet's auto save
 *
 * @author Pawel Majka
 */


public class AutoSave implements ActionListener {

	@ResourceBundleBean(key="dialog.autosave.title")
	private String dialogTitle;

	@ResourceBundleBean(key = "dialog.autosave.recover")
	private String buttonRecovery;

	@ResourceBundleBean(key = "dialog.autosave.startnew")
	private String buttonNew;

	private MainFrame mainFrame;
	private Timer saveTimer;

	private final int second = 100;
	private final int saveInterval = 60 * second;
	private final String autoSaveDirectory = System.getProperty("user.home") + File.separator + "VioletUML";

	/**
	 * Constructor Autosave
	 *  @param mainFrame where is attached this menu
	 */
	public AutoSave(MainFrame mainFrame) {
		BeanInjector.getInjector().inject(this);

		if (mainFrame != null) {
			this.mainFrame = mainFrame;
			if (createVioletDirectory()) {
				openAutoSaveDirectory();
				initializeTimer();
			}
		}
	}

	/**
	 *  Create Violet directory
	 * @return true if path was created
	 */
	private boolean createVioletDirectory() {
		File directory = new File(autoSaveDirectory);
		return directory.isDirectory() || directory.mkdir();


	}

	/**
	 *  Get autosave file in direcotry
	 */
	private void openAutoSaveDirectory() {
		File directory = new File(autoSaveDirectory);
		emptyFileRemove();
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files.length == 0)
				return;

			createSaveRecoverFrame();
		//	AutoSaveRecover aa= new AutoSaveRecover(mainFrame);// not work yet

		}
	}

	/**
	 *  Remove Violet empty saves
	 */
	private void emptyFileRemove() {
		File directory = new File(autoSaveDirectory);

		File[] files = directory.listFiles();

		for (File file : files) {
			if (file.length() == 0)
				file.delete();

		}
	}

	/**
	 * Initialize timer
	 */
	private void initializeTimer() {
		saveTimer = new Timer(saveInterval, (ActionListener) this);
		saveTimer.setInitialDelay(0);
		saveTimer.start();
	}

	/**
	 * Load autosave file
	 */
	public void loadAutoSaveFile() {
		File directory = new File(autoSaveDirectory);

			File[] files = directory.listFiles();

			for (File file : files) {


				try {

					IFile autoSaveFile = new LocalFile(file);
					IFileReader readFile = new JFileReader(file);
					InputStream in = readFile.getInputStream();

					if (in != null) {
						IGraphFile graphFile = new GraphFile(autoSaveFile);

						IWorkspace workspace = new Workspace(graphFile);

						mainFrame.addWorkspace(workspace);

						in.close();
					}


				} catch (IOException e) {
					file.delete();

				} catch (Exception e) {
					file.delete();

			}
		}
	}

	/**
	 * Open autosave frame
	 */
	private void createSaveRecoverFrame() {

		ResourceBundleInjector.getInjector().inject(this);
		ManiocFramework.BeanInjector.getInjector().inject(this);
		final JFrame autoSaveFrame = new JFrame(dialogTitle);
		autoSaveFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		autoSaveFrame.setSize(500, 300);
		autoSaveFrame.setLocation(dim.width / 2 - autoSaveFrame.getSize().width / 2, dim.height / 2 - autoSaveFrame.getSize().height / 2);

		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		autoSaveFrame.add(jPanel);

		JButton LoadAutoBtn = new JButton(buttonRecovery);
		LoadAutoBtn.setSize(100, 50);
		LoadAutoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{

				loadAutoSaveFile();

				autoSaveFrame.dispose();
			}
		});
		JButton StartNewBtn = new JButton(buttonNew);
		LoadAutoBtn.setSize(100, 50);
		StartNewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				removeAutoSaveFile();
				autoSaveFrame.dispose();

			}
		});
		jPanel.add(LoadAutoBtn);
		jPanel.add(StartNewBtn);
		autoSaveFrame.setResizable(false);
		autoSaveFrame.setAlwaysOnTop(true);
		autoSaveFrame.setVisible(true);
	}

	/**
	 * Remove autosave file
	 */
	private void removeAutoSaveFile()
	{
		File directory = new File(autoSaveDirectory);
		File[] files = directory.listFiles();
		for (File file : files) {

			file.delete();
		}
	}

	/**
	 * Action Performed
	 *  @param e event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for (IWorkspace workspace : mainFrame.getWorkspaceList()) {
			IGraphFile graphFile = workspace.getGraphFile();
			if (graphFile.isSaveRequired()) {
				graphFile.autoSave();
			}
		}
	}



}