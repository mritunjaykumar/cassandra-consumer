package com.rackspacecloud.blueflood.cassandraconsumer.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("metrics_full")
public class Metrics {

    public Metrics(String metricName, long collectionTime, double metricValue){
        this.metricName = metricName;
        this.collectionTime = collectionTime;
        this.metricValue = metricValue;
    }

    @PrimaryKey(value = "key")
    private String metricName;

    @Column(value = "column1")
    private long collectionTime;

    @Column(value = "value")
    private double metricValue;
}
