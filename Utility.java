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
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * This class provides methods that are used by all the other classes
 * @author Rahul Lawaria
 * @author Vipul Saini
 *
 */
public class Utility {
	/**
	 * This method displays the winner on the screen, locking the state of the game
	 * @param winner The first parameter takes the integer input corresponding to the player who won the game
	 * @param btn The second parameter is the button to start a new game
	 * @param btn2 The third parameter is the button to exit the game
	 * @param sbox The fourth parameter is the current serialized state of the grid
	 * @param undo The sixth parameter is the undo button
	 * @param grid The seventh parameer is the current grid
	 */
	public void popup(int winner,Button btn, Button btn2,Block_serialize[][] sbox, Button undo, GridPane grid) 
	{
        final Stage dialog = new Stage();
        undo.setDisable(true);
        grid.setDisable(true);
        dialog.setTitle("Game Over");
        Button new_game = new Button("New Game");
        new_game.setTranslateX(150);
        new_game.setTranslateY(300);
        Button exit = new Button("Exit");
        exit.setTranslateX(300);
        exit.setTranslateY(300);

        Label label = new Label("Player "+winner+" is the winner");
        label.setFont(Font.font(null, FontWeight.BOLD, 20));
        label.setTranslateX(140);
        label.setTranslateY(250);
        
        ImageView im = new ImageView();
        Image image = new Image(getClass().getResourceAsStream("welldone.jpg"));
        im.setImage(image);
        im.setFitWidth(200);
        im.setPreserveRatio(true);
        im.setSmooth(true);
        im.setCache(true);
        im.setTranslateX(150);
        im.setTranslateY(10);
        
        dialog.initModality(Modality.NONE);
        
        Group root = new Group();
        root.getChildren().addAll(label,new_game,exit,im);
        
        /*HBox dialogHbox = new HBox(20);
        dialogHbox.setAlignment(Pos.BOTTOM_CENTER);

        VBox dialogVbox1 = new VBox(20);
        dialogVbox1.setAlignment(Pos.BOTTOM_LEFT);

        VBox dialogVbox2 = new VBox(20);
        dialogVbox2.setAlignment(Pos.BOTTOM_RIGHT);

        dialogHbox.getChildren().add(displayLabel);
        dialogVbox1.getChildren().add(new_game);
        dialogVbox2.getChildren().add(exit);*/
        btn.setDisable(true);
        btn2.setDisable(true);

        new_game.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                    	btn.setDisable(false);
                    	btn.fire();
                    	for(int i=0;i<sbox.length;i++)
                    	{
                    		for(int j=0;j<sbox[0].length;j++)
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
                    	for(int i=0;i<sbox.length;i++)
                    	{
                    		for(int j=0;j<sbox[0].length;j++)
                    		{
                    			sbox[i][j].reset();
                    			sbox[i][j]=null;
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

        //dialogHbox.getChildren().addAll(dialogVbox1, dialogVbox2);
        Scene dialogScene = new Scene(root, 500, 400);
        //dialogScene.getStylesheets().add("//style sheet of your choice");
        dialog.setScene(dialogScene);
        dialog.resizableProperty().setValue(Boolean.FALSE);
        dialog.setOnCloseRequest(e->e.consume());
        dialog.show();
    }
	
	/**
	 * This method stores the current state of the grid in the form of serializable class 
	 * @param sbox The first parameter is the object of Serializable class Block_serialize which is stored in a file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
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
	
	/**
	 * This method gives the current state of the block by deserializing it from the file Block_state 
	 * @return Returns object of type Block_serialize[][] 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
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
		
	/**
	 * This method returns the number of players supposed to play the game from the file playerCount.txt
	 * @return Returns integer denoting the number of players who have to play the game
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int load() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		initializePlayers();
		int num=0;
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		location=location+"\\playerCount.txt";
		ObjectInputStream input=null;
		try
		{
			input=new ObjectInputStream(new FileInputStream(location));
			NoPlayers obj=(NoPlayers)input.readObject();
			//System.out.println("players read");
			num=obj.getPlayers();
		}
		catch(EOFException e)
		{
			
		}
		input.close();
		return num;
	}
	
	/**
	 * This method initializes the number of players to 2, when the give is run for the first time
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void initializePlayers() throws FileNotFoundException, IOException
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		location=location+"\\playerCount.txt";
		ObjectInputStream input=null;
		try
		{
			input=new ObjectInputStream(new FileInputStream(location));	
		}
		catch(EOFException e)
		{
			ObjectOutputStream out=null;
			out=new ObjectOutputStream(new FileOutputStream(location));
			out.writeObject(new NoPlayers());
			out.close();
			return;
		}
		finally
		{
			input.close();
		}
	}
	
	/**
	 * This method sets the number of players who wish to play the next game and serialize it in a file playerCount.txt 
	 * @param s The first parameter is the String denoting the number of players willing to play the game
	 * @throws IOException
	 */
	public void setPlayerCount(String s) throws IOException
	{
		int num=2;
		if(s.equals("2"))
		{
			num=2;
		}
		else if(s.equals("3"))
		{
			num=3;
		}
		else if(s.equals("4"))
		{
			num=4;
		}
		else if(s.equals("5"))
		{
			num=5;
		}
		else if(s.equals("6"))
		{
			num=6;
		}
		else if(s.equals("7"))
		{
			num=7;
		}
		else if(s.equals("8"))
		{
			num=8;
		}
		Path currentRelativePath = Paths.get("");
		s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		location=location+"\\playerCount.txt";
		ObjectOutputStream out=null;
		out=new ObjectOutputStream(new FileOutputStream(location));
		NoPlayers obj=new NoPlayers();
		obj.setPlayers(num);
		out.writeObject(obj);
		out.close();
		return;
	}
	
	/**
	 * 
	 * @param sbox
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void storeGridState(Block_serialize[][] sbox) throws FileNotFoundException, IOException
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
	
	/**
	 * This function stores the current state of players including the current turn, total players in a file Player_turn.txt
	 * @param obj First parameter is the object of type Player_turn that is serialized in the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void set_playerturns(Player_turn obj) throws FileNotFoundException, IOException
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		location=location+"\\Player_turn.txt";
		ObjectOutputStream out=null;
		out=new ObjectOutputStream(new FileOutputStream(location));
		out.writeObject(obj);
		out.close();
	}
	
	/**
	 * This method returns the player turns that is stored in Player_turn.txt 
	 * @return Returns object of type Player_turn that has current status of the player turns
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
	public Player_turn get_playerturns() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		location=location+"\\Player_turn.txt";
		ObjectInputStream input=null;
		Player_turn obj=null;
		try
		{
			input=new ObjectInputStream(new FileInputStream(location));
			obj= (Player_turn)input.readObject();	
		}
		catch(EOFException e)
		{
			
			//System.out.println("added");
			
		}
		input.close();
		return obj;
	}
	
	/**
	 * This method initializes the current settings with the changed settings and serializes it to savedSettings.txt 
	 */
	public void combineSettings()
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location1=s+"\\src\\application";
		//File folder = new File(location1);
		//File[] listOfFiles = folder.listFiles();
		location1=location1+"\\savedSettings.txt";
		
		currentRelativePath = Paths.get("");
		s = currentRelativePath.toAbsolutePath().toString();
		String location2=s+"\\src\\application";
		//File folder = new File(location);
		//File[] listOfFiles = folder.listFiles();
		location2=location2+"\\tempSetting.txt";
		
		try {
			Individual_Setting.add(location1,Individual_Setting.read(location2));
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
	}
}
