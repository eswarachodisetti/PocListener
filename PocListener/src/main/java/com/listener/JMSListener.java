package com.listener;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import com.utility.MetricsUtility;
import com.utility.SolrUtility;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

public class JMSListener {
	private static SolrUtility solrUtility;
	
	static Counter counter=Counter.build().name("JMScounter").help("JMS counter Help").register();
	static Gauge gauge=Gauge.build().name("JMSGauge").help("JMS gauge help").register();
	public static void main(String[] args) {
		solrUtility=new SolrUtility();
		System.out.println("Document is inserted");
		 MetricsUtility mutility=new MetricsUtility(); mutility.exposeMetrics();
		runConsumer();
	}

	static void runConsumer() {
		System.out.println("Inside runConsumer()");
		Consumer<Long, String> consumer = ConsumerCreator.createConsumer();		
		int noMessageToFetch = 0;	
		try {
		System.out.println("consumer"+consumer);
			while (true){
				
				System.out.println("Inside while loop");
				final ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
				System.out.println("Inside while loop");
				if (consumerRecords.count() == 0) {
					System.out.println("Check2");
					noMessageToFetch++;
					
				}
				System.out.println("Check5");
				System.out.println("cons " + consumerRecords.count());
				
				consumerRecords.forEach(record -> {
					counter.inc();
					solrUtility.insertDocument( record.value());
					
					System.out.println("Record value " + record.value());
					
				});
				consumer.commitAsync();
			}
			
			//consumer.close();
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
	}	
	  public SolrUtility getSolrUtility() { return solrUtility; }
	  public void setSolrUtility(SolrUtility solrUtility) {
		  this.solrUtility =
	  solrUtility; }
	  
	 
}
