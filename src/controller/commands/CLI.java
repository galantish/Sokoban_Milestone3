package controller.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import controller.commands.*;
import model.data.levels.Level;
import view.display.iDisplayer;

/**
 * The Class CLI.
 */
public class CLI 
{
	/** the displayer. */
	private iDisplayer displayer;
	
	/** The level. */
	private Level level;
	
	/** The boolean flag. */
	private boolean close;

	/**
	 * CLI - initializes the CLI by displayer.
	 *
	 * @param displayer
	 *            the level displayer
	 */
	public CLI(iDisplayer displayer)
	{
		this.displayer = displayer;
		this.level = new Level();
		this.close = false;
	}
	
	/**
	* GetDisplayer.
	* 
	* @return level displayer
	*/
	public iDisplayer getDisplayer()
	{
		return displayer;
	}

	/**
	* SetDisplayer - initializes a displayer to the local displayer
	* 
	* @param displayer
	* 			level displayer
	*/
	public void setDisplayer(iDisplayer displayer)
	{
		this.displayer = displayer;
	}

	/**
	* GetLevel - return the local level
	* 
	* @return the current level
	*/
	public Level getLevel()
	{
		return level;
	}

	/**
	* SetLevel - initializes a level to the local level
	* 
	* @param level
	* 			a level
	*/
	public void setLevel(Level level)
	{
		this.level = level;
	}

	
	/**
	* IsClose - return the boolean flag
	* 
	* @return close (boolean flag)
	*/
	public boolean isClose()
	{
		return close;
	}

	/**
	* SetClose - initializes the boolean flag
	* 
	* @param close
	* 			close (boolean flag)
	*/
	public void setClose(boolean close)
	{
		this.close = close;
	}

	/**
	 * StartMenu - presents a menu to the user
	 */
	public void StartMenu()
	{
		System.out.println("*************************************************");
		System.out.println("******************** SOKOBAN ********************");
		System.out.println("*************************************************");
		System.out.println("Please choose command to run:");
		System.out.println("> Load 'filename'\n> Display\n> Move {up,down,left,right}\n> Save 'filename'\n> Exit\n");
		System.out.println("*************************************************");
	}
	
	/**
	 * Listen - the input method for the user
	 */
	public void Listen()
	{
		//A path for Load and Save commands
		String filePath = null;		
		String input = null;
		String moveType = null;
		
		CommandsFactory comFactory = new CommandsFactory(this.level);
		
		//Menu
		StartMenu();

		//While the level is not finish or the user enters Exit command
		while(!close)
		{
			//Input from the user
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("> ");		

			try
			{
				//Reading the command from the user
				input = bf.readLine();		
				String[] inputArgs = input.split(" ");
				String strCommand = inputArgs[0];
								
				//Using command factory to create the fitting command
				iCommand command = comFactory.getCommand(strCommand);
				
				//If the user entered a wrong command - throw error
				if(command == null || inputArgs.length > 2)
					throw new IOException("ERROR: Invalid command.");
				
				
				
				//If the command is a type of Load we need the path level file				
				if(command instanceof LoadLevelCommand)
				{
					//If the user entered a wrong path - throw error
					if(inputArgs.length <= 1)
						throw new IOException("ERROR: Invalid path.");
										
					//Extracting the path from the all load command and set the path
					filePath = inputArgs[1];
					((LoadLevelCommand)command).setPath(filePath);	
					this.level.setTime(System.currentTimeMillis());
				}
				
				else if(command instanceof SaveLevelCommand)
				{
					//If the user entered a wrong path - throw error
					if(inputArgs.length <= 1)
						throw new IOException("ERROR: Invalid path.");
					
					//Extracting the path from the all load command and set the path
					filePath = inputArgs[1];
					((SaveLevelCommand)command).setPath(filePath);			
				}
					
				else if (command instanceof MoveCommand) 
				{
					//If the user entered a wrong type of move - throw error
					if(inputArgs.length <= 1 || inputArgs.length > 2)
						throw new IOException("ERROR: Invalid move type.");
					
					//Extracting the type of move from the all load command and set the path
					moveType = inputArgs[1];
					((MoveCommand) command).setItem(this.level.getPlayers().get(0));
					((MoveCommand) command).setMoveType(moveType);			
				}				
				
				//command.setParams(inputArgs[1]);			
				command.Execute();
				
				//If the level is not initialized
				if(this.level.isEmpty() == true)
				{
					if(!(command instanceof LoadLevelCommand) && !(command instanceof ExitCommand))
						throw new IOException("ERROR: You need to load file first.");
					continue;
				}
				
				//Printing the level after all move commands
				if((command instanceof MoveCommand) && ((MoveCommand) command).isValidMoveType(moveType.toLowerCase()))
				{
					System.out.println("Player's steps: " + this.level.getPlayersSteps());
					comFactory.getCommand("display").Execute();
				}
				
				if(command instanceof ExitCommand)
					close = true;
				
				//If the user solve the level
				if(level.isFinished() && level.numOfPlayers() != 0)
				{
					close = true;
					long startTime = level.getTime();
					long endTime   = System.currentTimeMillis();
					long totalTime = (long) ((endTime - startTime) * 0.001);
					System.out.println("***************************************************************");
					System.out.println("Congratulations, you finish the game!!!");
					System.out.println("Total Steps: " + this.getLevel().getPlayersSteps());
					System.out.println("Total Time: " + totalTime + " seconds.");
					System.out.println("If you want you can start a new level by loading another level.");
					System.out.println("***************************************************************");
				}
			} 
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}	
		}
		
	}	
}
