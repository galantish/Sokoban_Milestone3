package controller.commands;

import controller.SokobanController;

/**
* The Interface iSokobanCommand - Interface that holds the function that all
* the commands in the specific SOKOBAN game should implement.
*/
public interface iSokobanCommand extends iCommand 
{
	public void setParams(SokobanController sokobanController, String params);
}
