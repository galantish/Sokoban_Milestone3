package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import common.Level;
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
		System.out.println("MyVModel was created.");
	}
	
	@Override
	public void loadLevel(String path) 
	{
		iLevelLoader levelLoader = this.levelExtension.CreateLevelLoader(path.toLowerCase());
		//if(levelLoader == null)
				//throw new IOException("ERROR: invalid path.");	
		try 
		{
			setTheLevel(levelLoader.LoadLevel(new FileInputStream(new File(path))));
		} 
		catch (ClassNotFoundException e) 
		{
			//System.out.println("error");
		} 
		catch (FileNotFoundException e) 
		{
			//System.out.println("error");
		} 
		catch (IOException e) 
		{
			//System.out.println("error");

		}
		
		setChanged();
		notifyObservers("change");
	}
	
	@Override
	public void saveLevel(String path) 
	{
		iLevelLoader levelSaver = this.levelExtension.CreateLevelLoader(path.toLowerCase());
		//if(levelLoader == null)
		//throw new IOException("ERROR: invalid path.");
		
		try
		{
			levelSaver.SaveLevel(getTheLevel(), new FileOutputStream(new File(path)));				
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		setChanged();
		notifyObservers("change");
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
				notifyObservers("error");;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		setChanged();
		notifyObservers("change");
	}

//	@Override
//	public String displayLevel() 
//	{
//		
//		return null;
//	}
	
	@Override
	public Level getCurrentLevel() 
	{
		return theLevel;
	}

	public Level getTheLevel() 
	{
		return theLevel;
	}

	public void setTheLevel(Level theLevel) 
	{
		this.theLevel = theLevel;
	}	
}
