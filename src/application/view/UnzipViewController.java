package application.view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class UnzipViewController {
	
	 	@FXML
	    private Button chooseFileButton;
	    
	    @FXML
	    private Button chooseFolderButton;
	    
	    @FXML
	    private Button unzipButton;
	    
	    @FXML 
	    private TextField fileTextField;
	    
	    @FXML
	    private TextField folderTextField;
	    

	    // Reference to the main application.
	    private Main mainApp;
	    private File file;
	    private File directory;
	    public static final int BUFFOR = 1024;
	    
	    public UnzipViewController() {
	    	
	    }

	    
	  
	    @FXML
	    private void initialize() {
	        
	    }
	    
	    @FXML
	    private void handleChooseFileButton() {
	    	FileChooser fileChooser = new FileChooser();
	    	fileChooser.setTitle("Choose zip file");
	    	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	    	file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
	    	
	    	if(file!=null) {
	    	try {
				fileTextField.setText(file.getCanonicalPath());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	    	}
	    }
	    
	    @FXML
	    private void handleChooseFolderButton() {
	    	DirectoryChooser directoryChooser = new DirectoryChooser();
	    	directory = directoryChooser.showDialog(mainApp.getPrimaryStage());
	    	if(directory!= null) {
	    		try {
					folderTextField.setText(directory.getCanonicalPath());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
	    	}
	    }
	    
	    @FXML
	    private void handleUnzipButton() {
	    	byte[] tmpData = new byte[BUFFOR];
	    	File directory = new File(folderTextField.getText());
	    	ZipEntry newEntry = null;
	    	try {
	    		if (!directory.exists())
	                directory.mkdir();
	    		
	    		ZipInputStream input = new ZipInputStream(new BufferedInputStream(new FileInputStream(fileTextField.getText()),BUFFOR));
	            
	            
	            while ((newEntry = input.getNextEntry())!= null)
	            {
	                BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(directory+File.separator+newEntry.getName()),BUFFOR);
	                
	                int counter;
	                
	                while ((counter = input.read(tmpData, 0, BUFFOR)) != -1)
	                    output.write(tmpData, 0, counter);
	                
	                output.close();
	                input.closeEntry();
	            }
	            
	            input.close();


	    		
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	    	
	    	fileTextField.setText("");
	    	folderTextField.setText("");
	    	
	    	
	    	
	    }
	    
	    public void setMainApp(Main mainApp) {
	        this.mainApp = mainApp;
	    }
}