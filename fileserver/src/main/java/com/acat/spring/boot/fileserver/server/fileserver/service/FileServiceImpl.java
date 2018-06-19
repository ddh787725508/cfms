package com.acat.spring.boot.fileserver.server.fileserver.service;

import java.util.List;

import com.acat.spring.boot.fileserver.server.fileserver.domain.File;
import com.acat.spring.boot.fileserver.server.fileserver.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	public FileRepository fileRepository;


	@Override
	public File saveFile(File file) {
		return fileRepository.save(file);
	}

	@Override
	public void removeFile(String id) {
		fileRepository.deleteById(id);
	}


	@Override
	public File getFileById(String id) {
		return fileRepository.findById(id).get();
	}


	@Override
	public List<File> listFiles() {
		return fileRepository.findAll();
	}

}
