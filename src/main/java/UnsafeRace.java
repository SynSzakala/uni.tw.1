public class UnsafeRace {
  public static void main(String[] args) {
    UnsafeRace race = new UnsafeRace();
    int result = race.run();
    System.out.println("Result: " + result);
  }

  private int counter = 0;

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
      for (int i = 0; i < OPS; i++) counter += delta;
    }
  }
}
