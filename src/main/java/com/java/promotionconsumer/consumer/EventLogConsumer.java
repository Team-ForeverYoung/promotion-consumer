package com.java.promotionconsumer.consumer;

import com.java.promotionconsumer.dto.EventResultMessage;

public interface EventLogConsumer {
	void promotionResultConsumer(String message);
}
