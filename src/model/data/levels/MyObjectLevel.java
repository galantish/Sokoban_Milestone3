package model.data.levels;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
* The Class MyObjectLevelLoader - The class that load and save an object file.
*/
public class MyObjectLevel implements iLevelLoader
{
	@Override	
	public Level LoadLevel(InputStream file) throws IOException, ClassNotFoundException
	{
		ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(file));
		Level myLevel = (Level) objectIn.readObject();
		objectIn.close();
		
		//Checking if the level is correct.
		//if (myLevel.isLevelCorrect() == false)
			//throw new IOException("ERROR: Invalid Level, try another level.");	
		
		return myLevel;
	}

	@Override
	public void SaveLevel(Level level, OutputStream file) throws IOException
	{
		ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(file));
		objectOut.writeObject(level);
		objectOut.close();
	}
}