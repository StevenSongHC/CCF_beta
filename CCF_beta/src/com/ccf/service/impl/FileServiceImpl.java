package com.ccf.service.impl;

import com.ccf.dao.FileDAO;
import com.ccf.service.FileService;

public class FileServiceImpl implements FileService {
	private FileDAO fileDao;
	public FileDAO getFileDao() {
		return fileDao;
	}
	public void setFileDao(FileDAO fileDao) {
		this.fileDao = fileDao;
	}

}
