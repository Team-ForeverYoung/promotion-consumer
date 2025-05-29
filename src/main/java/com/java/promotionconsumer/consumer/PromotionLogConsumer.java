package com.java.promotionconsumer.consumer;

import com.java.promotionconsumer.dto.PromotionResultMessage;

public interface PromotionLogConsumer {
	void promotionResultConsumer(PromotionResultMessage message);
}
