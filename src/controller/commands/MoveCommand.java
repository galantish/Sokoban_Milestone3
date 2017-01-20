package controller.commands;

import java.util.ArrayList;
import model.iModel;

/**
 * The Class MoveCommand.
 */
public class MoveCommand extends Command
{
	private iModel model;

	public MoveCommand(iModel model)
	{
		this.model = model;
	}

	@Override
	public void execute()
	{
		if(isValidMoveType(getParams()) == false)
			return;
		this.model.move(getParams());
	}
	
	private boolean isValidMoveType(String moveType)
	{
		ArrayList<String> moveList = new ArrayList<String>();
		moveList.add("up");
		moveList.add("down");
		moveList.add("right");
		moveList.add("left");
		
		if(moveList.contains(moveType.toLowerCase()) == true)
			return true;
		
		return false;
	}
}
