package boot;

import java.io.IOException;
import controller.SokobanController;
import model.MyModel;
import view.CLI;
import view.Main;
import view.MainWindowController;
import view.SokobanDisplayer;

public class Run 
{
	public static void main (String[] args) throws ClassNotFoundException, IOException
	{		
		//MyView view = new MyView();
		CLI view = new CLI();
		
		//MainWindowController view = new MainWindowController();
		
		MyModel model = new MyModel();
		SokobanController sokobanController = new SokobanController(model, view);

		model.addObserver(sokobanController);
		view.addObserver(sokobanController);
		
		
		view.start();
		
	}
}