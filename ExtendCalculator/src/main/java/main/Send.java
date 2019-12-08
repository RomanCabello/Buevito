package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class Send implements ActionListener{
	private Brain brain;
	private JTextArea field;
	
	public Send (Brain a)
	{
		brain = a;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		brain.Send();
	}

}
