package it.polito.esame;
import java.net.URL;
import java.util.ResourceBundle;

import it.polito.esame.bean.Corso;
import it.polito.esame.bean.Link;
import it.polito.esame.bean.Model;
import it.polito.esame.bean.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CorsiController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Color x1;

    @FXML
    private Button btnElencoCorsi;

    @FXML
    private Button btnStudentiSimili;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtResult.clear();
    	int matricola;
    	try{
    		matricola=Integer.parseInt(txtMatricola.getText());
    		Studente s=model.getStudente(matricola);
    		if(s!=null){
    		txtResult.appendText("Studente "+ s.toString()+"\n");
    		
    		for(Corso ctemp:model.getCorsiStudente(matricola)){
    			txtResult.appendText(ctemp.toString()+"\n");
    			}
    		}	
    		else{
    			txtResult.appendText("Studente non esistente !");
    		}
    			
    	}catch(Exception e){
    		txtResult.appendText("Inserire matricola valida !");
    		return;
    	}

    }

    @FXML
    void doCercaSimili(ActionEvent event) {
    	txtResult.clear();
    	int matricola;
    	try{
    		matricola=Integer.parseInt(txtMatricola.getText());
    		Studente s=model.getStudente(matricola);
    			if(s!=null){
    				txtResult.appendText("Massimo numero di corsi comuni "+Integer.toString(model.getMaxCorsi(s))+"\n");
    				for(Studente stemp: model.getSimili(s)){
    					txtResult.appendText(stemp.toString()+"\n");
    				}
    				txtResult.appendText(" numero di studenti simili: " + Integer.toString(model.getSimili(s).size()) + "\n");
    			}
    			else
    				txtResult.appendText("Studente non esistente !");
    			
    	}catch(Exception e){
    		e.printStackTrace();
    		return;
    	}
    	

    }

    @FXML
    void initialize() {
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'iscrittiT1.fxml'.";
        assert x1 != null : "fx:id=\"x1\" was not injected: check your FXML file 'iscrittiT1.fxml'.";
        assert btnElencoCorsi != null : "fx:id=\"btnElencoCorsi\" was not injected: check your FXML file 'iscrittiT1.fxml'.";
        assert btnStudentiSimili != null : "fx:id=\"btnStudentiSimili\" was not injected: check your FXML file 'iscrittiT1.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'iscrittiT1.fxml'.";

    }

	public void setModel(Model model) {
	this.model=model;
		
	}
}
