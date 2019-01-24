import Model.Message;
import Model.Profile;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunnableConsumer implements Runnable {
    private final String topic;
    private boolean running = true;

    private final Logger logger;
    private final Properties props;

    public RunnableConsumer(String topic, Properties props, Logger logger) {
        this.topic = topic;
        this.props = props;

        this.logger = logger;
    }

    public void run() {
        final Consumer<String, String> consumer = createConsumer(topic);
        logger.log(Level.INFO, "Running Consumer for topic {0}", topic);

        while (running) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

            for (ConsumerRecord<String, String> record : records) {
                // logger.log(Level.INFO, "Received a message from Kafka: {0}", record.toString());
                Gson gson = new Gson();
                switch (topic) {
                    case "register":
                        Profile profile = gson.fromJson(record.value(), Profile.class);
                        Insurance.createProfile(profile);
                        break;
                    case "details":
                        Message update = gson.fromJson(record.value(), Message.class);
                        Insurance.update(update);
                        break;
                    default:
                        logger.log(Level.WARNING, "Topic {0} not supported");
                        break;
                }
            }
        }
    }

    public void stop() {
        running = false;
    }

    private Consumer<String, String> createConsumer(String topic) {
        // Create the consumer using props.
        Consumer<String, String> consumerTopic = new KafkaConsumer<>(props);

        // Subscribe to the topic.
        consumerTopic.subscribe(Collections.singletonList(topic));
        return consumerTopic;
    }

}
