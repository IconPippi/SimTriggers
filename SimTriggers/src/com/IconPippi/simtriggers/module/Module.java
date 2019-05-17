package com.IconPippi.simtriggers.module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.IconPippi.simtriggers.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Module {
	
	private File dir;
	private ModuleMetadata meta;
	
	public Module(File directory, ModuleMetadata meta) {
		this.dir = directory;
		this.meta = meta;
	}
	
	public File getDir() {
		return dir;
	}

	public ModuleMetadata getMeta() {
		final Logger logger = new Logger();
		
		try {
			meta = new Gson().fromJson(new FileReader(meta), ModuleMetadata.class);
		} catch (NullPointerException | JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			if (!e.toString().equals("java.lang.NullPointerException")) {
				logger.log(e.toString());
			}
		}
		
		return meta;
	}
}
