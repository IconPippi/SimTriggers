package com.IconPippi.simtriggers.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.IconPippi.simtriggers.ConnectionOpen;
import com.IconPippi.simtriggers.module.Module;
import com.IconPippi.simtriggers.module.ModuleManager;
import com.IconPippi.simtriggers.utils.GraphicsUtils;

public class SimTriggersGUI {

	private final JFrame mainFrame;
	private final JPanel mainPanel;
	private final JLabel topLabel;
	private final JList<String> modulesList;

	public SimTriggersGUI(ConnectionOpen open) {
		
		//Create main window
		mainFrame = new JFrame("SimTriggers v0.0.1");
		mainFrame.setSize(500, 600);
		mainFrame.setLocationRelativeTo(null);
		
		//Create main panel
		mainPanel = new JPanel(new BorderLayout());
		mainFrame.setContentPane(mainPanel);
		
		//Set top label name and location
		topLabel = new JLabel("Modules");
		topLabel.setBorder(BorderFactory.createEmptyBorder(5, 2, 5, 2));
		mainPanel.add(topLabel, BorderLayout.NORTH);
		
		//Add modules list
		final ModuleManager manager = new ModuleManager();
		final List<String> modules = new ArrayList<>(); //Create a list to store all the modules
		for (Module m : manager.getModules()) {
			modules.add(m.getMeta().getName()); //Add the module to the list
		}
		modulesList = new JList<String>(modules.toArray(new String[0])); //Translate the List into a GUI component (JList) and change the List object to an array
		final JScrollPane moduleComponent = new JScrollPane(modulesList); //Put the JList into a JScrollPane component to support scrolling
		mainPanel.add(moduleComponent, BorderLayout.CENTER); //Add the component to the main panel

		//Modules list click handler
		modulesList.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("rawtypes")
			public void mouseClicked(java.awt.event.MouseEvent evt) {
		        final JList list = (JList)evt.getSource(); //Get the modules JList object
		        if (evt.getClickCount() == 2) { //If the users clicks to times
		        	mainPanel.removeAll(); //Clear the GUI
		        	
		        	final ModuleManager moduleManager = new ModuleManager();
    		      	final Module module = moduleManager.getModuleByName((String) list.getSelectedValue()); //Get the module object by its name
		        	
		        	mainFrame.add((Component) new JLabel() { //Create a new JLabel for the module info page

						private static final long serialVersionUID = 1L; //Idk tbh

						@Override
		        		public void paintComponent(Graphics g) { //Paint all the GUI components
							if(g instanceof Graphics2D) {
		        		      		
		        		      	Graphics2D g2 = (Graphics2D) g; //Initialize Graphics2D obj
		        		      	g2.setRenderingHint( //Set rendering hints (idk what it does)
		        		      			RenderingHints.KEY_ANTIALIASING,
		        		      			RenderingHints.VALUE_ANTIALIAS_ON
		        		      	);
		        		      	g2.setFont(new Font("Palatino", 0, 20)); //Set the font and size
		        		      	
		        		      	//Use a utils method to center the title string (module name)
		        		      	new GraphicsUtils().centerString(g2, new Rectangle(0, 0, 460, 30), module.getMeta().getName(), new Font("Palatino", 0, 30)); //Module Name
		        		      	g2.drawString("Version: "+module.getMeta().getVersion(), 5, 90); //Version
		        		      	g2.drawString("ID: "+module.getMeta().getID(), 5, 120); //ID
		        		      	g2.drawString("Authors: "+getAuthorListString(module.getMeta().getAuthors()), 5, 150);
		        		      	
		        		      	g2.setFont(new Font("Palatino", 0, 19)); //Set a different font size for the description
		        		      	printDescription(module.getMeta().getDescription(), g2); //Render the description
		        		      	
		        		      	JButton statusButton = new JButton(module.getMeta().isEnabled() ? "Disable" : "Enable"); //Enable / Disable button
		    		        	//statusButton.setBorder(BorderFactory.createEmptyBorder(5, 2, 5, 2)); //TODO: idk it doesnt not show
		    		        	//statusButton.setLocation(0, 0);
		    		        	this.add(statusButton);
							}
		        		}
		        	});
		        	
		        	mainPanel.repaint(); //Repaint GUI
		        	mainPanel.revalidate(); //Refresh GUI
		        }
		    }
		});

	}

	
	/**
	 * Shows the modules GUI
	 */
	public void show() {
		mainFrame.setVisible(true); //Show GUI
	}
	
	
	/*
	 * *****************************
	 * 
	 * Helpers methods
	 * 
	 * *****************************
	 */
	
	/**
	 * Get a module's author list as a string
	 * @param Author list's array
	 * @return Authors list
	 */
	private String getAuthorListString(String[] authors) {
		StringBuilder sb = new StringBuilder();
		
		for (String author : authors) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			
			sb.append(author);
		}
		
		return sb.toString();
	}
	
	/**
	 * Print the module's description splitting it in lines
	 * @param Module's description
	 * @param Graphics2D
	 */
	private void printDescription(String description, Graphics2D g2) {
		List<String> lines = new ArrayList<>();
		int oldi = 0;
		String str = new String();
		
		/*
		 * Process the string
		 */
		for (int i = 0; i < description.length(); i++) {
			
			/*
			 * Split the description in separated lines (50 characters each)
			 */
			if (i % 50 == 0) {
				lines.add(description.substring(oldi, i));
				str = description.substring(oldi, i);
				oldi = i;
			}
		}
		
		lines.add(description.split(str)[1]); //Add the last line which isn't 50 characters
		
		/*
		 * Render each line
		 */
		int i = 200;
		for (String s : lines) { 
			g2.drawString(s, 5, i);
			System.out.println(i);
			i = i + 25;
		}
	}
}
