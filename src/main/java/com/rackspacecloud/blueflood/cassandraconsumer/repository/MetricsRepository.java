package com.rackspacecloud.blueflood.cassandraconsumer.repository;

import com.rackspacecloud.blueflood.cassandraconsumer.model.Metrics;
import org.springframework.data.repository.CrudRepository;

public interface MetricsRepository extends CrudRepository<Metrics, String> {
}
