package com.java.promotionconsumer.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.java.promotionconsumer.dto.EventResultMessage;

import lombok.Getter;

@Getter
@Document(collation = "promotion_logs")
public class EventResultLog {
	private final Long eventId;
	private final Long userId;
	private final String userName;
	private final String eventName;
	private final String message;
	private EventResultLog(Long eventId, Long userId, String userName, String eventName, String message) {
		this.eventId = eventId;
		this.userId = userId;
		this.userName = userName;
		this.eventName = eventName;
		this.message = message;
	}

	public static EventResultLog from(EventResultMessage resultMessage, String message){
		return new EventResultLog(resultMessage.getEventId(), resultMessage.getUserId(), resultMessage.getUserName(),
			resultMessage.getEventName(), message);
	}
}
