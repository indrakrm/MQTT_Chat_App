package com.indra.chat;

public class FinalOutput {     // creates instances for the view and MqttHandler class which has Mqtt client methods 

	public static void main(String[] args) {
		ChatWindow chatWin = new ChatWindow();
		MqttHandler pub = new MqttHandler();
		ChatController controller = new ChatController(chatWin, pub);
		chatWin.setVisible(true);
	}
}
