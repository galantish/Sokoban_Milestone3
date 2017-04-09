package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ScoresTableViewController extends AnchorPane
{
	@FXML TextField userField;
	@FXML TextField levelField;
	@FXML Button search;
	
	@FXML MenuButton orderByButtom;
//	@FXML MenuItem userOrder;
//	@FXML MenuItem levelOrder;
	
	String userId;
	String levelID;
	String orderBy;

	public void searchBy()
	{
		this.userId = userField.getText();
		this.levelID = levelField.getText();
	}
	
	public void orderBy()
	{
		//orderByButtom = new MenuButton("Options", null, userOrder, levelOrder);
		this.orderBy = orderByButtom.getText();
	}
	
	public void print()
	{
		System.out.print("************************************************************************\n");
		System.out.println("User ID: " + userId + " | Level ID: " + levelID + " | Order By: " + orderBy);
		
	}
}
