package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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
import javafx.scene.paint.Paint;
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
	public void zero(int x,int y, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color)
	{
		Sphere sphere = new Sphere(10);
    	PhongMaterial material = new PhongMaterial();
    	material.setDiffuseColor(color);
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
		box[y][x].setColor(color);
		sbox[y][x].setSphereCount(box[y][x].getCount());
		sbox[y][x].setColor(color.toString());
		try 
		{
			store_state(sbox);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		box[y][x].setSphere1(sphere);
	}
	
	public void one(int x, int y, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color)
	{
		if((x!=0 || y!=0) &&  (x!=0 || y!=8) && (x!=5 || y!=0) && (x!=5 || y!=8) )
		{
			Sphere sphere = new Sphere(10);
        	PhongMaterial material = new PhongMaterial();
        	material.setDiffuseColor(color);
            sphere.setMaterial(material);
            sphere.setEffect(new Lighting());
            
            grid.getChildren().remove(box[y][x].getSphere1());
            box[y][x].setCount(box[y][x].getCount() - 1);
            zero(x,y,box,grid,sbox,color);
			
			Circle circle = new Circle(10,Color.TRANSPARENT);
			circle.setCenterX(0);
			circle.setCenterY(0);
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
			//grid.add(circle,x,y);
			box[y][x].setCount();
			box[y][x].setColor(color);
			sbox[y][x].setSphereCount(box[y][x].getCount());
			sbox[y][x].setColor(color.toString());
			try 
			{
				store_state(sbox);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			box[y][x].setSphere2(sphere);
		}
		else
		{
			splitmain(x,y,box,grid, sbox, color);
		}
	}
	
	public void two(int x, int y, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color)
	{
		if(x!=0 && x!=5 && y!=0 && y!=8)
		{
			Sphere sphere = new Sphere(10);
        	PhongMaterial material = new PhongMaterial();
        	material.setDiffuseColor(color);
            sphere.setMaterial(material);
            sphere.setEffect(new Lighting());
    		
    		Sphere sphere2 = new Sphere(10);
    		material.setDiffuseColor(color);
            sphere2.setMaterial(material);
            sphere2.setEffect(new Lighting());

            
			Circle circle = new Circle(10,Color.TRANSPARENT);
			circle.setCenterX(0);
			circle.setCenterY(0);
			circle.setRadius(10);
			
			grid.getChildren().remove(box[y][x].getSphere2());
			grid.getChildren().remove(box[y][x].getSphere1());
			box[y][x].setCount(box[y][x].getCount() - 1);
			zero(x,y,box,grid,sbox,color);
			
			
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
            
			box[y][x].setCount();
			box[y][x].setColor(color);
			sbox[y][x].setSphereCount(box[y][x].getCount());
			sbox[y][x].setColor(color.toString());
			try 
			{
				store_state(sbox);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			box[y][x].setSphere2(sphere);
			box[y][x].setSphere3(sphere2);
		}
		else
		{
			splitmain(x,y,box,grid,sbox,color);
		}
	}
	public void split(int x1, int y1,int x2, int y2,int p1, int q1, int p2,int q2, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color)
	{
		grid.getChildren().remove(box[y1][x1].getSphere1());
		grid.getChildren().remove(box[y1][x1].getSphere2());
		grid.getChildren().remove(box[y1][x1].getSphere3());
		box[y1][x1].setempty();
		sbox[y1][x1].setEmpty();
		try 
		{
			store_state(sbox);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		Sphere sphere1 = new Sphere(10);
    	PhongMaterial material = new PhongMaterial();
    	material.setDiffuseColor(color);
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
        		zero(x2,y2,box,grid,sbox,color);
        	}
        	else if(box[y2][x2].getCount() == 1)
        	{       
        		one(x2,y2,box,grid,sbox,color);       		
        	}
        	else if(box[y2][x2].getCount() == 2)
            {        	
        		two(x2,y2,box,grid,sbox,color);      		
            }
        	else if(box[y2][x2].getCount() == 3)
        	{
        		splitmain(x2,y2,box,grid,sbox,color);
        	}
 		   
		});
        
        
	}
	
	public void splitmain(int x, int y, Box box[][], GridPane grid,Block_serialize[][] sbox, Color color)
	{
		if(x+1<=5)
		{
			split(x,y,x+1,y,0,0,50,0,box,grid,sbox,color);
		}
		if(x-1>=0)
		{
			split(x,y,x-1,y,0,0,-50,0,box,grid,sbox,color);
		}
		if(y+1<=8)
		{
			split(x,y,x,y+1,0,0,0,50,box,grid,sbox,color);
		}
		if(y-1>=0)
		{
			split(x,y,x,y-1,0,0,0,-50,box,grid,sbox,color);
		}
	}
	
	public void store_state(Block_serialize[][] sbox) throws FileNotFoundException, IOException
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		location=location+"\\Block_state.txt";
		ObjectOutputStream out=null;
		out=new ObjectOutputStream(new FileOutputStream(location));
		out.writeObject(sbox);
		out.close();
	}
	
	static Block_serialize[][] get_state() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		location=location+"\\Block_state.txt";
		ObjectInputStream input=null;
		Block_serialize[][] obj=null;
		try
		{
			input=new ObjectInputStream(new FileInputStream(location));
			obj= (Block_serialize[][])input.readObject();	
		}
		catch(EOFException e)
		{
			
		}
		input.close();
		return obj;
	}
	public void start(Block_serialize[][] sbox, int totnum, Player_turn playerturn)  throws FileNotFoundException, ClassNotFoundException, IOException
	{	
		Stage primaryStage=new Stage();
        primaryStage.setTitle("Game");
        
        Individual_Setting is = new Individual_Setting();
        Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		File folder = new File(location);
		File[] listOfFiles = folder.listFiles();
		location=location+"\\savedSettings.txt";
		is.load(location);
		serializedSetting obj=is.read(location);
		String[] pcolor = new String[8];
		pcolor[0] = obj.color1;
		pcolor[1] = obj.color2;
		pcolor[2] = obj.color3;
		pcolor[3] = obj.color4;
		pcolor[4] = obj.color5;
		pcolor[5] = obj.color6;
		pcolor[6] = obj.color7;
		pcolor[7] = obj.color8;
        
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
        Box prev[][] = new Box[9][6];
        
        ObservableList<Node> list = FXCollections.observableArrayList();
        list.addAll(grid.getChildren());
        
        grid.setOnMouseClicked(event ->
        {
        	list.remove(0, list.size());
        	list.addAll(grid.getChildren());
        	
        	for(int i=0;i<9;i++)
        	{
        		for(int j=0;j<6;j++)
        		{
        			prev[i][j] = new Box();
        			if(box[i][j]!=null)
        			{
        				prev[i][j].copy(box[i][j]);
        			}
        		}
        	}
        	
        	String col = pcolor[playerturn.getCur_turn()];
        	Color color = Color.valueOf(col);
        	int x = (int)(event.getSceneX()-100)/50;
        	int y = (int)(event.getSceneY()-100)/50;
        	if(box[y][x] == null)
        	{
        		box[y][x] = new Box();
        	}
        	
        	if(box[y][x].getCount() == 0)
        	{
        		zero(x,y,box,grid,sbox,color);
        		playerturn.increment();
        		//currentStatus(sbox);
        	}
        	else if(box[y][x].getCount() == 1 && box[y][x].getColor().equals(color))
        	{       
        		one(x,y,box,grid,sbox,color); 
        		playerturn.increment();
        		//currentStatus(sbox);
        	}
        	else if(box[y][x].getCount() == 2 && box[y][x].getColor().equals(color))
            {        	
        		two(x,y,box,grid,sbox,color);  
        		playerturn.increment();
        		//currentStatus(sbox);
            }
        	else if(box[y][x].getCount() == 3 && box[y][x].getColor().equals(color))
        	{
        		splitmain(x,y,box,grid,sbox,color);
        		playerturn.increment();
        		//synchroniseState(box,sbox);
        		//currentStatus(sbox);
        	}
        	//System.out.println();
        });

        btnExit.setOnAction(event->
        {
        	primaryStage.close();
        });

        btnNewGame.setOnAction(event->
        {
        	primaryStage.close();
        	for(int i=0;i<9;i++)
        	{
        		for(int j=0;j<6;j++)
        		{
        			sbox[i][j].reset();
        		}
        	}
        	try {
				this.start(sbox, totnum, playerturn);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        });
        
        btnSetting.setOnAction(event->
        {
        	Player_Setting p=new Player_Setting();
        	p.run();
        });
        
        btnUndo.setOnAction(event->
        {
        	grid.getChildren().remove(0,grid.getChildren().size());
        	grid.getChildren().setAll(list);
        	for(int i=0;i<9;i++)
        	{
        		for(int j=0;j<6;j++)
        		{
        			box[i][j] = new Box();
        			if(prev[i][j]!=null)
        			{
        				box[i][j].copy(prev[i][j]);
        			}
        		}
        	}
        	playerturn.decrement();
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	public void run(Block_serialize[][] sbox, int totnum, Player_turn playerturn) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		this.start(sbox,totnum, playerturn);
	}

	public void currentStatus(Block_serialize[][] sbox)
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<6;j++)
			{
				System.out.print(sbox[i][j].getColor()+" "+sbox[i][j].getSphereCount()+" ");
			}
			System.out.println();
		}
	}
	
	public void synchroniseState(Box[][] box, Block_serialize[][] sbox)
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<6;j++)
			{
				if(box[i][j]!=null && box[i][j].getColor()!=null)
				{
					System.out.println("At:"+i+" "+j);
					sbox[i][j].setColor(box[i][j].getColor().toString());
				}
			}
		}
	}
	public static void main(String[] args) 
	{
    }
}