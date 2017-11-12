package application;

import java.io.Serializable;
import java.util.Arrays;

public class Player_turn implements Serializable{
	private int players_left;
	private int cur_turn;
	private Boolean player[];
	
	Player_turn(Boolean[] player)
	{
		cur_turn = 0;
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
					cur_turn = i;
					flag=1;
					break;
				}
			}	
		}
		if(flag==0)
		{
			System.out.println("cur_turn+1"+" won");
		}
	}
	
}
