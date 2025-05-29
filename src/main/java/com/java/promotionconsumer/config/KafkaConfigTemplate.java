package com.java.promotionconsumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.java.promotionconsumer.dto.PromotionResultMessage;

@Configuration
public class KafkaConfigTemplate {
	private static final String BOOTSTRAP_SERVER = "kafka.kafka.svc.cluster.local:9092";

	@Bean
	public ConsumerFactory<String, PromotionResultMessage> promotionResultConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "event-result-group");
		config.put("spring.json.value.default.type", "com.java.promotionconsumer.dto.PromotionResultMessage");
		config.put("spring.json.trusted.packages", "com.java.promotionconsumer");

		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, PromotionResultMessage> eventResultTemplate() {
		ConcurrentKafkaListenerContainerFactory<String, PromotionResultMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(promotionResultConsumerFactory());
		return factory;
	}
}
