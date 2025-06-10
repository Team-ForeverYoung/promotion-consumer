package com.java.promotionconsumer;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.promotionconsumer.dto.EventResultMessage;

@Component
public class OutboxMessageParser {
	private final ObjectMapper objectMapper;

	public OutboxMessageParser(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public OutboxBase outboxParser(String message) throws JsonProcessingException {
		OutboxBase outboxBase = objectMapper.readValue(message, OutboxBase.class);
		return outboxBase;
	}

	public EventResultMessage eventResultMessageParser(String message) throws JsonProcessingException {
		OutboxBase outboxBase = objectMapper.readValue(message, OutboxBase.class);
		EventResultMessage eventResultMessage = objectMapper.readValue(outboxBase.getPayload(), EventResultMessage.class);
		return eventResultMessage;
	}
}


