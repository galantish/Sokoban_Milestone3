package controller.commands;

import controller.server.SokobanClientHandler;
import model.iModel;

/**
 * The Class DisplayCommand.
 */
public class DisplayCLICommand extends Command
{	
	private iModel model;
	private SokobanClientHandler clientHandler;
	
	public DisplayCLICommand(iModel model, SokobanClientHandler clientHandler)
	{
		this.model = model;
		this.clientHandler = clientHandler;
	}
	
	@Override
	public void execute()
	{
		this.clientHandler.sendLevel(this.model.getCurrentLevel().getLevelBoard());
	}
}