package com.IconPippi.simtriggers.gui.modules;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.IconPippi.simtriggers.gui.SimTriggersGUI;

public class ModuleUI implements MouseListener {
	
	//TODO: Finish
	
	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		SimTriggersGUI.getConsoleComponent().hide();
		SimTriggersGUI.getMainFrame().remove(SimTriggersGUI.getConsoleComponent());
		SimTriggersGUI.getMainFrame().repaint();
		SimTriggersGUI.getMainFrame().revalidate();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
