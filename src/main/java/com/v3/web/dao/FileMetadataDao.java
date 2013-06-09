package com.v3.web.dao;

import java.util.Collection;

import com.v3.web.exception.FileNotFoundException;
import com.v3.web.model.file.ImageFileMetadata;


public interface FileMetadataDao {
	void put(ImageFileMetadata metadata);
	ImageFileMetadata get(String id) throws FileNotFoundException;
	Collection<ImageFileMetadata> getByOwner(String owner);
	void remove(String id) throws FileNotFoundException;
}
