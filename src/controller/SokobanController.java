package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import controller.commands.iCommand;
import controller.commands.iSokobanCommand;
import controller.commands.DisplayCommand;
import controller.commands.DisplayGUICommand;
import controller.commands.ExitCommand;
import controller.commands.LoadLevelCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveLevelCommand;
import model.MyModel;
import model.iModel;
import view.MyView;
import view.iView;

public class SokobanController implements Observer
{
	private CommandController controller;
	private iModel model;
	private iView view;
	private HashMap<String, iSokobanCommand> commands;

	public SokobanController(iModel model, iView view) 
	{
		this.model = model;
		this.view = view;
		this.controller = new CommandController();
		initCommands();
		System.out.println("Sokoban Controller was created!");
		controller.start();
	}

	private void initCommands()
	{
		this.commands = new HashMap<>();
		this.commands.put("load", new LoadLevelCommand(this));
		this.commands.put("save", new SaveLevelCommand(this));
		this.commands.put("move", new MoveCommand(this));
		this.commands.put("display", new DisplayCommand(this));
		this.commands.put("exit", new ExitCommand());
		this.commands.put("change", new DisplayGUICommand());
	}
	
	private String[] objectToStrong(Object arg)
	{
		String[] input = ((String)arg).toUpperCase().split(" ");	
		return input;
	}
	
	@Override
	public void update(Observable o, Object arg) 
	{
		if(o == view)
		{	
			String[] input = objectToStrong(arg);
			String commandName = input[0];
			String params = null;	
			if(input.length == 2)
				params = input[1];
			
			iSokobanCommand command = this.commands.get(commandName.toLowerCase());

			if(command == null || input.length > 2)
			{
				view.displayError("Command " + commandName + " not found.");
				return;
			}
			
			command.setParams(this, params);
			try 
			{
				controller.insertCommand(command);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}		
		}
		
		else if(o == model)
		{
			if(arg.equals("change"))
			{
				//System.out.println("--- change from model ---");
				//view.displayLevel(model.getCurrentLevel());
			}

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
