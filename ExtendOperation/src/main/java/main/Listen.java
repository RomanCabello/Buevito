package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;

public class Listen extends Thread{
	private BufferedReader in;
	private Brain brain;
	private String msg;
	
	public Listen(Brain b)
	{
		in = new BufferedReader(new InputStreamReader(System.in));
		brain = b;
	}
	
	public void run()
	{
		
		
		while(true)
		{
			try {
				msg = in.readLine();
				
				JSONObject obj = new JSONObject(msg);
				int code = obj.getInt("Code");
				
				int op = brain.getOpCode();
				
				if (code==op) {
					brain.acknowledge(msg);
					brain.answer(obj);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}
