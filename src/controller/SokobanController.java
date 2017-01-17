package controller;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import controller.commands.CommandsFactory;
import controller.commands.iCommand;
import model.MyModel;
import view.MyView;

public class SokobanController implements Observer
{
	private CommandController commandController;
	private MyModel model;
	private MyView view;
	private CommandsFactory commandFactory;

	@Override
	public void update(Observable o, Object arg) 
	{
		if(o == view)
		{
			LinkedList<String> params = (LinkedList<String>) arg;			
			String commandName = params.removeFirst();			
			iCommand command = commandFactory.getCommand(commandName);
			if(command == null)
			{
				//v.ShowErrorMessage("ERROR: Invalid Command.");			
			}
			else
			{
				try 
				{
					commandController.insertCommand(command);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			
			//command.setParams(this);			
			//controller.insertCommand(command);
		}
		
		else if(o == model)
		{
			
		}	
		
	}

	public void setModel(MyModel model) 
	{
		this.model = model;
	}

	public void setView(MyView view) 
	{
		this.view = view;
	}

	public CommandController getCommandController() 
	{
		return commandController;
	}

	
	
}
