package com.v3.web.model.file;

public class UploadedImageFileMetadata extends ImageFileMetadata {
	String url;
	String thumbnailUrl;
	String deleteUrl;
	String deleteType;

	public UploadedImageFileMetadata(ImageFileMetadata fmd){
		this.contentType = fmd.getContentType();
		this.extension = fmd.getExtension();
		this.id = fmd.getId();
		this.name = fmd.getName();
		this.owner = fmd.getOwner();
		this.size = fmd.getSize();
		this.time = fmd.getTime();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}

}
