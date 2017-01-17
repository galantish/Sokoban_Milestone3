package model.data.items;

/**
 * The Class Wall  - an item in the game. 
 */
public class Wall extends CommonItems implements iUnmoveable
{
	/**
	 * Initializes a wall.
	 */
	public Wall() 
	{
		super.setRepChar('#');
	}
	
	/**
	 * Initializes wall's position.
	 *
	 * @param position
	 *            the new position of a target
	 */
	public Wall(Position position)
	{
		super.setRepChar('#');
		super.setPosition(position);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public char getTypeOfObject() 
	{
		return '#';
	}
	

}
