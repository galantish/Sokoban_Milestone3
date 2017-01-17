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
	CommandController commandController;
	MyModel model;
	MyView view;
	CommandsFactory commandFactory;

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
				//v.ShowErrorMessage();			
			}
			//command.setParams(params);			
			//controller.insertCommand(command);
		}
		
		else if(o == model)
		{
			
		}	
		
	}
}
