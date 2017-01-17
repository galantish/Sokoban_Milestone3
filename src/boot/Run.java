package boot;

import java.io.IOException;

import controller.commands.CLI;
import view.display.MyDisplayer;

public class Run 
{
	public static void main (String[] args) throws ClassNotFoundException, IOException
	{		
		CLI cli = new CLI(new MyDisplayer());
		cli.Listen();
		
	}
}