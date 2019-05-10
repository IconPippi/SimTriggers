package com.IconPippi.simtriggers.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.IconPippi.simtriggers.ConnectionOpen;
import com.IconPippi.simtriggers.module.Module;
import com.IconPippi.simtriggers.module.ModuleManager;

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
		
		mainPanel = new JPanel(new BorderLayout());
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
		final JScrollPane moduleComponent = new JScrollPane(modulesList);
		mainPanel.add(moduleComponent, BorderLayout.CENTER);

		//Modules list click handler
		modulesList.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("rawtypes")
			public void mouseClicked(java.awt.event.MouseEvent evt) {
		        final JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	mainPanel.removeAll();
		        	
		        	mainFrame.add((Component) new JComponent() {

						private static final long serialVersionUID = 1L;

						@Override
		        		  public void paintComponent(Graphics g) {
		        		      	if(g instanceof Graphics2D) {
		        		      		final Module module = new Module(new ModuleManager().getModuleByName((String) list.getSelectedValue()).getDir(), 
		        		      				new ModuleManager().getModuleByName((String) list.getSelectedValue()).getMeta()); //TODO Throws nullpointer
		        		      		
		        		      		System.out.println(module.getMeta().getName());
		        		      		
		        		      		Graphics2D g2 = (Graphics2D)g;
		        		      		g2.setRenderingHint(
		        		      				RenderingHints.KEY_ANTIALIASING,
		        		      				RenderingHints.VALUE_ANTIALIAS_ON
		        		      		);
		        		      		
		        		      		g2.setFont(new Font("Palatino", 0, 30));
		        		      		g2.drawString((String) list.getSelectedValue(), 150, 30); 
		        		       }
		        		   }
		        	});
		        	mainPanel.repaint();
		        	mainPanel.revalidate(); //Refresh GUI
		        }
		    }
		});

	}
	
	public void show() {
		mainFrame.setVisible(true);
	}
}
