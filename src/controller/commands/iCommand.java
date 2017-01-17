package controller.commands;

/**
* The Interface iCommand - Interface that holds the function that all
* the commands should implement.
*/
public interface iCommand
{
	public void Execute();
	public void setParams(String args);
}
