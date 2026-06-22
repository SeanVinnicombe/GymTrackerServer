package com.github.seanv.gymtracker.kafka.consumer;

import com.github.seanv.gymtracker.events.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserRegisteredConsumer {

    @KafkaListener(topics = "user-registered", groupId = "gymtracker-group")
    public void handleUserRegistered(UserRegisteredEvent userRegisteredEvent) {
        log.info("New user registered: {} - sending welcome email to {}",
                userRegisteredEvent.firstName(), userRegisteredEvent.email());
    }
}

/**
 * If we run two instances of application with same name 'groupId: 'gymtracker-group', Kafka treats  them as a single
 * consumer group sharing the workload:
 *
 * Topic: "user-registered" (say it has 3 partitions)
 * Consumer Group: "gymtracker-group"
 * ├── App Instance 1 → assigned Partition 0, 1
 * └── App Instance 2 → assigned Partition 2
 *
 * Each event is processed by exactly one consumer in the group - not both. That is how Kafka enables horizontal scaling.
 * As you add more application instances, Kafka automatically rebalances partitions across them, increasing your
 * processing throughput.
 */
