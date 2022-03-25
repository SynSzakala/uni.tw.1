package util;

import java.util.concurrent.atomic.AtomicBoolean;

public class Lock {
  private final AtomicBoolean isLocked = new AtomicBoolean(false);

  public void acquire() {
    while (!isLocked.compareAndSet(false, true))
      Thread.yield();
  }

  public void release() {
    isLocked.set(false);
  }
}