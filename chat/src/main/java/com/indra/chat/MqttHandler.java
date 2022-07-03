package com.indra.chat;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttHandler { // contains methods to implement Mqtt Client
	String clientId = "01"; // default id
	ArrayList<String> subscribedTopics = new ArrayList<String>();
	int qos = 2;
	String broker = "tcp://0.0.0.0:1883";
	MemoryPersistence persistence = new MemoryPersistence();
	MqttClient client;

	public int connect() { // to establish connection with broker
		try {
			client = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(false);
			System.out.println("Connecting to broker: " + broker);
			client.connect(connOpts);
			System.out.println("Connected");
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			return 0;
		}
		return 1;
	}

	public void subscribe(String topic) { // to subscribe to a topic
		try {
			this.client.subscribe(topic);
			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("subscribed to " + topic + "\n");
		this.subscribedTopics.add(topic);
	}

	public int publish(String top, MqttMessage Msg) { // to publish or send a message
		try {
			//System.out.println(top);
			if (!top.contains("Group")) {
				this.client.publish(top, Msg); //publish to group
				System.out.println(top);
			} else if (!this.subscribedTopics.contains(top))
				return -1;
			else
				this.client.publish(top, Msg);
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
			return 0;
		} catch (MqttException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public int unsubscribe(String topic) { // to unsubscribe a topic
		try {
			if (this.subscribedTopics.contains(topic))
				client.unsubscribe(topic);
			else
				return 0;

		} catch (MqttException e) {
			// TODO Auto-generated catch block\
			e.printStackTrace();
			return -1;
		}
		System.out.println(this.clientId + " unsubscribed to " + topic + "\n");
		this.subscribedTopics.remove(topic);
		return 1;
	}

	public void disconnect() { // to disconnect from broker.
		try {
			this.client.disconnect();
			System.out.println("disconnected");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
