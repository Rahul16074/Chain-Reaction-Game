package application;

import java.io.Serializable;

/**
 * This is a serializable class that stores the state of each block of the grid including, sphere count and color of spheres
 * @author Vipul Saini
 *
 */
public class Block_serialize implements Serializable{
	private static final long serialVersionUID = 1L;
	private int no_spheres;
	private String color;
	/**
	 * This constructor initializes no of spheres as zero and color as null 
	 */
	public Block_serialize()
	{
		no_spheres=0;
		color=null;
	}
	/**
	 * This function returns the color of the current cell
	 * @return Returns the color value in String
	 */
	public String getColor()
	{
		return color;
	}
	/**
	 * This function sets the color of the given block
	 * @param color The first parameter is the color of the given block
	 */
	public void setColor(String color)
	{
		this.color=color;
	}
	/**
	 * This method is used to get the number of spheres in a block
	 * @return Returns int value of number of spheres
	 */
	public int getSphereCount()
	{
		return no_spheres;
	}
	/**
	 * This method checks if he block is empty
	 * @return Returns a Boolean value, true if empty, false otherwise
	 */
	public boolean isEmpty()
	{
		if(no_spheres==0)
		{
			return true;
		}
		return false;
	}
	/**
	 * This method resets the state of the block
	 */
	public void setEmpty()
	{
		this.no_spheres=0;
		this.color =null;
	}
	/**
	 * This method set the number of spheres in a block
	 * @param spheres The first parameter is the number of spheres currently in he given block
	 */
	public void setSphereCount(int spheres)
	{
		no_spheres=spheres;
	}
	/**
	 * This method resets the state of the block
	 */
	public void reset()
	{
		color=null;
		no_spheres=0;
	}
	/**
	 * This method copies the state of one block to other
	 * @param sbox The first parameter is the block whose state is to be copied in the current block
	 */
	public void copy(Block_serialize sbox)
	{
		this.color=sbox.color;
		this.no_spheres=sbox.no_spheres;
	}
	
}
