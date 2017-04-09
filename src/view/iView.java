package view;

import commons.Level;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

public interface iView 
{
	public void displayLevel(Level theLevel); 
	public void displayError(String msg);
	public void createBindSteps(StringProperty Counter);
	public void setPrimaryStage(Stage primaryStage);
	public void exitPrimaryStage(Stage primaryStage);
	public void setSecondStage(Stage secondStage);

}
