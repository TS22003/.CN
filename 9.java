import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int MAX_PACKETS = 20;
        int QUEUE_SIZE = 10;
        double MAX_PROBABILITY = 0.7;
        double MIN_PROBABILITY = 0.3;
        Random rand = new Random();
        int queue_length = 0;
        double drop_probability = MIN_PROBABILITY;
        for (int i = 0; i < MAX_PACKETS; i++) {
            if (queue_length == QUEUE_SIZE) {
                System.out.println("Packet dropped (QUEUE FULL)");
                drop_probability = MIN_PROBABILITY;
            } else if (rand.nextDouble() < drop_probability) {
                System.out.println("Packet dropped (RANDOM)");
                drop_probability += (MAX_PROBABILITY - MIN_PROBABILITY) / (MAX_PACKETS - 1);
            } else {
                System.out.println("Packet accepted");
                queue_length++;
                drop_probability = MIN_PROBABILITY;
            }
        }
    }
}

