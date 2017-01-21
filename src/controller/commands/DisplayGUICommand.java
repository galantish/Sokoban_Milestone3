package controller.commands;

import model.iModel;
import view.iView;

public class DisplayGUICommand extends Command 
{
	iModel model;
	iView view;
	
	public DisplayGUICommand(iModel model, iView view) 
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
