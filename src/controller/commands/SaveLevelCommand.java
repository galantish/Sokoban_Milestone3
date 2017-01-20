package controller.commands;

import model.iModel;

/**
 * The Class SaveLevelCommand.
 */
public class SaveLevelCommand extends Command
{
	private iModel model;

	public SaveLevelCommand(iModel model)
	{
		this.model = model;
	}

	/** Execute the command. */
	@Override
	public void execute()
	{
		this.model.saveLevel(getParams());
	}


}
