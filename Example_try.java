package application;


import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

class Check
{
	private int count;
	private Sphere sphere;
	PathTransition transitionCircle;
	
	public Check()
	{
		count = 0;
	}
	public int getCount() {
		return count;
	}
	public void setCount() {
		this.count++;
	}
	public Sphere getSphere() {
		return sphere;
	}
	public void setSphere(Sphere sphere) {
		this.sphere = sphere;
	}
	
}

public class Example{
	
	public void start() 
	{
		Stage primaryStage=new Stage();
        primaryStage.setTitle("Game");
        Button btnUndo = new Button("Undo");
        Button btnNewGame = new Button("New Game");
        Button btnExit = new Button("Exit");
        Button btnSetting = new Button("Setting");
        btnUndo.setStyle("-fx-font-size: 10pt;");
        btnUndo.setMinSize(80, 20);
        btnUndo.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1px;-fx-background-color: #202020;-fx-text-fill: #ffffff");
        btnNewGame.setStyle("-fx-font-size: 10pt;");
        btnNewGame.setMinSize(80, 20);
        btnNewGame.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1px;-fx-background-color: #202020;-fx-text-fill: #ffffff");
        btnExit.setStyle("-fx-font-size: 10pt;");
        btnExit.setMinSize(80, 20);
        btnExit.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1px;-fx-background-color: #202020;-fx-text-fill: #ffffff");
        btnSetting.setStyle("-fx-font-size: 10pt;");
        btnSetting.setMinSize(80, 20);
        btnSetting.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1px;-fx-background-color: #202020;-fx-text-fill: #ffffff");
		
        HBox hbButtons=new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnUndo,btnSetting,btnNewGame,btnExit);
        GridPane grid = new GridPane();
        for(int i = 0; i < 6; i++) {
            ColumnConstraints column = new ColumnConstraints(100);
            grid.getColumnConstraints().add(column);
        }

