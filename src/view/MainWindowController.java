package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import commons.Level;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	
	//Errors
	@FXML private Label status;
	
	//Steps
	@FXML private Text countText;

	//Timer
	@FXML private Text timerText;
	private Timer timer;
	private StringProperty CounterTime;
	private int count;
	
	//Exit
	@FXML private Button exitButton;
	
	//Finish
	private boolean isFinish;
	
	public MainWindowController() 
	{
		this.status = new Label();
		this.CounterTime = new SimpleStringProperty();
		this.isFinish = false;
	}
	
	@Override
	public void createBindSteps(StringProperty Counter)
	{
		this.countText.textProperty().bind(Counter);
	}
	
	private void startTimer() 
	{	
		this.count = 0;
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
	
	@Override
	public void displayLevel(Level theLevel) 
	{
		this.sokoban.setLevelData(theLevel.getLevelBoard());

		if(theLevel.isFinished() == true)
		{
			finishLevel();
			stopTimer();
			notifyObservers("exit");
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
		this.isFinish = true;
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
		
		if(isLoadMusic == false)
			playAutoMusic();

		setFocus();
		stopTimer();
		startTimer();
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
		
		if (result.get().getText().equals("OK"))
		{
			setChanged();
			notifyObservers("exit");
			Stage stage = new Stage();
			Parent root;
		
			try 
			{
				root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage = (Stage)exitButton.getScene().getWindow();
				stage.close();
			} 
			catch (IOException e) 
			{	
				e.printStackTrace();
			}
		}
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
	
