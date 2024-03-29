package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * This program initializes the list of settings of total players 
 * @author Rahul Lawaria
 *
 */
public class Player_Setting{
	/**
	 * This method initializes the list of total players settings 
	 */
	public void start() {
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Chain Reaction Preferences");
        ListView<String> list=new ListView<String>();
        Individual_Setting[] setting_arr=new Individual_Setting[8];
        for(int i=0;i<8;i++)
        {
        	setting_arr[i]=new Individual_Setting();
        }
        list.getItems().add("Player1 Setting");
        list.getItems().add("Player2 Setting");
        list.getItems().add("Player3 Setting");
        list.getItems().add("Player4 Setting");
        list.getItems().add("Player5 Setting");
        list.getItems().add("Player6 Setting");
        list.getItems().add("Player7 Setting");
        list.getItems().add("Player8 Setting");
        list.setOnMousePressed(event->{
        	int t=list.getSelectionModel().getSelectedIndex();
        	if(t<8 && t>-1)
        	{
        		try 
        		{
					setting_arr[t].run(t+1);
				} 
        		catch (FileNotFoundException e) 
        		{
					e.printStackTrace();
				} 
        		catch (ClassNotFoundException e) 
        		{
					e.printStackTrace();
				}
        		catch (IOException e) 
        		{
					e.printStackTrace();
				}
        	}
        });
		Scene scene = new Scene(list, 298,387, Color.BLACK);     
		scene.fillProperty();
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