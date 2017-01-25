package view;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import commons.Level;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainWindowController extends Observable implements iView, Initializable
{
	//SokobanDisplayer
	@FXML private SokobanDisplayer sokobanDisplayer;

	//Music
	@FXML private MediaView mediaView;
	private MediaPlayer mediaPlayer;
	private Media media;
	private boolean isStop;
	
	//Errors
	@FXML private Label status;
	
	//Steps
	@FXML private Text countText;

	//Timer
	@FXML private Text timerText;
	private Timer timer;
	private StringProperty CounterTime;
	private int seconds;
	private int minutes;
	private boolean isLoadFromGUI;
	
	//Stage
	private Stage primaryStage;
	
	//Keyboard setting
	private KeySettings keySettings;
	
	public MainWindowController() 
	{
		this.status = new Label();
		this.CounterTime = new SimpleStringProperty();
		this.isStop = false;
		this.seconds = 0;
		this.minutes = 0;
		this.isLoadFromGUI = false;
		this.keySettings = initKeySetting("./resources/Settings/keySettings.xml");
	}
	
 	private KeySettings initKeySetting(String path)
 	{
 		XMLDecoder xmlDecoder;
 		KeySettings keySetting = null;
 		
 		try 
 		{
			xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File(path))));
	 		keySetting = (KeySettings) xmlDecoder.readObject();
	 		xmlDecoder.close();
		} 
 		catch (FileNotFoundException e) 
 		{
			e.printStackTrace();
		}
 		
 		return keySetting;
 	}
	
	@Override
	public void setPrimaryStage(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		exitPrimaryStage(this.primaryStage);
	}
 	
	@Override
	public void exitPrimaryStage(Stage primaryStage)
	{
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() 
		{
			@Override
			public void handle(WindowEvent event) 
			{
				setChanged();
				notifyObservers("exit");
			}
		});
	}
	
	@Override
	public void createBindSteps(StringProperty Counter)
	{
		this.countText.textProperty().bind(Counter);
	}
	
	private void startTimer(int sec, int min) 
	{	
		this.seconds = sec;
		this.minutes = min;
		this.timer = new Timer();	
		this.timerText.textProperty().bind(this.CounterTime);
		this.timer.scheduleAtFixedRate(new TimerTask() 
		{
			@Override
			public void run() 
			{
				seconds++;
				if(seconds > 59)
				{
					minutes++;
					seconds = 0;
				}
				if(minutes < 10)
				{
					if(seconds < 10)
						CounterTime.set("0" + (minutes) + ":0" + (seconds));
					else
						CounterTime.set("0" + (minutes) + ":" + (seconds));
				}
				else
					CounterTime.set("" + (minutes) + ":" + (seconds));
			}
		}, 0, 1000);
	}
	
	private void stopTimer()
	{
		if(timer != null)
			timer.cancel();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		setFocus();
		playAutoMusic();
		sokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sokobanDisplayer.requestFocus());		
		sokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent event) 
			{
				String command = null;
				status.setText("");
				
				//Change it.
				if(event.getCode() == keySettings.getMoveLeft())
					command = "move left";				
				else if(event.getCode() == keySettings.getMoveRight())
					command = "move right";
				else if(event.getCode() == keySettings.getMoveUp())
					command = "move up";
				else if(event.getCode() == keySettings.getMoveDown())
					command = "move down";
				else
				{
					command = null;
					displayError("Invalid key.");
				}
				
				if(command != null)
				{
					setChanged();
					notifyObservers(command);
				}
			}
		});	
	}
	
	@Override
	public void displayLevel(Level theLevel) 
	{
		this.sokobanDisplayer.setLevelData(theLevel.getLevelBoard());

		if(theLevel.isFinished() == true)
		{
			finishLevel();
			stopTimer();
		}

		if(isLoadFromGUI == false)
		{
			startTimer(0,0);
			this.isLoadFromGUI = true;
		}
	}
	
	private void finishLevel()
	{
		Platform.runLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Level complated");
				alert.setHeaderText("Congratulations! You win!!");
				alert.setContentText("Steps: " + countText.getText() + "\nTime: " + timerText.getText() + " seconds.");
				alert.show();
			}
		});
		stopTimer();
	}
	
	public void openFile()
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("Load level");
		fc.setInitialDirectory(new File("./resources"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"), new ExtensionFilter("XML File", "*.xml"), new ExtensionFilter("Object File", "*.obj"));

		File choosenFile = fc.showOpenDialog(this.primaryStage);
		if(choosenFile == null)
			return;
		
		setChanged();
		notifyObservers("load " + choosenFile.getPath());
		this.isLoadFromGUI = true;

		stopTimer();
		startTimer(0,0);
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
	
	public void exit()
	{
		stopTimer();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to exit?");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK)
		{
			setChanged();
			notifyObservers("exit");
			Platform.exit();
		}
		
		startTimer(this.seconds, this.minutes);
	}
	
	public void stopMusic()
	{
		if(isStop == false)
		{	
			mediaPlayer.pause();
			isStop = true;
		}
		else
		{
			mediaPlayer.play();
			isStop = false;
		}
	}
	
	private void playAutoMusic()
	{
		media = new Media(new File("./resources/Songs/Supaplex.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setOnEndOfMedia(null);
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
			stopMusic();
			String path = choosenFile.getAbsolutePath();
			media = new Media(new File(path).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			mediaView.setMediaPlayer(mediaPlayer);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setOnEndOfMedia(null);
		}
	}
	
	@Override
	public void displayError(String msg) 
	{
		Platform.runLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				status.setText("ERROR: " + msg);
			}
		});
	}
	
	private void setFocus()
	{
		sokobanDisplayer.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) 
            {
                Platform.runLater(new Runnable()
                {
                    public void run() 
                    {
                    	sokobanDisplayer.requestFocus();
                    }
                });                    
            }
        });
	}
}
