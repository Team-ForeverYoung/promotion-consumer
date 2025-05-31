package com.java.promotionconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.java.promotionconsumer.dto.EventResultMessage;
import com.java.promotionconsumer.entity.EventResultLog;
import com.java.promotionconsumer.factory.EventResultLogFactory;
import com.java.promotionconsumer.repository.EventResultRepository;

@Service
public class EventLogService {
	private final EventResultRepository eventResultRepository;
	private final static Logger log = LoggerFactory.getLogger(EventLogService.class);
	public EventLogService(EventResultRepository eventResultRepository) {
		this.eventResultRepository = eventResultRepository;
	}

	public void savePromotionResultLog(EventResultMessage message){
		log.info("로그 저장 중 ......");
		log.info("로그 저장 중 ......");
		log.info("로그 저장 중 ......");
		EventResultLog eventResultLog = EventResultLogFactory.createPromotionResultLog(message);
		log.info(eventResultLog.getEventName());
		try {
			eventResultRepository.save(eventResultLog);
		}catch (Exception e){
			log.info("저장이 안돴슈(임시 디버깅 용)");
		}
	}

}
