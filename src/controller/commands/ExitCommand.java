package controller.commands;

import controller.CommandController;

/**
 * The Class ExitCommand.
 */
public class ExitCommand extends Command
{
	private CommandController controller;
	
	public ExitCommand(CommandController controller) 
	{
		this.controller = controller;
	}
	
	@Override
	public void execute()
	{
		this.controller.stop();
	}

}