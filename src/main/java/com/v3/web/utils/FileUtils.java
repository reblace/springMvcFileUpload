package com.v3.web.utils;

public class FileUtils {
	public static String getFileExtension(String filename) {
		String fileExtension = null;

		int index = filename.lastIndexOf(".");
		if(index > 0) {
			fileExtension = filename.substring(index + 1).toLowerCase();
		}

		return fileExtension;
	}
	
	public static String getFilenameBase(String filename){
		String filenameBase = filename;

		int index = filename.lastIndexOf(".");
		if(index > 0) {
			filenameBase = filename.substring(0, index).toLowerCase();
		}

		return filenameBase;
	}
}
