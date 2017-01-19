package view;

import common.Level;

public interface iView 
{
	void displayLevel(Level level); 
	void displayError(String msg);
	void start();
}
