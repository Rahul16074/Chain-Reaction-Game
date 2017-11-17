package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
/**
 * This program stores the state of each block, like spheres and color of the block, of the grid
 * @author Rahul Lawaria
 *
 */
public class Box
{
	private int count;
	private Sphere sphere1;
	private Sphere sphere2;
	private Sphere sphere3;
	private Color color;

	/**
	 * This constructor initializes the block with no spheres in it
	 */
	public Box()
	{
		count = 0;
	}
	/**
	 * This method copies the state of a box to current box
	 * @param box The first parameter is the object of type Box which is to be copied
	 */
	public void copy(Box box)
	{
		this.count = box.getCount();
		this.sphere1 = box.getSphere1();
		this.sphere2 = box.getSphere2();
		this.sphere3 = box.getSphere3();
		this.color = box.getColor();
	}
	
	/**
	 * This method empties the current block
	 */
	public void setempty(){
		this.count=0;
		this.sphere1=null;
		this.sphere2=null;
		this.sphere3=null;
		this.color =null;
		
	}
	/**
	 * This method gives the number of spheres currently in the block
	 * @return Returns an int value denoting the number of spheres in the block
	 */
	public int getCount() 
	{
		return count;
	}
	
	/**
	 * This method increments the number of spheres in the block
	 */
	public void setCount() 
	{
		this.count++;
	}
	
	/**
	 * This method sets the number of spheres 
	 * @param count The first parameter is the number of spheres tha the user wants to set
	 */
	public void setCount(int count)
	{
		this.count = count;
	}
	/**
	 * This method gives the first sphere in the block
	 * @return Returns object of type Sphere
	 */
	public Sphere getSphere1() 
	{
		return sphere1;
	}
	/**
	 * This method set the first sphere
	 * @param sphere1 First parameter is the Sphere to be set
	 */
	public void setSphere1(Sphere sphere1) 
	{
		this.sphere1 = sphere1;
	}
	/**
	 * This method gives the second sphere in the block
	 * @return Returns object of type Sphere
	 */
	public Sphere getSphere2() {
		return sphere2;
	}
	
	/**
	 * This method sets the second sphere
	 * @param sphere2 First parameter is the Sphere to be set
	 */
	public void setSphere2(Sphere sphere2) {
		this.sphere2 = sphere2;
	}
	/**
	 * This method gives the thord sphere in the block
	 * @return Returns object of type Sphere
	 */
	public Sphere getSphere3() {
		return sphere3;
	}
	/**
	 * This method sets the third sphere
	 * @param sphere2 First parameter is the Sphere to be set
	 */
	public void setSphere3(Sphere sphere3) {
		this.sphere3 = sphere3;
	}
	/**
	 * This method gives the color of the block
	 * @return Returns object of type Color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * This method sets the color of the box
	 * @param color First parameter is the Color object to be set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}