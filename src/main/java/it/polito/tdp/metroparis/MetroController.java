package it.polito.tdp.metroparis;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.metroparis.model.Fermata;
import it.polito.tdp.metroparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MetroController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Fermata> dropPartenza;

    @FXML
    private ChoiceBox<Fermata> dropArrivo;

    @FXML
    private TableView<?> tblStampa;

    @FXML
    private TableColumn<StampType, Double> colIntervallo;

    @FXML
    private TableColumn<StampType, Double> colVelocita;

    @FXML
    private TableColumn<StampType, String> colLinea;

    @FXML
    private TableColumn<StampType, Double> colX;

    @FXML
    private TableColumn<StampType, Double> colY;

    @FXML
    private TableColumn<StampType, String> colFermata;

    @FXML
    private Label lblMessaggio;

    @FXML
    private Button btnStampa;

    @FXML
    void doStampa(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert dropPartenza != null : "fx:id=\"dropPartenza\" was not injected: check your FXML file 'Metro.fxml'.";
        assert dropArrivo != null : "fx:id=\"dropArrivo\" was not injected: check your FXML file 'Metro.fxml'.";
        assert tblStampa != null : "fx:id=\"tblStampa\" was not injected: check your FXML file 'Metro.fxml'.";
        assert colIntervallo != null : "fx:id=\"colIntervallo\" was not injected: check your FXML file 'Metro.fxml'.";
        assert colVelocita != null : "fx:id=\"colVelocita\" was not injected: check your FXML file 'Metro.fxml'.";
        assert colLinea != null : "fx:id=\"colLinea\" was not injected: check your FXML file 'Metro.fxml'.";
        assert colX != null : "fx:id=\"colX\" was not injected: check your FXML file 'Metro.fxml'.";
        assert colY != null : "fx:id=\"colY\" was not injected: check your FXML file 'Metro.fxml'.";
        assert colFermata != null : "fx:id=\"colFermata\" was not injected: check your FXML file 'Metro.fxml'.";
        assert lblMessaggio != null : "fx:id=\"lblMessaggio\" was not injected: check your FXML file 'Metro.fxml'.";
        assert btnStampa != null : "fx:id=\"btnStampa\" was not injected: check your FXML file 'Metro.fxml'.";

    }
    
    public void setModel (Model m) {
    	this.model=m;
    	
    	this.dropPartenza.getItems().setAll(this.model.getFermate());
    	this.dropArrivo.getItems().setAll(this.model.getFermate());
    	
    	this.dropPartenza.setValue(this.model.getFermate().get(0));
    	this.dropArrivo.setValue(this.model.getFermate().get(0));
    	
    }
}

