package com.java.promotionconsumer.dto;

import lombok.Getter;

@Getter
public class PromotionResultMessage {
	private final boolean success;
	private final Long userId;
	private final Long eventId;
	private final String userName;
	private final String eventName;

	public PromotionResultMessage(boolean success, Long userId, Long eventId, String userName, String eventName) {
		this.success = success;
		this.userId = userId;
		this.eventId = eventId;
		this.userName = userName;
		this.eventName = eventName;
	}
}

