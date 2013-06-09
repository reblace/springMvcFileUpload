package com.v3.web.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

public class InMemoryFileStorageDao implements FileStorageDao {
	Map<String, File> fileMap = new HashMap<String, File>();

	@Override
	public boolean exists(String id) {
		synchronized (fileMap) {
			return fileMap.containsKey(id);
		}
	}

	@Override
	public InputStream getFile(String id) {
		synchronized (fileMap) {
			if (!exists(id)) {
				return null;
			}
			return new ByteArrayInputStream(fileMap.get(id).fileContents);
		}
	}

	@Override
	public String put(InputStream fileContents) throws IOException {
		String id = UUID.randomUUID().toString();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		IOUtils.copy(fileContents, os);

		File file = new File();
		file.fileContents = os.toByteArray();
		file.id = id;

		synchronized (fileMap) {
			fileMap.put(id, file);
		}
		return id;
	}

	@Override
	public void remove(String id) {
		synchronized (fileMap) {
			if(exists(id)){
				fileMap.remove(id);
			}
		}

	}

	class File {
		byte[] fileContents;
		String id;
	}

}
