package cau2;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class cau2 {
public static void main(String[] args) throws JMSException {
	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	
	Connection connection = connectionFactory.createConnection();
	connection.start();
	
	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
	Destination destination = session.createQueue("HoangVanTung");

	MessageProducer producer = session.createProducer(destination);
    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    
	String text = "Cau 2 gui len queue";
	TextMessage message = session.createTextMessage(text);
	System.out.println("Da gui message: " + message.hashCode());
	producer.send(message);
	
    session.close();
	connection.close();
}
}
