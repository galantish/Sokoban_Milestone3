package model.data.items;

/**
 * The Class Player - an item in the game. 
 */

public class Player extends CommonItems implements iMoveable
{
	/**
	 * Initializes a player.
	 */
	public Player() 
	{
		super.setRepChar('A');
	}
	
	/**
	 * Initializes player's position.
	 *
	 * @param position
	 *            the new position of a player
	 */
	public Player(Position position)
	{
		super.setRepChar('A');
		super.setPosition(position);		
	}

	public Player(Player player) 
	{
		this.setPosition(player.getPosition());
		this.setRepChar(player.getRepChar());
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public char getTypeOfObject() 
	{
		return 'A';
	}

	@Override
	public void Move(Position newPos) 
	{
		this.setPosition(newPos);
		
	}
}
