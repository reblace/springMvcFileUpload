package com.v3.web.model.file;

import org.joda.time.DateTime;

public class ImageFileMetadata {
	String id;
	String thumbnailId;
	String name;
	String extension;
	String contentType;
	String owner;
	DateTime time;
	long size;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThumbnailId(){
		return this.thumbnailId;
	}

	public void setThumbnailId(String thumbnailId){
		this.thumbnailId = thumbnailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
