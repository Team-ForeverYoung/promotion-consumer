package com.java.promotionconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.java.promotionconsumer.dto.EventResultMessage;
import com.java.promotionconsumer.service.EventLogService;
@Component
public class KafkaEventLogConsumer implements EventLogConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaEventLogConsumer.class);
	private final static String TOPIC = "promotion_result";
	private final EventLogService eventLogService;

	public KafkaEventLogConsumer(EventLogService eventLogService) {
		this.eventLogService = eventLogService;
	}

	@Override
	@KafkaListener(topics = TOPIC, containerFactory = "promotionResultConsumerFactoryContainer")
	public void promotionResultConsumer(EventResultMessage message) {
		log.info("메시지 수신완료");
		log.info("메시지 수신완료"+message.getEventName() + message.getUserName());
		eventLogService.savePromotionResultLog(message);
	}




}

