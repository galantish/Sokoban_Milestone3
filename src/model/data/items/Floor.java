package model.data.items;

/**
 * The Class Floor - an item in the game. 
 */
public class Floor extends CommonItems implements iUnmoveable
{
	/**
	 * Initializes a floor.
	 */
	public Floor() 
	{
		super.setRepChar(' ');
	}
	
	/**
	 * Initializes floor's position.
	 *
	 * @param position
	 *            the new position of a floor
	 */
	public Floor(Position position)
	{
		super.setRepChar(' ');
		super.setPosition(position);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public char getTypeOfObject() 
	{
		return ' ';
	}
	

}
