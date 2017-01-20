package controller.commands;

/**
 * The Class ExitCommand.
 */
public class ExitCommand extends Command
{
	public ExitCommand() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute()
	{
		System.out.println("Goodbye!");
		//we should call controller.stop (command controller)
	}

}