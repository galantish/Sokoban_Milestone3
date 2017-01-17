package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import controller.SokobanController;
import model.data.items.Box;
import model.data.items.Player;
import model.data.items.Target;
import model.data.items.iMoveable;
import model.data.items.iUnmoveable;
import model.data.levels.Level;
import model.data.levels.LevelLoaderFactory;
import model.data.levels.iLevelLoader;
import model.policy.MySokobanPolicy;

/**
 * The Class LoadLevelCommand.
 */
public class LoadLevelCommand implements iSokobanCommand
{
	/** The level. */
	private Level level;
	
	/** A path of a file's location. */
	private String path;

	
	/**
	 * Initializes the level load command.
	 *
	 * @param level
	 *            the level
	 * @param exitCommand
	 * 			an exit command
	 */
	public LoadLevelCommand(Level level)
	{
		this.level = level;
		this.path = "";
	}

	/** Execute the command. */
	@Override
	public void execute()
	{
		//Creating a factory object in order to fitting the type of a level file
		LevelLoaderFactory loaderFactory = new LevelLoaderFactory();
		iLevelLoader levelLoader = loaderFactory.CreateLevelLoader(this.path);
			
		try
		{
			InputStream is = new FileInputStream(this.path);
			
			if(levelLoader == null)
				throw new IOException("ERROR: invalid path.");
	
			//Ask vered why this is not working without that!!!!!
			
			Level tempLevel = new Level(levelLoader.LoadLevel(is));
					
			this.level.setBoard(tempLevel.getBoard());
			this.level.setRow(tempLevel.getRow());
			this.level.setCol(tempLevel.getCol());
			this.level.setItemsOnBoard(tempLevel.getItemsOnBoard());
			this.level.setBoard(tempLevel.getBoard());
			this.level.setPlayers(tempLevel.getPlayers());
			this.level.setBoxes(tempLevel.getBoxes());
			this.level.setTargets(tempLevel.getTargets());
			this.level.setPlayersSteps(tempLevel.getPlayersSteps());
			this.level.setPolicy(tempLevel.getPolicy());
			this.level.setTime(tempLevel.getTime());

			System.out.println("The level is loaded successfully.");
			
		} 
		
		catch (ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
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
	
	String getPath()
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
