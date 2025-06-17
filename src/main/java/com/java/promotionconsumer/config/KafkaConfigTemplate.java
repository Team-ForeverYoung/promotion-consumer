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

	// eventResulttMessage 컨슈머 ====> Outbox Message 컨슈머
	// MeMo: 리스너에서 명시적으로 팩토리를 지정해주지 않아도
	// ConcurrentKafkaListenerContainerFactory가 1개라도 정의 되어 있다면, 기본설정(application.properties)을 덮어씌우는 현상때문에 추가

	@Bean
	public ConsumerFactory<String, String> outboxMessageConsumerFactory(){
		Map<String,Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "result_process_server");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean ConcurrentKafkaListenerContainerFactory<String,String> outboxMessageListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(outboxMessageConsumerFactory());
		return factory;
	}
	//----------------------- 아래 컨슈머는 현제 안쓰여요 ----------------
	// MeMo: 아레 컨슈머는 메인서버에서 직접 카프카로 메시지를 발행하고 카프카에서 메시지를 받을땨 썼지만 outbox도입 후 안쓰임 -----------

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
