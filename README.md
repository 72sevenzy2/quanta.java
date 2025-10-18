# EXAMPLE USAGE:


import quanta.core.*;

public class Main {
    public static void main(String[] args) {
        QuantaClock clock = new QuantaClock(60); // 60 ticks per second
        clock.setSeed(42);

        // Simple tickable
        clock.register((tick, delta) -> System.out.println("Tick: " + tick));

        // Scheduled task
        clock.scheduleIn(5, () -> System.out.println("Task executed at tick 5"));

        // Step 10 ticks manually
        clock.step(10);
    }
}
