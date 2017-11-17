package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javafx.scene.paint.Color;
/**
 * This program stores the player turn sequence and manages the flow of turns in the game
 * @author Rahul Lawaria
 * @author Vipul Saini
 *
 */

public class Player_turn implements Serializable{

	private static final long serialVersionUID = 1L;
	private int players_left;
	private int prev_turn;
	private int cur_turn;
	private Boolean player[];
	
	/**
	 * This constructor initializes variables like current turn, previous turn and players left
	 * @param player The first parameter is the Boolean array that stores the status of players who have to play the game
	 */
	Player_turn(Boolean[] player)
	{
		cur_turn = 0;
		prev_turn=0;
		players_left=0;
		this.player=player;
		count_playersleft();
	}

	/**
	 * This method counts the number of players left in the game
	 */
	public void count_playersleft()
	{
		for(int i=0;i<player.length;i++)
		{
			if(player[i]==true)
			{
				players_left++;
			}
		}
	}
	/**
	 * This method gives the player whose turn is next
	 * @return Returns integer value corresponding to the player whose turn is now
	 */
	public int getCur_turn() {
		return cur_turn;
	}

	/**
	 * This method sets the current turn to the desired player
	 * @param cur_turn The first parameter is the integer value corresponding to the player number who has to take turn
	 */
	public void setCur_turn(int cur_turn) {
		this.cur_turn = cur_turn;
	}

	/**
	 * This method gives the current status of the players left in the game
	 * @return Returns Boolean array
	 */
	public Boolean[] getPlayer() {
		return player;
	}

	/**
	 * This method sets the current status of players in the game 
	 * @param player The first parameter is the Boolean array that has the current status of the game 
	 */
	public void setPlayer(Boolean player[]) {
		this.player = player;
	}
	
	/**
	 * This method returns the number of players left in the game
	 * @return Returns integer value of players left
	 */
	public int getPlayers_left() {
		return players_left;
	}

	/**
	 * This method sets the number of players left in the game
	 * @param players_left The first parameter is the integer denoting number of players left
	 */
	public void setPlayers_left(int players_left) {
		this.players_left = players_left;
	}
	
	/**
	 * This method decrements the player turn, sets the counter and state to previous turn
	 */
	public void decrement()
	{
		int flag = 0;
		for(int i=cur_turn-1;i>=0;i--)
		{
			if(player[i]==true)
			{
				cur_turn = i;
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			for(int i=player.length-1;i>cur_turn;i--)
			{
				if(player[i]==true)
				{
					cur_turn = i;
					flag=1;
					break;
				}
			}
		}
	}
	
	/**
	 * This method passes the control to the player who is supposed to take next turn
	 */
	public void increment()
	{
		int flag=0;
		for(int i=cur_turn+1;i<player.length;i++)
		{
			if(player[i]==true)
			{
				prev_turn=cur_turn;
				cur_turn = i;
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			for(int i=0;i<cur_turn;i++)
			{
				if(player[i]==true)
				{
					prev_turn=cur_turn;
					cur_turn = i;
					flag=1;
					break;
				}
			}	
		}
		/*if(flag==0)
		{
			System.out.println("cur_turn+1"+" won");
		}*/
	}
	
	/**
	 * This method checks the condition of the grid and assign next player if the player is not lost
	 * @param sbox The first parameter is the current state of grid
	 * @param l The second parameter is the number of columns in the grid
	 * @param b The thirst parameter is the number of rows in the grid
	 */
	public void updatePlayer(Block_serialize[][] sbox,int l, int b)
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		location=location+"\\savedSettings.txt";
		serializedSetting obj=null;
		try {
			obj=Individual_Setting.read(location);
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
		for(int i=0;i<8;i++)
		{
			String col=null;
			if(i!=prev_turn)
			{
				if(i==0)
				{
					col=obj.getColor1();
				}
				else if(i==1)
				{
					col=obj.getColor2();
				}
				else if(i==2)
				{
					col=obj.getColor3();
				}
				else if(i==3)
				{
					col=obj.getColor4();
				}
				else if(i==4)
				{
					col=obj.getColor5();
				}
				else if(i==5)
				{
					col=obj.getColor6();
				}
				else if(i==6)
				{
					col=obj.getColor7();
				}
				else if(i==7)
				{
					col=obj.getColor8();
				}
				col=Color.valueOf(col).toString();
				int flag=0;
				for(int j=0;j<l;j++)
				{
					for(int k=0;k<b;k++)
					{
						if(sbox[j][k]!=null && sbox[j][k].getColor()!=null && sbox[j][k].getColor().equals(col))
						{
							flag=1;
							break;
						}
					}
				}
				if(flag==0)
				{
					//System.out.println("false at :"+i);
					player[i]=false;
				}
				else if(flag==1)
				{
					player[i]=true;
				}
			}
		}
			
	}
	
	/**
	 * This method assigns the control to the player who is still in the game 
	 */
	public void check_increment()
	{
		if(player[cur_turn]==false)
		{
			increment();
		}
	}
	
	/**
	 * This method sets the player back in the game
	 * @param num The first parameter is the number of players playing the game
	 */
	public void reset(int num)
	{
		Arrays.fill(player, false);
		for(int i=0;i<num;i++)
		{
			player[i]=true;
		}
	}
	
	/**
	 * This method checks if there is a winner in he game or not, in 15*10 Grid
	 * @param obj The first parameter is the object of type HD_Grid.cond 
	 * @return Returns the HD_Grid.cond object with necessary changes 
	 */
	public HD_Grid.cond isWinnerHD(HD_Grid.cond obj)
	{
		int flag=0;
		int index=0;
		for(int i=0;i<8;i++)
		{
			if(player[i]==true)
			{
				//System.out.println("added at:"+i);
				flag+=1;
				index=i;
			}
		}
		if(flag==1)
		{
			obj.setWinner(index+1+" is the winner");
			obj.add();
			//System.out.println(index+1+" is the winner");
		}
		return obj;
	}
	
	/**
	 * This method checks if there is a winner in he game or not, in 9*6 Grid
	 * @param obj The first parameter is the object of type Normal_Grid.cond 
	 * @return Returns the Normal_Grid.cond object with necessary changes 
	 */
	public Normal_Grid.cond isWinner(Normal_Grid.cond obj)
	{
		int flag=0;
		int index=0;
		for(int i=0;i<8;i++)
		{
			if(player[i]==true)
			{
				//System.out.println("added at:"+i);
				flag+=1;
				index=i;
			}
		}
		if(flag==1)
		{
			obj.setWinner(index+1+" is the winner");
			obj.add();
			//System.out.println(index+1+" is the winner");
		}
		return obj;
	}
}
