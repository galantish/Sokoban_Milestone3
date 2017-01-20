package boot;

import java.io.IOException;
import controller.SokobanController;
import controller.server.ClientHandler;
import model.MyModel;
import view.CLI;
import view.Main;
import view.MainWindowController;

public class Run 
{
	public static void main (String[] args) throws ClassNotFoundException, IOException
	{		
		//MainWindowController view = new MainWindowController();	
		CLI view = new CLI();
		MyModel model = new MyModel();
		SokobanController sokobanController = new SokobanController(model, view);

		model.addObserver(sokobanController);
		view.addObserver(sokobanController);	
		
		view.start();
		
	}
}