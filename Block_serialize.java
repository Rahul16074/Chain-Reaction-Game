package application;

import java.io.Serializable;

import javafx.scene.paint.Paint;

public class Block_serialize implements Serializable{
	private int no_spheres;
	private Paint color;
	public Block_serialize()
	{
		no_spheres=0;
		color=null;
	}
	public Paint getColor()
	{
		return color;
	}
	public void setColor(Paint color)
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
		no_spheres=0;
	}
	public void setSphereCount()
	{
		no_spheres+=1;
	}
	public void reset()
	{
		color=null;
		no_spheres=0;
	}
	
}
