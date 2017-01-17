package view.display;

import java.io.IOException;

import model.data.levels.Level;
import model.data.levels.MyTextLevel;

/**
 * The Class CLIDisplayer - Class that display a level to the user.
 */
public class MyDisplayer implements iDisplayer
{	
	@Override
	public void DisplayLevel(Level level)
	{		
		//Using the TextLevelLoader Class in order to save a level and display it to the user
		MyTextLevel myTextLevelLoader = new MyTextLevel();
		
		try
		{
			myTextLevelLoader.SaveLevel(level, System.out);
		} 
		catch (IOException e)
		{
			System.out.println("ERROR: Invalid level.");
		}
	}
}