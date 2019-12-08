package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Load {
	private java.lang.Process pro;
	private BufferedReader in;
	private PrintWriter out;
	private Brain brain;
	
	public Load(Brain a)
	{
		brain = a;
	}
	
	public void run()
	{
		try {
			pro = Runtime.getRuntime().exec("java -jar Alt2/Ext.jar");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't load module");
			
		}
		
		in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		out = new PrintWriter(pro.getOutputStream(), true);
		
		brain.Store(in, out);
		
	}
}
