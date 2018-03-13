package application.view;

import java.io.File;
import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	    
	    }
	    
	    @FXML
	    private void handleUnzipButton() {
	    	
	    }
	    
	    public void setMainApp(Main mainApp) {
	        this.mainApp = mainApp;
	    }
}