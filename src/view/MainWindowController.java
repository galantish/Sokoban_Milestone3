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
	@FXML private Sokoban sokoban;

	//Music
	@FXML private MediaView mediaView;
	private MediaPlayer mediaPlayer;
	private Media media;
	private boolean isLoadMusic;
	private boolean isStop;
	
	//Errors
	@FXML private Label status;
	
	//Steps
	@FXML private Text countText;

	//Timer
	@FXML private Text timerText;
	private Timer timer;
	private StringProperty CounterTime;
	private int count;
	private boolean isLoadFromGUI;
	
	//Stage
	private Stage primaryStage;
	
	//Finish
	private boolean isFinish;
	
	//Keyboard setting
	private KeySettings keySettings;
	
	public MainWindowController() 
	{
		this.status = new Label();
		this.CounterTime = new SimpleStringProperty();
		this.isLoadMusic = false;
		this.isStop = false;
		this.isFinish = false;
		this.count = 0;
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
	
	private void startTimer(int countndex) 
	{	
		this.count = countndex;
		this.timer = new Timer();	
		this.timerText.textProperty().bind(this.CounterTime);
		this.timer.scheduleAtFixedRate(new TimerTask() 
		{
			@Override
			public void run() 
			{
				CounterTime.set(" "+(++count));
			}
		}, 0, 1000);
	}
	
	public void stopTimer()
	{
		if(timer != null)
			timer.cancel();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		setFocus();
		sokoban.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sokoban.requestFocus());		
		sokoban.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent event) 
			{
				String command = null;
				status.setText("");
								
				//Change it.
				if(event.getCode() == keySettings.getMoveLeft())
					command = "move left";				
				else if(event.getCode() ==keySettings.getMoveRight())
					command = "move right";
				else if(event.getCode() == keySettings.getMoveUp())
					command = "move up";
				else if(event.getCode() == keySettings.getMoveDown())
					command = "move down";
				
				setChanged();
				notifyObservers(command);
			}
		});	
	}
	
	@Override
	public void displayLevel(Level theLevel) 
	{
		this.sokoban.setLevelData(theLevel.getLevelBoard());

		if(theLevel.isFinished() == true)
		{
			this.isFinish = false;
			finishLevel();
			stopTimer();
		}

		if(isLoadFromGUI == false)
		{
			startTimer(0);
			this.isLoadFromGUI = true;
		}
	}
	
	private void finishLevel()
	{
		if(this.isFinish == true)
			return;
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
		this.isFinish = true;
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
		
		if(isLoadMusic == false)
			playAutoMusic();

		this.isFinish = false;
		stopTimer();
		startTimer(0);
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
	
	public void playAutoMusic()
	{
		media = new Media(new File("./resources/Songs/Supaplex.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setOnEndOfMedia(null);
		isLoadMusic = true;
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
		if(isLoadMusic == false)
			return;
		
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
		
		startTimer(this.count);
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
	
	@Override
	public void displayError(String msg) 
	{
		status.setText("ERROR: " + msg);
	}
	
}
	
