package com.java.promotionconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.java.promotionconsumer.OutboxMessageParser;
import com.java.promotionconsumer.dto.EventResultMessage;
import com.java.promotionconsumer.service.EventLogService;
@Component
public class KafkaEventLogConsumer implements EventLogConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaEventLogConsumer.class);
	private final static String TOPIC = "promotion_result";;
	private final EventLogService eventLogService;
	private final OutboxMessageParser outboxMessageParser;

	public KafkaEventLogConsumer(EventLogService eventLogService, OutboxMessageParser outboxMessageParser) {
		this.eventLogService = eventLogService;
		this.outboxMessageParser = outboxMessageParser;
	}

	@Override
	@KafkaListener(topics = TOPIC, containerFactory = "outboxMessageListenerContainerFactory")
	public void promotionResultConsumer(String message) {
		try {
			EventResultMessage eventResultMessage =outboxMessageParser.eventResultMessageParser(message);
			eventLogService.savePromotionResultLog(eventResultMessage);
		}catch (Exception e){
			return;
		}
	}




}

