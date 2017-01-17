package controller.commands;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import controller.SokobanController;
import model.data.levels.Level;
import model.data.levels.LevelLoaderFactory;
import model.data.levels.iLevelLoader;

/**
 * The Class SaveLevelCommand.
 */
public class SaveLevelCommand implements iSokobanCommand
{
	/** The level. */
	private Level level;
	
	/** A path of a file's location. */
	private String path;
	
	/**
	 * Initializes the save level command.
	 * @param level
	 * 			the level
	 * @param exitCommand
	 * 			the exit command
	 */
	public SaveLevelCommand(Level level)
	{
		this.level = level;
	}

	/** Execute the command. */
	@Override
	public void execute()
	{
		//Creating a factory object in order to fitting the type of a level file
		LevelLoaderFactory loaderFactory = new LevelLoaderFactory();
		iLevelLoader levelsaver = loaderFactory.CreateLevelLoader(path);
		
		try
		{
			if(this.level.isEmpty() == true)
				throw new IOException("ERROR: invalid path.");
			
			 OutputStream os = new FileOutputStream(path);
			
			//Saves a level
			levelsaver.SaveLevel(this.level, os);
			System.out.println("The level is saved successfully.");

		} 
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}	
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

	public Level getLevel() 
	{
		return level;
	}

	public void setLevel(Level level) 
	{
		this.level = level;
	}
}
