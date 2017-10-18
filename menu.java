package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class menu implements javafx.fxml.Initializable{
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button play;

	    @FXML
	    private Button play_HD;

	    @FXML
	    private ComboBox<?> sel_player;

	    @FXML
	    private Button setting;


	    @FXML
	    void initialize() {
	        assert play != null : "fx:id=\"play\" was not injected: check your FXML file 'main_menu.fxml'.";
	        assert play_HD != null : "fx:id=\"play_HD\" was not injected: check your FXML file 'main_menu.fxml'.";
	        assert sel_player != null : "fx:id=\"sel_player\" was not injected: check your FXML file 'main_menu.fxml'.";
	        assert setting != null : "fx:id=\"setting\" was not injected: check your FXML file 'main_menu.fxml'.";


	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		play.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent e){
				Example ex=new Example();
				ex.run();
			}
		});		
	}
	
}
