package model.data.levels;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
* The Class MyXMLLevelLoader - The class that load and save an XML file.
*/
public class MyXMLLevelLoader implements iLevelLoader
{
	/*
	 * (non-Javadoc)
	 * 
	 */	
	@Override
	public Level LoadLevel(InputStream file) throws IOException, ClassNotFoundException
	{
		XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(file));		
		Level myLevel = (Level) xmlDecoder.readObject();		
		xmlDecoder.close();		
		
		//Checking if the level is correct.
		//if (myLevel.isLevelCorrect() == false)
			//throw new IOException("ERROR: Invalid Level, try another level.");	
		
		return myLevel;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public void SaveLevel(Level level, OutputStream file) throws IOException
	{
		XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(file));		
		xmlEncoder.writeObject(level);		
		xmlEncoder.close();		
	}

}
