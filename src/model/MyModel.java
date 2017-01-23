package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import commons.Level;
import model.data.levels.iLevelLoader;
import model.factories.LevelsExtensionFactory;
import model.policy.MySokobanPolicy;

public class MyModel extends Observable implements iModel 
{
	private Level theLevel;
	private MySokobanPolicy policy;
	private LevelsExtensionFactory levelExtension;
	
	public MyModel() 
	{
		this.theLevel = new Level();
		this.levelExtension = new LevelsExtensionFactory();
		this.policy = new MySokobanPolicy();
	}
	
	@Override
	public void loadLevel(String path) 
	{
		Thread t = new Thread(new Runnable() 
		{		
			@Override
			public void run() 
			{
				iLevelLoader levelLoader = getLevelExtension().CreateLevelLoader(path.toLowerCase());
				//if(levelLoader == null)
						//throw new IOException("ERROR: invalid path.");	
				try 
				{
					setTheLevel(levelLoader.LoadLevel(new FileInputStream(new File(path))));
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
		});
		
		t.start();
		try 
		{
			t.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		setChanged();
		notifyObservers("change");
	}
	
	@Override
	public void saveLevel(String path) 
	{
		Thread t = new Thread(new Runnable() 
		{		
			@Override
			public void run() 
			{
				iLevelLoader levelSaver = getLevelExtension().CreateLevelLoader(path.toLowerCase());
				//if(levelLoader == null)
						//throw new IOException("ERROR: invalid path.");	
				try 
				{
					levelSaver.SaveLevel(getTheLevel(), new FileOutputStream(new File(path)));				
				}  
				catch (FileNotFoundException e) 
				{
					//System.out.println("error");
				} 
				catch (IOException e) 
				{
					//System.out.println("error");

				}
			}
		});
		
		t.start();
	}
	
	@Override
	public void move(String moveType) 
	{
		try
		{
			if(this.theLevel.isEmpty() == true)
				throw new Exception("ERROE: Invalid level.");
			
			if(moveType == null)
				throw new Exception("ERROE: Invalid move type.");

			boolean isCanMove = policy.move(this.theLevel, moveType);

			if(isCanMove == false)
				return;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		setChanged();
		notifyObservers("change");
	}
	
	@Override
	public Level getCurrentLevel() 
	{
		return theLevel;
	}

	@Override
	public int getSteps() 
	{
		return this.theLevel.getPlayersSteps();				
	}	
	
	public Level getTheLevel() 
	{
		return theLevel;
	}

	public void setTheLevel(Level theLevel) 
	{
		this.theLevel = theLevel;
	}

	public LevelsExtensionFactory getLevelExtension() 
	{
		return levelExtension;
	}

	public void setLevelExtension(LevelsExtensionFactory levelExtension) 
	{
		this.levelExtension = levelExtension;
	}
}
