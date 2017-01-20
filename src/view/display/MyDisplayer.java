package view.display;

import java.io.IOException;

import commons.Level;
import model.data.levels.MyTextLevel;

/**
 * The Class CLIDisplayer - Class that display a level to the user.
 */
public class MyDisplayer implements iDisplayer
{
	@Override
	public void DisplayLevel(Level level) throws IOException 
	{
		char[][] levelArr = new char[level.getRow()][level.getCol()];
		
		for(int i=0; i<level.getRow(); i++)
		{
			for(int j=0;j<level.getCol(); j++)	
				System.out.print(levelArr[i][j]);
			System.out.println();
		}
	}	

	
}