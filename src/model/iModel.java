package model;

import commons.Level;

/**
 * IModel - an interface that holds all the function that a model should implement.
 */
public interface iModel 
{
	public void loadLevel(String path);
	public void saveLevel(String path);
	public void move(String moveType);
	public Level getCurrentLevel();	
	public int getSteps();
}
