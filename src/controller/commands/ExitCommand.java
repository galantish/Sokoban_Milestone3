package controller.commands;

import controller.CommandController;
import controller.server.MyServer;

/**
 * The Class ExitCommand.
 */
public class ExitCommand extends Command
{
	private CommandController controller;
	private MyServer theServer;
	
	public ExitCommand(CommandController controller, MyServer theServer) 
	{
		this.controller = controller;
		this.theServer = theServer;
	}
	
	@Override
	public void execute()
	{
		this.controller.stop();
		if(this.theServer != null)
			this.theServer.stop();
	}

}