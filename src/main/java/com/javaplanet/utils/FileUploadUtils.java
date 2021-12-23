package com.javaplanet.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.javaplanet.config.FileStorageProperties;

@Component
public class FileUploadUtils {
	
	private final Path filePath;
	
	public FileUploadUtils(FileStorageProperties fileStorageProperties) {
	 System.out.println("FileUploadUtils :: Constructor");
	 filePath=Paths.get(fileStorageProperties.getUploadDir());
	 try {
		Files.createDirectories(filePath);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

}
