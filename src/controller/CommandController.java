package controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.commands.Command;
import controller.commands.iCommand;

public class CommandController 
{
	private BlockingQueue<iCommand> commandQueue;
	private boolean stop;
	
	public CommandController() 
	{
		this.stop = false;
		commandQueue = new ArrayBlockingQueue<iCommand>(20);
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
						iCommand command = getCommandQueue().poll(1, TimeUnit.SECONDS);				
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

	public BlockingQueue<iCommand> getCommandQueue() 
	{
		return commandQueue;
	}

	public void setCommandQueue(BlockingQueue<iCommand> commandQueue) 
	{
		this.commandQueue = commandQueue;
	}
	
	
}
