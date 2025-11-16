package ExercisesFirstPartialExam;

import java.util.*;

class SeatTakenException extends Exception {
    @Override
    public String getMessage() {
        return "SeatTakenException";
    }
}

class SeatNotAllowedException extends Exception {
    @Override
    public String getMessage() {
        return "SeatNotAllowedException";
    }
}

class Sector implements Comparable<Sector> {
    private String code;
    private int seats;
    private Map<Integer, Boolean> available;
    private int type;

    public Sector(String code, int seats) {
        this.code = code;
        this.seats = seats;
        available = new HashMap<>();
        for (int i = 0; i < seats; i++) {
            available.put(i + 1, true);
        }
        this.type = 0;
    }

    public int numberAvailableSeats() {
        return (int) available.values().stream().filter(Boolean::booleanValue).count();
    }

    public String getCode() {
        return code;
    }

    public Map<Integer, Boolean> getAvailable() {
        return available;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int compareTo(Sector o) {
        int comparison = Integer.compare(o.numberAvailableSeats(), this.numberAvailableSeats());
        if (comparison == 0)
            return this.code.compareTo(o.code);
        return comparison;
    }

    public double occupancy() {
        return 100 - ((double) numberAvailableSeats() / seats * 100.0);
    }

    @Override
    public String toString() {
        return String.format("%s\t%d/%d\t%.1f%%", code, numberAvailableSeats(), seats, occupancy());
    }
}

class Stadium {
    private String name;
    private List<Sector> sectors;

    public Stadium(String name) {
        this.name = name;
        this.sectors = new ArrayList<>();
    }

    public void createSectors(String[] sectorNames, int[] sizes) {
        for (int i = 0; i < sectorNames.length; i++) {
            sectors.add(new Sector(sectorNames[i], sizes[i]));
        }
    }

    public void buyTicket(String sectorName, int seat, int type) throws SeatNotAllowedException, SeatTakenException {
        Sector sector = sectors.stream().filter(s -> s.getCode().equalsIgnoreCase(sectorName)).findFirst().orElse(null);
        if (sector == null) return;
        if (!sector.getAvailable().get(seat)) {
            throw new SeatTakenException();
        }
        if (type != 0) {
            if (sector.getType() != 0 && sector.getType() != type) {
                throw new SeatNotAllowedException();
            }
        }

        if (sector.getType() == 0 && type != 0) {
            sector.setType(type);
        }
        sector.getAvailable().put(seat, false);
    }

    public void showSectors() {
        sectors.sort(null);
        for (Sector sector : sectors)
            System.out.println(sector);
    }
}

public class StadiumTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] sectorNames = new String[n];
        int[] sectorSizes = new int[n];
        String name = scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            sectorNames[i] = parts[0];
            sectorSizes[i] = Integer.parseInt(parts[1]);
        }
        Stadium stadium = new Stadium(name);
        stadium.createSectors(sectorNames, sectorSizes);
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            try {
                stadium.buyTicket(parts[0], Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]));
            } catch (SeatNotAllowedException e) {
                System.out.println("SeatNotAllowedException");
            } catch (SeatTakenException e) {
                System.out.println("SeatTakenException");
            }
        }
        stadium.showSectors();
    }
}
