package main;

import java.io.BufferedReader;
import java.io.IOException;

public class Listen extends Thread{
	private int ID;
	private BufferedReader in;
	private String msg;
	private Brain brain;
	
	public Listen(BufferedReader a, int b, Brain c)
	{
		in = a;
		ID = b;
		brain = c;
	}
	
	public void run()
	{
		
		
		
		
//		while(true)
//		{
//			try {
//				msg = in.readLine();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			System.out.println("From: "+ID);
//			brain.Relay(msg, ID);
//			
//		}
		
		
		
		
		try {
			while((msg = in.readLine())!=null)
			{
				System.out.println(msg);
				brain.Relay(msg, ID);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Shoutout to Harambe");
		}
				
			
		
	}

}
