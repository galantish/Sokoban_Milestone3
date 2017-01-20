package view;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import commons.Level;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowController extends Observable implements Initializable, iView
{
	private char[][] levelData = {
			{'#','#','#','#','#','#','#','#','#','#'},
			{'#',' ','@',' ',' ','#','#','#','#','#'},
			{'#',' ','#','#',' ','#','#','#','#','#'},
			{'#',' ','o',' ',' ',' ',' ',' ','#','#'},
			{'#',' ',' ',' ','#',' ','@',' ','#','#'},
			{'#','#',' ','#','#',' ','#','o','#','#'},
			{'#','#','#','#','#',' ','#','#','#','#'},
			{'#','#','#','#','#','A','#','#','#','#'}};
	
	@FXML private SokobanDisplayer levelDisplayer;
	@FXML private MediaView mediaView;
	private MediaPlayer mediaPlayer;
	private Media media;
	
	public MainWindowController()
	{
		this.levelDisplayer = new SokobanDisplayer();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		String path = new File("./resources/007.mp3").getAbsolutePath();
		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.setAutoPlay(true);
		
		levelDisplayer.setLevelData(levelData);
		levelDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->levelDisplayer.requestFocus());
		levelDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent event) 
			{
				String command = null;
				
				if(event.getCode() == KeyCode.LEFT)
				{
					command = "move left";
				}
				else if(event.getCode() == KeyCode.RIGHT)
				{
					command = "move right";
					//levelDisplayer.setPlayerPosition(r, c+1);
				}
				else if(event.getCode() == KeyCode.UP)
				{
					command = "move up";
					//levelDisplayer.setPlayerPosition(r-1, c);
				}
				else if(event.getCode() == KeyCode.DOWN)
				{
					command = "move down";
					//levelDisplayer.setPlayerPosition(r+1, c);
				}
				
				System.out.println(command);					
				setChanged();
				notifyObservers(command);				
			}
		});
	}
	
	@Override
	public void start()
	{
		System.out.println("Hello");
	}
	
	public void openFile()
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("Open level file");
		fc.setInitialDirectory(new File("./resources"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"), new ExtensionFilter("XML File", "*.xml"), new ExtensionFilter("Object File", "*.obj"));

		File choosenFile = fc.showOpenDialog(null);
		if(choosenFile != null)
		{
			System.out.println("load " + choosenFile.getPath());
			setChanged();
			notifyObservers("load " + choosenFile.getPath());
		}
	}
	
	public void saveFile()
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("Save level file");
		fc.setInitialDirectory(new File("./resources"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"), new ExtensionFilter("XML File", "*.xml"), new ExtensionFilter("Object File", "*.obj"));
		
		File choosenFile = fc.showSaveDialog(null);
		if(choosenFile != null)
		{
			System.out.println("save " + choosenFile.getPath());
			//notifyObservers("save " + choosenFile.getPath());
		}
	}

	@Override
	public void displayLevel(Level theLevel) 
	{

		
	}

	@Override
	public void displayError(String msg) 
	{

		
	}
	
}
