package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * This program initializes the main game window
 * @author Vipul Saini
 * @author Rahul Lawaria
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		primaryStage.setTitle("CHAIN REACTION GAME");
		try 
		{	
			AnchorPane root =FXMLLoader.load(getClass().getResource("main_menu.fxml"));
			Scene scene=new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
