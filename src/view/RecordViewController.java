package view;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import commons.Record;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class RecordViewController extends Observable
{
	@FXML private TextField userField;
	@FXML private TextField levelField;
	@FXML private Button search;
	
	@FXML private MenuButton orderByButtom;
	@FXML private CheckMenuItem test;

	private String userId;
	private String levelID;
	private String orderBy;
	
	public void showDBRecord(List<Record> records)
	{
		
	}
	
	public void searchBy()
	{
		this.userId = userField.getText();
		this.levelID = levelField.getText();
	}
	
	public void orderBy()
	{
		System.out.println("ORDERRRRR");
		this.orderBy = orderByButtom.getText();
		System.out.println("Order by test? " + this.test.isSelected());
	}
	
	public void search()
	{
		setChanged();
		notifyObservers("query " + levelID + " " + userId + " " + "time");
		//notifyObservers("query null User1 time");
	}
	
}
