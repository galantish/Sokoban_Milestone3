package controller.commands;

import common.Level;
import controller.SokobanController;
import model.iModel;
import view.iView;
import view.display.MyDisplayer;

/**
 * The Class DisplayCommand.
 */
public class DisplayCommand implements iSokobanCommand
{	
	private iModel model;
	private iView view;

	public DisplayCommand(SokobanController controller)
	{
		this.model = controller.getIModel();
		this.view = controller.getIView();
	}
	
	@Override
	public void execute()
	{
		Level theLevel = model.getCurrentLevel();
		view.displayLevel(theLevel);
	}

	@Override
	public void setParams(SokobanController sokobanController, String params) 
	{
		
		
	}

	
}