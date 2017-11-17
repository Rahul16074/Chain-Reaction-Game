package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.animation.Animation;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.AudioClip;
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
		int global_flag=0;
		public class cond{
		private int count;
		private String winner;
		public cond()
		{
			count=0;
			winner=null;
		}
		public void add()
		{
			count+=1;
		}
		public int getCount()
		{
			return count;
		}
		public void setWinner(String text)
		{
			winner=text;
		}
		public String getWinner()
		{
			return winner;
		}
		public void reset()
		{
			count=0;
			winner=null;
		}
	}
	cond flag_obj=new cond();
	Utility ut = new Utility();
	public void zero(int x,int y, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color, Button btn, Button btn2, Button undo, Stage stage)
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
		sbox[y][x].setSphereCount(sbox[y][x].getSphereCount()+1);
		sbox[y][x].setColor(color.toString());
		try 
		{
			ut.store_state(sbox);
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
	
	public void one(int x, int y, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color,Player_turn playerturn, Button btn, Button btn2,Button undo, Stage stage)
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
            if(sbox[y][x]==null)
            {
            	return;
            }
            sbox[y][x].setSphereCount(sbox[y][x].getSphereCount()-1);
            zero(x,y,box,grid,sbox,color,btn,btn2, undo, stage);
			
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
			sbox[y][x].setSphereCount(sbox[y][x].getSphereCount()+1);
			sbox[y][x].setColor(color.toString());
			try 
			{
				ut.store_state(sbox);
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
			splitmain(x,y,box,grid, sbox, color, playerturn,btn,btn2,undo,stage);
		}
	}
	
	public void two(int x, int y, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color,Player_turn playerturn, Button btn, Button btn2,Button undo, Stage stage)
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
			sbox[y][x].setSphereCount(sbox[y][x].getSphereCount()-1);
			zero(x,y,box,grid,sbox,color,btn,btn2,undo, stage);
			
			
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
			sbox[y][x].setSphereCount(sbox[y][x].getSphereCount()+1);
			sbox[y][x].setColor(color.toString());
			try 
			{
				ut.store_state(sbox);
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
			splitmain(x,y,box,grid,sbox,color,playerturn,btn,btn2,undo, stage);
		}
	}
	
	public void split(int x1, int y1,int x2, int y2,int p1, int q1, int p2,int q2, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color, Player_turn playerturn, Button btn, Button btn2, Button undo, int val,Stage stage)
	{
		grid.getChildren().remove(box[y1][x1].getSphere1());
		grid.getChildren().remove(box[y1][x1].getSphere2());
		grid.getChildren().remove(box[y1][x1].getSphere3());
		box[y1][x1].setempty();
		if(sbox[y1][x1]!=null){sbox[y1][x1].setEmpty();}
		try 
		{
			ut.store_state(sbox);
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
		transitionCircle.statusProperty().addListener(
        		(o,olds,news) -> {
        			if(news==Animation.Status.RUNNING)
        			{
        				grid.setDisable(true);
        			}
        			if(news==Animation.Status.STOPPED && global_flag==0)
        			{
        				grid.setDisable(false);
        			}
        			if(news==Animation.Status.STOPPED && global_flag==1)
        			{
        				grid.setDisable(true);
        			}
        			
        		}
        		);
				
        
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
        	//grid.setDisable(true);
        	
        	if(box[y2][x2] == null)
        	{
        		box[y2][x2] = new Box();
        	}
        	
        	if(box[y2][x2].getCount() == 0 && sbox[y2][x2]!=null)
        	{
        		zero(x2,y2,box,grid,sbox,color,btn,btn2,undo, stage);
        	}
        	else if(box[y2][x2].getCount() == 1 && sbox[y2][x2]!=null)
        	{       
        		one(x2,y2,box,grid,sbox,color,playerturn,btn,btn2,undo,stage);       		
        	}
        	else if(box[y2][x2].getCount() == 2 && sbox[y2][x2]!=null)
            {        	
        		two(x2,y2,box,grid,sbox,color,playerturn,btn,btn2,undo,stage);      		
            }
        	else if(box[y2][x2].getCount() == 3 && sbox[y2][x2]!=null)
        	{
        		splitmain(x2,y2,box,grid,sbox,color,playerturn,btn,btn2,undo,stage);
        	}
        	//currentStatus(sbox);
        	//System.out.println();
        	Individual_Setting is = new Individual_Setting();
            Path currentRelativePath = Paths.get("");
     		String s = currentRelativePath.toAbsolutePath().toString();
     		String location=s+"\\src\\application";
     		location=location+"\\savedSettings.txt";
     		
     		try {
				is.load(location);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
     		serializedSetting obj = null;
			try {
				obj = Individual_Setting.read(location);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     		String[] pcolor = new String[8];
     		pcolor[0] = obj.color1;
     		pcolor[1] = obj.color2;
     		pcolor[2] = obj.color3;
     		pcolor[3] = obj.color4;
     		pcolor[4] = obj.color5;
     		pcolor[5] = obj.color6;
     		pcolor[6] = obj.color7;
     		pcolor[7] = obj.color8;
     		if(val==1)
        	{playerturn.updatePlayer(sbox, 9, 6);
        	String col = pcolor[playerturn.getCur_turn()];
        	int tmp = 0;
        	for(Node a: grid.getChildren())
           	{
            	if(tmp<55)
            	{
            		a.setStyle("-fx-border-color: "+col);
            		tmp++;
            	}
            	else
            	{
            		break;
            	}
           	}
    		playerturn.isWinner(flag_obj);
    		if(flag_obj.getCount()==1)
    		{
    			//System.out.println("hey");
    			String w=flag_obj.getWinner();
    			w=w.substring(0, 1);
    			stage.setOnCloseRequest(e->e.consume());
    			final java.net.URL resource = getClass().getResource("winning.wav");
                final AudioClip clip = new AudioClip(resource.toString());
                clip.play();
    			ut.popup(Integer.parseInt(w),btn,btn2,sbox,undo,grid,transitionCircle);
    			global_flag=1;
    			/*Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information Dialog");
    			alert.setHeaderText("Look, an Information Dialog");
    			alert.setContentText(flag_obj.getWinner());
    			alert.show();*/
    		}
    		playerturn.check_increment();
        	}
    		//grid.setDisable(false);
		});
        
	}
	
	public void splitmain(int x, int y, Box box[][], GridPane grid,Block_serialize[][] sbox, Color color, Player_turn playerturn, Button btn, Button btn2,Button undo, Stage stage)
	{
		if(x+1<=5)
		{
			if(sbox[y][x+1]!=null){split(x,y,x+1,y,0,0,50,0,box,grid,sbox,color,playerturn,btn,btn2,undo,0,stage);}
		}
		if(x-1>=0)
		{
			if(sbox[y][x-1]!=null){split(x,y,x-1,y,0,0,-50,0,box,grid,sbox,color,playerturn,btn,btn2,undo,0,stage);}
		}
		if(y+1<=8)
		{
			if(sbox[y+1][x]!=null){split(x,y,x,y+1,0,0,0,50,box,grid,sbox,color,playerturn,btn,btn2,undo,1,stage);}
		}
		if(y-1>=0)
		{
			if(sbox[y-1][x]!=null){split(x,y,x,y-1,0,0,0,-50,box,grid,sbox,color,playerturn,btn,btn2,undo,1,stage);}
		}
	}
	
	public void start(Block_serialize[][] sbox, int totnum, Player_turn playerturn, int res)  throws FileNotFoundException, ClassNotFoundException, IOException
	{
		global_flag=0;
		Stage stage=new Stage();
        stage.setTitle("Game");
               
        if(res==0)
        {
        	ut.combineSettings();
        }
        Individual_Setting is = new Individual_Setting();
        Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";

		location=location+"\\savedSettings.txt";
		is.load(location);
		serializedSetting obj=Individual_Setting.read(location);
		String[] pcolor = new String[8];
		pcolor[0] = obj.color1;
		pcolor[1] = obj.color2;
		pcolor[2] = obj.color3;
		pcolor[3] = obj.color4;
		pcolor[4] = obj.color5;
		pcolor[5] = obj.color6;
		pcolor[6] = obj.color7;
		pcolor[7] = obj.color8;
        
		
		
		MenuItem newGame=new MenuItem("New Game");
		MenuItem exit=new MenuItem("Exit");
		FileInputStream input = new FileInputStream(Paths.get("").toAbsolutePath().toString()+"\\src\\application\\dropdown.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(19);
        imageView.setFitHeight(17);
        MenuButton menuButton = new MenuButton("", imageView, newGame, exit);


        
        
        Button btnUndo = new Button("Undo");
        Button btnNewGame = new Button("New Game");
        Button btnExit = new Button("Exit");
       // Button btnSetting = new Button("Setting");

        btnUndo.setStyle("-fx-font-size: 10pt;");
        btnUndo.setMinSize(80, 20);
        btnUndo.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1px;-fx-background-color: #202020;-fx-text-fill: #ffffff");
        menuButton.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1px;-fx-background-color: #202020;-fx-text-fill: #ffffff");
        //btnSetting.setStyle("-fx-font-size: 10pt;");
        //btnSetting.setMinSize(80, 20);
        //btnSetting.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1px;-fx-background-color: #202020;-fx-text-fill: #ffffff");
		btnUndo.setDisable(true);
        
        HBox hbButtons=new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnUndo,menuButton);
        
        Box box[][] = new Box[9][6];
        Box prev[][] = new Box[9][6];
        
        
        Block_serialize[][] prev_sbox=new Block_serialize[9][6];
        
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

       	for(int i=0;i<9;i++)
       	{
       		for(int j=0;j<6;j++)
       		{
       			grid.add(new Pane(), j, i);
       		}
       	}
        
       	if(res==1)
       	{
        	for(int i=0;i<9;i++)
        	{
        		for(int j=0;j<6;j++)
        		{
        			if(box[i][j] == null)
                	{
                		box[i][j] = new Box();
                	}
        			if(sbox[i][j].getSphereCount()>=1)
        			{
        				sbox[i][j].setSphereCount(sbox[i][j].getSphereCount()-1);
        				Color color = Color.valueOf(sbox[i][j].getColor());
        				zero(j,i,box,grid,sbox,color, btnNewGame, btnExit, btnUndo,stage);
        			}
        			if(sbox[i][j].getSphereCount()>=2)
        			{
        				sbox[i][j].setSphereCount(sbox[i][j].getSphereCount()-1);
        				Color color = Color.valueOf(sbox[i][j].getColor());
        				one(j,i,box,grid,sbox,color,playerturn,btnNewGame, btnExit, btnUndo,stage);
        			}
        			if(sbox[i][j].getSphereCount()>=3)
        			{
        				sbox[i][j].setSphereCount(sbox[i][j].getSphereCount()-1);
        				Color color = Color.valueOf(sbox[i][j].getColor());
        				two(j,i,box,grid,sbox,color,playerturn,btnNewGame,btnExit,btnUndo,stage);
        			}
        		}
        	}
       	}
        
        final Group root = new Group(grid,hbButtons);
        grid.setStyle("-fx-background-color: black; -fx-grid-lines-visible: true");
        grid.setTranslateX(100);
        grid.setTranslateY(100);
        Scene scene = new Scene(root, 530,600, Color.BLACK);
        
        ObservableList<Node> list = FXCollections.observableArrayList();
        list.addAll(grid.getChildren());

        String co = pcolor[playerturn.getCur_turn()];
        int temp = 0;
        for(Node a: grid.getChildren())
       	{
        	if(temp<55)
        	{
        		a.setStyle("-fx-border-color: "+co);
        		temp++;
        	}
        	else
        	{
        		break;
        	}
       	}
        
        grid.setOnMouseClicked(event ->
        {
        	final java.net.URL resource = getClass().getResource("Beep1.wav");
            final AudioClip clip = new AudioClip(resource.toString());
        	
        	btnUndo.setDisable(false);
        	list.remove(0, list.size());
        	list.addAll(grid.getChildren());
        	
        	for(int i=0;i<9;i++)
        	{
        		for(int j=0;j<6;j++)
        		{
        			prev[i][j] = new Box();
        			prev_sbox[i][j]=new Block_serialize();
        			if(box[i][j]!=null)
        			{
        				prev[i][j].copy(box[i][j]);
        			}
        			if(sbox[i][j]!=null)
        			{
        				prev_sbox[i][j].copy(sbox[i][j]);
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
                clip.play(0.5);
        		
        		zero(x,y,box,grid,sbox,color,btnNewGame, btnExit,btnUndo,stage);
        		playerturn.increment();
        		try {
					ut.set_playerturns(playerturn);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String c = pcolor[playerturn.getCur_turn()];
            	int tmp = 0;
            	for(Node a: grid.getChildren())
               	{
                	if(tmp<55)
                	{
                		a.setStyle("-fx-border-color: "+c);
                		tmp++;
                	}
                	else
                	{
                		break;
                	}
               	}
        		//currentStatus(sbox);
        	}
        	else if(box[y][x].getCount() == 1 && box[y][x].getColor().equals(color))
        	{  
        		clip.play(0.5);
        		
        		one(x,y,box,grid,sbox,color,playerturn,btnNewGame, btnExit,btnUndo,stage); 
        		playerturn.increment();
        		try {
					ut.set_playerturns(playerturn);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String c = pcolor[playerturn.getCur_turn()];
            	int tmp = 0;
            	for(Node a: grid.getChildren())
               	{
                	if(tmp<55)
                	{
                		a.setStyle("-fx-border-color: "+c);
                		tmp++;
                	}
                	else
                	{
                		break;
                	}
               	}
        		//currentStatus(sbox);
        	}
        	else if(box[y][x].getCount() == 2 && box[y][x].getColor().equals(color))
            { 
        		clip.play(0.5);
        		
        		two(x,y,box,grid,sbox,color,playerturn,btnNewGame,btnExit,btnUndo,stage);  
        		playerturn.increment();
        		try {
					ut.set_playerturns(playerturn);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		String c = pcolor[playerturn.getCur_turn()];
        		int tmp = 0;
				for(Node a: grid.getChildren())
               	{
                	if(tmp<55)
                	{
                		a.setStyle("-fx-border-color: "+c);
                		tmp++;
                	}
                	else
                	{
                		break;
                	}
               	}
        		//currentStatus(sbox);
            }
        	else if(box[y][x].getCount() == 3 && box[y][x].getColor().equals(color))
        	{
        		clip.play(0.5);
        		splitmain(x,y,box,grid,sbox,color,playerturn,btnNewGame,btnExit,btnUndo,stage);
        		playerturn.increment();
        		try {
					ut.set_playerturns(playerturn);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		//synchroniseState(box,sbox);
        		//currentStatus(sbox);
        	}
        	//System.out.println();
        });

        
        btnExit.setOnAction(event->
        {
        	stage.close();
        });

        newGame.setOnAction(event ->
        {
        	btnNewGame.fire();
        });
        
        exit.setOnAction(event ->
        {
        	btnExit.fire();
        });
        
        btnNewGame.setOnAction(event->
        {
        	stage.close();
        	for(int i=0;i<9;i++)
        	{
        		for(int j=0;j<6;j++)
        		{
        			sbox[i][j].reset();
        		}
        	}
        	try {
        		playerturn.reset(totnum);
        		playerturn.setCur_turn(0);
        		flag_obj.reset();
				this.start(sbox, totnum, playerturn,0);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        });
                
        btnUndo.setOnAction(event->
        {
        	btnUndo.setDisable(true);
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
        				sbox[i][j].copy(prev_sbox[i][j]);
        			}
        		}
        	}
        	playerturn.decrement();
        	try {
				ut.set_playerturns(playerturn);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	try {
				ut.store_state(sbox);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String c = pcolor[playerturn.getCur_turn()];
        	int tmp = 0;
        	for(Node a: grid.getChildren())
           	{
            	if(tmp<55)
            	{
            		a.setStyle("-fx-border-color: "+c);
            		tmp++;
            	}
            	else
            	{
            		break;
            	}
           	}
        });
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }
	public void run(Block_serialize[][] sbox, int totnum, Player_turn playerturn,int res) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		this.start(sbox,totnum, playerturn,res);
	}

	public static void main(String[] args) 
	{
    }
}