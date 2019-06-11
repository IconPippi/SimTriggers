package com.IconPippi.simtriggers.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains some file utilities
 * @author IconPippi
 *
 */
public class FileUtils {
	
	/**
	 * Gets all the files inside a directory
	 * @param dir Target directory
	 * @param folders If true also lists all subfolders of the target directory
	 * @return All files in the target directory
	 */
	public List<File> getFilesInDir(File dir, boolean folders) {
		List<File> files = new ArrayList<>();
		
		for (File f : dir.listFiles()) {
			if (f.isDirectory() && !folders) {
				//do nothing
			} else {
				files.add(f);
			}
		}
		
		return files;
	}
	
}
