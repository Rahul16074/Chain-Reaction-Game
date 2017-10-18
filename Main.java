package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage=primaryStage;
		try {
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