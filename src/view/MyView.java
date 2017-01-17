package view;

import java.util.Observable;

import controller.SokobanController;

public class MyView extends Observable implements iView
{
	public MyView(SokobanController controller) 
	{
		System.out.println("MyView was created!");
	}

	@Override
	public void showErrorMessage(String error) 
	{
		//print the error to screen
		
	}

}
