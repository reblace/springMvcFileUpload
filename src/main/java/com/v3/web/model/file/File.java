package com.v3.web.model.file;

import java.io.InputStream;

public class File {
	InputStream fileContent;
	ImageFileMetadata imageFileMetadata;

	public InputStream getFileContent() {
		return fileContent;
	}

	public void setFileContent(InputStream fileContent) {
		this.fileContent = fileContent;
	}

	public ImageFileMetadata getImageFileMetadata() {
		return imageFileMetadata;
	}

	public void setImageFileMetadata(ImageFileMetadata imageFileMetadata) {
		this.imageFileMetadata = imageFileMetadata;
	}

}
