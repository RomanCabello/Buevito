package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Listener extends Thread{
	private Node node;
	private BufferedReader in;
	private PrintWriter out;
	private Brain brain;
	
	public Listener(Node node, Brain brain)
	{
		this.node = node;
		this.brain = brain;
		
		in = node.getIn();
		out = node.getOut();
	}
	
	public void run() {
		
		handshake();
		
		boolean flag = true;
		String msg;
		
		while(flag)
		{
			try {
				msg = in.readLine();
				System.out.println(msg);

				brain.Boradcast(msg, node.getType(), node.getID());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				flag = false;
			}
		}
		
		brain.Terminate(node);
		
	}
	
	public void handshake() {
		brain.Hello(node.getID());
		try {
			node.setType(Integer.parseInt(in.readLine()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
