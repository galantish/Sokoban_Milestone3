package view;

import java.io.File;
import java.util.Observable;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowController extends Observable
{
	public void start()
	{
		System.out.println("hello");
	}
	
	public void openFile()
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("Open level file");
		fc.setInitialDirectory(new File("./resources"));
		fc.setSelectedExtensionFilter(new ExtensionFilter("txt", "obj", "xml"));
		File choosenFile = fc.showOpenDialog(null);
		if(choosenFile != null)
		{
			//notifyObservers("load" + choosenFile.getPath());
			System.out.println("load " + choosenFile.getPath());

		}
	}
}
