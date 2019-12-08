package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends Thread{
	private Frame frame;
	private List<JButton>button = new ArrayList<JButton>();
	private EnterNum en;
	private Send send;
	private Brain brain;
	private Clear clear;
	private ClearE clearE;
	
	public GUI(Brain a)
	{
		brain = a;
		clear = new Clear(brain);
		clearE = new ClearE(brain);
	}

	
	public void run()
	{
		
		frame = new JFrame("Calculator");
		
		Font font1 = new Font("Arial", Font.PLAIN, 50);
		
		JTextField field = new JTextField();
		field.setBounds(10, 10, 565, 70);
		field.setFont(font1);
		
		brain.setPrimary(field);
		
		
		
		JTextArea field2 = new JTextArea();
		field2.setBounds(590, 10, 565, 500);
		
		field2.append("loaded");
		
		
		
		brain.setSecondary(field2);
		
		JScrollPane sp = new JScrollPane(field2);
		sp.setBounds(590, 10, 565, 500);
		sp.setAutoscrolls(true);
		
		
		new Listen(brain).start();
		
		
		
		en = new EnterNum(brain);
		
		
		int x = 10;
		int y = 110;
		
		int count = 0;
		
		
		for (int i=0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++) {
				int s = count + 1;
				String label = Integer.toString(s);
				button.add(new JButton(label));
				button.get(count).setBounds(x, y, 60, 60);
				x = x+75;
				
				button.get(count).addActionListener(en);
				
				
				frame.add(button.get(count));
				count++;
			}
			
			x = 10;
			y = y+80;
			
		}
		
		button.add(new JButton("0"));
		button.get(count).setBounds(x, y, 60, 60);
		button.get(count).addActionListener(en);
		
		JButton plus = new JButton("+");
		plus.setBounds(240, 110, 120, 60);
		plus.addActionListener(en);
		brain.setButton(plus);
		
		JButton minus = new JButton("-");
		minus.setBounds(240, 190, 120, 60);
		minus.addActionListener(en);
		brain.setButton(minus);
		
		JButton divide = new JButton("/");
		divide.setBounds(240, 270, 120, 60);
		divide.addActionListener(en);
		brain.setButton(divide);
		
		JButton mult = new JButton("*");
		mult.setBounds(240, 350, 120, 60);
		mult.addActionListener(en);
		brain.setButton(mult);
		
		JButton delete = new JButton("C");
		delete.setBounds(85, y, 60, 60);
		delete.addActionListener(clear);
		
		JButton clear = new JButton("CE");
		clear.setBounds(160, y, 60, 60);
		clear.addActionListener(clearE);
		
		send = new Send(brain);
		
		JButton submit = new JButton("Submit");
		submit.setBounds(10, 430, 120, 60);
		submit.addActionListener(send);
		
		JButton nuke = new JButton("Terminate");
		nuke.setBounds(380, 190, 120, 60);
		
		

		
		JButton close= new JButton("Close");
		close.setBounds(380, 110, 120, 60);
		close.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
		           System.exit(0);  
		}  
		});
		

		
		frame.add(button.get(count));
		frame.add(plus);
		frame.add(minus);
		frame.add(divide);
		frame.add(mult);
		frame.add(delete);
		frame.add(clear);
		frame.add(submit);
		frame.add(close);
		frame.add(nuke);
		
		frame.add(field);
		frame.add(sp, BorderLayout.CENTER);
		
		field2.setEnabled(false);

		
		
		frame.setSize(1200, 600);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
