package com.indra.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ChatController implements ActionListener,MqttCallback { // controls computation part and GUI part

	ChatWindow chatWin;
	MqttHandler pub;
	//MqttMsgHandler callback;

	ChatController(ChatWindow chatWin, MqttHandler pub) {
		this.chatWin = chatWin;
		this.pub = pub;
		this.chatWin.addListener(this);
	}

	public void actionPerformed(ActionEvent e) { // whenever Enter button is clicked
		String query = this.chatWin.getChatTypeArea();
		String[] out = query.split(",", 0);
		int publishResponse;

		if (this.chatWin.getChatArea().contains("Enter Username to Login")) { // if the input is the first message then
																				// input should be username

			this.pub.clientId = query;
			int response = this.pub.connect();
			if (response == 1) {
				//callback = new MqttMsgHandler(this.chatWin);
				this.pub.client.setCallback(this);
				this.chatWin.setChatArea("");
				this.chatWin.setChatArea("Logged in as " + query + "\n");
				this.pub.subscribe(query);
				chatWin.setChatArea("subscribed to " + query + "\n");
				
			} else {
				this.chatWin.setChatArea("Failed to connect please check your connection\n");
			}
		} else if (out[0].equals("sub")) { // if message is to subscribe a topic
			// this.chatWin.setChatArea(query + "\n");
			this.pub.subscribe(out[1]);
			chatWin.setChatArea("subscribed to " + out[1] + "\n");
			
		} else if (out[0].equals("pub")) { // if the entered message is to publish
			publishResponse = this.pub.publish(out[1], new MqttMessage((out[2] + "," + this.pub.clientId).getBytes()));
			if (publishResponse == -1)//if tried to publish in unsubscribed group.
			{
				chatWin.setChatArea("Publish failed\n");
				chatWin.setChatArea(out[1] + " is not subscribed\n");   
			}
			else
			{
				chatWin.setChatArea("you to " + out[1] + " : " + out[2] + "\n");
			}

		} else if (out[0].equals("unsub")) { // if the entered message is to unsubscribe a topic
			publishResponse = this.pub.unsubscribe(out[1]);
			if (publishResponse == 0)
				this.chatWin.setChatArea(out[1] + " is not subscribed\n");  //if tried to unsubscribe to topic which is not subscribed by the client
			else if (publishResponse==-1)
				chatWin.setChatArea("Error: cannot  unsubscribe to  " + out[1] + "\n");
			else
			chatWin.setChatArea("You have Successfully unsubscribed to " + out[1] + "\n");

		} else if (out[0].equals("exit")) { // if the entered message is to exit or logout from the application
			this.pub.disconnect();
			this.chatWin.setChatArea("");
			this.chatWin.setChatArea("Enter Username to Login\n");
		}
		this.chatWin.setCharTypeArea(); // to clear the text box where user types.
	}
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		chatWin.setChatArea("");
		chatWin.setChatArea("Connection Lost\nEnter Username to Login\n");

	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		String[] out = message.toString().split(",", 0);
		if (!topic.contains("Group")) { // if the received message is a group message
			chatWin.setChatArea(out[1] + " : " + out[0] + "\n");
		} else // if the received message is a non group message.
			chatWin.setChatArea(topic + "," + out[1] + " : " + out[0] + "\n");
	}
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

}
