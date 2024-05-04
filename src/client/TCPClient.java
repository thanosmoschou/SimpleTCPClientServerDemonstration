/*
 * Author: Thanos Moschou
 * Description: A simple tcp client server project demonstration
 * 
 * I provide a main because each server and client programs should run separately.
 */

package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient 
{
	private static final int PORT = 1234;
	private static String SERVER = "localhost";
	private static Scanner out = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException
	{
		//To create the client application, we need to create the instance of Socket class. 
		//Here, we need to pass the IP address or hostname of the Server and a port number. 
		//Here, we are using "localhost" because our server is running on same system.
		Socket socket = new Socket(SERVER, PORT);
		
		InputStream inputStream = socket.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader inputReader = new BufferedReader(inputStreamReader);
		
		OutputStream outputStream = socket.getOutputStream();
		PrintWriter outputWriter = new PrintWriter(outputStream, true);
		
		while(true)
		{	
			String output = readOutputForServer();
			if(output.equalsIgnoreCase("CLOSE"))
				break;
			outputWriter.println(output);
			

			String input = readInputFromServer(inputReader);
			if(input == null)
				break;
		}

		socket.close();
	}
	
	private static String readOutputForServer()
	{
		System.out.print("You: ");		
		return out.nextLine();
	}
	
	private static String readInputFromServer(BufferedReader inputReader) throws IOException
	{
		String input = inputReader.readLine();
		System.out.println("Server: " + input);

		return input;
	}
}
