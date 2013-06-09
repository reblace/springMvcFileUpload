package com.v3.web.dao;

import java.io.IOException;
import java.io.InputStream;

import com.v3.web.exception.FileNotFoundException;

/**
 * Interface for storing files to abstract storage layer. Supports storing 
 * and retrieving files from storage layer by identifier.
 * 
 * @author reblace
 *
 */
public interface FileStorageDao {

	boolean exists(String id);
	InputStream getFile(String id) throws FileNotFoundException;
	String put(InputStream fileContents) throws IOException;
	void remove(String id) throws FileNotFoundException;
}
