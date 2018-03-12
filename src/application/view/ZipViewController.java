package application.view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class ZipViewController {
	
    @FXML
    private Button addFilesButton;
    
    @FXML
    private Button deleteFilesButton;
    
    @FXML
    private Button zipButton;
    
    @FXML
    private Button destinationPathButton;
    
    @FXML 
    private ListView<String> list;
    
    @FXML
    private TextField destinationPath;
    

    // Reference to the main application.
    private Main mainApp;
    private ObservableList<String> items;
    private List<File> allFiles;
    public static final int BUFFOR = 1024;

    
    public ZipViewController() {
    	
    }

    
  
    @FXML
    private void initialize() {
    	items = FXCollections.observableArrayList();
    	list.setItems(items);
    	allFiles = new LinkedList<>();
    	
    }
    
    @FXML 
    private void handleAddFilesButton() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Choose files");
    	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    	List<File> files = fileChooser.showOpenMultipleDialog(mainApp.getPrimaryStage());
    	if(files!=null) { //files == null jesli okno zostanie zamkniete bez wybrania plikow
    	for(File file : files) {
    		try {
				items.add(file.getCanonicalPath());
				allFiles.add(file);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
    	}
    }
    }
    
   
	@FXML
    private void handleDeleteFilesButton() {
    	int index = list.getSelectionModel().getSelectedIndex();
    	items.remove(index);
    	allFiles.remove(index);
    	
    }
    
    @FXML
    private void handleDestinationPathButton() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Create destination file");
    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Zip file", "*.zip");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    	File destinationFile = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
    	if(destinationFile!=null) {
    		try {
				destinationPath.setText(destinationFile.getCanonicalPath());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
    	}
    }
    
    @FXML
    private void handleZipButton() {
    	byte[] tmpData = new byte[BUFFOR];
    	try {
			ZipOutputStream output = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(destinationPath.getText()),BUFFOR));
			
			for(int i=0;i<items.size();i++) {
			BufferedInputStream inputFile = new BufferedInputStream(new FileInputStream(items.get(i)),BUFFOR);
			output.putNextEntry(new ZipEntry(allFiles.get(i).getName()));
			
			int counter;
			while((counter=inputFile.read(tmpData, 0, BUFFOR)) != -1) {
				output.write(tmpData, 0, counter);
			}
			
			output.closeEntry();
			inputFile.close();
			}
			output.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    	
    	items.clear();
    	allFiles.clear();
    	destinationPath.setText("");
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
    
    public List<File> getAllFiles(){
    	return allFiles;
    }
    
    public TextField getDestinationPath() {
    	return destinationPath;
    }
}
