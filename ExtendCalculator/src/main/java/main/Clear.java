package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class Clear implements ActionListener{
	
	private Brain brain;
	private JTextField field;
	
	public Clear(Brain a)
	{
		brain = a;
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		field = brain.getPrimary();
		
		field.setText(field.getText().substring(0,field.getText().length()-1));
		
		
	}

}
