package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleController {
	
	private Model m = new Model();
	
	public void setModle(Model m){
		this.m = m;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboCitta;

    @FXML
    private Button btnElenco;

    @FXML
    private TextField txtProb;

    @FXML
    private TextField txtNumeroT;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void doElenco(ActionEvent event) {
    	txtResult.clear();
    	String citta = comboCitta.getValue();
    	if(citta==null){
    		txtResult.appendText("Seleziona una citta!\n");
    		return;
    	}

    }

    @FXML
    void doSimula(ActionEvent event) {
    	String citta = comboCitta.getValue();
    	if(citta==null){
    		txtResult.appendText("Seleziona una citta!\n");
    		return;
    	}


    }

    @FXML
    void initialize() {
        assert comboCitta != null : "fx:id=\"comboCitta\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnElenco != null : "fx:id=\"btnElenco\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtProb != null : "fx:id=\"txtProb\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtNumeroT != null : "fx:id=\"txtNumeroT\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

        comboCitta.getItems().addAll(m.getCitta());
    }
}
