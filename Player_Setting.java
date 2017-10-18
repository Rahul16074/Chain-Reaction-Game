package application;


import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class Player_Setting{
	
	public void start() {
        Stage primaryStage=new Stage();
		primaryStage.setTitle("Chain Reaction Preferences");
        ListView<String> list=new ListView<String>();
        list.getItems().add("Player1 Setting");
        list.getItems().add("Player2 Setting");
        list.getItems().add("Player3 Setting");
        list.getItems().add("Player4 Setting");
        list.getItems().add("Player5 Setting");
        list.getItems().add("Player6 Setting");
        list.getItems().add("Player7 Setting");
        list.getItems().add("Player8 Setting");
		Scene scene = new Scene(list, 220,299, Color.BLACK);     
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	public void run()
	{
		this.start();
	}
	public static void main(String[] args) {
        
    }
}
