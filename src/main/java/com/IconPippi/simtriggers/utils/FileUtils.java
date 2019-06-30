package com.IconPippi.simtriggers.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
			} else if (f.getName().equalsIgnoreCase("simtriggersDevKit.js")) {
				//
			} else {
				files.add(f);
			}
		}
		
		return files;
	}
	
	/**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName ie. simtriggersDevKit.js
     * @return The path to the exported resource
     * @throws Exception
     */
    public String exportResourceToModulesFolder(String resourceName) throws Exception {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder;
        try {
            stream = FileUtils.class.getResourceAsStream(resourceName);
            if(stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = new File(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
            resStreamOut = new FileOutputStream(jarFolder + "/modules/" +resourceName);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            stream.close();
            resStreamOut.close();
        }

        return jarFolder + resourceName;
    }
}
