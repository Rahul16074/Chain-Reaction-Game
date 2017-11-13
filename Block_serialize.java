package application;

import java.io.Serializable;

import javafx.scene.paint.Paint;

public class Block_serialize implements Serializable{
	private int no_spheres;
	private String color;
	public Block_serialize()
	{
		no_spheres=0;
		color=null;
	}
	public String getColor()
	{
		return color;
	}
	public void setColor(String color)
	{
		this.color=color;
	}
	public int getSphereCount()
	{
		return no_spheres;
	}
	public boolean isEmpty()
	{
		if(no_spheres==0)
		{
			return true;
		}
		return false;
	}
	public void setEmpty()
	{
		this.no_spheres=0;
		this.color =null;
	}
	public void setSphereCount(int spheres)
	{
		no_spheres=spheres;
	}
	public void reset()
	{
		color=null;
		no_spheres=0;
	}
	
}