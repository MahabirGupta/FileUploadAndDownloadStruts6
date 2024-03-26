package com.nabisoft.struts2.demo.action;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;

public class FileUploadAction extends ActionSupport {

    private File uploadedFile;
    private String uploadedFileContentType;
    private String uploadedFileFileName;

    public String execute() throws Exception {
        // Get the path where you want to save the uploaded file
        String uploadPath = ServletActionContext.getServletContext().getRealPath("/uploads");

        // Create a File object representing the directory
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create the directory if it doesn't exist
        }

        // Create a File object representing the uploaded file in the destination directory
        File destFile = new File(uploadDir, uploadedFileFileName);

        // Copy the uploaded file to the destination directory
        FileUtils.copyFile(uploadedFile, destFile);

        return SUCCESS;
    }

 // Getter and setter methods for uploadedFile, uploadedFileContentType, and uploadedFileFileName
	public File getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getUploadedFileContentType() {
		return uploadedFileContentType;
	}

	public void setUploadedFileContentType(String uploadedFileContentType) {
		this.uploadedFileContentType = uploadedFileContentType;
	}

	public String getUploadedFileFileName() {
		return uploadedFileFileName;
	}

	public void setUploadedFileFileName(String uploadedFileFileName) {
		this.uploadedFileFileName = uploadedFileFileName;
	}

    
    
}

