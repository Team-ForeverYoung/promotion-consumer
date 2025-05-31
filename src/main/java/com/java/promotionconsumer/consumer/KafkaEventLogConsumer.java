package com.java.promotionconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.java.promotionconsumer.dto.EventResultMessage;
import com.java.promotionconsumer.service.EventLogService;
@Component
public class KafkaEventLogConsumer implements EventLogConsumer {

	private final static String TOPIC = "promotion_result";
	private final EventLogService eventLogService;

	public KafkaEventLogConsumer(EventLogService eventLogService) {
		this.eventLogService = eventLogService;
	}

	@Override
	@KafkaListener(topics = TOPIC, containerFactory = "promotionResultConsumerFactoryContainer")
	public void promotionResultConsumer(EventResultMessage message) {
		eventLogService.savePromotionResultLog(message);
	}




}

