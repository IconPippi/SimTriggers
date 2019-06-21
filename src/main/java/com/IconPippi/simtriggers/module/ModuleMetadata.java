package com.IconPippi.simtriggers.module;

import java.io.File;

/**
 * This class represents the metadata.json file and its components
 * @author IconPippi
 *
 */
public class ModuleMetadata extends File {
	
	private static final long serialVersionUID = 1L; //Idk tbh
	
	/*
	 * Meta values
	 */
	private String name;
	private String version;
	private String description;
	private String[] authors;
	private boolean enabled = true;
	private int id;
	
	/**
	 * Register a new metadata file
	 * @param parent parent file
	 * @param child child file
	 */
	public ModuleMetadata(File parent, String child) {
		super(parent, child);
	}
	
	/**
	 * Gets the module's name
	 * @return module's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the module's version
	 * @return module's version
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Gets the module's description
	 * @return module's description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Gets the module's authors
	 * @return module's authors
	 */
	public String[] getAuthors() {
		return authors;
	}
	
	/**
	 * Gets the module's enabled state
	 * @return module's enabled state
	 */
	public boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * Gets the module's ID
	 * @return module's ID
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Sets the module's name
	 * @param name new module's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the module's version
	 * @param version new module's version
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * Sets the module's description
	 * @param description new module's description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Sets the module's authors
	 * @param authors new module's authors
	 */
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	
	/**
	 * Sets the module's enabled state
	 * @param enable new module's enabled state
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * @return module's metadata
	 */
	public String toString() {
		return "{name="+getName()+
				", version="+getVersion()+
				", description="+getDescription()+
				", authors"+getAuthors()+
				", isEnabled="+enabled+
				", id="+getID()+"}";
	}
}
