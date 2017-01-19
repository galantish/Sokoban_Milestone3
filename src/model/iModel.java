package model;

import common.Level;

public interface iModel 
{
	public void loadLevel(String path);
	public void saveLevel(String path);
	public void move(String moveType);
	public Level getCurrentLevel();

	//public void displayLevel();
	

}
