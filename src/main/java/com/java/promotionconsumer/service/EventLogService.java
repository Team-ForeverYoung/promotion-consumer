package com.java.promotionconsumer.service;

import org.springframework.stereotype.Service;

import com.java.promotionconsumer.dto.EventResultMessage;
import com.java.promotionconsumer.factory.EventResultLogFactory;
import com.java.promotionconsumer.repository.EventResultRepository;

@Service
public class EventLogService {
	private final EventResultRepository eventResultRepository;

	public EventLogService(EventResultRepository eventResultRepository) {
		this.eventResultRepository = eventResultRepository;
	}

	public void savePromotionResultLog(EventResultMessage message){
		eventResultRepository.save(EventResultLogFactory.createPromotionResultLog(message));
	}

}
