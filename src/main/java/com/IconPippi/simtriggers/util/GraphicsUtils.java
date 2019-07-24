package com.IconPippi.simtriggers.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains some graphic utilities
 * @author IconPippi
 *
 */
public class GraphicsUtils {
	
	/**
	 * Centers the given string in the selected area (reactangle)
	 * @param g Graphics Object
	 * @param r Area
	 * @param s String to center
	 * @param font String's font
	 */
	public void centerString(Graphics g, Rectangle r, String s, Font font) {
	    FontRenderContext frc = 
	            new FontRenderContext(null, true, true);

	    Rectangle2D r2D = font.getStringBounds(s, frc);
	    int rWidth = (int) Math.round(r2D.getWidth());
	    int rHeight = (int) Math.round(r2D.getHeight());
	    int rX = (int) Math.round(r2D.getX());
	    int rY = (int) Math.round(r2D.getY());

	    int a = (r.width / 2) - (rWidth / 2) - rX;
	    int b = (r.height / 2) - (rHeight / 2) - rY;

	    g.setFont(font);
	    g.drawString(s, r.x + a, r.y + b);
	}
	
	/**
	 * Print the module's description splitting it in lines
	 * @param description Module's description
	 * @param g2 Graphics2D
	 */
	public void printDescription(String description, Graphics2D g2) {
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
			i = i + 25;
		}
	}
	
}
