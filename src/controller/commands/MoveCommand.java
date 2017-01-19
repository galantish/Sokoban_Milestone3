package controller.commands;

import java.util.ArrayList;
import controller.SokobanController;
import model.iModel;

/**
 * The Class MoveCommand.
 */
public class MoveCommand implements iSokobanCommand
{
	private iModel model;
	private String moveType;

	public MoveCommand(SokobanController controller)
	{
		this.model = controller.getIModel();
		this.moveType = null;
	}

	@Override
	public void setParams(SokobanController sokobanController, String params) 
	{
		if(isValidMoveType(params) == true)
			this.moveType = params.toLowerCase();			
	}	
	
	@Override
	public void execute()
	{
		this.model.move(this.moveType);
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
