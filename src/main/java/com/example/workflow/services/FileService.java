package com.example.workflow.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class FileService {
	static String basicSourcePath = "..\\send-to-topic\\src\\main\\resources\\";
	static String basicLocalPath = System.getProperty("user.dir") + "\\src\\main\\resources\\";

	public void creatDirectory(String directoryName) {
		log.debug(">>>>>>>>>>> FileService->creatDirectory("+ directoryName+") : creation xml file ...");
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdirs();
			// If you require it to make the entire directory path including parents,
			// use directory.mkdirs(); here instead.
			log.info(">>>>>>>>>>> FileService->creatDirectory("+directory + ") directory created!");
		}

	}

	public void copyFile(String sourcePath, String dirPath, String destPath) throws IOException{ 
		File source = new File(sourcePath);
		creatDirectory(dirPath);
		File destination = new File(destPath);
		FileUtils.copyFile(source, destination);
		log.debug(">>>>>>>>>>> FileService->copyFile: File copied to: "+destPath);
	}
	public String copyXmlFile(String filePath, String dirPath) {
		try {
			String localFilePath=basicLocalPath+filePath;
			this.copyFile(basicSourcePath+filePath, basicLocalPath+dirPath,localFilePath);
			log.debug(">>>>>>>>>>> FileService->copyXmlFile: File copied to "+localFilePath);
			return localFilePath;
		} catch (IOException e) {
			log.debug(">>>>>>>>>>> FileService->copyXmlFile: Error occured while copying the XML file!");
			e.printStackTrace();
			return null;
		}
	}
}