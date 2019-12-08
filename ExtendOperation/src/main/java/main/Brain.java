package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

import org.json.JSONObject;

public class Brain {
	private BufferedReader in2;
	private JSONObject object;
	private java.lang.Process pro;
	private Properties prop = new Properties();
	private int OpCode;
	
	public Brain()
	{
		
//		try {
//			prop.load(new FileInputStream("config.properties"));
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		
//		OpCode = Integer.parseInt(prop.getProperty("OpCode"));
		
		try {
			pro = Runtime.getRuntime().exec("java -jar Interface/Op.jar");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't load module");
			
		}
		
		in2 = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		
		try {
			OpCode = Integer.parseInt(in2.readLine());
		} catch(IOException e) {
			// TODO Auto-generated catch block
			
		} catch(java.lang.NumberFormatException e1)
		{
			
		}
		System.out.println(OpCode);
		
		new Listen(this).start();
	}
	
	public int getOpCode()
	{
		return OpCode;
	}
	
	public void answer(JSONObject obj)
	{
		int num1 = obj.getInt("Num1");
		int num2 = obj.getInt("Num2");
		int event = obj.getInt("Event");
		int ans = 0;
		
		try {
			pro = Runtime.getRuntime().exec("java -jar Interface/Op.jar "+num1+" "+num2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't load module");
			
		}
		
		in2 = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		
		try {
			ans = Integer.parseInt(in2.readLine());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject();
		json.put("Event", event);
		json.put("Code", "42");
		json.put("Answer", ans);
		
		System.out.println(json);
	}
	
	public void acknowledge(String msg)
	{
		object = new JSONObject(msg);
		object.remove("ID");
		object.remove("Code");
		object.put("Code", "117");
		System.out.println(object);
	}
	
}
