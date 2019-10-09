package com.tester.fxview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		// Path to the FXML File
		//loader.setLocation(this.getClass().getResource("fxml/MainApp.fxml"));
		
	  // Create the Pane and all Details
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainApp.fxml"));

		// Create the scene
		Scene scene = new Scene(root);
		stage.setTitle("Service Test");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(MainView.class, args);
	}
}
