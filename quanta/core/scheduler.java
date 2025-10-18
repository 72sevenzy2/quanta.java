package quanta.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class scheduler {
    private Map<Long, List<Runnable>> tasks = new HashMap<>();

    public void schedule(long tick, Runnable task) {
        tasks.computeIfAbsent(tick, k -> new ArrayList<>()).add(task);
    }

    public void execute(long tick) {
        List<Runnable> runnables = tasks.remove(tick);
        if (runnables != null) {
            for (Runnable r : runnables) {
                r.run();
            }
        }
    }
}
