package model.data.items;

import java.io.Serializable;

/**
* The Class CommonItems - The abstract class that implements all the functions for an item
* of a level game.
*/
public abstract class CommonItems implements iGeneralItem, Serializable
{
	/** The item's position. */
	private Position position;
	
	/** The representative char of an item. */
	private char repChar;
	
	/**
	 * Initializes a new default item.
	 */
	public CommonItems() 
	{
		this.position = position;
		this.repChar = ' ';
	}
	
	/**
	 * Initializes a new item.
	 * @param position
	 * 			the position of the new item
	 * @param repChar
	 *			the representative char of the new item			
	 */
	 
	public CommonItems(Position position, char repChar)
	{
		this.position = position;
		this.repChar = repChar;
	}
	
	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public char getRepChar() 
	{
		return repChar;
	}
	
	public void setRepChar(char repChar) 
	{
		this.repChar = repChar;
	}
	
	@Override
	public String toString()
	{
		String s = String.valueOf(this.repChar);
		return s;
		
	}
}
