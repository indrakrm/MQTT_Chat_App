package com.indra.chat;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MessageHandler implements MqttCallback{

	int id;
	MessageHandler(int id)
	{
		this.id=id;
	}
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(topic+" : "+message.toString()+"\n");
	}
	
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		//System.out.println("Delivery Completed\n");
	}
	
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		System.out.println(cause.getLocalizedMessage());
		System.out.println("connection lost\n");
	}
}
