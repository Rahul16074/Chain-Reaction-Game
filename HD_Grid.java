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
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class HD_Grid
{
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
	public void zero(int x,int y, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color, Button btn, Button btn2, Button undo, Button setting)
	{
		Sphere sphere = new Sphere(7);
    	PhongMaterial material = new PhongMaterial();
    	material.setDiffuseColor(color);
        sphere.setMaterial(material);
        sphere.setEffect(new Lighting());
		
		grid.add(sphere, x, y);
		if((x==0 && y==0) ||  (x==0 && y==14) || (x==9 && y==0) || (x==9 && y==14))
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
	
	public void one(int x, int y, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color,Player_turn playerturn, Button btn, Button btn2,Button undo, Button setting)
	{
		if((x!=0 || y!=0) &&  (x!=0 || y!=14) && (x!=9 || y!=0) && (x!=9 || y!=14))
		{
			Sphere sphere = new Sphere(7);
        	PhongMaterial material = new PhongMaterial();
        	material.setDiffuseColor(color);
            sphere.setMaterial(material);
            sphere.setEffect(new Lighting());
            
            grid.getChildren().remove(box[y][x].getSphere1());
            box[y][x].setCount(box[y][x].getCount() - 1);
            zero(x,y,box,grid,sbox,color,btn,btn2, undo, setting);
			
			Circle circle = new Circle(10,Color.TRANSPARENT);
			circle.setCenterX(0);
			circle.setCenterY(0);
			circle.setRadius(7);
		
			PathTransition transitionCircle = new PathTransition();
			transitionCircle.setPath(circle);
			transitionCircle.setNode(sphere);
			transitionCircle.setInterpolator(Interpolator.LINEAR);      			
			if(x==0 || x==9 || y==0 || y==14)
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
			splitmain(x,y,box,grid, sbox, color, playerturn,btn,btn2,undo, setting);
		}
	}
	
	public void two(int x, int y, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color,Player_turn playerturn, Button btn, Button btn2,Button undo, Button setting)
	{
		if(x!=0 && x!=9 && y!=0 && y!=14)
		{
			Sphere sphere = new Sphere(7);
        	PhongMaterial material = new PhongMaterial();
        	material.setDiffuseColor(color);
            sphere.setMaterial(material);
            sphere.setEffect(new Lighting());
    		
    		Sphere sphere2 = new Sphere(7);
    		material.setDiffuseColor(color);
            sphere2.setMaterial(material);
            sphere2.setEffect(new Lighting());

            
			Circle circle = new Circle(10,Color.TRANSPARENT);
			circle.setCenterX(0);
			circle.setCenterY(0);
			circle.setRadius(7);
			
			grid.getChildren().remove(box[y][x].getSphere2());
			grid.getChildren().remove(box[y][x].getSphere1());
			box[y][x].setCount(box[y][x].getCount() - 1);
			zero(x,y,box,grid,sbox,color,btn,btn2,undo, setting);
			
			
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
			sbox[y][x].setSphereCount(box[y][x].getCount()+1);
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
			splitmain(x,y,box,grid,sbox,color,playerturn,btn,btn2,undo, setting);
		}
	}
	public void split(int x1, int y1,int x2, int y2,int p1, int q1, int p2,int q2, Box box[][], GridPane grid, Block_serialize[][] sbox, Color color, Player_turn playerturn, Button btn, Button btn2, Button undo, Button setting)
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
		
		Sphere sphere1 = new Sphere(7);
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
        		zero(x2,y2,box,grid,sbox,color,btn,btn2,undo, setting);
        	}
        	else if(box[y2][x2].getCount() == 1)
        	{       
        		one(x2,y2,box,grid,sbox,color,playerturn,btn,btn2,undo, setting);       		
        	}
        	else if(box[y2][x2].getCount() == 2)
            {        	
        		two(x2,y2,box,grid,sbox,color,playerturn,btn,btn2,undo, setting);      		
            }
        	else if(box[y2][x2].getCount() == 3)
        	{
        		splitmain(x2,y2,box,grid,sbox,color,playerturn,btn,btn2,undo, setting);
        	}
        	playerturn.updatePlayer(sbox, 15, 10);
    		playerturn.isWinnerHD(flag_obj);
    		if(flag_obj.getCount()==1)
    		{
    			//System.out.println("hey");
    			String w=flag_obj.getWinner();
    			w=w.substring(0, 1);
    			popup(Integer.parseInt(w),btn,btn2,sbox,undo, setting,grid);
    			
    			/*Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information Dialog");
    			alert.setHeaderText("Look, an Information Dialog");
    			alert.setContentText(flag_obj.getWinner());
    			alert.show();*/
    		}
    		playerturn.check_increment();
    		//grid.setDisable(false);
		});
        
        
	}
	
	public void popup(int winner,Button btn, Button btn2,Block_serialize[][] sbox, Button undo, Button setting, GridPane grid) {
        final Stage dialog = new Stage();
        undo.setDisable(true);
        setting.setDisable(true);
        grid.setDisable(true);
        dialog.setTitle("Game Over");
        Button new_game = new Button("New Game");
        Button exit = new Button("Exit");

        Label displayLabel = new Label("Player "+winner+" is the winner");
        displayLabel.setFont(Font.font(null, FontWeight.BOLD, 14));

        dialog.initModality(Modality.NONE);
       // dialog.initOwner((Stage) tableview.getScene().getWindow());

        HBox dialogHbox = new HBox(20);
        dialogHbox.setAlignment(Pos.CENTER);

        VBox dialogVbox1 = new VBox(20);
        dialogVbox1.setAlignment(Pos.CENTER_LEFT);

        VBox dialogVbox2 = new VBox(20);
        dialogVbox2.setAlignment(Pos.CENTER_RIGHT);

        dialogHbox.getChildren().add(displayLabel);
        dialogVbox1.getChildren().add(new_game);
        dialogVbox2.getChildren().add(exit);
        btn.setDisable(true);
        btn2.setDisable(true);

        new_game.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                    	btn.setDisable(false);
                    	btn.fire();
                    	for(int i=0;i<15;i++)
                    	{
                    		for(int j=0;j<10;j++)
                    		{
                    			sbox[i][j].reset();
                    		}
                    	}
                    	try {
							store_state(sbox);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        dialog.close();
                    }
                });
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                    	btn2.setDisable(false);
                    	btn2.fire();
                    	for(int i=0;i<15;i++)
                    	{
                    		for(int j=0;j<10;j++)
                    		{
                    			sbox[i][j].reset();
                    		}
                    	}
                    	try {
							store_state(sbox);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        dialog.close();
                    }
                });

        dialogHbox.getChildren().addAll(dialogVbox1, dialogVbox2);
        Scene dialogScene = new Scene(dialogHbox, 500, 40);
        //dialogScene.getStylesheets().add("//style sheet of your choice");
        dialog.setScene(dialogScene);
        dialog.show();
    }
	
	public void splitmain(int x, int y, Box box[][], GridPane grid,Block_serialize[][] sbox, Color color, Player_turn playerturn, Button btn, Button btn2,Button undo, Button setting)
	{
		if(x+1<=9)
		{
			split(x,y,x+1,y,0,0,35,0,box,grid,sbox,color,playerturn,btn,btn2,undo, setting);
		}
		if(x-1>=0)
		{
			split(x,y,x-1,y,0,0,-35,0,box,grid,sbox,color,playerturn,btn,btn2,undo, setting);
		}
		if(y+1<=14)
		{
			split(x,y,x,y+1,0,0,0,35,box,grid,sbox,color,playerturn,btn,btn2,undo, setting);
		}
		if(y-1>=0)
		{
			split(x,y,x,y-1,0,0,0,-35,box,grid,sbox,color,playerturn,btn,btn2,undo, setting);
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
	
	
	public void start(Block_serialize[][] sbox, int totnum, Player_turn playerturn, int res)  throws FileNotFoundException, ClassNotFoundException, IOException
	{	
		Stage primaryStage=new Stage();
        primaryStage.setTitle("Game");
               
        menu m = new menu();
        Individual_Setting is = new Individual_Setting();
        Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		File folder = new File(location);
		//File[] listOfFiles = folder.listFiles();
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
		btnUndo.setDisable(true);
        
        HBox hbButtons=new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnUndo,btnSetting,btnNewGame,btnExit);
        
        Box box[][] = new Box[15][10];
        Box prev[][] = new Box[15][10];
        
        
        Block_serialize[][] prev_sbox=new Block_serialize[15][10];
        
        GridPane grid = new GridPane();
   
        for(int i = 0; i < 10; i++)
       	{
       		ColumnConstraints column = new ColumnConstraints(35);
       		grid.getColumnConstraints().add(column);
       	}
       	for(int i = 0; i < 15; i++) 
       	{
       		RowConstraints row = new RowConstraints(35);
       		grid.getRowConstraints().add(row);
       	}
        
       	if(res==1)
       	{
        	for(int i=0;i<15;i++)
        	{
        		for(int j=0;j<10;j++)
        		{
        			if(box[i][j] == null)
                	{
                		box[i][j] = new Box();
                	}
        			if(sbox[i][j].getSphereCount()>=1)
        			{
        				sbox[i][j].setSphereCount(sbox[i][j].getSphereCount()-1);
        				Color color = Color.valueOf(sbox[i][j].getColor());
        				zero(j,i,box,grid,sbox,color, btnNewGame, btnExit, btnUndo, btnSetting);
        			}
        			if(sbox[i][j].getSphereCount()>=2)
        			{
        				sbox[i][j].setSphereCount(sbox[i][j].getSphereCount()-1);
        				Color color = Color.valueOf(sbox[i][j].getColor());
        				one(j,i,box,grid,sbox,color,playerturn,btnNewGame, btnExit, btnUndo, btnSetting);
        			}
        			if(sbox[i][j].getSphereCount()>=3)
        			{
        				sbox[i][j].setSphereCount(sbox[i][j].getSphereCount()-1);
        				Color color = Color.valueOf(sbox[i][j].getColor());
        				two(j,i,box,grid,sbox,color,playerturn,btnNewGame,btnExit,btnUndo,btnSetting);
        			}
        		}
        	}
       	}
        
        final Group root = new Group(grid,hbButtons);
        grid.setStyle("-fx-background-color: black; -fx-grid-lines-visible: true");
        grid.setTranslateX(100);
        grid.setTranslateY(100);
        Scene scene = new Scene(root, 550,800, Color.BLACK);
        
        ObservableList<Node> list = FXCollections.observableArrayList();
        list.addAll(grid.getChildren());
        
        grid.setOnMouseClicked(event ->
        {
        	btnUndo.setDisable(false);
        	list.remove(0, list.size());
        	list.addAll(grid.getChildren());
        	
        	for(int i=0;i<15;i++)
        	{
        		for(int j=0;j<10;j++)
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
        	int x = (int)(event.getSceneX()-100)/35;
        	int y = (int)(event.getSceneY()-100)/35;
        	if(box[y][x] == null)
        	{
        		box[y][x] = new Box();
        	}
        	
        	if(box[y][x].getCount() == 0)
        	{
        		zero(x,y,box,grid,sbox,color,btnNewGame, btnExit,btnUndo,btnSetting);
        		playerturn.increment();
        		try {
					menu.set_playerturns(playerturn);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		//currentStatus(sbox);
        	}
        	else if(box[y][x].getCount() == 1 && box[y][x].getColor().equals(color))
        	{       
        		one(x,y,box,grid,sbox,color,playerturn,btnNewGame, btnExit,btnUndo,btnSetting); 
        		playerturn.increment();
        		try {
					menu.set_playerturns(playerturn);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		//currentStatus(sbox);
        	}
        	else if(box[y][x].getCount() == 2 && box[y][x].getColor().equals(color))
            {        	
        		two(x,y,box,grid,sbox,color,playerturn,btnNewGame,btnExit,btnUndo,btnSetting);  
        		playerturn.increment();
        		try {
					menu.set_playerturns(playerturn);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		//currentStatus(sbox);
            }
        	else if(box[y][x].getCount() == 3 && box[y][x].getColor().equals(color))
        	{
        		splitmain(x,y,box,grid,sbox,color,playerturn,btnNewGame,btnExit,btnUndo,btnSetting);
        		playerturn.increment();
        		try {
					menu.set_playerturns(playerturn);
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
        	primaryStage.close();
        });

        btnNewGame.setOnAction(event->
        {
        	primaryStage.close();
        	for(int i=0;i<15;i++)
        	{
        		for(int j=0;j<10;j++)
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
        
        btnSetting.setOnAction(event->
        {
        	Player_Setting p=new Player_Setting();
        	p.run();
        });
        
        btnUndo.setOnAction(event->
        {
        	grid.getChildren().remove(0,grid.getChildren().size());
        	grid.getChildren().setAll(list);
        	for(int i=0;i<15;i++)
        	{
        		for(int j=0;j<10;j++)
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
				menu.set_playerturns(playerturn);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	try {
				store_state(sbox);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	public void run(Block_serialize[][] sbox, int totnum, Player_turn playerturn, int res) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		this.start(sbox,totnum, playerturn,res);
	}

	public void currentStatus(Block_serialize[][] sbox)
	{
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<10;j++)
			{
				System.out.print(sbox[i][j].getColor()+" "+sbox[i][j].getSphereCount()+" ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) 
	{
    }
}