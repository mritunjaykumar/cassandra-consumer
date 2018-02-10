package com.rackspacecloud.blueflood.cassandraconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rackspacecloud.blueflood.cassandraconsumer.model.Input;
import com.rackspacecloud.blueflood.cassandraconsumer.model.Metrics;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Component
public class Listener {
    @Autowired
    private CassandraOperations cassandraOperations;

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "#{'${kafka.topics.in}'.split(',')}")
    public void ListenBluefloodMetrics(ConsumerRecord<?, ?> record) {
        //TODO: Get tracking ID from the record to stitch the tracing
        String passedTrackingId = "";
        String currentTrackingId = "";

        currentTrackingId = StringUtils.isEmpty(passedTrackingId)
                ? UUID.randomUUID().toString() : String.format("%s|%s", passedTrackingId, currentTrackingId);

        LOGGER.info("TrackingId:{}, START: Processing", currentTrackingId);
        LOGGER.debug("TrackingId:{}, Received payload:{}", currentTrackingId, record);
        String strRecord = record.value().toString();

        ObjectMapper objectMapper = new ObjectMapper();
        Input input = null;
        try {
            input = objectMapper.readValue(strRecord, Input.class);
        } catch (IOException e) {
            LOGGER.error("TrackingId:{}, Exception message: {}, Stack trace: {}",
                    currentTrackingId, e.getMessage(), e.getStackTrace());
        }

        Metrics metrics = new Metrics(input.getMetricName(), input.getCollectionTime(), input.getMetricValue());

        try {
            cassandraOperations.insert(metrics);
        }
        catch(Exception ex){
            LOGGER.error("TrackingId:{}, Exception message: {}, Stack trace: {}",
                    currentTrackingId, ex.getMessage(), ex.getStackTrace());
        }

        LOGGER.info("TrackingId:{}, FINISH: Processing", currentTrackingId);
    }
}
