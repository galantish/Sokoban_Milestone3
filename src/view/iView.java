package view;

import commons.Level;
import javafx.beans.property.StringProperty;

public interface iView 
{
	public void displayLevel(Level theLevel); 
	public void displayError(String msg);
	public void start();
	public void createBindSteps(StringProperty Counter);
}
