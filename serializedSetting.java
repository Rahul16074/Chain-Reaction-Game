package application;

import java.io.Serializable;

public class serializedSetting implements Serializable {
	private static final long serialVersionUID = 1L;
	String color1;
	String color2;
	String color3;
	String color4;
	String color5;
	String color6;
	String color7;
	String color8;
	public serializedSetting()
	{
		color1="Red";
		color2="Blue";
		color3="Green";
		color4="White";
		color5="Cyan";
		color6="Yellow";
		color7="Pink";
		color8="Orange";
	}
	public void setColor1(String color)
	{
		this.color1=color;
	}
	public void setColor2(String color)
	{
		this.color2=color;
	}
	public void setColor3(String color)
	{
		this.color3=color;
	}
	public void setColor4(String color)
	{
		this.color4=color;
	}
	public void setColor5(String color)
	{
		this.color5=color;
	}
	public void setColor6(String color)
	{
		this.color6=color;
	}
	public void setColor7(String color)
	{
		this.color7=color;
	}
	public void setColor8(String color)
	{
		this.color8=color;
	}
	public String getColor1()
	{
		return color1;
	}
	public String getColor2()
	{
		return color2;
	}
	public String getColor3()
	{
		return color3;
	}
	public String getColor4()
	{
		return color4;
	}
	public String getColor5()
	{
		return color5;
	}
	public String getColor6()
	{
		return color6;
	}
	public String getColor7()
	{
		return color7;
	}
	public String getColor8()
	{
		return color8;
	}
}
