package controller.commands;

import java.io.IOException;
import java.util.ArrayList;

import model.data.items.Player;
import model.data.items.Position;
import model.data.items.iMoveable;
import model.data.levels.Level;

/**
 * The Class MoveCommand.
 */
public class MoveCommand implements iCommand
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
	public void Execute()
	{
		try
		{
			if(this.level.isEmpty() == true)
				throw new Exception("ERROE: Invalid level.");
			
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
		
		if(moveList.contains(moveType))
			return true;
		
		return false;
	}
	
	/**
	 * GetMoveType - return the local move type.
	 * 
	 * @return moveType
	 */
	public String getMoveType()
	{
		return moveType;
	}

	/**
	 * SetMoveType - initializes the move type variable
	 * 
	 * @param moveType
	 * 			type of move, like: up, down, right and left
	 */
	public void setMoveType(String moveType)
	{
		this.moveType = moveType.toLowerCase();
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public iMoveable getItem() {
		return item;
	}

	public void setItem(iMoveable item) {
		this.item = item;
	}
	
	@Override
	public void setParams(String args) 
	{
		this.moveType = args;
	}
	
}
