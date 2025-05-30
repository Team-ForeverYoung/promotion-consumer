package com.java.promotionconsumer.service;

import org.springframework.stereotype.Service;

import com.java.promotionconsumer.dto.PromotionResultMessage;
import com.java.promotionconsumer.factory.PromotionResultLogFactory;
import com.java.promotionconsumer.repository.PromotionResultRepository;

@Service
public class PromotionLogService {
	private final PromotionResultRepository promotionResultRepository;

	public PromotionLogService(PromotionResultRepository promotionResultRepository) {
		this.promotionResultRepository = promotionResultRepository;
	}

	public void savePromotionResultLog(PromotionResultMessage message){
		promotionResultRepository.save(PromotionResultLogFactory.createPromotionResultLog(message));
	}

}
