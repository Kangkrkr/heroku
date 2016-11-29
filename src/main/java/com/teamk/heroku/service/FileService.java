package com.teamk.heroku.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService {
	public String store(MultipartHttpServletRequest req, Path storePath, String name) throws IOException;
}
