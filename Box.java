package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;

public class Box
{
	private int count;
	private Sphere sphere1;
	private Sphere sphere2;
	private Sphere sphere3;
	private Color color;

	public Box()
	{
		count = 0;
	}
	public void copy(Box box)
	{
		this.count = box.getCount();
		this.sphere1 = box.getSphere1();
		this.sphere2 = box.getSphere2();
		this.sphere3 = box.getSphere3();
		this.color = box.getColor();
	}
	
	public void setempty(){
		this.count=0;
		this.sphere1=null;
		this.sphere2=null;
		this.sphere3=null;
		this.color =null;
		
	}
	public int getCount() 
	{
		return count;
	}
	public void setCount() 
	{
		this.count++;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public Sphere getSphere1() 
	{
		return sphere1;
	}
	public void setSphere1(Sphere sphere1) 
	{
		this.sphere1 = sphere1;
	}
	public Sphere getSphere2() {
		return sphere2;
	}
	public void setSphere2(Sphere sphere2) {
		this.sphere2 = sphere2;
	}
	public Sphere getSphere3() {
		return sphere3;
	}
	public void setSphere3(Sphere sphere3) {
		this.sphere3 = sphere3;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}