        for(int i = 0; i < 9; i++) {
            RowConstraints row = new RowConstraints(100);
            grid.getRowConstraints().add(row);
        }
        final Group root = new Group(grid,hbButtons);
        grid.setStyle("-fx-background-color: black; -fx-grid-lines-visible: true");
        grid.setTranslateX(100);
        grid.setTranslateY(100);
        Scene scene = new Scene(root, 2000,2000, Color.BLACK);
        Check check[][] = new Check[9][6];
        grid.setOnMouseClicked(event ->{
        	
        	Sphere sphere = new Sphere(20);
        	PhongMaterial material = new PhongMaterial();
            material.setDiffuseMap(new Image(getClass().getResource("green.jpg").toExternalForm()));
            sphere.setMaterial(material);
            sphere.setEffect(new Lighting());
        	int x = (int)event.getSceneX()/100 - 1;
        	int y = (int)event.getSceneY()/100 - 1;
        	if(check[y][x] == null)
        	{
        		check[y][x] = new Check();
        	}
        	if(check[y][x].getCount() == 1)
        	{
        		if((x!=0 || y!=0) &&  (x!=0 || y!=8) && (x!=5 || y!=0) && (x!=5 || y!=8) )
        		{
        			//sphere.setTranslateX(10);
        			//sphere.setTranslateY(10);
        			Circle circle = new Circle(20,Color.TRANSPARENT);
        			circle.setCenterX(0);
        			circle.setCenterY(0);
        			circle.translateXProperty().bind(check[y][x].getSphere().translateXProperty());
        			circle.translateYProperty().bind(check[y][x].getSphere().translateYProperty());
        			circle.setRadius(25);
        			
        			PathTransition transitionCircle = new PathTransition();
        			transitionCircle.setPath(circle);
        			transitionCircle.setNode(sphere);
        			transitionCircle.setInterpolator(Interpolator.LINEAR);
        			
        			if(x==0 || x==5 || y==0 || y==8)
        			{
        				transitionCircle.setDuration(Duration.seconds(2));
        			}
        			else
        			{
        				transitionCircle.setDuration(Duration.seconds(5));
        			}
        			transitionCircle.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        			transitionCircle.setCycleCount(Timeline.INDEFINITE);
        			transitionCircle.play();
        			
        			check[y][x].transitionCircle = transitionCircle;
        			
        			Timeline animationTimeLine = new Timeline(60, new KeyFrame(Duration.seconds(5), new KeyValue(sphere.rotateProperty(), 360.0)));
                    animationTimeLine.setCycleCount(Timeline.INDEFINITE);
                    animationTimeLine.play();
                    
                    GridPane.setHalignment(sphere, HPos.CENTER);
                    GridPane.setHalignment(circle, HPos.CENTER);
        			grid.add(sphere,x,y);
        			grid.add(circle,x,y);
        			check[y][x].setCount();
        		}
        		
        	}
        	else if(check[y][x].getCount() == 2)
            {
        		if(x!=0 && x!=5 && y!=0 && y!=8)
        		{
        			//sphere.setTranslateX(40);
        			//sphere.setTranslateY(10);
        			Circle circle = new Circle(20,Color.TRANSPARENT);
        			circle.setCenterX(0);
        			circle.setCenterY(0);
        			circle.translateXProperty().bind(check[y][x].getSphere().translateXProperty());
        			circle.translateYProperty().bind(check[y][x].getSphere().translateYProperty());
        			circle.setRadius(20);
        			
        			PathTransition transitionCircle = new PathTransition();
        			transitionCircle.setPath(circle);
        			transitionCircle.setNode(sphere);
        			transitionCircle.setInterpolator(Interpolator.LINEAR);
        			transitionCircle.setDuration(Duration.seconds(5));
        			//check[y][x].transitionCircle.stop();
        			//check[y][x].transitionCircle.setDuration(Duration.seconds(2));
        			//check[y][x].transitionCircle.play();
        			transitionCircle.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        			transitionCircle.setCycleCount(Timeline.INDEFINITE);
        			transitionCircle.play();
        			
        			Timeline animationTimeLine = new Timeline(60, new KeyFrame(Duration.seconds(5), new KeyValue(sphere.rotateProperty(), 360.0)));
                    animationTimeLine.setCycleCount(Timeline.INDEFINITE);
                    animationTimeLine.play();
                    
                    GridPane.setHalignment(sphere, HPos.CENTER);
                    GridPane.setHalignment(circle, HPos.CENTER);
                    grid.add(sphere,x,y);
        			grid.add(circle,x,y);
        			check[y][x].setCount();
        		}
            }
        	else if(check[y][x].getCount() == 0)
        	{
        		grid.add(sphere, x, y);
        		
        		if((x==0 && y==0) ||  (x==0 && y==8) || (x==5 && y==0) || (x==5 && y==8))
        		{
        			Timeline animationTimeLine = new Timeline(60, new KeyFrame(Duration.seconds(1), new KeyValue(sphere.rotateProperty(), 360.0)));
                    animationTimeLine.setCycleCount(Timeline.INDEFINITE);
                    animationTimeLine.play();
        		}
        		else
        		{
        			Timeline animationTimeLine = new Timeline(60, new KeyFrame(Duration.seconds(5), new KeyValue(sphere.rotateProperty(), 360.0)));
                    animationTimeLine.setCycleCount(Timeline.INDEFINITE);
                    animationTimeLine.play();
        		}
        		GridPane.setHalignment(sphere, HPos.CENTER);
        		check[y][x].setCount();
        		check[y][x].setSphere(sphere);
        	}
        });
         btnExit.setOnAction(event->{
        	primaryStage.close();
        });
        btnNewGame.setOnAction(event->{
        	primaryStage.close();
        	this.start();
        });
        btnSetting.setOnAction(event->{
        	Player_Setting p=new Player_Setting();
        	p.run();
        });
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	public void run()
	{
		this.start();
	}

	public static void main(String[] args) 
	{
     
    }
}
