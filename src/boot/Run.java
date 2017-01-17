package boot;

import java.io.IOException;

import controller.SokobanController;
import controller.commands.CLI;
import model.MyModel;
import view.MyView;
import view.display.MyDisplayer;

public class Run 
{
	public static void main (String[] args) throws ClassNotFoundException, IOException
	{		
		SokobanController controller = new SokobanController();
		MyView view = new MyView(controller);
		MyModel model = new MyModel(controller);
		
		controller.setModel(model);
		controller.setView(view);	
		
		//controller.getCommandController().start();
		
		CLI cli = new CLI(new MyDisplayer());
		cli.Listen();
		
	}
}