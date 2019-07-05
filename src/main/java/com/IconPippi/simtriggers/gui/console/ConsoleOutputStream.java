package com.IconPippi.simtriggers.gui.console;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

/**
 * Console Stream
 * @author ChatTriggers [FalseHonesty & kerbybit] ({@link}https://github.com/ChatTriggers/ct.js/blob/master/src/main/kotlin/com/chattriggers/ctjs/utils/console/TextAreaOutputStream.kt)
 *
 */
class ConsoleOutputStream extends OutputStream {
	
	private final StringBuilder buffer = new StringBuilder(128);
	
	private final JTextArea console;
	
	public ConsoleOutputStream(JTextArea console) {
		this.console = console;
	}
	
	private final PrintStream printStream = new PrintStream(this);
	
	@Override
	public void write(int b) throws IOException {
		char letter = (char) b;
		buffer.append(letter);
		
		if (letter == '\n') {
			String line = buffer.toString();
			
			console.append(line);
		
			buffer.delete(0, buffer.length());
		}
	}
	
	public void println(String line) {
		printStream.println(line);
	}
	
	public void clear() {
		console.setText("");

	}
}
