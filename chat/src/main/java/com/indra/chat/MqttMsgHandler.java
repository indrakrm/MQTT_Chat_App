package com.indra.chat;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttMsgHandler implements MqttCallback { // handles messages received to a client
	ChatWindow cw;

	MqttMsgHandler(ChatWindow cw) {
		this.cw = cw;
	}
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		this.cw.setChatArea("");
		this.cw.setChatArea("Connection Lost\nEnter Username to Login\n");

	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		String[] out = message.toString().split(",", 0);
		if (!topic.contains("Group")) { // if the received message is a group message
			this.cw.setChatArea(out[1] + " : " + out[0] + "\n");
		} else // if the received message is a non group message.
			this.cw.setChatArea(topic + "," + out[1] + " : " + out[0] + "\n");
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}
}
