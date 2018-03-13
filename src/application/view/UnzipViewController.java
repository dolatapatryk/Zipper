package application.view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	    	try {
				ZipInputStream input = new ZipInputStream(new BufferedInputStream(new FileInputStream(folderTextField.getText())));
				
				
				
				
				input.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	    	
	    	
	    	
	    	
	    	
	    }
	    
	    public void setMainApp(Main mainApp) {
	        this.mainApp = mainApp;
	    }
}