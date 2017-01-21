package controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import controller.commands.Command;
import controller.commands.DisplayCommand;
import controller.commands.DisplayGUICommand;
import controller.commands.ExitCommand;
import controller.commands.LoadLevelCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveLevelCommand;
import controller.commands.iCommand;
import model.iModel;
import view.iView;

public class SokobanController implements Observer
{
	private CommandController controller;
	private iModel model;
	private iView view;
	private HashMap<String, iCommand> commands;

	public SokobanController(iModel model, iView view) 
	{
		this.model = model;
		this.view = view;
		this.controller = new CommandController();
		initCommands();
		controller.start();
	}

	private void initCommands()
	{
		this.commands = new HashMap<>();
		this.commands.put("load", new LoadLevelCommand(model));
		this.commands.put("save", new SaveLevelCommand(model));
		this.commands.put("move", new MoveCommand(model));
		this.commands.put("display", new DisplayCommand(model, view));
		this.commands.put("exit", new ExitCommand(controller));
		this.commands.put("change", new DisplayGUICommand(model, view));
		//this.commands.put("change", new DisplayCommand(model, view));
	}
	
	private String[] objectToStrong(Object arg)
	{
		String[] input = ((String)arg).toUpperCase().split(" ");	
		return input;
	}
	
	@Override
	public void update(Observable o, Object arg) 
	{				
		if(arg == null)
		{
			view.displayError("Invalid Key.");
			return;
		}
		
		String[] input = objectToStrong(arg);
		String commandName = input[0];
		String params = null;	
		if(input.length > 1)
			params = input[1];
		
		iCommand command = this.commands.get(commandName.toLowerCase());
		
		if(command == null || input.length > 2)
		{
			view.displayError("Command " + commandName + " not found.");
			return;
		}
		
		command.setParams(params);

		try 
		{
			controller.insertCommand(command);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}			
			
	}

	public CommandController getCommandController() 
	{
		return controller;
	}

	public iModel getIModel() 
	{
		return model;
	}

	public iView getIView() 
	{
		return view;
	}
	
	
}
