package controller.commands;

public abstract class Command implements iCommand
{
	private String params;
	
	@Override
	public void setParams(String params)
	{
		this.params = params;
	}
	
	public String getParams()
	{
		return this.params;
	}
	
}
