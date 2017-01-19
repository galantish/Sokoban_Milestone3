package controller.commands;

import controller.SokobanController;
import model.iModel;

/**
 * The Class LoadLevelCommand.
 */
public class LoadLevelCommand implements iSokobanCommand
{
	private String path;	
	private iModel model;

	public LoadLevelCommand(SokobanController controller)
	{
		this.path = null;
		this.model = controller.getIModel();
	}

	@Override
	public void execute()
	{
		this.model.loadLevel(this.path);
	}
	
	@Override
	public void setParams(SokobanController sokobanController, String params) 
	{
		this.setPath(params);
	}
	
	String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}
}
