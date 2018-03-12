package application.view;

import java.io.File;
import java.io.IOException;
import java.util.List;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

public class ZipViewController {
	
    @FXML
    private Button addFilesButton;
    
    @FXML
    private Button deleteFilesButton;
    
    @FXML
    private Button zipButton;
    
    @FXML 
    private ListView<String> list;
    

    // Reference to the main application.
    private Main mainApp;
    private ObservableList<String> items;

    
    public ZipViewController() {
    	
    }

    
  
    @FXML
    private void initialize() {
    	items = FXCollections.observableArrayList();
    	list.setItems(items);
    }
    
    @FXML 
    private void handleAddFilesButton() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Choose files");
    	//fileChooser.showOpenDialog(mainApp.getPrimaryStage());
    	//fileChooser.showOpenMultipleDialog(mainApp.getPrimaryStage());
    	List<File> files = fileChooser.showOpenMultipleDialog(mainApp.getPrimaryStage());
    	for(File file : files) {
    		try {
				items.add(file.getCanonicalPath());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
    	}
    }
    
    @FXML
    private void handleDeleteFilesButton() {
    	int index = list.getSelectionModel().getSelectedIndex();
    	items.remove(index);
    }
    
    @FXML
    private void handleZipButton() {
    	
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    public ObservableList<String> getItems(){
    	return items;
    }
}
