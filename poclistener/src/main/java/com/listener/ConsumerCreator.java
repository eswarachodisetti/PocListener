package com.listener;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;


import com.utility.PropertyUtility;

public class ConsumerCreator {

	public static Consumer<Long, String> createConsumer() {
		final Properties props = new Properties();
		PropertyUtility prop=new PropertyUtility();
		
		
		
		  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				  prop.readProperty("KAFKA_BROKERS") );
		  props.put(ConsumerConfig.GROUP_ID_CONFIG,
				  prop.readProperty("CLIENT_ID") );
		  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
		  LongDeserializer.class.getName());
		  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
		  StringDeserializer.class.getName());
		  props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,
				  prop.readProperty("MAX_POLL_RECORDS"));
		  props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, prop.readProperty("OFFSET_RESET_EARLIER")); 
		  
		  final Consumer<Long,String> consumer = new KafkaConsumer<Long, String>(props);
		  consumer.subscribe(Collections.singletonList(
				  prop.readProperty("TOPIC_NAME")));
		 
		/*
		 * props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
		 * KafkaConstants.KAFKA_BROKERS); props.put(ConsumerConfig.GROUP_ID_CONFIG,
		 * KafkaConstants.CLIENT_ID);
		 * props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
		 * LongDeserializer.class.getName());
		 * props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
		 * StringDeserializer.class.getName());
		 * props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,
		 * KafkaConstants.MAX_POLL_RECORDS);
		 * props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		 * props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
		 * KafkaConstants.OFFSET_RESET_EARLIER);
		 * 
		 * 
		 * final Consumer<Long, String> consumer = new KafkaConsumer<Long,
		 * String>(props);
		 */
		consumer.subscribe(Collections.singletonList(KafkaConstants.TOPIC_NAME));
		return consumer;
	}

}
