package com.rackspacecloud.blueflood.cassandraconsumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Input {
    @JsonProperty("metricName")
    String metricName;

    @JsonProperty("collectionTime")
    long collectionTime;

    @JsonProperty("metricValue")
    double metricValue;
}

