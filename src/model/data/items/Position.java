package model.data.items;

import java.io.Serializable;

/**
 * The Class Position - an item in the game. 
 */
public class Position implements Serializable
{
	/** Point x **/
	private int x;
	
	/** Point y **/
	private int y;
	
	/**
	 * Initializes the local variables to null.
	 */
	public Position() 
	{
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Gets 2 points and initializes the local x and y points.
	 * @param x
	 * 			point x
	 * @param y
	 * 			point y
	 */
	public Position(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}

	public Position(Position pos) 
	{
		this.x = pos.getX();
		this.y = pos.getY();
	}

	/**
	 * GetX - return point x.
	 * @return point x
	 */
	public int getX() 
	{
		return x;
	}

	/**
	 * SetX - gets a X point and initializes the local x point.
	 * @param x
	 * 			point x
	 */
	public void setX(int x) 
	{
		this.x = x;
	}

	/**
	 * GetY - return point y.
	 * @return point y
	 */
	public int getY() 
	{
		return y;
	}

	/**
	 * SetY - gets a y point and initializes the local y point.
	 * @param y
	 * 			point y
	 */
	public void setY(int y) 
	{
		this.y = y;
	}
	
	@Override
	public String toString()
	{
		return ("(" + x + "," + y + ")");
	}

	/**
	 * GetPosition - return the (local) position.
	 * @return position
	 */
	public Position getPosition()
	{
		return this;
	}

	/**
	 * IsEqualPosition - checking if 2 positions are equals.
	 * 
	 * @param position
	 * 			a position to compare
	 * @return true/false if the local position is equal to a position.
	 */
	public Boolean isEqualPosition(Position position)
	{
		if((this.getX() == position.getX()) && (this.getY() == position.getY()))
			return true;
		
		return false;
	}

}
