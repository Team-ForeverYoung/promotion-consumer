package com.java.promotionconsumer.factory;

import com.java.promotionconsumer.dto.EventResultMessage;
import com.java.promotionconsumer.entity.EventResultLog;

public class EventResultLogFactory {

	public static EventResultLog createPromotionResultLog(EventResultMessage message) {
		String userName = message.getUserName();
		String eventName = message.getEventName();
		String result;
		String logMessage;

		if (message.isSuccess()) {
			result = "성공";
			logMessage = userName + " 고객님 안녕하세요 ForeverYoung입니다.\n"
				+ "이번에 참여하신 " + eventName + " 이벤트에 당첨되셨습니다! 축하드립니다.";
		} else {
			result = "실패";
			logMessage = userName + " 고객님 안녕하세요 ForeverYoung입니다.\n"
				+ "이번에 참여하신 " + eventName + "은(는) 조기 재고 소진으로 부득이하게 당첨되지 않으셨습니다. 다음에도 참여 부탁드립니다.";
		}

		return EventResultLog.from(message, logMessage);
	}
}
