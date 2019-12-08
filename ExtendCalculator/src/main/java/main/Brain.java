package main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONObject;

public class Brain {
	private JTextField primary;
	private JTextArea secondary;
	private JSONObject object;
	private int code, num1, num2, event;
	private List<Event> events = new ArrayList<Event>();
	private List<Answer> answers = new ArrayList<Answer>();
	private List<JButton>operations = new ArrayList<JButton>();
	
	public Brain()
	{
		new GUI(this).start();
		
	}
	
	public void setButton(JButton button)
	{
		operations.add(button);
	}

	public JTextField getPrimary() {
		return primary;
	}

	public void setPrimary(JTextField primary) {
		this.primary = primary;
	}

	public JTextArea getSecondary() {
		return secondary;
	}

	public void setSecondary(JTextArea secondary) {
		this.secondary = secondary;
	}

	public void Send() {
		String msg = primary.getText();
		object = new JSONObject();
		
		
		Timestamp ts1 = new Timestamp(System.nanoTime());
		event = ts1.hashCode();
		
		code = DetOp(msg);
		
		object.put("Event", event);
		object.put("Code", code);
		
		int lock = code -1;
		
		operations.get(lock).setEnabled(false);
		
		setNums(msg);
		
		events.add(new Event(object,secondary));
		
		primary.setText("");
		
		System.out.println(object);
		
		

	}
	
	public void setNums(String msg)
	{
		String sym = null;
		switch (code)
		{
			case 1:
			{
				sym = "+";
				break;
			}
			case 2:
			{
				sym = "-";
				break;
			}
			case 3:
			{
				sym = "/";
				break;
			}
			case 4:
			{
				sym = "*";
				break;
			}
		}
		
		int cut = msg.indexOf(sym);
		num1 = Integer.parseInt(msg.substring(0, cut));
		num2 = Integer.parseInt(msg.substring(cut+1));
		
		object.put("Num1", num1);
		object.put("Num2", num2);
		
		
	}
	

	public int DetOp(String msg) {
		
		if (msg.contains("+")) {
			return 1;
		}
		if (msg.contains("-")) {
			return 2;
		}
		if (msg.contains("/")) {
			return 3;
		}
		if (msg.contains("*")) {
			return 4;
		} else {
			return -1;
		}
		
		
	}
	
	public void RouteTo(String msg)
	{
		JSONObject obj = new JSONObject(msg);
		int eid = obj.getInt("Event");
		int id = obj.getInt("ID");
		int res = 0;
		Event tmp = null;
		
		for(int i = 0; i < events.size(); i++)
		{
			if(eid == events.get(i).getEID())
			{
				events.get(i).Response(id);
				res = events.get(i).getRes();
				tmp = events.get(i);
			}
		}
		
		if(!tmp.isLocked())
		{
			int lock = tmp.getCode()-1;
			operations.get(lock).setEnabled(true);
		}
		secondary.append("\n "+res+" responses");

		
	}
	
	public void Answer (String msg)
	{
		JSONObject obj = new JSONObject(msg);
		int eid = obj.getInt("Event");
		int ans = obj.getInt("Answer");
		Event tmp = null;
		for(int i=0; i < events.size(); i++)
		{
			if(eid==events.get(i).getEID()) {
				tmp = events.get(i);
			}
		}
		
		
		if((!tmp.isLocked())&&(tmp.getACount()==0))
		{
			tmp.addA();
			answers.add(new Answer(eid, ans));
			secondary.append("\nThe answer of event: "+eid+" is: "+ans);
		}
		
	}

}
