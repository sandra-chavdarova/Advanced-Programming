package ExercisesFirstPartialExam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class NonExistingItemException extends Exception {
    private int id;

    public NonExistingItemException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Item with id %d doesn't exist", id);
    }
}

class Archive {
    private int id;
    private LocalDate dateArchived;
    private int timesOpened;

    public Archive(int id) {
        this.id = id;
        this.dateArchived = LocalDate.now();
        this.timesOpened = 0;
    }

    public int getId() {
        return id;
    }

    public void setDateArchived(LocalDate dateArchived) {
        this.dateArchived = dateArchived;
    }

    public int getTimesOpened() {
        return timesOpened;
    }

    public void setTimesOpened(int timesOpened) {
        this.timesOpened = timesOpened;
    }
}

class LockedArchive extends Archive {
    private LocalDate dateToOpen;

    public LockedArchive(int id, LocalDate dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
    }

    public LocalDate getDateToOpen() {
        return dateToOpen;
    }
}

class SpecialArchive extends Archive {
    private int maxOpen;


    public SpecialArchive(int id, int maxOpen) {
        super(id);
        this.maxOpen = maxOpen;

    }

    public int getMaxOpen() {
        return maxOpen;
    }
}

class ArchiveStore {
    private List<Archive> items;
    private StringBuilder log;

    public ArchiveStore() {
        this.items = new ArrayList<>();
        this.log = new StringBuilder();
    }

    public void archiveItem(Archive item, LocalDate dateToOpen) {
        item.setDateArchived(dateToOpen);
        items.add(item);
        log.append(String.format("Item %d archived at %s\n", item.getId(), dateToOpen));
    }

    public void openItem(int id, LocalDate date) throws NonExistingItemException {
        Archive item = items.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
        if (item instanceof LockedArchive && ((LockedArchive) item).getDateToOpen().isAfter(date))
            log.append(String.format("Item %d cannot be opened before %s\n", id, ((LockedArchive) item).getDateToOpen()));
        else if (item instanceof SpecialArchive && ((SpecialArchive) item).getMaxOpen() == item.getTimesOpened())
            log.append(String.format("Item %d cannot be opened more than %d times\n", id, item.getTimesOpened()));
        else if (item != null) {
            log.append(String.format("Item %d opened at %s\n", id, date));
            item.setTimesOpened(item.getTimesOpened() + 1);
        } else throw new NonExistingItemException(id);
    }

    public String getLog() {
        return log.toString();
    }
}

public class ArchiveStoreTest2 {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        LocalDate date = LocalDate.of(2013, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();
            LocalDate dateToOpen = date.atStartOfDay().plusSeconds(days * 24 * 60 * 60).toLocalDate();
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while (scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch (NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}
