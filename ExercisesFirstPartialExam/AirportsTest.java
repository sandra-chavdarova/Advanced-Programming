package ExercisesFirstPartialExam;

import java.util.*;

class Flight implements Comparable<Flight> {
    private String from;
    private String to;
    private int time;
    private int duration;

    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
    }

    @Override
    public String toString() {
        int end = time + duration;
        int plus = end / (24 * 60);
        end %= (24 * 60);
        return String.format("%s-%s %02d:%02d-%02d:%02d%s %dh%02dm", from, to, time / 60, time % 60, end / 60, end % 60, plus > 0 ? " +1d" : "", duration / 60, duration % 60
        );
    }

    @Override
    public int compareTo(Flight o) {
        int comparison = Integer.compare(this.time, o.time);
        if (comparison == 0)
            return this.from.compareToIgnoreCase(o.from);
        return comparison;
    }
}

class Airport {
    private String name;
    private String country;
    private String code;
    private int passengers;
    private Map<String, Set<Flight>> flights;

    public Airport(String name, String country, String code, int passengers) {
        this.name = name;
        this.country = country;
        this.code = code;
        this.passengers = passengers;
        this.flights = new TreeMap<>();
    }

    public Map<String, Set<Flight>> getFlights() {
        return flights;
    }

    public void addFlight(String from, String to, int time, int duration) {
        flights.computeIfAbsent(to, k -> new TreeSet<>()).add(new Flight(from, to, time, duration));
    }

    @Override
    public String toString() {
        return String.format("%s (%s)\n%s\n%d", name, code, country, passengers);
    }
}

class Airports {
    private Map<String, Airport> airportMap;

    public Airports() {
        this.airportMap = new TreeMap<>();
    }

    public void addAirport(String name, String country, String code, int passengers) {
        airportMap.put(code, new Airport(name, country, code, passengers));
    }

    public void addFlights(String from, String to, int time, int duration) {
        airportMap.get(from).addFlight(from, to, time, duration);
    }

    public void showFlightsFromAirport(String code) {
        Airport airport = airportMap.get(code);
        System.out.println(airport);
        int i = 1;
        for (String toCode : airport.getFlights().keySet()) {
            Set<Flight> flights = airport.getFlights().get(toCode);
            for (Flight flight : flights) {
                System.out.println(i++ + ". " + flight);
            }
        }
    }

    public void showDirectFlightsFromTo(String from, String to) {
        Airport airport = airportMap.get(from);
        Set<Flight> flights = airport.getFlights().get(to);
        if (flights != null)
            for (Flight flight : flights) {
                System.out.println(flight);
            }
        else
            System.out.println("No flights from " + from + " to " + to);
    }

    public void showDirectFlightsTo(String to) {
        Set<Flight> flights = new TreeSet<>();
        for (Airport airport : airportMap.values()) {
            Set<Flight> flightsTo = airport.getFlights().get(to);
            if (flightsTo != null) {
                flights.addAll(flightsTo);
            }
        }

        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}

public class AirportsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airports airports = new Airports();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] codes = new String[n];
        for (int i = 0; i < n; ++i) {
            String al = scanner.nextLine();
            String[] parts = al.split(";");
            airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            codes[i] = parts[2];
        }
        int nn = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nn; ++i) {
            String fl = scanner.nextLine();
            String[] parts = fl.split(";");
            airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        int f = scanner.nextInt();
        int t = scanner.nextInt();
        String from = codes[f];
        String to = codes[t];
        System.out.printf("===== FLIGHTS FROM %S =====\n", from);
        airports.showFlightsFromAirport(from);
        System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
        airports.showDirectFlightsFromTo(from, to);
        t += 5;
        t = t % n;
        to = codes[t];
        System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
        airports.showDirectFlightsTo(to);
    }
}
