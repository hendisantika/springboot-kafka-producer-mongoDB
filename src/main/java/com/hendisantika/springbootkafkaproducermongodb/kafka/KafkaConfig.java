package com.hendisantika.springbootkafkaproducermongodb.kafka;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.codec.StringDecoder;

import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-kafka-producer-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/11/18
 * Time: 07.11
 */
@Configuration
public class KafkaConfig {
    @Value("${kafka.topic:inventoryTest}")
    private String topic;

    @Value("${kafka.broker.address:localhost:9092}")
    private String brokerAddress;

    @Value("${kafka.zookeeper.connect:localhost:2181}")
    private String zookeeperConnect;

    @ServiceActivator(inputChannel = "toKafka")
    @Bean
    public MessageHandler handler() throws Exception {
        KafkaProducerMessageHandler handler = new KafkaProducerMessageHandler(producerContext());
        handler.setTopicExpression(new LiteralExpression(this.topic));
        return handler;
    }

    @Bean
    public ConnectionFactory kafkaBrokerConnectionFactory() {
        return new DefaultConnectionFactory(kafkaConfiguration());
    }

    @Bean
    public org.springframework.integration.kafka.core.Configuration kafkaConfiguration() {
        BrokerAddressListConfiguration configuration = new BrokerAddressListConfiguration(
                BrokerAddress.fromAddress(this.brokerAddress));
        configuration.setSocketTimeout(500);
        return configuration;
    }

    @Bean
    public KafkaProducerContext producerContext() {
        KafkaProducerContext kafkaProducerContext = new KafkaProducerContext();
        ProducerMetadata<String, String> producerMetadata = new ProducerMetadata<>(this.topic, String.class,
                String.class, new StringSerializer(), new StringSerializer());
        Properties props = new Properties();
        props.put("linger.ms", "1000");
        ProducerFactoryBean<String, String> producer =
                new ProducerFactoryBean<>(producerMetadata, this.brokerAddress, props);
        ProducerConfiguration<String, String> config =
                new ProducerConfiguration<>(producerMetadata, producer.getObject());
        Map<String, ProducerConfiguration<?, ?>> producerConfigurationMap =
                Collections.<String, ProducerConfiguration<?, ?>>singletonMap(this.topic, config);
        kafkaProducerContext.setProducerConfigurations(producerConfigurationMap);
        return kafkaProducerContext;
    }

    @Bean
    public KafkaMessageListenerContainer container() throws Exception {
        final KafkaMessageListenerContainer kafkaMessageListenerContainer = new KafkaMessageListenerContainer(
                kafkaBrokerConnectionFactory(), new Partition(this.topic, 0));
        kafkaMessageListenerContainer.setMaxFetch(100);
        kafkaMessageListenerContainer.setConcurrency(1);
        return kafkaMessageListenerContainer;
    }

    @Bean
    public KafkaMessageDrivenChannelAdapter adapter(KafkaMessageListenerContainer container) {
        KafkaMessageDrivenChannelAdapter kafkaMessageDrivenChannelAdapter =
                new KafkaMessageDrivenChannelAdapter(container);
        StringDecoder decoder = new StringDecoder();
        kafkaMessageDrivenChannelAdapter.setKeyDecoder(decoder);
        kafkaMessageDrivenChannelAdapter.setPayloadDecoder(decoder);
        kafkaMessageDrivenChannelAdapter.setOutputChannel(fromKafka());
        return kafkaMessageDrivenChannelAdapter;
    }

    @Bean
    public PollableChannel fromKafka() {
        return new QueueChannel();
    }

    @Bean
    public TopicCreator topicCreator() {
        return new TopicCreator(this.topic, this.zookeeperConnect);
    }
}