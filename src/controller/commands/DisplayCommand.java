package controller.commands;

import model.iModel;
import view.iView;

/**
 * The Class DisplayCommand.
 */
public class DisplayCommand extends Command
{	
	private iModel model;
	private iView view;

	public DisplayCommand(iModel model, iView view)
	{
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void execute()
	{
		view.displayLevel(model.getCurrentLevel());
	}
}