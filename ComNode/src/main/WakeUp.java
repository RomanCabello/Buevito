package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class WakeUp{
	
	private Socket cli;
	private ServerSocket server;
	private BufferedReader in;
	private PrintWriter out;
	private int port;
	private Brain brain;
	private Properties prop = new Properties();
	
	public WakeUp (Brain a)
	{
		brain = a;
		
		
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run()
	{
		lookup();
		
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Uhm... Wut?");
		}
		
		while(true)
		{
			try {
				cli = server.accept();
				in = new BufferedReader(new InputStreamReader(cli.getInputStream()));
				out = new PrintWriter(cli.getOutputStream(), true);
				
				brain.store(cli, in, out);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void lookup()
	{
		int from = Integer.parseInt(prop.getProperty("From"));
		int to = Integer.parseInt(prop.getProperty("To"));
		
		port = from;
		
		boolean flag = true;
		
		while (flag)
		{
			if (port <= to) {
				try {
					cli = new Socket("localhost", port);
					in = new BufferedReader(new InputStreamReader(cli.getInputStream()));
					out = new PrintWriter(cli.getOutputStream(), true);
					
					brain.store(cli, in, out);
				
					flag = true;
					port ++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					flag = false;
				}
			}else {
				System.out.println("Out of usable ports");
				System.exit(0);
			}
		}
	}

}
