package src.main.java.quanta.core;

import java.util.ArrayList;
import java.util.List;

public class quanta {
    private int ticksPerSecond;
    private double delta;
    private long tickCount = 0;
    private double timeScale = 1.0;
    private boolean runnning = false;

    private List<tickable> tickables = new ArrayList<>();
    private scheduler Scheduler = new scheduler();
    private determinisingrndm range = new determinisingrndm(0L);

    public quanta(int ticksPerSecond) {
        this.ticksPerSecond = ticksPerSecond;
        this.delta = 1.0 / ticksPerSecond;
    }

    public void setseed(long speed) {
        range = new determinisingrndm(speed);
    }

    public void register(tickable t) {
        tickables.add(t);
    }

    public void step(long n) {
        for (long i = 0; i < n; i++) {
            tick();
        }
    }

    private void tick() {
        tickCount++;

        for (tickable t : tickables) {
            t.ontick(tickCount, delta * timeScale);
        }
        Scheduler.execute(tickCount);
    }

    public void scheduleIn(long ticksFromNow, Runnable task) {
        Scheduler.schedule(tickCount + ticksFromNow, task);
    }

    public long getTick() {
        return tickCount++;
    }
}
