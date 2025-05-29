package com.java.promotionconsumer.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.java.promotionconsumer.dto.PromotionResultMessage;

import lombok.Getter;

@Getter
@Document(collation = "promotion_logs")
public class PromotionResultLog {
	private final Long evnetId;
	private final Long userId;
	private final String userName;
	private final String eventName;
	private final String message;
	private PromotionResultLog(Long evnetId, Long userId, String userName, String eventName, String message) {
		this.evnetId = evnetId;
		this.userId = userId;
		this.userName = userName;
		this.eventName = eventName;
		this.message = message;
	}

	public static PromotionResultLog from(PromotionResultMessage resultMessage, String message){
		return new PromotionResultLog(resultMessage.getEventId(), resultMessage.getUserId(), resultMessage.getUserName(),
			resultMessage.getEventName(), message);
	}
}
