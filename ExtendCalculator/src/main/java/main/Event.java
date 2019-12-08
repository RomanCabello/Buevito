package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONObject;

public class Event {
	private JSONObject obj;
	private int EID;
	private boolean locked;
	private boolean Answered;
	private int min;
	private List<Integer> cells = new ArrayList<Integer>();
	private Properties prop = new Properties();
	private JTextArea secondary;
	private int ACount;
	
	
	public Event(JSONObject obj, JTextArea secondary)
	{
		this.obj = obj;
		EID = this.obj.getInt("Event");
		locked = true;
		Answered = false;
		this.secondary = secondary;
		ACount = 0;
		
		
		try {
			prop.load(new FileInputStream("Interface/minimums.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			secondary.append("The config file straight up disappeared");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		
		int code = getCode();
		
		switch (code)
		{
			case 1:
			{
				min = Integer.parseInt(prop.getProperty("Sum"));
				break;
			}
			case 2:
			{
				min = Integer.parseInt(prop.getProperty("Min"));
				break;
			}
			case 3:
			{
				min = Integer.parseInt(prop.getProperty("Div"));
				break;
			}
			case 4:
			{
				min = Integer.parseInt(prop.getProperty("Mult"));
				break;
			}
		}
		
		
		new Ping(this).start();
		
	}
	
	public void addA()
	{
		ACount ++;
	}
	
	public int getACount()
	{
		return ACount;
	}

	
	public int getCode()
	{
		return obj.getInt("Code");
	}
	
	public int getEID()
	{
		return EID;
	}
	
	public int getRes()
	{
		return cells.size();
	}
	
	public void Answer(int ans)
	{
		
	}
	
	public JSONObject getObj()
	{
		return obj;
	}
	
	public void lock()
	{
		locked = true;
	}
	
	public boolean isLocked()
	{
		return locked;
	}
	
	public void Response(int id)
	{
		if(cells.size()==0)
		{
			cells.add(id);
		}else {
			if(!cells.contains(id)) {
				cells.add(id);
			}
		}
		
		
		
		if(cells.size() >= min)
		{
			locked = false;
		}
	}

}
