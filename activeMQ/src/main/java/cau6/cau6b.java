package cau6;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class cau6b {
public static void main(String[] args) throws JMSException {
	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

	Connection connection = connectionFactory.createConnection();
	connection.start();

	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

	Destination destination = session.createQueue("HoangVanTung");

	MessageConsumer consumer = session.createConsumer(destination);
	Message message = consumer.receive(1000);

	do {
	if (message instanceof TextMessage) {
		TextMessage textMessage = (TextMessage) message;
		String text = textMessage.getText();
		System.out.println("Da nhan tin nhan: " + text);
	}
	}while(message != null);
	
	consumer.close();
	session.close();
	connection.close();
}
}
