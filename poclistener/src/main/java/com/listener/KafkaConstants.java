package com.listener;

public interface KafkaConstants {
	
	public static String KAFKA_BROKERS = "104.198.185.90:9092";
	
	public static String CLIENT_ID="client2";
	
	public static String TOPIC_NAME="cvmtopic";
	
	public static String GROUP_ID_CONFIG="consumerGroup10";
	
	//public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
	
	public static String OFFSET_RESET_LATEST="latest";
	
	public static String OFFSET_RESET_EARLIER="earliest";
	
	public static Integer MAX_POLL_RECORDS=1;
}
