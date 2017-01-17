package model;

import java.util.Observable;
import java.util.logging.Level;

import controller.SokobanController;

public class MyModel extends Observable implements iModel 
{

	public MyModel(SokobanController controller) 
	{
		System.out.println("MyVModel was created.");
	}
	
	@Override
	public boolean move(String direction) 
	{

		return false;
	}

	@Override
	public void displayLevel() 
	{

		
	}

	@Override
	public void saveLevel(String path) 
	{

		
	}

	@Override
	public Level loadLevel(String path) 
	{

		return null;
	}

	@Override
	public Level solveLevel(Level level) 
	{

		return null;
	}

	@Override
	public Level getCurrentLevel() 
	{

		return null;
	}

}
