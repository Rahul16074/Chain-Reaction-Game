package application;

import java.io.Serializable;

public class NoPlayers implements Serializable {
	int num;
	public NoPlayers()
	{
		num=2;
	}
	public void setPlayers(int num)
	{
		this.num=num;
	}
	public int getPlayers()
	{
		return num;
	}

}
