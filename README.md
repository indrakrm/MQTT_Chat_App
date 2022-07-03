# MQTT_Chat_App

Messenger Application using MQTT
Design Approach
To implement MQTT broker HiveMq is used.
To implement clients, eclipse paho is used.

Implementation
Project consists of the following classes.
ChatController-This class forms a bridge between MqttHandler and ChatWindow class
to provide chat application functionality.
MqttHandler-This class contains MQTT methods for connecting , subscribing ,
publishing,disconnecting clients to the broker.
MqttMsgHandler-Handles message reception from broker to corresponding client.
ChatWindow-It contains the design and implementation of GUI.The Gui is based on the
Swing library in Java.
FinalOutput-Takes instances of ChatWindow and MqttHandler and passes them to
ChatController.It also sets ChatWindow visibility to true.
Execution
Import folder “chat” as maven project into eclipse.
Please make sure swing tools are installed in eclipse.Reference video
https://www.youtube.com/watch?v=oeswfZz4IW0
Run HiveMq broker and please change broker address accordingly in MqttHandler class
which can be found in “src/main/java” folder.
Run FinalOutput class which can be found in the “src/main/java” folder.
Chat Message Formats and outputs
1)To loginEnter username at the start
Output
2)To publish or send a message
Message Format- pub,To_name,Message.
Message received in karan’s inbox
3)To join or subscribe to a group chat, message format - sub,Group_name
(group name should start or end with “Group”)
.Output- Confirmation of subscribing to Group_1
4)Demonstration of group chat
with three users indra,karan and srikar and all are subscribed to group_1 as mentioned above.
Step 1
Srikar sending or publishing a message in Group_1
Step 2
Following are the inboxes of Indra and Karan after Srikar sending or publishing a
message to Group_1.
Karan’s inbox
Indra’s Inbox:
Received message is of format - “Group_name,Sender : Message”
5)To unsubscribe
type “unsub” and press enter.Following is the confirmation output on the screen.
6)To disconnect
type “exit” and press enter.
