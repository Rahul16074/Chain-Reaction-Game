package Chain_reaction;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Example extends Application{
	
	public void start(Stage primaryStage) {
        primaryStage.setTitle("Game");
        GridPane grid = new GridPane();
        for(int i = 0; i < 6; i++) {
            ColumnConstraints column = new ColumnConstraints(100);
            grid.getColumnConstraints().add(column);
        }

        for(int i = 0; i < 9; i++) {
            RowConstraints row = new RowConstraints(100);
            grid.getRowConstraints().add(row);
        }
        final Group root = new Group(grid);
        grid.setStyle("-fx-background-color: black; -fx-grid-lines-visible: true");
        grid.setTranslateX(100);
        grid.setTranslateY(100);
        Scene scene = new Scene(root, (6 * 100) + 100, (9 * 100) + 100, Color.BLACK);
        grid.setOnMouseClicked(event ->{
        	Sphere sphere = new Sphere(20);
        	
        	
        	int x = (int)event.getSceneX()/100;
        	int y = (int)event.getSceneY()/100;
        	sphere.setTranslateX(((x + x + 1)/2.0)*100);
        	sphere.setTranslateY(((y + y + 1)/2.0)*100);
        	sphere.setVisible(true);
        	root.getChildren().add(sphere);
        });
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	
	public static void main(String[] args) {
        launch(args);
    }
}
