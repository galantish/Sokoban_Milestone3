package view;

import javafx.application.Application;
import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import commons.Level;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.MeshView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
public class MyView extends Observable implements Initializable, iView
{
	private char[][] levelData;
	
	private MediaPlayer mediaPlayer;
	private Media media;
	@FXML private MediaView mediaView;

	@FXML 
	private SokobanDisplayer levelDisplayer;
			
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
				}
				else if(event.getCode() == KeyCode.UP)
				{
					command = "move up";
				}
				else if(event.getCode() == KeyCode.DOWN)
				{
					command = "move down";
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
		Thread t = new Thread(new Runnable() 
		{			
			@Override
			public void run() 
			{
				Application.launch(Main.class);		
			}
		});
		t.start();
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
			setChanged();
			notifyObservers("save " + choosenFile.getPath());
		}
	}

	@Override
	public void displayLevel(Level theLevel) 
	{
		char[][] levelArr = theLevel.getLevelBoard();
		this.levelData = levelArr;
		this.levelDisplayer.setRowAndCol(this.levelData.length, this.levelData[0].length);
		this.levelDisplayer.setLevelData(this.levelData);
		this.levelDisplayer.redraw();		
	}

	@Override
	public void displayError(String msg) 
	{
		
	}
	
	public void exit()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to exit?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
		    System.out.println("Goodbbye!");
			notifyObservers("exit");
		} 
		else
			System.out.println("Good!");
	}
}
