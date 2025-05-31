package com.java.promotionconsumer.dto;

import lombok.Getter;

@Getter
public class EventResultMessage {
	private boolean success;
	private Long userId;
	private Long eventId;
	private String userName;
	private String eventName;

	public EventResultMessage() {
	}

	public EventResultMessage(boolean success, Long userId, Long eventId, String userName, String eventName) {
		this.success = success;
		this.userId = userId;
		this.eventId = eventId;
		this.userName = userName;
		this.eventName = eventName;
	}
}

