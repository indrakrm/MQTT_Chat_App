package com.indra.chat;

import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;

public class ChatWindow extends JFrame {  //This class holds references to parts in the GUI.
	int pos = 0;
	private static final long serialVersionUID = 1L;
	private JTextArea chatArea = new JTextArea("Enter Username to Login\n", 20, 50);
	private JTextArea chatTypeArea = new JTextArea("type here", 3, 20);
	JScrollPane scrollableTextArea = new JScrollPane(chatArea);
	JScrollPane scrollableTextArea1 = new JScrollPane(chatTypeArea);
	private JButton enter = new JButton("ENTER");

	ChatWindow() {
		this.setTitle("MQTT_Chat");
		JPanel jp = new JPanel();

		chatArea.setEditable(false);
		scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollableTextArea1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableTextArea1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatArea.setBackground(new Color(248, 248, 255));
		chatTypeArea.setBackground(new Color(224, 255, 255));
		
		jp.add(scrollableTextArea);
		jp.add(scrollableTextArea1);
		jp.add(enter);
		this.setContentPane(jp);
		getContentPane().setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		setResizable(false);
	}

	public void addListener(ActionListener al) {
		enter.addActionListener(al);
	}
    //following are getter,setter methods.
	public String getChatArea() {
		return chatArea.getText();
	}

	public void setChatArea(String msg) {  //setter method used to update chat window
		if (msg.equals(""))
			this.chatArea.setText("");
		else
			this.chatArea.append(msg);
	}

	public String getChatTypeArea() {
		return this.chatTypeArea.getText();
	}

	public void setCharTypeArea() {
		this.chatTypeArea.setText("");
	}

}
