package view;

import java.util.Observable;
import java.util.Scanner;
import commons.Level;
import javafx.beans.property.StringProperty;

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
		char[][] levelArr = level.getLevelBoard();
		
		for(int i=0; i<level.getRow(); i++)
		{
			for(int j=0;j<level.getCol(); j++)	
				System.out.print(levelArr[i][j]);
			System.out.println();
		}
		
		System.out.println("Steps: " + level.getPlayersSteps());
	}

	@Override
	public void displayError(String msg) 
	{
		System.out.println("ERROR: " + msg);		
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

	@Override
	public void createBindSteps(StringProperty Counter) {
		// TODO Auto-generated method stub
		
	}
}
