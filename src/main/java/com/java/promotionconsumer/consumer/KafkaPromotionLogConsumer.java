package com.java.promotionconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.java.promotionconsumer.dto.PromotionResultMessage;
import com.java.promotionconsumer.service.PromotionLogService;
@Component
public class KafkaPromotionLogConsumer implements PromotionLogConsumer{

	private final static String TOPIC = "promotion_result";
	private final PromotionLogService promotionLogService;

	public KafkaPromotionLogConsumer(PromotionLogService promotionLogService) {
		this.promotionLogService = promotionLogService;
	}

	@Override
	@KafkaListener(topics = TOPIC, containerFactory = "promotionResultConsumerFactoryContainer")
	public void promotionResultConsumer(PromotionResultMessage message) {
		promotionLogService.savePromotionResultLog(message);
	}




}

