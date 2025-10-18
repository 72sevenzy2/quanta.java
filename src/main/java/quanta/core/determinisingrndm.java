package src.main.java.quanta.core;

import java.util.Random;

public class determinisingrndm {
    private Random random;

    public determinisingrndm(long speed) {
        random = new Random(speed);
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public double nextDouble() {
        return random.nextDouble();
    }
}
