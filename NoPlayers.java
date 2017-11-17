package application;

import java.io.Serializable;
/**
 * This is a serializable class whose object stores the number of players that play the game
 * @author Vipul Saini
 *
 */
public class NoPlayers implements Serializable {

	private static final long serialVersionUID = -3707909883151262795L;
	int num;
	/**
	 * This constructor sets the initial number of players to be 2
	 */
	public NoPlayers()
	{
		num=2;
	}
	/**
	 * This method sets the number of players 
	 * @param num First parameter is the number of players who need to play the game
	 */
	public void setPlayers(int num)
	{
		this.num=num;
	}
	/**
	 * This method gives the number of players that can play the game
	 * @return Returns int representing number of players
	 */
	public int getPlayers()
	{
		return num;
	}

}
