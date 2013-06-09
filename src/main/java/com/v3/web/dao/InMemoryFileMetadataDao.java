package com.v3.web.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.v3.web.exception.FileNotFoundException;
import com.v3.web.model.file.ImageFileMetadata;

public class InMemoryFileMetadataDao implements FileMetadataDao {

	Map<String, ImageFileMetadata> fmdMap = new HashMap<String, ImageFileMetadata>();
	Multimap<String, ImageFileMetadata> fmdOwnerMap = LinkedListMultimap.create();

	@Override
	public void put(ImageFileMetadata metadata) {
		synchronized (fmdMap) {
			fmdMap.put(metadata.getId(), metadata);
			fmdOwnerMap.put(metadata.getOwner(), metadata);
		}
	}

	@Override
	public ImageFileMetadata get(String id) throws FileNotFoundException {
		synchronized (fmdMap) {
			if (fmdMap.containsKey(id)) {
				return fmdMap.get(id);
			} else {
				throw new FileNotFoundException("Cannot find id: " + id);
			}
		}
	}

	@Override
	public void remove(String id) throws FileNotFoundException {
		synchronized(fmdMap){
			if (fmdMap.containsKey(id)) {
				fmdMap.remove(id);
			} else {
				throw new FileNotFoundException("Cannot find id: " + id);
			}
		}
	}

	@Override
	public Collection<ImageFileMetadata> getByOwner(String owner) {
		synchronized(fmdMap){
			return fmdOwnerMap.get(owner);
		}
	}

}
