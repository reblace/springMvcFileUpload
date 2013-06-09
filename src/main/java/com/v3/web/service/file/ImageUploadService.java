package com.v3.web.service.file;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Collection;
import java.util.Set;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.ImmutableSet;
import com.v3.web.dao.FileMetadataDao;
import com.v3.web.dao.FileStorageDao;
import com.v3.web.exception.FileNotFoundException;
import com.v3.web.exception.NotAuthorizedException;
import com.v3.web.exception.UnsupportedFormatException;
import com.v3.web.model.file.File;
import com.v3.web.model.file.ImageFileMetadata;
import com.v3.web.utils.FileUtils;

public class ImageUploadService {
	FileStorageDao fileStorageDao;
	FileMetadataDao fileMetadataDao;

	Set<String> validExtensions = ImmutableSet.of("jpg", "png");

	public ImageFileMetadata uploadImage(Principal principal, MultipartFile file)
			throws IOException, UnsupportedFormatException {
		// Check the extension
		String extension = FileUtils.getFileExtension(file.getOriginalFilename());
		if (!validExtensions.contains(extension)) {
			throw new UnsupportedFormatException("Unsupported file extension "
					+ extension);
		}

		byte[] image = readImage(file.getInputStream());
		byte[] thumbnail = this.createThumbnail(new ByteArrayInputStream(image), extension);

		String imageId = fileStorageDao.put(new ByteArrayInputStream(image));
		String thumbnailId = fileStorageDao.put(new ByteArrayInputStream(thumbnail));

		ImageFileMetadata fmd = new ImageFileMetadata();
		fmd.setId(imageId);
		fmd.setThumbnailId(thumbnailId);
		fmd.setName(file.getOriginalFilename());
		fmd.setExtension(extension);
		fmd.setOwner(principal.getName());
		fmd.setSize(file.getSize());
		fmd.setTime(DateTime.now());
		fmd.setContentType(file.getContentType());

		fileMetadataDao.put(fmd);

		ImageFileMetadata fmdThumbnail = new ImageFileMetadata();
		fmdThumbnail.setId(thumbnailId);
		fmdThumbnail.setThumbnailId(null);
		fmdThumbnail.setName(fmd.getName());
		fmdThumbnail.setExtension(fmd.getExtension());
		fmdThumbnail.setOwner(fmd.getOwner());
		fmdThumbnail.setSize(fmd.getSize());
		fmdThumbnail.setTime(fmd.getTime());
		fmdThumbnail.setContentType(fmd.getContentType());

		fileMetadataDao.put(fmdThumbnail);

		return fmd;
	}

	public File getImage(Principal principal, String id, String filename) throws FileNotFoundException, NotAuthorizedException{
		ImageFileMetadata fmd = fileMetadataDao.get(id);
		if(!fmd.getName().equals(filename)){
			throw new FileNotFoundException("Cannot find file: " + filename);
		}

		if (fmd.getOwner().equals(principal.getName())) {
			File file = new File();
			file.setFileContent(fileStorageDao.getFile(id));
			file.setImageFileMetadata(fmd);

			return file;
		} else {
			throw new NotAuthorizedException("User " + principal.getName() + " is not authorized to remove " + filename);
		}
	}

	public Collection<ImageFileMetadata> getImages(Principal principal) {
		return fileMetadataDao.getByOwner(principal.getName());
	}

	public void removeImage(Principal principal, String id, String filename) throws FileNotFoundException, NotAuthorizedException{
		ImageFileMetadata fmd = fileMetadataDao.get(id);

		if(!fmd.getName().equals(filename)){
			throw new FileNotFoundException("Cannot find file: " + filename);
		}

		if (fmd.getOwner().equals(principal.getName())) {
			fileStorageDao.remove(id);
			fileMetadataDao.remove(id);
		} else {
			throw new NotAuthorizedException("User " + principal.getName() + " is not authorized to remove " + filename);
		}
	}

	@Autowired
	public void setFileStorageDao(FileStorageDao fileStorageDao) {
		this.fileStorageDao = fileStorageDao;
	}

	@Autowired
	public void setFileMetadataDao(FileMetadataDao fileMetadataDao) {
		this.fileMetadataDao = fileMetadataDao;
	}
	
	private byte[] readImage(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		byte[] bytes = new byte[512];
		int length = -1;
		while(-1  != (length = inputStream.read(bytes))){
			outputStream.write(bytes, 0, length);
		}

		return outputStream.toByteArray();
	}

	private byte[] createThumbnail(InputStream source, String sourceExtension) throws IOException {
		BufferedImage sourceImage = ImageIO.read(source);
		BufferedImage thumbnail = Scalr.resize(sourceImage, 150);
		
		ByteArrayOutputStream dest = new ByteArrayOutputStream();
		ImageIO.write(thumbnail, sourceExtension, dest);
		
		return dest.toByteArray();
	}
}
