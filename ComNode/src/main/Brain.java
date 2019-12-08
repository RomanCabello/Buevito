package main;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Brain {

	private List<Node> nodes = new ArrayList<Node>();
	private int count;

	public Brain() {
		count = 0;

	}

	public void Boradcast(String msg, int Type, int ID) {

		PrintWriter tmp;

		if (Type == 1) {
			for (int i = 0; i < nodes.size(); i++) {
				if (nodes.get(i).getID() != ID) {
					tmp = nodes.get(i).getOut();
					tmp.println(msg);
				}
			}
		} else {
			for (int i = 0; i < nodes.size(); i++) {
				if ((nodes.get(i).getID() != ID) && (nodes.get(i).getType() == 1)) {
					tmp = nodes.get(i).getOut();
					tmp.println(msg);
				}
			}
		}
	}

	public void store(Socket a, BufferedReader b, PrintWriter c) {
		Node tmp = new Node(a, b, c, count);
		nodes.add(tmp);
		new Listener(tmp, this).start();
		count++;
	}

	public int getNodeStat(int index) {

		return index;

	}

	public void Terminate(Node node) {
		synchronized (this) {
			int ID = node.getID();
			
			Node tmp;
			tmp = nodes.get(ID);
			tmp.Terminate();
			nodes.remove(ID);

			UpdateIndex();
		}
	}

	public void UpdateIndex() {
		Node aux;
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.size() != 0) {
				aux = nodes.get(i);
				aux.setID(i);
			}
		}

		count = nodes.size();
	}

	public void Hello(int ID) {
		PrintWriter tmp = nodes.get(ID).getOut();

		tmp.println("0");
	}
}
