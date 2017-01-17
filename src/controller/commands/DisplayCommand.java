package controller.commands;

import controller.SokobanController;
import model.data.levels.Level;
import view.display.MyDisplayer;

/**
 * The Class DisplayCommand.
 */
public class DisplayCommand implements iSokobanCommand
{
	/** The level. */
	private Level level;
	
	/** A displayer. */
	private MyDisplayer displayer;
	
	/**
	 * Initializes the Display command.
	 *
	 * @param level
	 *            the level
	 */
	public DisplayCommand(Level level)
	{
		this.level = level;
		this.displayer = new MyDisplayer();
	}
	
	/** Execute the command. */
	@Override
	public void execute()
	{
	
			//Checking if the user has loaded a level.
			if(this.level.isEmpty() == true)
				return;
			
			//Display the level
			displayer.DisplayLevel(this.level);
			
			System.out.println();
	
	}

	@Override
	public void setParams(SokobanController sokobanController, String params) 
	{
		
		
	}

	
}