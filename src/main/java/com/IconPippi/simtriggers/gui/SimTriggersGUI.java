package com.IconPippi.simtriggers.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.IconPippi.simtriggers.gui.console.Console;
import com.IconPippi.simtriggers.module.Module;
import com.IconPippi.simtriggers.module.ModuleManager;

/**
 * SimTriggers GUI
 * @author IconPippi
 *
 */
public class SimTriggersGUI {
	
	/* Main frame */
	private JFrame frame;
	
	/*
	 * Console
	 */
	private JTextArea console;
	private Console consoleOut;
	@SuppressWarnings("unused")
	private JTextField consoleIn;
	
	/* Modules */
	private JList<String> modulesList;
	
	/**
	 * Show the GUI
	 */
	public void show() {
		frame.setVisible(true);
	}
	
	/**
	 * Hide the GUI
	 */
	public void hide() {
		frame.setVisible(false);
	}
	
	/**
	 * Shutdown the GUI functions
	 */
	public void shut() {
		Console.close();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setSize(600, 500);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		console = new JTextArea();
		console.setEditable(false);
		console.setAutoscrolls(true);
		
		/* Console */
		consoleOut = new Console(console);
		consoleIn = new JTextField();
		
		/* Modules */
		modulesList = new JList<String>(getModulesNames().toArray(new String[0]));
		
		Console.println("                                                                       "); //Make console panel wider than the modules panel
		
		frame.getContentPane().add(new JScrollPane(console), BorderLayout.WEST);
		frame.getContentPane().add(new JScrollPane(modulesList), BorderLayout.EAST);
	}
	
	private List<String> getModulesNames() {
		final ModuleManager manager = new ModuleManager();
		final List<String> modules = new ArrayList<>(); //Create a list to store all the modules
		for (Module m : manager.getModules()) {
			modules.add(m.getMeta().getName()); //Add the module to the list
		}
		
		modules.add("                                                                               "); //Add a long string for spacing
		
		return modules;
	}
	
	public Console getConsole() {
		return consoleOut;
	}
}
