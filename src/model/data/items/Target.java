package model.data.items;

/**
 * The Class Target - an item in the game. 
 */
public class Target extends Floor implements iUnmoveable
{
	/**
	 * Initializes a target.
	 */
	public Target() 
	{
		super.setRepChar('o');
	}
	
	/**
	 * Initializes target's position.
	 *
	 * @param position
	 *            the new position of a target
	 */
	public Target(Position position)
	{
		super.setRepChar('o');
		super.setPosition(position);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public char getTypeOfObject() 
	{
		return 'o';
	}
}
