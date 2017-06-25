package it.polito.esame ;

import java.io.IOException;

import it.polito.esame.bean.Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EsameT1 extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		try{
		FXMLLoader loader = new FXMLLoader( this.getClass().getResource("gui/iscrittiT1.fxml")) ;
		BorderPane root = (BorderPane) loader.load();
		CorsiController controller= loader.getController();
		
		Model model=new Model();
		controller.setModel(model);
		
		
		Scene scene = new Scene(root) ;
		primaryStage.setScene(scene) ;
		primaryStage.show() ;
		}catch(Exception e ){
			e.printStackTrace();
		}
		}


	public static void main(String[] args) {
		launch(args);
	}
}
