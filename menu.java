package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

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
	    
	    Utility ut = new Utility();

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
			sel_player.getSelectionModel().select(ut.load()-2);
		} 
		catch (ClassNotFoundException | IOException e1) 
		{
			e1.printStackTrace();
		}
		
		btnPlay.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent e){
				ut.combineSettings();
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
					ut.storeGridState(sbox);
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
					ut.setPlayerCount(num);
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
					ut.set_playerturns(obj);
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
				ut.combineSettings();
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
					ut.setPlayerCount(num);
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
					ut.set_playerturns(obj);
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
					n=ut.load();
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
					sbox=Utility.get_state();
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
					pl=ut.get_playerturns();
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
	
	
	
}