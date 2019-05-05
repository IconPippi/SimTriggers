package com.IconPippi.simtriggers.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.IconPippi.simtriggers.ConnectionOpen;
import com.IconPippi.simtriggers.module.Module;
import com.IconPippi.simtriggers.module.ModuleManager;

public class SimTriggersGUI {

	private JFrame mainFrame;
	private JLabel topLabel;
	private JList<String> modulesList;
	
	@SuppressWarnings("unchecked")
	public SimTriggersGUI(ConnectionOpen open) {
		//Create main window
		mainFrame = new JFrame("SimTriggers v0.0.1");
		mainFrame.setSize(500, 600);
		mainFrame.setLocationRelativeTo(null);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainFrame.setContentPane(mainPanel);
		
		//Set top label name and location
		topLabel = new JLabel("Modules");
		topLabel.setBorder(BorderFactory.createEmptyBorder(5, 2, 5, 2));
		mainPanel.add(topLabel, BorderLayout.NORTH);
		
		//Add modules list
		final ModuleManager manager = new ModuleManager();
		final List<String> modules = new ArrayList<>();
		for (Module m : manager.getModules()) {
			modules.add(m.getMeta().getName());
		}
		modulesList = new JList<String>(modules.toArray(new String[0]));
		mainPanel.add(new JScrollPane(modulesList), BorderLayout.CENTER);
		//modulesList.action(Event., new Object{}); TODO: Handle mouse click
	}
	//Make GUI basing on this: https://imgur.com/a/gYwhXII
	
	public void show() {
		mainFrame.setVisible(true);
	}
}
