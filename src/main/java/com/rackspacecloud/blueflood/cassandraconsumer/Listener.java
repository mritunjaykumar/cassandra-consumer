package com.rackspacecloud.blueflood.cassandraconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rackspacecloud.blueflood.cassandraconsumer.model.Input;
import com.rackspacecloud.blueflood.cassandraconsumer.model.Metrics;
import com.rackspacecloud.blueflood.cassandraconsumer.repository.MetricsRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Component
public class Listener {
    @Autowired
    MetricsRepository metricsRepository;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Listener.class);

    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(id = "blueflood-metrics-listener", topicPartitions =
            { @TopicPartition(topic = "blueflood-metrics",
                    partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0"))
            })
    public void ListenBluefloodMetrics(ConsumerRecord<?, ?> record) throws IOException {
        LOGGER.info("Received Payload '{}'", record);
        String strRecord = record.value().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(strRecord, Input.class);

        Metrics metrics = new Metrics(input.getMetricName(), input.getCollectionTime(), input.getMetricValue());

        metricsRepository.save(metrics);
    }
}
