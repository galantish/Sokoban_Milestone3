package view;
	
import java.io.FileInputStream;
import java.util.List;
import controller.SokobanController;
import controller.server.SokobanClientHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;

public class Main extends Application 
{	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));				 
			BorderPane root = (BorderPane) loader.load();
			MainWindowController view = loader.getController();
			MyModel model = new MyModel();
			
			SokobanController sokobanController;
			
			//Get the args from the main
			List<String> args = getParameters().getRaw();
			
			//Running with the server
			if((args.size() > 0) && (args.get(0).toLowerCase().equals("-server")))
			{
				int port = Integer.parseInt(args.get(1));
				SokobanClientHandler clientHandler = new SokobanClientHandler();
				sokobanController = new SokobanController(model, view, clientHandler, port);
				clientHandler.addObserver(sokobanController);
			}
			
			//Running without the server
			else
				sokobanController = new SokobanController(model, view);

			model.addObserver(sokobanController);
			view.addObserver(sokobanController);	
			view.setPrimaryStage(primaryStage);
	       
			Scene scene = new Scene(root,1200,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Sokoban-Pac");
			primaryStage.getIcons().add(new Image(new FileInputStream("./resources/Images/1.gif")));
			primaryStage.setScene(scene);
			primaryStage.show();	
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
