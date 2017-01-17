package model;

import java.util.logging.Level;

public interface iModel 
{
	public boolean move(String moveType);
	public void displayLevel();
	public void saveLevel(String path);
	public Level loadLevel(String path);
	public Level solveLevel(Level level);
	
	public Level getCurrentLevel();
	//public void setLevel(Level level);

}
