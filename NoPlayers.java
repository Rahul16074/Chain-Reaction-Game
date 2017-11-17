package application;

import java.io.Serializable;

public class NoPlayers implements Serializable {

	private static final long serialVersionUID = -3707909883151262795L;
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
