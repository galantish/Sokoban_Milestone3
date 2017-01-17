package controller.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import controller.SokobanController;
import model.data.levels.Level;

/**
 * The Class ExitCommand.
 */
public class ExitCommand implements iSokobanCommand

{
	public ExitCommand()
	{

	}
	
	/**
	 * ExitCommand - gets exit command and initialized the local io to exitcommand's ios.
	 * @param exitCommand
	 * 			a exit command
	 */
	public ExitCommand(Level level)
	{

	}
	
	/** Execute the command. */
	@Override
	public void execute()
	{
		System.out.println("Goodbye!");
		//we should do controller.stop (command controller)
	}

	@Override
	public void setParams(SokobanController sokobanController, String params) 
	{
		
	}

}