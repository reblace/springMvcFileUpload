package com.v3.web.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.v3.web.exception.FileNotFoundException;
import com.v3.web.exception.NotAuthorizedException;
import com.v3.web.exception.UnsupportedFormatException;
import com.v3.web.model.file.File;
import com.v3.web.model.file.ImageFileMetadata;
import com.v3.web.model.file.UploadedImageFileMetadata;
import com.v3.web.model.file.UploadedImageMetadataResponse;
import com.v3.web.service.file.ImageUploadService;

@Controller
@RequestMapping("/images")
public class ImageUploadController {
	ImageUploadService imageUploadService;

	@RequestMapping(value = { "/", "" }, method = { RequestMethod.HEAD })
	public @ResponseBody ResponseEntity<String> status() {
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = { "/{id}/{filename:.+}" }, method = { RequestMethod.GET, RequestMethod.HEAD })
	public void getFile(Principal principal, @PathVariable("id") String id, @PathVariable("filename") String filename, HttpServletResponse response) throws IOException{
		if(null == filename || "".equals(filename.trim())){
			response.sendError(HttpStatus.BAD_REQUEST.value());
			return;
		}

		File file;
		try {
			file = imageUploadService.getImage(principal, id, filename);
		} catch (FileNotFoundException e) {
			response.sendError(HttpStatus.NOT_FOUND.value());
			return;
		} catch (NotAuthorizedException e) {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return;
		}

		response.setContentType(file.getImageFileMetadata().getContentType());
		IOUtils.copy(file.getFileContent(), response.getOutputStream());
		response.setStatus(HttpStatus.OK.value());
	}

	@RequestMapping(value= {"/", "" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UploadedImageMetadataResponse> listFiles(Principal principal) {
		Collection<ImageFileMetadata> files = imageUploadService.getImages(principal);

		UploadedImageMetadataResponse response = new UploadedImageMetadataResponse();
		for(ImageFileMetadata fmd : files) {
			UploadedImageFileMetadata ufmd = new UploadedImageFileMetadata(fmd);
			ufmd.setDeleteType("DELETE");
			ufmd.setDeleteUrl("/images/delete/" + fmd.getId() + "/" + fmd.getName());

			ufmd.setThumbnailUrl("/images/" + fmd.getId() + "/" + fmd.getName());
			ufmd.setUrl("/images/" + fmd.getThumbnailId() + "/" + fmd.getName());

			response.getFiles().add(ufmd);
		}

		return new ResponseEntity<UploadedImageMetadataResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = { "/", "" }, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<UploadedImageMetadataResponse> upload(Principal principal, @RequestParam("file") MultipartFile file) throws IOException {
		ImageFileMetadata fmd;
		try {
			fmd = imageUploadService.uploadImage(principal, file);
		} catch (UnsupportedFormatException e) {
			return new ResponseEntity<UploadedImageMetadataResponse>(HttpStatus.BAD_REQUEST);
		}

		UploadedImageFileMetadata ufmd = new UploadedImageFileMetadata(fmd);
		ufmd.setDeleteType("DELETE");
		ufmd.setDeleteUrl("/images/" + fmd.getId() + "/" + fmd.getName());
		ufmd.setThumbnailUrl("/images/" + fmd.getThumbnailId() + "/" + fmd.getName());
		ufmd.setUrl("/images/" + fmd.getId() + "/" + fmd.getName());

		UploadedImageMetadataResponse response = new UploadedImageMetadataResponse();
		response.getFiles().add(ufmd);
		return new ResponseEntity<UploadedImageMetadataResponse>(response, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/{id}/{filename:.+}" }, method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(Principal principal, @PathVariable("id") String id, @PathVariable("filename") String filename){
		try {
			imageUploadService.removeImage(principal, id, filename);
		} catch (FileNotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} catch (NotAuthorizedException e) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@Autowired
	public void setImageUploadProcessor(ImageUploadService imageUploadProcessor){
		this.imageUploadService = imageUploadProcessor;
	}

}
