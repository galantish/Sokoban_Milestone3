package controller.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer 
{
	private int port = 9999;
	private ServerSocket server;
	
	public void start() throws IOException
	{
		server = new ServerSocket(this.port);
		server.setSoTimeout(1000);
		
		
		try
		{
			Socket aClient = server.accept();
			
			InputStream inFromUser = aClient.getInputStream();
			OutputStream outToClient = aClient.getOutputStream();
			
			if(inFromUser.toString() == "exit")
				System.out.println("bye!");
			
			inFromUser.close();
			outToClient.close();
			aClient.close();
		}
		catch (SocketTimeoutException e) 
		{
			System.out.println("ERROR: Timeout.");
		}

	}

}