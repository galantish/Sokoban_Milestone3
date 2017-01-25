package controller.commands;

import model.iModel;

/**
 * The Class SaveLevelCommand - save a level.
 */
public class SaveLevelCommand extends Command
{
	private iModel model;

	public SaveLevelCommand(iModel model)
	{
		this.model = model;
	}

	@Override
	public void execute()
	{
		if(this.model.getCurrentLevel().isEmpty() == true)
			return;
		else
			this.model.saveLevel(getParams());
	}
}
