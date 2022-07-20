package com.example.smaato.restwebservicechallenge.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.example.smaato.restwebservicechallenge.exception.ApplicationException;
import com.example.smaato.restwebservicechallenge.service.UniqueIdService;

@Service
public class Scheduler {
	Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	@Autowired
	UniqueIdService uniqueIdService;
	
	 @Autowired
	 private KafkaTemplate<String, KafkaMessage> kafkaTemplate;

	 @Value(value = "${kafka.topic_name}")
	 private String kafkaTopicName;
	
	@Scheduled(fixedRate = 60000)
	@Async
	public void sendAllUniqueId() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MINUTE, -1);
		logger.info("Scheduler started at "+ date.getTime());
		KafkaMessage message = getUniqueIds(date.getTime());
		if(null!=message && message.getListOfIds().isEmpty()) {
			logger.info("List of Ids are empty for date "+ date.getTime()+" therefore not sending details to Kafka");
		}
		else {
			logger.info("List of Ids "+ message.getListOfIds()+" sending to kafka");
			
			ListenableFuture<SendResult<String, KafkaMessage>> future =
	                this.kafkaTemplate.send(kafkaTopicName, message);

	        future.addCallback(new ListenableFutureCallback<SendResult<String, KafkaMessage>>() {
	            @Override
	            public void onSuccess(SendResult<String,KafkaMessage> result) {
	      
	                logger.info("successfully sent message = {}, with offset = {}", message,
	                        result.getRecordMetadata().offset());
	            }

	            @Override
	            public void onFailure(Throwable ex) {
	                logger.info("Failed to send message = {}, error = {}", message, ex.getMessage());
	               
	            }
	        });
			
		}
	}
	
	public KafkaMessage getUniqueIds(Date date){
		List<Integer> listOfIds = null;
		KafkaMessage message = null;
		try {
			listOfIds = uniqueIdService.getUniqueIdList(date);
			message = new KafkaMessage(listOfIds,listOfIds.size());
		 
		} catch (ApplicationException e) {
			logger.error("Error in converting date, skipping"+e.getMessage());
		}
		return message;
		
	}
	

}
