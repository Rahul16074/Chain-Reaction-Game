package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javafx.scene.paint.Color;

public class Player_turn implements Serializable{
	private int players_left;
	private int prev_turn;
	private int cur_turn;
	private Boolean player[];
	
	Player_turn(Boolean[] player)
	{
		cur_turn = 0;
		prev_turn=0;
		players_left=0;
		this.player=player;
		count_playersleft();
	}

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
	public int getCur_turn() {
		return cur_turn;
	}

	public void setCur_turn(int cur_turn) {
		this.cur_turn = cur_turn;
	}

	public Boolean[] getPlayer() {
		return player;
	}

	public void setPlayer(Boolean player[]) {
		this.player = player;
	}

	public int getPlayers_left() {
		return players_left;
	}

	public void setPlayers_left(int players_left) {
		this.players_left = players_left;
	}
	
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
	
	
	public void updatePlayer(Block_serialize[][] sbox,int l, int b)
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String location=s+"\\src\\application";
		File folder = new File(location);
		File[] listOfFiles = folder.listFiles();
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
			if(player[i]==true && i!=prev_turn)
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
			}
		}
			
	}
	public void isWinner()
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
			System.out.println(index+1+" is the winner");
		}
	}
}
