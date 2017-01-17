package controller.commands;

import java.util.ArrayList;

import controller.SokobanController;
import model.data.items.iMoveable;
import model.data.levels.Level;

/**
 * The Class MoveCommand.
 */
public class MoveCommand implements iSokobanCommand
{
	/** The level. */
	private Level level;

	/** Type of move, like: up, down, right and left. */
	private String moveType;
	
	private iMoveable item;
	
	/**
	 * Initializes the move command.
	 *
	 * @param level
	 *            the level
	 */
	public MoveCommand(Level level)
	{
		this.level = level;
	}

	/** Execute the command. */
	@Override
	public void execute()
	{
		try
		{
			if(this.level.isEmpty() == true)
				throw new Exception("ERROE: Invalid level.");
			
			if(this.moveType == null)
				throw new Exception("ERROE: Invalid move type.");

			boolean isCanMove = this.level.getPolicy().move(this.level, this.moveType);

			if(isCanMove == false)
				return;

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * isValidMoveType - initializes the CLI and the type of a move command.
	 *
	 * @param moveType
	 * 			  the move type command
	 * 
	 * @return true/false if the user entered valid move type
	 */
	public boolean isValidMoveType(String moveType)
	{
		ArrayList<String> moveList = new ArrayList<String>();
		moveList.add("up");
		moveList.add("down");
		moveList.add("right");
		moveList.add("left");
		
		if(moveList.contains(moveType.toLowerCase()))
			return true;
		
		return false;
	}	

	@Override
	public void setParams(SokobanController sokobanController, String params) 
	{
		this.moveType = params;
	}	
	
	public String getMoveType()
	{
		return moveType;
	}

	public void setMoveType(String moveType)
	{
		if(isValidMoveType(moveType) == true)
			this.moveType = moveType.toLowerCase();
	}

	public Level getLevel() 
	{
		return level;
	}

	public void setLevel(Level level) 
	{
		this.level = level;
	}

	public iMoveable getItem() 
	{
		return item;
	}

	public void setItem(iMoveable item) 
	{
		this.item = item;
	}


}
