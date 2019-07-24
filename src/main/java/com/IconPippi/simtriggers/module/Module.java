package com.IconPippi.simtriggers.module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.IconPippi.simtriggers.util.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * This class represent a module
 * @author IconPippi
 *
 */
public class Module {
	
	/*
	 * Module
	 */
	private File dir;
	private ModuleMetadata meta;
	
	/**
	 * Register a new module specifying its directory and metadata file
	 * @param directory module's folder location
	 * @param meta metadata .json file
	 */
	public Module(File directory, ModuleMetadata meta) {
		this.dir = directory;
		this.meta = meta;
	}
	
	/**
	 * Gets the module's folder
	 * @return module's folder
	 */
	public File getDir() {
		return dir;
	}
	
	/**
	 * Gets the metadata information for the module
	 * @return metadata 
	 */
	public ModuleMetadata getMeta() {
		try {
			meta = new Gson().fromJson(new FileReader(meta), ModuleMetadata.class);
		} catch (NullPointerException | JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			if (!e.toString().equals("java.lang.NullPointerException")) {
				Logger.log(e.toString());
			}
		}
		
		return meta;
	}
}
