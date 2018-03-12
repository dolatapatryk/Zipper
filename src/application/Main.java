package application;
	
import java.io.IOException;

import application.view.RootViewController;
import application.view.UnzipViewController;
import application.view.ZipViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane[] views;
    
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Zipper");

	        initRootLayout();
	        
	        //showZipView();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initRootLayout() {
        try {
        	views = new AnchorPane[2];
        	
            // Load root layout from fxml file.
            FXMLLoader loaderRoot = new FXMLLoader();
            loaderRoot.setLocation(Main.class.getResource("view/RootView.fxml"));
            rootLayout = (BorderPane) loaderRoot.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            // Give the controller access to the main app.
            RootViewController rootController = loaderRoot.getController();
            rootController.setMainApp(this);
            
            
            //zip layout
            FXMLLoader loaderZip = new FXMLLoader();
            loaderZip.setLocation(Main.class.getResource("view/ZipView.fxml"));
            AnchorPane zipView = (AnchorPane) loaderZip.load();
            views[0] = zipView;
            rootLayout.setCenter(zipView);
            ZipViewController zipController = loaderZip.getController();
            zipController.setMainApp(this);
            
            //unzip layout
            FXMLLoader loaderUnzip = new FXMLLoader();
            loaderUnzip.setLocation(Main.class.getResource("view/UnzipView.fxml"));
            AnchorPane unzipView = (AnchorPane) loaderUnzip.load();
            views[1] = unzipView;
            UnzipViewController unzipController = loaderUnzip.getController();
            unzipController.setMainApp(this);
            
  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showZipView() {
        try {
            // Load zip overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ZipView.fxml"));
            AnchorPane zipView = (AnchorPane) loader.load();

            // Set zip overview into the center of root layout.
            rootLayout.setCenter(zipView);
            
         // Give the controller access to the main app.
            ZipViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public BorderPane getRootLayout() {
		return rootLayout;
	}
	
	public AnchorPane[] getViews() {
		return views;
	}

}
