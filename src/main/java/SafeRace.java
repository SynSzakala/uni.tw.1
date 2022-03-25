import util.Lock;

public class SafeRace {
  private final Lock lock = new Lock();
  private int counter = 0;

  public static void main(String[] args) {
    SafeRace race = new SafeRace();
    int result = race.run();
    System.out.println("Result: " + result);
  }

  public int run() {
    Thread inc = new ThreadImpl(1);
    Thread dec = new ThreadImpl(-1);
    inc.start();
    dec.start();
    try {
      inc.join();
      dec.join();
      return counter;
    } catch (InterruptedException unused) {
      return 0;
    }
  }

  private class ThreadImpl extends Thread {
    private static final int OPS = 1000000;

    private final int delta;

    ThreadImpl(int delta) {
      this.delta = delta;
    }

    @Override public void run() {
      for (int i = 0; i < OPS; i++) {
        lock.acquire();
        counter += delta;
        lock.release();
      }
    }
  }
}
