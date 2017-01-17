package controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.commands.iCommand;

public class CommandController 
{
	private BlockingQueue<iCommand> commandQueue;
	private boolean isFinish = false;
	
	public CommandController() 
	{
		commandQueue = new LinkedBlockingQueue<iCommand>();
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
				while(!isFinish)//will run until the user enter exit or the game is finish
				{
					try 
					{
						iCommand command = commandQueue.poll(1, TimeUnit.SECONDS);//set time unit = 1 second if there are no commands in the queue
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
		isFinish = true;
	}
}
