package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import commons.Level;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SokobanDisplayer extends Canvas
{
	//private TheLevel theLevel;	
	private char[][] levelData;
	private int cRow;
	private int cCol;
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
	
	public void redraw()
	{
		if(levelData != null)
		{
			double W = getWidth();
			double H = getHeight();
			double w = W / cCol;
			double  h = H / cRow;
			
			GraphicsContext gc = this.getGraphicsContext2D();
						
			Image player = null;
			Image box = null;
			Image wall = null;
			Image target = null;
			
			try 
			{
				player = new Image(new FileInputStream(getPlayerFileName()));
				box = new Image(new FileInputStream(getBoxFileName()));
				wall = new Image(new FileInputStream(getWallFileName()));
				target = new Image(new FileInputStream(getTargetFileName()));
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			
			gc.clearRect(0, 0, W, H);
			
			for(int i=0; i<cRow; i++)
			{
				for(int j=0; j<cCol; j++)
				{
					char c = levelData[i][j];
					switch (c) 
					{
						case 'A':
							gc.drawImage(player, j*w, i*h, w, h);
						break;
						case '@':
							gc.drawImage(box, j*w, i*h, w, h);
						break;
						case '#':
							gc.drawImage(wall, j*w, i*h, w, h);
						break;
						case 'o':
							gc.drawImage(target, j*w, i*h, w, h);
						break;
					default:
						gc.setFill(Color.WHITE);
						//gc.fillRect(j*w, i*h, w, h);//black is the default color
						break;
					}
				}
			}
			
			//gc.fillOval(cCol*w, cRow*h, w, h);
		}
	}

	public void setLevelData(char[][] levelData) 
	{
		this.levelData = levelData;
		this.cRow = levelData.length;
		this.cCol = levelData[0].length;
		redraw();
	}
	
	public void setPlayerPosition(int row, int col)
	{
		this.cCol = col;
		this.cRow = row;
		redraw();
	}
		
	public String getWallFileName() 
	{
		return wallFileName.get();
	}

	public String getPlayerFileName() 
	{
		return PlayerFileName.get();
	}

	public String getBoxFileName() 
	{
		return BoxFileName.get();
	}

	public String getTargetFileName() 
	{
		return TargetFileName.get();
	}

	public char[][] getLevelData() 
	{
		return levelData;
	}

	public void setWallFileName(String wallFileName) 
	{
		this.wallFileName.set(wallFileName);
	}

	public void setPlayerFileName(String playerFileName) 
	{
		PlayerFileName.set(playerFileName);
	}

	public void setBoxFileName(String boxFileName) 
	{
		BoxFileName.set(boxFileName);
	}

	public void setTargetFileName(String targetFileName) 
	{
		TargetFileName.set(targetFileName);
	}
}
