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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class Individual_Setting{
	
	public void start(String color, int num) {
        Stage primaryStage=new Stage();
		primaryStage.setTitle("Chain Reaction Preferences");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("color_picker_2.png")));
        Scene scene = new Scene(new HBox(20), 298, 387);
        HBox box = (HBox) scene.getRoot();
        box.setPadding(new Insets(5, 5, 5, 5));
        ComboBox<String> list=new ComboBox<String>();
        list.getItems().addAll(
        		"Red","Blue","Green","White","Cyan","Yellow","Pink","Orange");
        StackPane stack=new StackPane();
        		
        final SVGPath svg = new SVGPath();
        svg.setContent("M-50,0a50,50 0 1,0 100,0a50,50 0 1,0 -100,0");
        svg.setStroke(Color.DARKGREY);
        svg.setStrokeWidth(2);
        svg.setEffect(new DropShadow());
        list.getSelectionModel().select(color);
        svg.setFill(Paint.valueOf(color));
        stack.getChildren().addAll(svg);
        list.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				svg.setFill(Paint.valueOf(list.getValue()));
				change(list.getValue(),num);
			}
        	
        });
        box.getChildren().addAll(stack,list);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	public void run(int num) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		File folder = new File(location);
		File[] listOfFiles = folder.listFiles();
		location=location+"\\savedSettings.txt";
		load(location);
		String color="";
		serializedSetting obj=read(location);
		if(num==1)
		{
			color=obj.getColor1();
		}
		else if(num==2)
		{
			color=obj.getColor2();
		}
		else if(num==3)
		{
			color=obj.getColor3();
		}
		else if(num==4)
		{
			color=obj.getColor4();
		}
		else if(num==5)
		{
			color=obj.getColor5();
		}
		else if(num==6)
		{
			color=obj.getColor6();
		}
		else if(num==7)
		{
			color=obj.getColor7();
		}
		else if(num==8)
		{
			color=obj.getColor8();
		}
		this.start(color, num);
	}
	public void add(String location, serializedSetting obj) throws FileNotFoundException, IOException
	{
		ObjectOutputStream out=null;
		out=new ObjectOutputStream(new FileOutputStream(location));
		//System.out.println("added");
		out.writeObject(obj);
		out.close();
	}
	public void load(String location) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		ObjectInputStream input=null;
		try
		{
			input=new ObjectInputStream(new FileInputStream(location));
			
		}
		catch(EOFException e)
		{
			add(location, new serializedSetting());
			//System.out.println("added");
			return;
		}
		input.close();
	}
	static serializedSetting read(String location) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		ObjectInputStream input=null;
		serializedSetting obj=null;
		try
		{
			input=new ObjectInputStream(new FileInputStream(location));
			obj= (serializedSetting)input.readObject();
		}
		catch(EOFException e)
		{
			
		}
		input.close();
		return obj;
	}
	public void change(String color, int num)
	{
		Path currentRelativePath = Paths.get("");
		
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		File folder = new File(location);
		File[] listOfFiles = folder.listFiles();
		location=location+"\\savedSettings.txt";
		try 
		{
			serializedSetting obj=read(location);
			if(num==1)
			{
				obj.setColor1(color);
				//System.out.println("at one");
			}
			else if(num==2)
			{
				obj.setColor2(color);
			}
			else if(num==3)
			{
				obj.setColor3(color);
			}
			else if(num==4)
			{
				obj.setColor4(color);
			}
			else if(num==5)
			{
				obj.setColor5(color);
			}
			else if(num==6)
			{
				obj.setColor6(color);
			}
			else if(num==7)
			{
				obj.setColor7(color);
			}
			else if(num==8)
			{
				obj.setColor8(color);
			}
			//System.out.println("changed");
			add(location,obj);
		} 
		catch (ClassNotFoundException | IOException e) 
		{
			
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
        
    }
}