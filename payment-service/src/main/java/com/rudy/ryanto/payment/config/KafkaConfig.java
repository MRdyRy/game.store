package com.rudy.ryanto.payment.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers:ocalhost:19092, localhost:29092, localhost:39092}")
    private String bootstrapAddress;
    @Value("${kafka.schema.registry.url.key:schema.registry.url}")
    private String schemaRegistryUrlKey;
    @Value("${kafka.schema.registry.url:http://localhost:8081}")
    private String schemaRegistryUrl;

    @Value("${compression.type:snappy")
    private String compressionType;
    @Value("${kafka.producer.acks:all}")
    private String acks;
    @Value("${kafka.producer.batch.size:16384")
    private String batchSize;

    @Value("${kafka.producer.batch.size.boost.factor:100")
    private String batchSizeBoost;

    @Value("${kafka.producer.linger.ms:5")
    private String lingerMs;

    @Value("${kafka.producer.rto.ms:60000")
    private String rtoMs;

    @Value("${kafka.producer.retry.count:5")
    private String retryCount;


    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


    @Bean
    public ProducerFactory<String, String> producerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(schemaRegistryUrlKey, schemaRegistryUrl);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, Integer.parseInt(batchSize) * Integer.parseInt(batchSizeBoost));
        props.put(ProducerConfig.LINGER_MS_CONFIG, Integer.parseInt(lingerMs));
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType);
        props.put(ProducerConfig.ACKS_CONFIG, acks);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, Integer.parseInt(rtoMs));
        props.put(ProducerConfig.RETRIES_CONFIG, Integer.parseInt(retryCount));
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
