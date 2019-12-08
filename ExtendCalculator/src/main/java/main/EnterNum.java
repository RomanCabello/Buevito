package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;


public class EnterNum implements ActionListener{
	
	private JTextField text;
	private Brain brain;
	
	public EnterNum(Brain tf)
	{
		brain = tf;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		text = brain.getPrimary();
		
		
		String s = e.getActionCommand();
		String s1 = text.getText();
		String s2 = s1+s;
		
		text.setText(s2);
		
	}

}
