package controller.commands;

import controller.SokobanController;
import model.iModel;

/**
 * The Class SaveLevelCommand.
 */
public class SaveLevelCommand implements iSokobanCommand
{
	private String path;
	private iModel model;

	public SaveLevelCommand(SokobanController controller)
	{
		this.path = null;
		this.model = controller.getIModel();
	}

	/** Execute the command. */
	@Override
	public void execute()
	{
		this.model.saveLevel(this.path);
	}

	@Override
	public void setParams(SokobanController sokobanController, String params) 
	{
		this.setPath(params);
	}
	
	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

}
