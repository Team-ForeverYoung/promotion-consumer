package com.java.promotionconsumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OutboxBase {
	private Long id;
	private Long created_at;
	private String message_key;
	private String payload;
	private OutBoxStatus status;
	private String topic;
	@Override
	public String toString() {
		return "OutboxBase{" +
			"id=" + id +
			", created_at=" + created_at +
			", message_key='" + message_key + '\'' +
			", payload='" + payload + '\'' +
			", status=" + status +
			", topic='" + topic + '\'' +
			'}';
	}
}
