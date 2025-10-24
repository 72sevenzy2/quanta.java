# context

quanta.java is a lightweight utility that provides a deterministic ticking system for simulations, game, or step-based logic. Allows developers to schedule asynchronous tasks and run objects on fixed simulation steps using the interface

# key methods include:

start() , stop() - for stopping/starting ticking.
register(tickable t) - for registering objects that update on every tick.
scheduleIn(ticks, task) - for async operation, enter the number of ticks and the task that should be performed.
setTimeScale(scale) - for setting the tick speed (slow/fast).
setseed(seed) - set deterministic, random speed.
getTick() - the current tick count/number;


* Interface for tick-updating objects:

void ontick(long tick, double delta);

#

# optional methods:

scheduleRepeat(startTick, interval, task) - enter the start tick, the interval in which the task will perform once ended.

Deterministic number generator:
- nextInt(bound);
- nextDouble();

allows predictable results.


# example usage:

quanta q = new quanta(20);
q.register((tick, delta) -> System.out.println("a tick " + tick));
q.scheduleIn(50, () -> System.out.println("after 50 ticks"));
q.start();