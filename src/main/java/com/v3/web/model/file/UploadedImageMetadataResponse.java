package com.v3.web.model.file;

import java.util.ArrayList;
import java.util.List;

public class UploadedImageMetadataResponse {
	List<UploadedImageFileMetadata> files = new ArrayList<UploadedImageFileMetadata>();
	
	public List<UploadedImageFileMetadata> getFiles(){
		return files;
	}
}
