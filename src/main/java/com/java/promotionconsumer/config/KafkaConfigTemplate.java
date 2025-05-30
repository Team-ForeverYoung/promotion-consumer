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

import com.java.promotionconsumer.dto.EventResultMessage;

@Configuration
public class KafkaConfigTemplate {
	private static final String BOOTSTRAP_SERVER = "kafka.kafka.svc.cluster.local:9092";

	@Bean
	public ConsumerFactory<String, EventResultMessage> promotionResultConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonDeserializer.class);
		config.put("spring.json.type.mapping", "EventResultMessage:com.java.promotionconsumer.dto.EventResultMessage");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "event-result-group");
		config.put("spring.json.trusted.packages", "com.java.promotionconsumer");
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, EventResultMessage> promotionResultConsumerFactoryContainer() {
		ConcurrentKafkaListenerContainerFactory<String, EventResultMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(promotionResultConsumerFactory());
		return factory;
	}
}
