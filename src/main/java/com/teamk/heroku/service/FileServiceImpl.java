package com.teamk.heroku.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String store(MultipartHttpServletRequest req, Path storePath, String name) throws IOException {
		MultipartFile imageFile = req.getFile(name);
		Files.copy(imageFile.getInputStream(), storePath.resolve(imageFile.getOriginalFilename()));
		
		return imageFile.getOriginalFilename();
	}

}
