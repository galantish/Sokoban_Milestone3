package view;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import commons.Level;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowController extends Observable implements iView, Initializable
{
	//SokobanDisplayer
	@FXML private Sokoban sokoban;

	//Music
	@FXML private MediaView mediaView;
	private MediaPlayer mediaPlayer;
	private Media media;
	private boolean isLoadMusic = false;
	private boolean isMute = false;
	@FXML private Label playerSteps;
	@FXML private Label status;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		sokoban.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sokoban.requestFocus());		
		sokoban.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent event) 
			{
				String command = null;
				status.setText("");

				//Change it.
				if(event.getCode() == KeyCode.LEFT)
					command = "move left";				
				else if(event.getCode() == KeyCode.RIGHT)
					command = "move right";
				else if(event.getCode() == KeyCode.UP)
					command = "move up";
				else if(event.getCode() == KeyCode.DOWN)
					command = "move down";
				
				setChanged();
				notifyObservers(command);	
			}
		});	
	}
	public void openFile()
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("Load level");
		fc.setInitialDirectory(new File("./resources"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"), new ExtensionFilter("XML File", "*.xml"), new ExtensionFilter("Object File", "*.obj"));

		File choosenFile = fc.showOpenDialog(null);
		if(choosenFile != null)
		{
			setChanged();
			notifyObservers("load " + choosenFile.getPath());
		}

		setFocus();
	}
	
	private void setFocus()
	{
		sokoban.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) 
            {
                Platform.runLater(new Runnable()
                {
                    public void run() 
                    {
                    	sokoban.requestFocus();
                    }
                });                    
            }
        });
	}
	
	public void saveFile()
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("Save level");
		fc.setInitialDirectory(new File("./resources"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"), new ExtensionFilter("XML File", "*.xml"), new ExtensionFilter("Object File", "*.obj"));
		File choosenFile = fc.showSaveDialog(null);
		if(choosenFile != null)
		{
			setChanged();
			notifyObservers("save " + choosenFile.getPath());
		}
	}
	
	public void playMusic()
	{
		
		FileChooser fc = new FileChooser();
		fc.setTitle("Open Song file");
		fc.setInitialDirectory(new File("./resources"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("MP3", "*.mp3"), new ExtensionFilter("MP4", "*.mp4"));

		File choosenFile = fc.showOpenDialog(null);
		
		if(choosenFile != null)
		{
			if(isLoadMusic == true )
				mediaPlayer.stop();
			
			String path = choosenFile.getAbsolutePath();
			media = new Media(new File(path).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			mediaView.setMediaPlayer(mediaPlayer);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setOnEndOfMedia(null);
			isLoadMusic = true;
		}
		
	}
	
	public void stopMusic()
	{
		if(isMute == false)
		{	
			mediaPlayer.setVolume(0);
			isMute = true;
		}
		else
		{
			mediaPlayer.setVolume(20);
			isMute = false;
		}
	
	}
	
	public void exit()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to exit?");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get().getText().toUpperCase().equals("OK"))
		{
			setChanged();
			notifyObservers("exit");
		}

	}
	
	@Override
	public void displayLevel(Level theLevel) 
	{
		this.sokoban.setLevelData(theLevel.getLevelBoard());
		this.sokoban.redraw();
	}
	
	@Override
	public void displayError(String msg) 
	{
		status.setText(msg);
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
}
	
