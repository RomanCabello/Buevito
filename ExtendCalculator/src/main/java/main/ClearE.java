package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ClearE implements ActionListener{
	
	private Brain brain;
	private JTextField field;
	
	public ClearE(Brain a)
	{
		brain = a;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		field = brain.getPrimary();
		field.setText("");
		
	}

}
