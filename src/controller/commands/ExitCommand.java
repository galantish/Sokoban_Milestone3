package controller.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import model.data.levels.Level;

/**
 * The Class ExitCommand.
 */
public class ExitCommand implements iCommand
{
	/** ExitCommand - initializes the io to null. */
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
	public void Execute()
	{
		System.out.println("Goodbye!");

	}

	@Override
	public void setParams(String args) 
	{
		
	}
	
}