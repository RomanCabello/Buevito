package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Node {
	private BufferedReader in;
	private PrintWriter out;
	private Socket cli;
	private int Type;
	private int ID;
	
	public Node(Socket a, BufferedReader b, PrintWriter c, int id)
	{
		this.cli = a;
		this.in = b;
		this.out = c;
		this.ID = id;
	}
	
	
	public void Terminate()
	{
		try {
			cli.close();
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public BufferedReader getIn() {
		return in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
	
}
