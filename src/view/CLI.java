package view;

import java.util.Observable;
import java.util.Scanner;
import common.Level;

/**
 * The Class CLI.
 */
public class CLI extends Observable implements iView
{
	private boolean close;

	public void StartMenu()
	{
		System.out.println("*************************************************");
		System.out.println("******************** SOKOBAN ********************");
		System.out.println("*************************************************");
		System.out.println("Please choose command to run:");
		System.out.println("> Load 'filename'\n> Display\n> Move {up,down,left,right}\n> Save 'filename'\n> Exit\n");
		System.out.println("*************************************************");
	}
	
	@Override
	public void start() 
	{		
		//Menu
		StartMenu();
		Scanner scanner = new Scanner(System.in);
		
		Thread t = new Thread(new Runnable() 
		{			
			@Override
			public void run() 
			{
				while(true)
				{
					String command = scanner.nextLine();
					
					setChanged();
					notifyObservers(command);
					
					if(command.equals("exit"))
						break;
				}
			}
		});
			
		t.start();
	}
	
	public void finish()
	{

//				if(level.isFinished() && level.numOfPlayers() != 0)
//				{
//					close = true;
//					long startTime = level.getTime();
//					long endTime   = System.currentTimeMillis();
//					long totalTime = (long) ((endTime - startTime) * 0.001);
//					System.out.println("***************************************************************");
//					System.out.println("Congratulations, you finish the game!!!");
//					System.out.println("Total Steps: " + this.getLevel().getPlayersSteps());
//					System.out.println("Total Time: " + totalTime + " seconds.");
//					System.out.println("If you want you can start a new level by loading another level.");
//					System.out.println("***************************************************************");
//				}		
	}	

	public boolean isClose()
	{
		return close;
	}

	public void setClose(boolean close)
	{
		this.close = close;
	}

	@Override
	public void displayLevel(Level level) 
	{
		String levelToString = level.toString();
	}

	@Override
	public void displayError(String msg) 
	{
		System.out.println("ERROR: " + msg);
		
	}



}
