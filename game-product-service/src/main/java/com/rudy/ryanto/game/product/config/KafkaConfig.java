package com.rudy.ryanto.game.product.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value("${kafka.schema.registry.url.key:schema.registry.url}")
    private String schemaRegistryUrlKey;
    @Value("${kafka.schema.registry.url}")
    private String schemaRegistryUrl;

    @Value("${compression.type")
    private String compressionType;
    @Value("${kafka.producer.acks}")
    private String acks;

    private final Integer BATCH_SIZE =16384;

    private final Integer BATCH_SIZE_BOOST=100;
    private final Integer LINGERMS=5;
    private final Integer RTOMS=60000;
    private final Integer RETRY_COUNT=5;

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(schemaRegistryUrlKey, schemaRegistryUrl);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, BATCH_SIZE* BATCH_SIZE_BOOST);
        props.put(ProducerConfig.LINGER_MS_CONFIG, LINGERMS);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType);
        props.put(ProducerConfig.ACKS_CONFIG, acks);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, RTOMS);
        props.put(ProducerConfig.RETRIES_CONFIG, RETRY_COUNT);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
