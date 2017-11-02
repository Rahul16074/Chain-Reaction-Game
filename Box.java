package application;

import javafx.scene.shape.Sphere;

public class Box
{
	private int count;
	private Sphere sphere1;
	private Sphere sphere2;
	private Sphere sphere3;
	private String color;

	public Box()
	{
		count = 0;
	}
	public int getCount() 
	{
		return count;
	}
	public void setCount() 
	{
		this.count++;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
