
import Model.Message;
import Model.Profile;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;

@Stateless
public class Insurance {

    private static final Logger _kafkaLog = Logger.getLogger("__car");
    private static final Set<Profile> profiles = new HashSet<>();

    private final String[] BOOTSTRAP_SERVERS = new String[]{
            "localhost:9092"
    };

    // Import settings from xml

    public Insurance() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(
                createProps()
        );

        runConsumer("register");
        runConsumer("details");
    }

    private Properties createProps() {
        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",", BOOTSTRAP_SERVERS));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_insurance");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        return props;
    }

    public void runConsumer(String topic) {
        new Thread(new RunnableConsumer(topic, createProps(), _kafkaLog)).start();
    }

    // Topic register
    public static Profile createProfile(Profile create) {
        try {
            findCar(create.getType().getPlates());
            _kafkaLog.log(Level.WARNING, "Plates {0} already created.", create.getType().getPlates());
            return null;
        } catch (Error e) {
            profiles.add(create);

            _kafkaLog.log(Level.INFO, "Car with plates {0} created.", create.getType().getPlates());
            return create;
        }
    }

    // Topic details
    public static boolean update(Message update) {
        try {
            Profile profile = findCar(update.plates);
            if (profile.update(update)) {
                _kafkaLog.log(Level.INFO, "{0} updated.", update.plates);

                if (profile.isEmptyTank()) {
                    _kafkaLog.log(Level.WARNING, "{0} has to tank!", update.plates);
                }
                if (profile.isCentripetalDead()) {
                    _kafkaLog.log(Level.WARNING, "{0} died to centripetal forces...", profile.getOwner().getName());
                }

                return true;
            } else {
                return false;
            }
        } catch (Error e) {
            _kafkaLog.log(Level.WARNING, "Plates {0} not registered", update.plates);
            return false;
        }
    }

    private static Profile findCar(String plate) throws Error {
        for (Profile profile : profiles) {
            if (profile.getType().getPlates().equals(plate))
                return profile;
        }
        throw new Error();
    }
}
