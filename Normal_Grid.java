package application;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Normal_Grid
{
	public void zero(int x,int y, Box box[][], GridPane grid)
	{
		Sphere sphere = new Sphere(10);
    	PhongMaterial material = new PhongMaterial();
    	material.setDiffuseColor(Color.RED);
        //material.setDiffuseMap(new Image(getClass().getResource("redmin.jpg").toExternalForm()));
        sphere.setMaterial(material);
        sphere.setEffect(new Lighting());
		
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
		box[y][x].setCount();
		box[y][x].setSphere1(sphere);
	}
	
	public void one(int x, int y, Box box[][], GridPane grid)
	{
		if((x!=0 || y!=0) &&  (x!=0 || y!=8) && (x!=5 || y!=0) && (x!=5 || y!=8) )
		{
			Sphere sphere = new Sphere(10);
        	PhongMaterial material = new PhongMaterial();
        	material.setDiffuseColor(Color.RED);
            //material.setDiffuseMap(new Image(getClass().getResource("redmin.jpg").toExternalForm()));
            sphere.setMaterial(material);
            sphere.setEffect(new Lighting());
			
			Circle circle = new Circle(10,Color.TRANSPARENT);
			circle.setCenterX(0);
			circle.setCenterY(0);
			circle.translateXProperty().bind(box[y][x].getSphere1().translateXProperty());
			circle.translateYProperty().bind(box[y][x].getSphere1().translateYProperty());
			circle.setRadius(10);
		
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

			Timeline animationTimeLine = new Timeline(60, new KeyFrame(Duration.seconds(5), new KeyValue(sphere.rotateProperty(), 360.0)));
            animationTimeLine.setCycleCount(Timeline.INDEFINITE);
            animationTimeLine.play();
            
            GridPane.setHalignment(sphere, HPos.CENTER);
            GridPane.setHalignment(circle, HPos.CENTER);
			grid.add(sphere,x,y);
			grid.add(circle,x,y);
			box[y][x].setCount();
			box[y][x].setSphere2(sphere);
		}
		else
		{
			splitmain(x,y,box,grid);
		}
	}
	
	public void two(int x, int y, Box box[][], GridPane grid)
	{
		if(x!=0 && x!=5 && y!=0 && y!=8)
		{
			Sphere sphere = new Sphere(10);
        	PhongMaterial material = new PhongMaterial();
        	material.setDiffuseColor(Color.RED);
            //material.setDiffuseMap(new Image(getClass().getResource("redmin.jpg").toExternalForm()));
            sphere.setMaterial(material);
            sphere.setEffect(new Lighting());
    		
    		Sphere sphere2 = new Sphere(10);
    		material.setDiffuseColor(Color.RED);
            //material.setDiffuseMap(new Image(getClass().getResource("redmin.jpg").toExternalForm()));
            sphere2.setMaterial(material);
            sphere2.setEffect(new Lighting());

            
			Circle circle = new Circle(10,Color.TRANSPARENT);
			circle.setCenterX(0);
			circle.setCenterY(0);
			circle.translateXProperty().bind(box[y][x].getSphere1().translateXProperty());
			circle.translateYProperty().bind(box[y][x].getSphere1().translateYProperty());
			circle.setRadius(10);
			
			grid.getChildren().remove(box[y][x].getSphere2());
			
			PathTransition transitionCircle = new PathTransition();
			transitionCircle.setPath(circle);
			transitionCircle.setNode(sphere);
			transitionCircle.setInterpolator(Interpolator.LINEAR);
			transitionCircle.setDuration(Duration.seconds(2));
			transitionCircle.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
			transitionCircle.setCycleCount(Timeline.INDEFINITE);
			transitionCircle.play();
		
			transitionCircle = new PathTransition();
			transitionCircle.setPath(circle);
			transitionCircle.setNode(sphere2);
			transitionCircle.setInterpolator(Interpolator.LINEAR);
			transitionCircle.setDuration(Duration.seconds(2));
			transitionCircle.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
			transitionCircle.setCycleCount(Timeline.INDEFINITE);
			transitionCircle.playFrom(Duration.seconds(0.5));
			
			Timeline animationTimeLine = new Timeline(60, new KeyFrame(Duration.seconds(5), new KeyValue(sphere.rotateProperty(), 360.0)));
            animationTimeLine.setCycleCount(Timeline.INDEFINITE);
            animationTimeLine.play();                 
            GridPane.setHalignment(sphere, HPos.CENTER);
            GridPane.setHalignment(sphere2, HPos.CENTER);
            GridPane.setHalignment(circle, HPos.CENTER);
            grid.add(sphere,x,y);
            grid.add(sphere2,x,y);
			grid.add(circle,x,y);
			box[y][x].setCount();
			box[y][x].setSphere2(sphere);
			box[y][x].setSphere3(sphere2);
		}
		else
		{
			splitmain(x,y,box,grid);
		}
	}
	public void split(int x1, int y1,int x2, int y2,int p1, int q1, int p2,int q2, Box box[][], GridPane grid)
	{
		grid.getChildren().remove(box[y1][x1].getSphere1());
		grid.getChildren().remove(box[y1][x1].getSphere2());
		grid.getChildren().remove(box[y1][x1].getSphere3());
		box[y1][x1].setempty();
		
		Sphere sphere1 = new Sphere(10);
    	PhongMaterial material = new PhongMaterial();
    	material.setDiffuseColor(Color.RED);
        //material.setDiffuseMap(new Image(getClass().getResource("redmin.jpg").toExternalForm()));
        sphere1.setMaterial(material);
        sphere1.setEffect(new Lighting());
        
		Line line = new Line(p1,q1,p2,q2);
		line.setStroke(Color.TRANSPARENT);
		
		
		PathTransition transitionCircle = new PathTransition();
		transitionCircle.setPath(line);
		transitionCircle.setNode(sphere1);
		transitionCircle.setInterpolator(Interpolator.LINEAR);
		transitionCircle.setDuration(Duration.seconds(0.3));
		transitionCircle.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		transitionCircle.setCycleCount(1);
		transitionCircle.play();
		
		
		Timeline animationTimeLine = new Timeline(60, new KeyFrame(Duration.seconds(5), new KeyValue(sphere1.rotateProperty(), 360.0)));
        animationTimeLine.setCycleCount(Timeline.INDEFINITE);
        animationTimeLine.play();
        GridPane.setHalignment(sphere1, HPos.CENTER);
        GridPane.setHalignment(line, HPos.CENTER);
        grid.add(sphere1,x1,y1);
        //grid.add(line,x1,y1);
        
        transitionCircle.setOnFinished((ActionEvent event) -> {
        	
        	grid.getChildren().remove(sphere1);
        	
        	if(box[y2][x2] == null)
        	{
        		box[y2][x2] = new Box();
        	}
        	
        	if(box[y2][x2].getCount() == 0)
        	{
        		zero(x2,y2,box,grid);
        	}
        	else if(box[y2][x2].getCount() == 1)
        	{       
        		one(x2,y2,box,grid);       		
        	}
        	else if(box[y2][x2].getCount() == 2)
            {        	
        		two(x2,y2,box,grid);      		
            }
        	else if(box[y2][x2].getCount() == 3)
        	{
        		splitmain(x2,y2,box,grid);
        	}
 		   
		});
        
        
	}
	
	public void splitmain(int x, int y, Box box[][], GridPane grid)
	{
		if(x+1<=5)
		{
			split(x,y,x+1,y,0,0,50,0,box,grid);
		}
		if(x-1>=0)
		{
			split(x,y,x-1,y,0,0,-50,0,box,grid);
		}
		if(y+1<=8)
		{
			split(x,y,x,y+1,0,0,0,50,box,grid);
		}
		if(y-1>=0)
		{
			split(x,y,x,y-1,0,0,0,-50,box,grid);
		}
	}
	
	
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
        for(int i = 0; i < 6; i++)
        {
            ColumnConstraints column = new ColumnConstraints(50);
            grid.getColumnConstraints().add(column);
        }
        for(int i = 0; i < 9; i++) 
        {
            RowConstraints row = new RowConstraints(50);
            grid.getRowConstraints().add(row);
        }
        final Group root = new Group(grid,hbButtons);
        grid.setStyle("-fx-background-color: black; -fx-grid-lines-visible: true");
        grid.setTranslateX(100);
        grid.setTranslateY(100);
        Scene scene = new Scene(root, 530,600, Color.BLACK);
        Box box[][] = new Box[9][6];
        ObservableList<Node> list = FXCollections.observableArrayList();
        
        grid.setOnMouseClicked(event ->
        {
        	//Sphere sphere = new Sphere(10);
        	//PhongMaterial material = new PhongMaterial();
            //material.setDiffuseMap(new Image(getClass().getResource("redmin.jpg").toExternalForm()));
            //sphere.setMaterial(material);
            //sphere.setEffect(new Lighting());
        	int x = (int)(event.getSceneX()-100)/50;
        	int y = (int)(event.getSceneY()-100)/50;
        	if(box[y][x] == null)
        	{
        		box[y][x] = new Box();
        	}
        	
        	if(box[y][x].getCount() == 0)
        	{
        		zero(x,y,box,grid);
        	}
        	else if(box[y][x].getCount() == 1)
        	{       
        		one(x,y,box,grid);       		
        	}
        	else if(box[y][x].getCount() == 2)
            {        	
        		two(x,y,box,grid);      		
            }
        	else if(box[y][x].getCount() == 3)
        	{
        		//Line line = new Line(0,0,100,100);
        		//line.setStroke(Color.WHITE);
        		//grid.add(line, y, x);
        		splitmain(x,y,box,grid);
        	}
        	list.remove(0, list.size());
        	list.addAll(grid.getChildren());
        	for(Node a:list){
        		System.out.println(a.idProperty());
        	}
        });

        btnExit.setOnAction(event->
        {
        	primaryStage.close();
        });

        btnNewGame.setOnAction(event->
        {
        	primaryStage.close();
        	this.start();
        });
        
        btnSetting.setOnAction(event->
        {
        	Player_Setting p=new Player_Setting();
        	p.run();
        });
        btnUndo.setOnAction(event->
        {
        	//grid.getChildren().remove(0,grid.getChildren().size());
        	grid.getChildren().setAll(list);
        	System.out.println("Hello");
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
