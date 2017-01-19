package view;

import java.util.Observable;
import common.Level;

public class MyView implements iView
{

	@Override
	public void displayLevel(Level level) 
	{
		String levelToString = level.toString();
		System.out.println(levelToString);
		
	}

	@Override
	public void displayError(String msg) 
	{

		
	}

	@Override
	public void start() 
	{

		
	}




}
