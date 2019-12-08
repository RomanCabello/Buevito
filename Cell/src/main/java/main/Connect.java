package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Connect {
	private BufferedReader in;
	private PrintWriter out;
	private int from;
	private int to;
	private  Properties prop = new Properties();
	private Brain brain;
	private boolean flag;
	private Socket client;
	private int port;
	private List<Integer> ports = new ArrayList<Integer>();
	
	public Connect(Brain a)
	{
		brain = a;
		flag = false;
	}
	
	public void run()
	{
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		from = Integer.parseInt(prop.getProperty("From"));
		to = Integer.parseInt(prop.getProperty("To"));
		
		

		System.out.println("Waiting for connection...");
		
		
		
		for (int i = from; i <= to; i++)
		{
			ports.add(i);
		}
		
		
		
		while(!flag)
		{
			int point = (int)(Math.random()*ports.size()) ;
			port = ports.get(point);
			
			try {
				client = new Socket("localhost",port);

				flag = true;
			} catch(IOException e) {
				// TODO Auto-generated catch block
				flag = false;
				ports.remove(point);
			}
		}
		
		System.out.println("Connected to port: "+port);
		
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
			out.println("1");
			//String trash = in.readLine();
			//System.out.println(trash);
			brain.Store(in, out);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
