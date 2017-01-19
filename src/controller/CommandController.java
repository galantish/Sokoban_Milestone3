package controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import controller.commands.iCommand;

public class CommandController 
{
	private BlockingQueue<iCommand> commandQueue;
	private boolean stop;
	
	public CommandController() 
	{
		this.stop = false;
		commandQueue = new LinkedBlockingQueue<iCommand>();
		System.out.println("Command controller was created!");
	}
	
	public void insertCommand(iCommand command) throws InterruptedException
	{
		commandQueue.put(command);
	}
	
	public void start()
	{
		Thread t = new Thread(new Runnable() 
		{	
			@Override
			public void run() 
			{
				while(!stop)//will run until the user enter exit or the game is finish
				{
					try 
					{
						iCommand command = commandQueue.take();					
						if(command != null)				
							command.execute();	
					} 
					catch (InterruptedException e) 
					{
						System.out.println(e.getMessage());
					}
				}
			}
		});
		
		t.start();
	}
	
	public void stop()
	{
		this.stop = true;
	}
}
