#!/bin/sh

jq -c .[] "$1" | python /data/json-to-avro.py "$SCHEMA_FILE" | /data/key_value.sh | \
  kafka-avro-console-producer --bootstrap-server kafka:29092 \
    --property schema.registry.url=http://schema-registry:8081 \
    --property parse.key=true \
    --property key.separator=\| \
    --property key.serializer=org.apache.kafka.common.serialization.IntegerSerializer \
    --topic "$TOPIC" \
    --property value.schema.file="$SCHEMA_FILE"