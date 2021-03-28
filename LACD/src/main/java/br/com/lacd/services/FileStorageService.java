package br.com.lacd.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lacd.config.FileStorageConfig;
import br.com.lacd.exception.FileStorageException;
import br.com.lacd.exception.MyFileNotFoundException;
import br.com.lacd.model.File;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be storage", e);
		}
	}

	public String storageFile(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (filename.contains("..")) {
				throw new FileStorageException("Srry! Filename contains invalid path sequence " + filename);
			}
			
			Path targetLocation = this.fileStorageLocation.resolve(filename);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return filename;
		} catch (Exception e) {
			throw new FileStorageException("Could not store file " + filename + ". Please, try again", e);
		}
	}
	
	public File registryFile(MultipartFile file) {
		String filename = storageFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/v1/file/downloadFile/")
				.path(filename)
				.toUriString();

		return new File(filename, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	public Resource loadFileAsResource(String filename) {
		try {
			Path filePath = this.fileStorageLocation.resolve(filename).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + filename);
			}
		} catch (Exception e) {
			throw new MyFileNotFoundException("File not found " + filename, e);
		}
	}
	
	public String getFilePath(String filename) {
		try {
			Path filePath = this.fileStorageLocation.resolve(filename).normalize();
			return filePath.toString();
		} catch (Exception e) {
			throw new MyFileNotFoundException("File not found " + filename, e);
		}
	}
	
}
