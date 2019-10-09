package com.tester.fxcontroller;

import com.tester.controller.TestCaseListController;
import com.tester.fxmodel.ServiceModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {

    @FXML private TextField txtService;
    @FXML private Button btnStart;    
    @FXML private Label lblService;
    
    @FXML
    public void initialize() {
    	
    }
    
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
    	if(event.getSource().equals(btnStart)){
    		System.out.println("Iniciar presionado");
    		if(txtService.getText().equals("")){
    			System.out.println("Servicio en blanco");
    		}else{
    			try {
						ServiceModel sm = new ServiceModel(txtService.getText());
						TestCaseListController tclController = new TestCaseListController(sm.getXmlRequest(), sm.getRequestKeyData());
						tclController.execute();
					} catch (Exception e) {
						e.printStackTrace();
					}
    		}
    	}
    	
    }
}
