-- this cdl schema create the main tables for blueflood in cassandra using the cqlsh client
-- usage:
--   $ cqlsh hostname -f load.cdl -u username -p password
-- example:
--   $ cqlsh 127.0.0.1 -f ./blueflood/src/cassandra/cli/load.cdl

CREATE KEYSPACE IF NOT EXISTS "mrit_data"
    WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };


CREATE TABLE IF NOT EXISTS "mrit_data".metrics_locator (
    key bigint,
    column1 text,
    value text,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_delayed_locator (
    key text,
    column1 text,
    value text,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_discovery (
    key text,
    column1 text,
    value text,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_state (
    key bigint,
    column1 text,
    value bigint,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_metadata (
    key text,
    column1 text,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_string (
    key text,
    column1 bigint,
    value text,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_full (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_5m (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_20m (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_60m (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_240m (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_1440m (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';


CREATE TABLE IF NOT EXISTS "mrit_data".metrics_preaggregated_full (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_preaggregated_5m (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_preaggregated_20m (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_preaggregated_60m (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_preaggregated_240m (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';

CREATE TABLE IF NOT EXISTS "mrit_data".metrics_preaggregated_1440m (
    key text,
    column1 bigint,
    value double,
    PRIMARY KEY (key, column1)
) WITH COMPACT STORAGE AND speculative_retry = 'NONE';
