package model.policy;

import common.Level;
import model.data.items.Position;
import model.data.items.iMoveable;

public interface iSokobanPolicy 
{
	//This method checks all the boolean memebers in the class MySokobanPolicy and checks if a movable item can move to the new position.
	public boolean move(Level level, String moveType);
}
