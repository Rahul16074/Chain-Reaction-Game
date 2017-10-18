package application;


import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class Example{
	
	public void start() {
        Stage primaryStage=new Stage();
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
        Scene scene = new Scene(root, 2000,2000, Color.BLACK);
        int check[][] = new int[9][6];
        grid.setOnMouseClicked(event ->{
        	
        	Sphere sphere = new Sphere(20);
        	
        	int x = (int)event.getSceneX()/100 - 1;
        	int y = (int)event.getSceneY()/100 - 1;
        	System.out.println(x + " " + y);
        	if(check[y][x] == 1)
        	{
        		if((x!=0 || y!=0) &&  (x!=0 || y!=8) && (x!=5 || y!=0) && (x!=5 || y!=8) )
        		{
        			sphere.setTranslateX(10);
        			sphere.setTranslateY(10);
        			grid.add(sphere, x, y);
        			check[y][x]++;
        		}
        		
        	}
        	else if(check[y][x] == 2)
            {
        		if(x!=0 && x!=5 && y!=0 && y!=8)
        		{
        			sphere.setTranslateX(40);
        			sphere.setTranslateY(10);
        			grid.add(sphere, x, y);
        			check[y][x]++;
        		}
            }
        	else if(check[y][x] == 0)
        	{
        	//System.out.println(x + " " + y);
        	//sphere.setTranslateX(((x + x + 1)/2.0)*100);
        	//sphere.setTranslateY(((y + y + 1)/2.0)*100);
        	grid.add(sphere, x, y);
        	GridPane.setHalignment(sphere, HPos.CENTER);
        	check[y][x]++;
        	//sphere.setVisible(true);
        	//root.getChildren().add(sphere);
        	}
        });
        
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
