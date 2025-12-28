package Exercises;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

class CategoryNotFoundException extends Exception {
    private String category;

    public CategoryNotFoundException(String category) {
        this.category = category;
    }

    @Override
    public String getMessage() {
        return String.format("Category %s was not found", category);
    }
}

class Category implements Comparable<Category> {
    private String category;

    public Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }


    @Override
    public int compareTo(Category o) {
        return this.category.compareTo(o.category);
    }
}

interface INewsItem {
    String getTitle();

    LocalDateTime getDate();

    Category getCategory();

    public String getTeaser();
}

abstract class NewsItem implements INewsItem {
    private String title;
    private LocalDateTime date;
    private Category category;

    public NewsItem(String title, LocalDateTime date, Category category) {
        this.title = title;
        this.date = date;
        this.category = category;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public String getTeaser() {
        return String.format("%s\n%d\n", title, date.until(LocalDateTime.now(), ChronoUnit.MINUTES));
    }
}

class TextNewsItem extends NewsItem {
    private String text;

    public TextNewsItem(String title, LocalDateTime date, Category category, String text) {
        super(title, date, category);
        this.text = text;
    }

    public String getTeaser() {
        return super.getTeaser() + String.format("%s\n", text.substring(0, Math.min(text.length(), 80)));
    }
}

class MediaNewsItem extends NewsItem {
    private String url;
    private int views;

    public MediaNewsItem(String title, LocalDateTime date, Category category, String url, int views) {
        super(title, date, category);
        this.url = url;
        this.views = views;
    }

    public String getTeaser() {
        return super.getTeaser() + String.format("%s\n%d\n", url, views);
    }
}

class FrontPage {
    private List<Category> categories;
    private List<NewsItem> news;

    public FrontPage(List<Category> categories) {
        this.categories = categories;
        this.news = new ArrayList<>();
    }

    public void addNewsItem(NewsItem newsItem) {
        news.add(newsItem);
    }

    public List<NewsItem> listByCategory(Category category) {
        return news.stream().filter(n -> n.getCategory() == category).collect(Collectors.toList());
    }

    public List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
        List<Category> filtered = categories.stream().filter(c -> c.getCategory().equals(category)).collect(Collectors.toList());
        if (filtered.isEmpty())
            throw new CategoryNotFoundException(category);
        return news.stream().filter(n -> n.getCategory().getCategory().equals(category)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (NewsItem item : news) {
            stringBuilder.append(item.getTeaser());
        }
        return stringBuilder.toString();
    }
}

public class FrontPageTest {
    public static void main(String[] args) {
        // Reading
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        List<Category> categories = new ArrayList<>();
//        Category[] categories = new Category[parts.length];
        for (int i = 0; i < parts.length; ++i) {
            categories.add(new Category(parts[i]));
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        FrontPage frontPage = new FrontPage(categories);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int min = scanner.nextInt();
            scanner.nextLine();

            LocalDateTime date = LocalDateTime.now().minusMinutes(min);

            String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();

            TextNewsItem tni =
                    new TextNewsItem(title, date, categories.get(categoryIndex), text);
            frontPage.addNewsItem(tni);
        }

        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int min = scanner.nextInt();
            scanner.nextLine();

            LocalDateTime date = LocalDateTime.now().minusMinutes(min);

            String url = scanner.nextLine();
            int views = scanner.nextInt();
            scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();

            MediaNewsItem mni =
                    new MediaNewsItem(title, date, categories.get(categoryIndex), url, views);
            frontPage.addNewsItem(mni);
        }
        // Execution
        String category = scanner.nextLine();
        System.out.println(frontPage);
        for (Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
            System.out.println(frontPage.listByCategoryName(category).size());
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
