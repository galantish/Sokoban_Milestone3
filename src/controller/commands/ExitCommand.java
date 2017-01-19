package controller.commands;

import controller.SokobanController;

/**
 * The Class ExitCommand.
 */
public class ExitCommand implements iSokobanCommand
{
	@Override
	public void execute()
	{
		System.out.println("Goodbye!");
		//we should call controller.stop (command controller)
	}

	@Override
	public void setParams(SokobanController sokobanController, String params) 
	{


	}

}