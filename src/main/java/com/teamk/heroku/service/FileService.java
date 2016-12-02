package com.teamk.heroku.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class FileService {
	public String store(MultipartHttpServletRequest req, Path storePath, String name) throws IOException {
		MultipartFile imageFile = req.getFile(name);
		String originalFilename = imageFile.getOriginalFilename();
		int lastIdx = originalFilename.lastIndexOf('.');
		
		// 파일명
		String filename = originalFilename.substring(0, lastIdx);
		// 확장자명
		String executerName = originalFilename.substring(lastIdx);
		
		filename += (('-' + (System.currentTimeMillis())) + executerName);
		
		Files.copy(imageFile.getInputStream(), storePath.resolve(filename));
		return filename;
	}
}
