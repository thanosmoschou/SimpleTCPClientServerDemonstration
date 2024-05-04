/*
 * Author: Thanos Moschou
 * Description: A simple tcp client server project demonstration
 */

package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer 
{
	private static final int PORT = 1234;
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException
	{
		//The ServerSocket class can be used to create a server socket. 
		//This object is used to establish communication with the clients.
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server is listening to port: " + PORT);
		
		//Returns the socket and establish a connection between server and client.
		//The accept() method waits for the client. 
		//If clients connects with the given port number, it returns an instance of Socket.
		Socket dataSocket = serverSocket.accept();
		System.out.println("Connection from: " + dataSocket.getInetAddress());
		
		//Read the input
		//Add all steps with details for demonstration purposes only
		//First we need to create an input stream where all data will be placed
		//Then we need something to read the input stream and then we need a buffer
		//to read multiple data at once
		InputStream inputStream = dataSocket.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader inputReader = new BufferedReader(inputStreamReader);
		
		//Create a stream for the output
		//Then we need to create a writer that writes to this stream
		OutputStream outputStream = dataSocket.getOutputStream();
		PrintWriter outputWriter = new PrintWriter(outputStream, true); //true is for autoFlush
		
		while(true)
		{
			String input = readInputFromClient(inputReader);

			if(input == null)
				break;
			
			String output = readOutputForClient();
			if(output.equalsIgnoreCase("CLOSE"))
				break;
			outputWriter.println(output);
		}	
		
		dataSocket.close();
	}
	
	private static String readInputFromClient(BufferedReader inputReader) throws IOException
	{
		String inputMsg = inputReader.readLine();
		System.out.println("Client: " + inputMsg);

		return inputMsg;
	}
	
	private static String readOutputForClient()
	{
		System.out.print("You: ");
		return in.nextLine();		
	}
}
