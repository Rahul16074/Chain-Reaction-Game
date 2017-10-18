package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

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
	    private ComboBox<String> sel_player;

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
		sel_player.getItems().remove("Item 1");
		sel_player.getItems().remove("Item 2");
		sel_player.getItems().remove("Item 3");
		sel_player.getItems().add("2");
		sel_player.getItems().add("3");
		sel_player.getItems().add("4");
		sel_player.getItems().add("5");
		sel_player.getItems().add("6");
		sel_player.getItems().add("7");
		sel_player.getItems().add("8");
		
		play.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent e){
				Example ex=new Example();
				ex.run();
			}
		});
		setting.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Player_Setting p=new Player_Setting();
				p.run();
			}
			
		});
	}
	
}
