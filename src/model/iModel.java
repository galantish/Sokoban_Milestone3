package model;

import commons.Level;

public interface iModel 
{
	public void loadLevel(String path);
	public void saveLevel(String path);
	public void move(String moveType);
	public Level getCurrentLevel();	

}
