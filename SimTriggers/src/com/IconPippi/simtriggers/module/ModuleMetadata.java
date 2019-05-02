package com.IconPippi.simtriggers.module;

import java.io.File;

public class ModuleMetadata extends File {
	
	private static final long serialVersionUID = 1L; //Idk tbh
	
	private String name;
	private String version;
	private String description;
	private String[] authors;
	private boolean enabled = true;
	private int id;
	
	public ModuleMetadata(File parent, String child) {
		super(parent, child);
	}
	
	public String getName() {
		return name;
	}
	
	public String getVersion() {
		return version;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String[] getAuthors() {
		return authors;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public int getID() {
		return id;
	}
	
	public String toString() {
		return "{name="+getName()+
				", version="+getVersion()+
				", description="+getDescription()+
				", authors"+getAuthors()+
				", isEnabled="+enabled+
				", id="+getID()+"}";
	}
}
