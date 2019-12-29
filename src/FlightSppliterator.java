import java.time.LocalDate;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class FlightSppliterator implements Spliterator<Flight> {
    private final Spliterator<String> linesSpliterator;
    private String line;

    public FlightSppliterator(Spliterator<String> linesSpliterator) {
        this.linesSpliterator = linesSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Flight> action) {
        if (linesSpliterator.tryAdvance(line -> this.line = line)) {
            String[] split = Pattern.compile(",").split(line);
            action.accept(new Flight(
                    LocalDate.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2])),
                    split[3],
                    split[4],
                    split[5]
            ));
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<Flight> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return linesSpliterator.estimateSize();
    }

    @Override
    public int characteristics() {
        return linesSpliterator.characteristics();
    }
}
