package model.data.items;

/**
 * The Class Box - an item in the game. 
 */
public class Box extends CommonItems implements iMoveable
{
	/** A boolean variable that defines whether the box is in target or not. */
	private Boolean isBoxInTarget;

	/**
	 * Initializes a box.
	 */
	public Box() 
	{
		super.setRepChar('@');
		this.isBoxInTarget = false;
	}
	
	/**
	 * Initializes box's position.
	 *
	 * @param position
	 *            the new position of a box
	 */
	public Box(Position position)
	{
		super.setRepChar('@');
		this.isBoxInTarget = false;
		super.setPosition(position);	
	}
		
	/**
	 * GetIsBoxInTarget.
	 * 
	 * @return isBoxInTarget
	 */
	public Boolean getIsBoxInTarget() 
	{
		return isBoxInTarget;
	}

	/**
	 * SetIsBoxInTarget.
	 * 
	 * @param isBoxInTarget
	 * 			if box is on target
	 */
	public void setIsBoxInTarget(Boolean isBoxInTarget) 
	{
		this.isBoxInTarget = isBoxInTarget;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public char getTypeOfObject() 
	{
		return '@';
	}
	
	@Override
	public void Move(Position newPos) 
	{
		this.setPosition(newPos);
		
	}
}
