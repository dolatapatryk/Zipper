package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
	    
	    public UnzipViewController() {
	    	
	    }

	    
	  
	    @FXML
	    private void initialize() {
	        
	    }
	    
	    @FXML
	    private void handleChooseFileButton() {
	    	
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
