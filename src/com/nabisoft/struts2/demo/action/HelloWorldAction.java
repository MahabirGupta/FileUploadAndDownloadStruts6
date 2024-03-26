package com.nabisoft.struts2.demo.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;


import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class HelloWorldAction extends ActionSupport {

    private String message;

    private Date nowDate;
    
    protected HttpServletRequest request;
    
 // Add a new property to hold the uploaded file
    private File uploadedFile;

    @Override
    public void validate(){
        if (message==null || message.length()==0)
            addActionError(getText("error.enter.message"));
    }

    @Override
    public String execute() throws Exception {
        nowDate = new Date();
        
        return SUCCESS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getNowDate() {
        return nowDate;
    }
    

    // Add getter and setter for uploadedFile
    public File getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

    // Add a new method to handle file upload
    public String uploadFile() {
        // Logic to handle file upload and save the file to the server
    	// Add a method to handle file upload
    	// Check if the request contains multipart content (i.e., file upload)
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            // Handle error: Request does not contain multipart content
            return ERROR;
        }
        
     // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set the repository location where temporary files will be stored
        File repository = new File(System.getProperty("java.io.tmpdir"));
        factory.setRepository(repository);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // Parse the request and get all file items
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                // Process only file items (ignore form fields)
                if (!item.isFormField()) {
                    // Get the uploaded file name
                    String fileName = item.getName();

                    // Specify the directory where you want to save the uploaded file
                    String uploadDirectory = "C:\\Users\\P0107869\\Documents\\Upload-file";

                    // Create the directory if it doesn't exist
                    File uploadDir = new File(uploadDirectory);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    // Create a new file in the upload directory
                    File uploadedFile = new File(uploadDirectory, fileName);

                    // Save the uploaded file to the server
                    item.write(uploadedFile);

                    // Optionally, you can store the file path or perform additional processing
                }
            }

            return SUCCESS; // Or any other result code

        } catch (FileUploadException e) {
            e.printStackTrace();
            return ERROR; // Or handle the error appropriately
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR; // Or handle the error appropriately
        }
    }


	


}