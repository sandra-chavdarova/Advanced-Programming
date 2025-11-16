package ExercisesFirstPartialExam;

import java.util.*;
import java.util.stream.Collectors;

class Movie implements Comparable<Movie> {
    private String title;
    private List<Double> ratings;

    public Movie(String title, List<Double> ratings) {
        this.title = title;
        this.ratings = ratings;
    }

    public double rating() {
        double sum = ratings.stream().mapToDouble(Double::doubleValue).sum();
        return sum / ratings.size();
    }

    public double ratingCoef() {
        return rating() * ratings.size();
    }

    public int getSize() {
        return ratings.size();
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f) of %d ratings", title, rating(), ratings.size());
    }

    @Override
    public int compareTo(Movie o) {
        int comparison = Double.compare(o.rating(), this.rating());
        if (comparison == 0)
            return this.title.compareTo(o.title);
        return comparison;
    }
}

class MoviesList {
    private List<Movie> movies;

    public MoviesList() {
        this.movies = new ArrayList<>();
    }

    public void addMovie(String title, int[] ratings) {
        List<Double> ratingsList = new ArrayList<>();
        for (int i = 0; i < ratings.length; i++) {
            ratingsList.add((double) ratings[i]);
        }
        movies.add(new Movie(title, ratingsList));
    }

    public List<Movie> top10ByAvgRating() {
        movies.sort(null);
        return movies.stream().limit(10).collect(Collectors.toList());
    }

    public List<Movie> top10ByRatingCoef() {
        movies.sort(Comparator.comparingDouble(Movie::ratingCoef).reversed().thenComparing(Movie::getTitle));
        return movies.stream().limit(10).collect(Collectors.toList());
    }
}

public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoviesList moviesList = new MoviesList();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int x = scanner.nextInt();
            int[] ratings = new int[x];
            for (int j = 0; j < x; ++j) {
                ratings[j] = scanner.nextInt();
            }
            scanner.nextLine();
            moviesList.addMovie(title, ratings);
        }
        scanner.close();
        List<Movie> movies = moviesList.top10ByAvgRating();
        System.out.println("=== TOP 10 BY AVERAGE RATING ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        movies = moviesList.top10ByRatingCoef();
        System.out.println("=== TOP 10 BY RATING COEFFICIENT ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}
