package com.ccf.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class FileUtil {
	
	private String realPath;
	
	/**
	 * @param realPath
	 * 
	 * initial realPath
	 */
	public FileUtil() {
		HttpServletRequest request = ServletActionContext.getRequest();
    	realPath = request.getSession().getServletContext().getRealPath("");
	}
	
	/**
	 * 
	 * use in collegeFolder creation and datePackFolder creation
	 * @param parent
	 * @param child
	 * @return
	 */
	public boolean createFolder(String parent, String child) {
		File folder = new File(realPath+"\\" + parent, child);
		// change to this: File folder = new File(realPath + "\\" + parent, child);
		if (!folder.exists()) {
			try {
				folder.mkdir();
			} catch (Exception e) {
				System.out.println("creating folder failed!");
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	public boolean deleteFolder(String parent, String child) {
		File folder = new File(realPath+"\\" + parent, child);
		if (folder.exists()) {
			try {
				folder.delete();
			} catch (Exception e) {
				System.out.println("deleting folder failed!");
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public boolean deleteFile(String filePath, String fileName) {
		File file = new File(realPath+"\\"+filePath, fileName);
		if (file.exists()) {
			try {
				file.delete();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("failed to delete file");
			}
		}
		return false;
	}
	
	/**
	 * 
	 * updating province name property will call this method
	 * 
	 * @param parent
	 * @param oldChildName
	 * @param newChildName
	 * @return
	 */
	public boolean renameFolder(String parent, String oldChildName, String newChildName) {
		if (!oldChildName.equals(newChildName)) {
			File oldFolder = new File(realPath+"\\"+parent, oldChildName);
			if (oldFolder.exists()) {
				try {
					File newFolder = new File(realPath+"\\"+parent, newChildName);
					if (!newFolder.exists()) {
						oldFolder.renameTo(newFolder);	// if everything is right
						return true;
					}
					System.out.println("already existed a folder after the newChildName");
				} catch (Exception e) {
					System.out.println("renam folder failed!");
					e.printStackTrace();
				}
			}
			else {	//cannot rename a not existed folder
				System.out.println("no such folder existed");
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param filePath
	 * @param oldFileName
	 * @param newFileName
	 * @return
	 */
	public boolean renameFile(String filePath, String oldFileName, String newFileName) {
		if (!oldFileName.equals(newFileName)) {
			File oldFile = new File(realPath+"\\"+filePath, oldFileName);
			if (oldFile.exists()) {
				try {
					File newFile = new File(realPath+"\\"+filePath, newFileName);
					if (!newFile.exists()) {
						oldFile.renameTo(newFile);	// works
						return true;
					}
					System.out.println("already existed a file named after the newChildName");
				} catch (Exception e) {
					System.out.println("renam file failed!");
					e.printStackTrace();
				}
			}
			else {
				System.out.println("no such file existed");
			}
		}
		return false;
	}
	
	/**
	 * 
	 * including file increasing and modify function
	 * 
	 * @param filePath
	 * @param fileName
	 * @param content
	 * @return
	 */
	public boolean editFileContent(String filePath, String fileName, String content) {
		filePath = realPath + "\\" + filePath;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		File file = new File(filePath, fileName);
		// if no exited, then create one
		if (!file.exists()) {
			try {
				// getParentFile() !important
				if(!file.getParentFile().mkdirs())
					System.out.println("creating file failed");
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		// continue editing
		try {
			reader = new BufferedReader(new StringReader(content));
			writer = new BufferedWriter(new FileWriter(file));
			char[] buffer = new char[1024];
			int len;
			while ((len = reader.read(buffer)) != -1)
				writer.write(buffer, 0, len);
			writer.flush();
			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param prName
	 * @param ctName
	 * @param coCode
	 * 
	 * path kinda like "\archive\GuangDong-GuangZhou-GDUT"
	 * 
	 * @return status
	 */
	public boolean createCollegeFolder(String provinceName, String cityName, String collegeName) {
		return createFolder("archive", provinceName+"-"+cityName+"-"+collegeName);
	}
	
	/**
	 * 
	 * @param oldProvinceName
	 * @param oldCityName
	 * @param oldCollegeName
	 * @param newProvinceName
	 * @param newCityName
	 * @param newCollegeName
	 * @return status
	 */
	public boolean renameCollegeFolder(String oldProvinceName, String oldCityName, String oldCollegeName, 
									   String newProvinceName, String newCityName, String newCollegeName) {
		return renameFolder("archive", oldProvinceName+"-"+oldCityName+"-"+oldCollegeName, 
									   newProvinceName+"-"+newCityName+"-"+newCollegeName); 
	}
	
	/**
	 * 
	 * @param oldProvinceName
	 * @param newProvinceName
	 * @return status
	 */
	public boolean renameProvincePart(String oldProvinceName, String newProvinceName) {
		boolean status = true;
		File f = new File(realPath+"\\"+"archive");
		File[] files = f.listFiles();
		for (File file : files) {
			String[] fileName = file.getName().split("-");
			if (fileName[0].equals(oldProvinceName))
				renameFolder("archive", fileName[0]+"-"+fileName[1]+"-"+fileName[2], newProvinceName+"-"+fileName[1]+"-"+fileName[2]);
			else
				status = false;
		}
		return status;
	}
	
	/**
	 * 
	 * @param oldCityName
	 * @param newCityName
	 * @return status
	 */
	public boolean renameCityPart(String oldCityName, String newCityName) {
		boolean status = true;
		File f = new File(realPath+"\\"+"archive");
		File[] files = f.listFiles();
		for (File file : files) {
			String[] fileName = file.getName().split("-");
			if (fileName[1].equals(oldCityName))
				renameFolder("archive", fileName[0]+"-"+fileName[1]+"-"+fileName[2], fileName[0]+"-"+newCityName+"-"+fileName[2]);
			else
				status = false;
		}
		return status;
	}
	
}
