package controller.commands;

import java.util.HashMap;

import model.data.levels.Level;

/**
* The Class CommandsFactory - The class where we hold the commands list.
*/
public class CommandsFactory
{
	/** The level. */
	private Level level;
		
	/** The commands list. */
	private HashMap<String, commandCreator> commandHash;

	/**
	* Gets the level and exit command, and initializes the commands list.
	* 
	* @param level
	* 			the level
	* @param exitCommand
	* 			exit command
	* 
	*/
	public CommandsFactory(Level level)
	{
		this.level = level;
		this.commandHash = new HashMap<String, commandCreator>();
		this.commandHash.put("load", new CreateLoadLevelCommand());
		this.commandHash.put("save", new CreateSaveLevelCommand());
		this.commandHash.put("move", new CreateMoveCommand());
		this.commandHash.put("exit", new CreateExitCommand());
		this.commandHash.put("display", new CreateDisplayCommand());
	}
	
	/**
	* The interface commandCreator - An interface that all the command will implement.
	*/
	private interface commandCreator
	{
		public iCommand createCommand();
	}
	
	/**
	* The Class CreateLoadLevelCommand - The Class that create the "Load level" command.
	*/
	private class CreateLoadLevelCommand implements commandCreator
	{
		@Override
		public iCommand createCommand()
		{
			return new LoadLevelCommand(level);
		}	
	}
	
	/**
	* The Class CreateSaveLevelCommand - The Class that create the "Save level" command.
	*/
	private class CreateSaveLevelCommand implements commandCreator
	{
		@Override
		public iCommand createCommand()
		{
			return new SaveLevelCommand(level);
		}		
	}
	
	/**
	* The Class CreateMoveCommand - The Class that create the "Move" command.
	*/
	private class CreateMoveCommand implements commandCreator
	{
		@Override
		public iCommand createCommand()
		{
			return new MoveCommand(level);
		}
	}
	
	
	/**
	* The Class CreateDisplayCommand - The Class that create the "Display" command.
	*/
	private class CreateDisplayCommand implements commandCreator
	{
		@Override
		public iCommand createCommand()
		{
			return new DisplayCommand(level);
		}
	}
	
	/**
	* The Class CreateExitCommand - The Class that create the "Exit" command.
	*/
	private class CreateExitCommand implements commandCreator
	{
		@Override
		public iCommand createCommand()
		{
			return new ExitCommand(level);
		}
	}	
	
	/**
	 * Gets string from the user (not case-sensitive).
	 *
	 * @param input
	 * 			user's input (a command)
	 * @return the fitting create command.
	 */
	public iCommand getCommand(String input)
	{
		if(commandHash.get(input.toLowerCase()) == null)
			return null;
		
		return commandHash.get(input.toLowerCase()).createCommand();
	}
}