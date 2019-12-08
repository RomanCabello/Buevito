package main;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Timestamp;

import org.json.JSONObject;

public class Brain {
	private int ID;
	private Connect connect;
	private BufferedReader[] in = new BufferedReader[2];
	private PrintWriter[] out = new PrintWriter[2];
	private int count;
	private JSONObject object;
	private Load load;
	
	public Brain()
	{
		count = 0;
		Timestamp ts1 = new Timestamp(System.nanoTime());
		ID = ts1.hashCode();
		connect = new Connect(this);
		connect.run();
		load = new Load(this);
		load.run();
		
	}
	
	public void Store(BufferedReader a, PrintWriter b)
	{
		if (count < 2) {
			in[count] = a;
			out[count] = b;
			
			new Listen(a,count,this).start();
			
			count++;
		}else {
			System.out.println("Uhhhm... what?");
		}
	}
	
	
	public void Relay(String msg, int id)
	{
		
		try {
			object = new JSONObject(msg);
			if (!object.has("ID")) {
				object.put("ID", ID);
			}
			if (id == 0) {
				out[1].println(object);
			} else {
				System.out.println(object);
				out[0].println(object);
			} 
		} catch (org.json.JSONException e) {
			System.out.println("No worries, it's cool");
		}
	}
	
}
