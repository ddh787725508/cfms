/**
 * 
 */
package com.acat.spring.boot.fileserver.server.fileserver.service;

import com.acat.spring.boot.fileserver.server.fileserver.domain.File;

import java.util.List;



public interface FileService {

	File saveFile(File file);
	
	/**
	 * 删除文件
	 * @param
	 * @return
	 */
	void removeFile(String id);
	
	/**
	 * 根据id获取文件
	 * @param
	 * @return
	 */
	File getFileById(String id);
	
	/**
	 * 获取文件列表
	 * @param
	 * @return
	 */
	List<File> listFiles();
}
