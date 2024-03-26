package com.nabisoft.struts2.demo.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileDownloadAction extends ActionSupport {

    private String fileName;

    // Getter and setter for fileName
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    public String execute() throws Exception {
        // Get the path to the file on the server
        String filePath = "C:\\Users\\P0107869\\Documents\\download-file" + fileName;

        // Set the content type and headers for the response
        ServletActionContext.getResponse().setContentType("application/octet-stream");
        ServletActionContext.getResponse().setContentLength((int) new File(filePath).length());
        ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        // Get the InputStream for the file
        InputStream inputStream = new FileInputStream(filePath);

        // Get the OutputStream for the response
        ServletActionContext.getResponse().getOutputStream();

        // Write the file data to the response OutputStream
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            ServletActionContext.getResponse().getOutputStream().write(buffer, 0, bytesRead);
        }

        // Close the streams
        inputStream.close();
        ServletActionContext.getResponse().getOutputStream().flush();

        return null; // No result mapping since the response is handled directly
    }

	
}

