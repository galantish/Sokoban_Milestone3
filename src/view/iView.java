package view;

import commons.Level;

public interface iView 
{
	void displayLevel(Level theLevel); 
	void displayError(String msg);
	void start();
}
