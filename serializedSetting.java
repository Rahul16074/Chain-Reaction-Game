package application;

import java.io.Serializable;

/**
 * This program initializes the colors and serializes it in a file
 * @author Vipul Saini
 *
 */
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
	/**
	 * This constructor initializes players with initial colors
	 */
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
	/**
	 * This function sets the color of player 1
	 * @param color First parameter is of type Color to be set
	 */
	public void setColor1(String color)
	{
		this.color1=color;
	}
	/**
	 * This function sets the color of player 2
	 * @param color First parameter is of type Color to be set
	 */
	public void setColor2(String color)
	{
		this.color2=color;
	}
	/**
	 * This function sets the color of player 3
	 * @param color First parameter is of type Color to be set
	 */
	public void setColor3(String color)
	{
		this.color3=color;
	}
	/**
	 * This function sets the color of player 4
	 * @param color First parameter is of type Color to be set
	 */
	public void setColor4(String color)
	{
		this.color4=color;
	}
	/**
	 * This function sets the color of player 5
	 * @param color First parameter is of type Color to be set
	 */
	public void setColor5(String color)
	{
		this.color5=color;
	}
	/**
	 * This function sets the color of player 6
	 * @param color First parameter is of type Color to be set
	 */
	public void setColor6(String color)
	{
		this.color6=color;
	}
	/**
	 * This function sets the color of player 7
	 * @param color First parameter is of type Color to be set
	 */
	public void setColor7(String color)
	{
		this.color7=color;
	}
	/**
	 * This function sets the color of player 8
	 * @param color First parameter is of type Color to be set
	 */
	public void setColor8(String color)
	{
		this.color8=color;
	}
	/**
	 * This method gives the color of player 1
	 * @return Returns an object of type Color
	 */
	public String getColor1()
	{
		return color1;
	}
	/**
	 * This method gives the color of player 2
	 * @return Returns an object of type Color
	 */
	
	public String getColor2()
	{
		return color2;
	}
	/**
	 * This method gives the color of player 3
	 * @return Returns an object of type Color
	 */
	
	public String getColor3()
	{
		return color3;
	}
	/**
	 * This method gives the color of player 4
	 * @return Returns an object of type Color
	 */
	
	public String getColor4()
	{
		return color4;
	}
	/**
	 * This method gives the color of player 5
	 * @return Returns an object of type Color
	 */
	
	public String getColor5()
	{
		return color5;
	}
	/**
	 * This method gives the color of player 6
	 * @return Returns an object of type Color
	 */
	
	public String getColor6()
	{
		return color6;
	}
	/**
	 * This method gives the color of player 7
	 * @return Returns an object of type Color
	 */
	
	public String getColor7()
	{
		return color7;
	}
	/**
	 * This method gives the color of player 8
	 * @return Returns an object of type Color
	 */
	
	public String getColor8()
	{
		return color8;
	}
}
