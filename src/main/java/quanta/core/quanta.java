package quanta.core;

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

        // for debugging
        System.out.println("tick # " + tickCount);

        for (tickable t : tickables) {
            t.ontick(tickCount, delta * timeScale);
        }
        Scheduler.execute(tickCount);
    }

    // small but useful start and stop methods
    public void start() {
        // check if its already running
        if (runnning) {
            return;
        }

        runnning = true;

        new Thread(() -> {
            long tickInt = 1000 / ticksPerSecond;
            while (runnning) {
                long start = System.currentTimeMillis();

                tick();

                long duration = System.currentTimeMillis() - start;
                long sleep = tickInt - duration;
                if (sleep > 0) {
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException error) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }

    public void stop() {
        runnning = false;
    }

    // setting custom time scales

    public void setTimeScale(double timeScale) {
        this.timeScale = timeScale;
    }

    public void scheduleIn(long ticksFromNow, Runnable task) {
        Scheduler.schedule(tickCount + ticksFromNow, task);
    }

    public long getTick() {
        return tickCount;
    }
}
