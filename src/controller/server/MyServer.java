package controller.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
public class MyServer 
{
	private int port;
	private ClientHandler clientHandler;
	private volatile  boolean stop;
	
	public MyServer(int port, ClientHandler clientHandler) 
	{
		this.port = port;
		this.clientHandler = clientHandler;
		this.stop = false;
	}
	
	public void runServer() throws Exception
	{
		ServerSocket server = new ServerSocket(this.port);
		server.setSoTimeout(10000);
		
		while(!stop)//Waiting to the next client
		{
			try
			{
				Socket aClient = server.accept();//Blocking calls from clients
								
				InputStream inFromUser = aClient.getInputStream();
				OutputStream outToClient = aClient.getOutputStream();
			
				clientHandler.handleClient(inFromUser, outToClient);

				aClient.getInputStream().close(); 
				aClient.getOutputStream().close(); 
				aClient.close();
				
				inFromUser.close();
				outToClient.close();
				aClient.close();
			}
			catch (SocketTimeoutException e) 
			{
				System.out.println("ERROR: Timeout.");
			}
		}
		server.close();
	}

	public void start()
	{
		Thread t = new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					runServer();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
	
	public void stop()
	{
		this.stop = true;
	}
}