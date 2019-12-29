import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get("flights.csv"));) {
            Spliterator<String> linesSpliterator = lines.spliterator();
            Spliterator<Flight> flightSpliterator = new FlightSppliterator(linesSpliterator);
            Stream<Flight> flights = StreamSupport.stream(flightSpliterator, false);

            // calculate distinct destination
            //  long count = flights.map(Flight::getDest).distinct().count();
            //  System.out.println(count);

            // group by origin destination and count
            Map<String, Map<String, Long>> collect = flights.collect(
                    Collectors.groupingBy(
                            Flight::getOrigin,
                            Collectors.groupingBy(
                                    Flight::getDest,
                                    Collectors.counting()
                            )
                    )
            );
            System.out.println("Grouping by origin destination and count");
            collect.forEach((s, stringLongMap) -> System.out.println("Origin: " + s + "\n Destination:" + stringLongMap));
            System.out.println("============================");
            // Which one has the max of destinations
            Map<String, Map.Entry<String, Long>> flightsOriginWithTopDestination = collect.entrySet().stream()
                    .collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    entry -> entry.getValue()
                                            .entrySet().stream()
                                            .max(
                                                    Map.Entry.comparingByValue()
                                            ).get()
                            )
                    );

            System.out.println("Flights origin with top destination");
            flightsOriginWithTopDestination.forEach((s, stringLongMap) -> System.out.println("Origin: " + s + "\n Destination:" + stringLongMap));
            System.out.println("============================");

            System.out.println("Flight origin with top destination");
            flightsOriginWithTopDestination.entrySet().stream()
                    .max(
                            Map.Entry.comparingByValue(
                                    Comparator.comparing(Map.Entry::getValue)
                            )
                    ).ifPresent(System.out::println);


            /*
            collect.entrySet().stream()
                    .flatMap(stringMapEntry -> stringMapEntry.getValue().entrySet().stream())
                    .max(Comparator.comparingLong(Map.Entry::getValue))
                    .ifPresent(System.out::println);
*/


/*            collect.entrySet().stream()
                    .mapToLong(Map.Entry::getValue)
                    .max()
                    .ifPresent(System.out::println);*/
//            collect.forEach((key, value) -> System.out.println(key + " - " + value));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
