package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class menu implements javafx.fxml.Initializable{
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button btnPlay;

	    @FXML
	    private Button btnPlay_HD;

	    @FXML
	    private ComboBox<String> sel_player;

	    @FXML
	    private Button btnSetting;

	    @FXML
	    private Button btnResume;

	    @FXML
	    void initialize() {
	        assert btnPlay != null : "fx:id=\"play\" was not injected: check your FXML file 'main_menu.fxml'.";
	        assert btnPlay_HD != null : "fx:id=\"play_HD\" was not injected: check your FXML file 'main_menu.fxml'.";
	        assert sel_player != null : "fx:id=\"sel_player\" was not injected: check your FXML file 'main_menu.fxml'.";
	        assert btnSetting != null : "fx:id=\"setting\" was not injected: check your FXML file 'main_menu.fxml'.";


	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sel_player.getItems().remove("Item 1");
		sel_player.getItems().remove("Item 2");
		sel_player.getItems().remove("Item 3");
		sel_player.getItems().add("2");
		sel_player.getItems().add("3");
		sel_player.getItems().add("4");
		sel_player.getItems().add("5");
		sel_player.getItems().add("6");
		sel_player.getItems().add("7");
		sel_player.getItems().add("8");
		try
		{
			//System.out.println("loaded");
			sel_player.getSelectionModel().select(load()-2);
		} 
		catch (ClassNotFoundException | IOException e1) 
		{
			e1.printStackTrace();
		}
		btnPlay.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent e){
				Normal_Grid ex=new Normal_Grid();
				ex.run();
			}
		});
		btnPlay_HD.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent e){
				HD_Grid ex=new HD_Grid();
				ex.run();
			}
		});
		btnSetting.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				Player_Setting p=new Player_Setting();
				p.run();
			}
			
		});
		sel_player.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				String num=sel_player.getValue();
				try 
				{
					setPlayerCount(num);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				
			}
        	
        });
	}
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
	
}
