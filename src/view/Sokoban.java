package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Sokoban extends Canvas 
{
	private char[][] levelData;
	private int cRow; //levelData.length
	private int cCol; //levelData[0].length
	private StringProperty wallFileName;
	private StringProperty playerFileName;
	private StringProperty boxFileName;
	private StringProperty targetFileName;
	private StringProperty floorFileName;
		
	public Sokoban() 
	{
		this.cRow = 0;
		this.cCol = 0;
		this.wallFileName = new SimpleStringProperty();
		this.playerFileName = new SimpleStringProperty();
		this.boxFileName = new SimpleStringProperty();
		this.targetFileName = new SimpleStringProperty();	
		this.floorFileName = new SimpleStringProperty();
	}
	
	public void redraw()
	{
		if(levelData != null)
		{
			double W = getWidth(); //The canvas width
			double H = getHeight(); //The canvas height
			double w = W / Math.max(cCol,cRow);
			double  h = H /Math.max(cCol,cRow);

			GraphicsContext gc = this.getGraphicsContext2D();
						
			Image player = null;
			Image box = null;
			Image wall = null;
			Image target = null;
			Image floor = null;
			
			try 
			{
				player = new Image(new FileInputStream(getPlayerFileName()));
				box = new Image(new FileInputStream(getBoxFileName()));
				wall = new Image(new FileInputStream(getWallFileName()));
				target = new Image(new FileInputStream(getTargetFileName()));
				floor = new Image(new FileInputStream(getFloorFileName()));		
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
						case ' ':
							gc.drawImage(floor, j*w, i*h, w, h);
					default:
						gc.setFill(Color.BLUE);
						break;
					}
				}
			}
		}
	}

	public void setLevelData(char[][] arr) 
	{
		this.cRow = arr.length;
		this.cCol = arr[0].length;
		this.levelData = arr;
		redraw();
	}
		
	public char[][] getLevelData() 
	{
		return levelData;
	}
	
	public String getWallFileName() 
	{
		return this.wallFileName.get();
	}

	public String getPlayerFileName() 
	{
		return this.playerFileName.get();
	}

	public String getBoxFileName() 
	{
		return this.boxFileName.get();
	}

	public String getTargetFileName() 
	{
		return this.targetFileName.get();
	}

	public String getFloorFileName()
	{
		return this.floorFileName.get();
	}

	public void setWallFileName(String wallFileName) 
	{
		this.wallFileName.set(wallFileName);
	}

	public void setPlayerFileName(String playerFileName) 
	{
		this.playerFileName.set(playerFileName);
	}

	public void setBoxFileName(String boxFileName) 
	{
		this.boxFileName.set(boxFileName);
	}

	public void setTargetFileName(String targetFileName) 
	{
		this.targetFileName.set(targetFileName);
	}
	
	public void setFloorFileName(String floorFileName)
	{
		this.floorFileName.set(floorFileName);
	}
}
