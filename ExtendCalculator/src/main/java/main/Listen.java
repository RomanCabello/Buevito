package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

import org.json.JSONObject;

public class Listen extends Thread{
	private Brain brain;
	private BufferedReader in;
	private String msg;
	private JTextArea field;
	private JSONObject object;
	
	public Listen(Brain brain)
	{
		in = new BufferedReader(new InputStreamReader(System.in));
		this.brain = brain;
		
	}
	
	public void run()
	{
		
		field = brain.getSecondary();
		field.append("\nlistening");
		
		
		while(true)
		{
			try {
				msg = in.readLine();
				//field.append("\n"+msg);
				
				object = new JSONObject(msg);
				int code = object.getInt("Code");
				
				switch (code)
				{
					case 117:
					{
						brain.RouteTo(msg);
						break;
					}
					case 42:
					{
						brain.Answer(msg);
						break;
					}
				}
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
