package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class RootViewController {
	
	@FXML
	private MenuItem zip;
	
	@FXML
	private MenuItem unzip;
	
	@FXML
	private Menu switchMenu;
	
	private Main mainApp;
	
	public RootViewController() {
    	
    }

    
  
    @FXML
    private void initialize() {

    }
    
    @FXML
    private void handleZip() {
    	mainApp.getRootLayout().setCenter(mainApp.getViews()[0]);
    }
    
    @FXML
    private void handleUnzip() {
    	mainApp.getRootLayout().setCenter(mainApp.getViews()[1]);
    	
    }
    
    public void setMainApp(Main mainApp) {
    	this.mainApp = mainApp;
    }

}
