package view;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;

public class SokobanDisplayer extends Canvas 
{
	char[][] levelData;
	private StringProperty wallFileName;
	private StringProperty PlayerFileName;
	private StringProperty BoxFileName;
	private StringProperty TargetFileName;
	
	public SokobanDisplayer() 
	{
		this.wallFileName = new SimpleStringProperty();
		this.PlayerFileName = new SimpleStringProperty();
		this.BoxFileName = new SimpleStringProperty();
		this.TargetFileName = new SimpleStringProperty();		
	}




}
