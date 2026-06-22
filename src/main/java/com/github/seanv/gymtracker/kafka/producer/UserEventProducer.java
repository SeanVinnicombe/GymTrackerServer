package com.github.seanv.gymtracker.kafka.producer;


import com.github.seanv.gymtracker.events.UserRegisteredEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventProducer {

    private static final String TOPIC = "user-registered";
    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishUserRegistered(UserRegisteredEvent userRegisteredEvent) {
        kafkaTemplate.send(TOPIC, userRegisteredEvent);
    }
}

/**
 *
 *The 'String' in KafkaTemplate '<String, UserRegisteredEvent>' is about partition routing and ordering, not to
 * do with Topic.
 *
 * Topics are split into partitions. Kafka needs to decide which  partition each message goes to. the key determines
 * this:
 *
 * kafkaTemplate.send(topic, key, value)
 *
 * If you provide a key, Kafka hashes it to consistently route messages with the same key to th partition:
 *
 * key = "user-123" → always goes to Partition 0
 * key = "user-456" → always goes to Partition 1
 * key = "user-123" → always goes to Partition 0 (same as before)
 *
 * Why this matters?
 * ------------------------------
 *
 * If you want all events for specific user to be processed in order, you'd uses the users ID as the key. Kafka
 * guarantees ordering within a partition - so all of users 123's events always land on the same partition and
 * get processed in the ordered they were sent.
 *
 * If you don't provide key(like in current 'publishUserRegistred'). Kafka distributes messages across partitions
 * round-robin - no ordering guarantee, but better load distribution.
 *
 *
 */