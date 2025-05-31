package com.java.promotionconsumer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PromotionResultMessage {
	private boolean success;
	private Long userId;
	private Long eventId;
	private String userName;
	private String eventName;

	public PromotionResultMessage() {
	}

	public PromotionResultMessage(boolean success, Long userId, Long eventId, String userName, String eventName) {
		this.success = success;
		this.userId = userId;
		this.eventId = eventId;
		this.userName = userName;
		this.eventName = eventName;
	}
}

