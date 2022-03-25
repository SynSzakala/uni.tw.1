import java.io.FileWriter;
import java.util.stream.IntStream;

public class RaceHistogram {
  private static final int COUNT = 100;
  private static final String CSV_HEADER = "Result\n";
  private static final String FILENAME = "result.csv";

  public static void main(String[] args) {
    int[] results = runRaces().toArray();
    saveToCsv(results);
  }

  private static IntStream runRaces() {
    return IntStream.range(1, COUNT).map((it) -> new UnsafeRace().run());
  }

  private static void saveToCsv(int[] results) {
    try (FileWriter writer = new FileWriter(FILENAME)) {
      writer.write(CSV_HEADER);
      for (int result : results) {
        writer.write(result + "\n");
      }
    } catch (Throwable unused) {
      // ignore
    }
  }
}
