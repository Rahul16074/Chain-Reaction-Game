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
import java.util.Arrays;
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
				combineSettings();
				Normal_Grid ex=new Normal_Grid();
				Block_serialize[][] sbox=new Block_serialize[9][6];
				for(int i=0;i<9;i++)
				{
					for(int j=0;j<6;j++)
					{
						sbox[i][j]=new Block_serialize();
					}
				}
				try 
				{
					storeGridState(sbox);
				} 
				catch (FileNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int n;
				n=Integer.parseInt(sel_player.getValue());
				/*try 
				{
					n=load();
				} 
				catch (FileNotFoundException e2) 
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
				catch (ClassNotFoundException e2) 
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
				catch (IOException e2) 
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}*/
				String num=sel_player.getValue();
				try 
				{
					setPlayerCount(num);
				} 
				catch (IOException f) 
				{
					f.printStackTrace();
				}
				try 
				{
					Boolean[] b=new Boolean[8];
					Arrays.fill(b, false);
					for(int i=0;i<n;i++)
					{
						b[i]=true;
					}
					Player_turn obj=new Player_turn(b);
					set_playerturns(obj);
					ex.run(sbox,n,obj,0);
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
			}
		});
		btnPlay_HD.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent e){
				HD_Grid ex=new HD_Grid();
				combineSettings();
				Block_serialize[][] sbox=new Block_serialize[15][10];
				for(int i=0;i<15;i++)
				{
					for(int j=0;j<10;j++)
					{
						sbox[i][j]=new Block_serialize();
					}
				}
				/*try 
				{
					storeGridState(sbox);
				} 
				catch (FileNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				int n;
				n=Integer.parseInt(sel_player.getValue());
				String num=sel_player.getValue();
				try 
				{
					setPlayerCount(num);
				} 
				catch (IOException f) 
				{
					f.printStackTrace();
				}
				try 
				{
					Boolean[] b=new Boolean[8];
					Arrays.fill(b, false);
					for(int i=0;i<n;i++)
					{
						b[i]=true;
					}
					Player_turn obj=new Player_turn(b);
					set_playerturns(obj);
					ex.run(sbox,n,obj,0);
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} 
				catch (ClassNotFoundException e1) 
				{
					e1.printStackTrace();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
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
		btnResume.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				int n=0;
				
				try 
				{
					n=load();
				} 
				catch (FileNotFoundException e2) 
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
				catch (ClassNotFoundException e2) 
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
				catch (IOException e2) 
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Block_serialize[][] sbox=null;
				try 
				{
					sbox=Normal_Grid.get_state();
				} 
				catch (FileNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Player_turn pl=null;
				try 
				{
					pl=get_playerturns();
				} 
				catch (FileNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(sbox.length==15)
				{
					//System.out.println("hey");
					HD_Grid ex=new HD_Grid();
					int flag=0;
					for(int i=0;i<sbox[0].length;i++)
					{
						for(int j=0;j<sbox.length;j++)
						{
							if(sbox[j][i]!=null && sbox[j][i].isEmpty()==false)
							{
								flag=1;
								break;
							}
						}
					}
					if(flag==1)
					{
						try {
							ex.run(sbox, n, pl,1);
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
				else
				{
					Normal_Grid ex=new Normal_Grid();
					int flag=0;
					for(int i=0;i<sbox[0].length;i++)
					{
						for(int j=0;j<sbox.length;j++)
						{
							if(sbox[j][i] != null && sbox[j][i].isEmpty()==false)
							{
								flag=1;
								break;
							}
						}
					}
					if(flag==1)
					{
						try {
							ex.run(sbox, n, pl,1);
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
			}
			
		});
		sel_player.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {				
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
	
	static void set_playerturns(Player_turn obj) throws FileNotFoundException, IOException
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
	
	static Player_turn get_playerturns() throws FileNotFoundException, IOException, ClassNotFoundException
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