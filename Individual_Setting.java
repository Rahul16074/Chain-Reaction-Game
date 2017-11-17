package application;


import java.io.EOFException;
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

/**
 * This program is used to set, change the colors of players
 * @author Rahul Lawaria
 * @author Vipul Saini
 */
public class Individual_Setting{
	/**
	 * This method initializes color for the specified player and gives a list to choose color from it
	 * @param color First parameter is the color of the player
	 * @param num Second parameter is the player number
	 */
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

	/**
	 * This method initializes the color of he player corresponding to the parameter passed
	 * @param num First parameter is the player number
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void run(int num) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		location=location+"\\tempSetting.txt";
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
	/**
	 * This method writes the current state of players color in a text file
	 * @param location First parameter is the location where the text file exists
	 * @param obj Second parameter is the object that is to be serialized
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	static void add(String location, serializedSetting obj) throws FileNotFoundException, IOException
	{
		ObjectOutputStream out=null;
		out=new ObjectOutputStream(new FileOutputStream(location));
		//System.out.println("added");
		out.writeObject(obj);
		out.close();
	}
	/**
	 * This method initializes an empty file with default color of players
	 * @param location First parameter is the location of the text file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
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
	/*
	 * This method gives the current player setting that stores the color of players
	 * @param location First parameter is the location of file where setting is serialized 
	 */
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
	/**
	 * this function changes/swaps the color of one player with color of another player 
	 * @param color First parameter is the color of type Color
	 * @param num Second parameter is the player whose color is changed
	 */
	public void change(String color, int num)
	{
		Path currentRelativePath = Paths.get("");
		
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		location=location+"\\tempSetting.txt";
		try 
		{
			serializedSetting obj=read(location);
			if(num==1)
			{

				String replace=obj.getColor1();
				obj.setColor1(color);
				if(obj.getColor2().equals(color))
				{
					obj.setColor2(replace);
				}
				else if(obj.getColor3().equals(color))
				{
					obj.setColor3(replace);
				}
				else if(obj.getColor4().equals(color))
				{
					obj.setColor4(replace);
				}
				else if(obj.getColor5().equals(color))
				{
					obj.setColor5(replace);
				}
				else if(obj.getColor6().equals(color))
				{
					obj.setColor6(replace);
				}
				else if(obj.getColor7().equals(color))
				{
					obj.setColor7(replace);
				}
				else if(obj.getColor8().equals(color))
				{
					obj.setColor8(replace);
				}
				//System.out.println("at one");
			}
			else if(num==2)
			{

				String replace=obj.getColor2();
				obj.setColor2(color);
				if(obj.getColor1().equals(color))
				{
					obj.setColor1(replace);
				}
				else if(obj.getColor3().equals(color))
				{
					obj.setColor3(replace);
				}
				else if(obj.getColor4().equals(color))
				{
					obj.setColor4(replace);
				}
				else if(obj.getColor5().equals(color))
				{
					obj.setColor5(replace);
				}
				else if(obj.getColor6().equals(color))
				{
					obj.setColor6(replace);
				}
				else if(obj.getColor7().equals(color))
				{
					obj.setColor7(replace);
				}
				else if(obj.getColor8().equals(color))
				{
					obj.setColor8(replace);
				}
			}
			else if(num==3)
			{

				String replace=obj.getColor3();
				obj.setColor3(color);
				if(obj.getColor2().equals(color))
				{
					obj.setColor2(replace);
				}
				else if(obj.getColor1().equals(color))
				{
					obj.setColor1(replace);
				}
				else if(obj.getColor4().equals(color))
				{
					obj.setColor4(replace);
				}
				else if(obj.getColor5().equals(color))
				{
					obj.setColor5(replace);
				}
				else if(obj.getColor6().equals(color))
				{
					obj.setColor6(replace);
				}
				else if(obj.getColor7().equals(color))
				{
					obj.setColor7(replace);
				}
				else if(obj.getColor8().equals(color))
				{
					obj.setColor8(replace);
				}
			}
			else if(num==4)
			{
				String replace=obj.getColor4();
				obj.setColor4(color);
				if(obj.getColor2().equals(color))
				{
					obj.setColor2(replace);
				}
				else if(obj.getColor3().equals(color))
				{
					obj.setColor3(replace);
				}
				else if(obj.getColor1().equals(color))
				{
					obj.setColor1(replace);
				}
				else if(obj.getColor5().equals(color))
				{
					obj.setColor5(replace);
				}
				else if(obj.getColor6().equals(color))
				{
					obj.setColor6(replace);
				}
				else if(obj.getColor7().equals(color))
				{
					obj.setColor7(replace);
				}
				else if(obj.getColor8().equals(color))
				{
					obj.setColor8(replace);
				}
			}
			else if(num==5)
			{

				String replace=obj.getColor5();
				obj.setColor5(color);
				if(obj.getColor2().equals(color))
				{
					obj.setColor2(replace);
				}
				else if(obj.getColor3().equals(color))
				{
					obj.setColor3(replace);
				}
				else if(obj.getColor4().equals(color))
				{
					obj.setColor4(replace);
				}
				else if(obj.getColor1().equals(color))
				{
					obj.setColor1(replace);
				}
				else if(obj.getColor6().equals(color))
				{
					obj.setColor6(replace);
				}
				else if(obj.getColor7().equals(color))
				{
					obj.setColor7(replace);
				}
				else if(obj.getColor8().equals(color))
				{
					obj.setColor8(replace);
				}
			}
			else if(num==6)
			{

				String replace=obj.getColor6();
				obj.setColor6(color);
				if(obj.getColor2().equals(color))
				{
					obj.setColor2(replace);
				}
				else if(obj.getColor3().equals(color))
				{
					obj.setColor3(replace);
				}
				else if(obj.getColor4().equals(color))
				{
					obj.setColor4(replace);
				}
				else if(obj.getColor5().equals(color))
				{
					obj.setColor5(replace);
				}
				else if(obj.getColor1().equals(color))
				{
					obj.setColor1(replace);
				}
				else if(obj.getColor7().equals(color))
				{
					obj.setColor7(replace);
				}
				else if(obj.getColor8().equals(color))
				{
					obj.setColor8(replace);
				}
			}
			else if(num==7)
			{

				String replace=obj.getColor7();
				obj.setColor7(color);
				if(obj.getColor2().equals(color))
				{
					obj.setColor2(replace);
				}
				else if(obj.getColor3().equals(color))
				{
					obj.setColor3(replace);
				}
				else if(obj.getColor4().equals(color))
				{
					obj.setColor4(replace);
				}
				else if(obj.getColor5().equals(color))
				{
					obj.setColor5(replace);
				}
				else if(obj.getColor6().equals(color))
				{
					obj.setColor6(replace);
				}
				else if(obj.getColor1().equals(color))
				{
					obj.setColor1(replace);
				}
				else if(obj.getColor8().equals(color))
				{
					obj.setColor8(replace);
				}
			}
			else if(num==8)
			{

				String replace=obj.getColor8();
				obj.setColor8(color);
				if(obj.getColor2().equals(color))
				{
					obj.setColor2(replace);
				}
				else if(obj.getColor3().equals(color))
				{
					obj.setColor3(replace);
				}
				else if(obj.getColor4().equals(color))
				{
					obj.setColor4(replace);
				}
				else if(obj.getColor5().equals(color))
				{
					obj.setColor5(replace);
				}
				else if(obj.getColor6().equals(color))
				{
					obj.setColor6(replace);
				}
				else if(obj.getColor7().equals(color))
				{
					obj.setColor7(replace);
				}
				else if(obj.getColor1().equals(color))
				{
					obj.setColor1(replace);
				}
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