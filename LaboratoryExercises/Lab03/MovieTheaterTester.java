package LaboratoryExercises.Lab03;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Movie {
    private String title;
    private String genre;
    private int year;
    private double averageRating;

    public Movie(String title, String genre, int year, double averageRating) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.averageRating = averageRating;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public double getAverageRating() {
        return averageRating;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %.2f", title, genre, year, averageRating);
    }
}

class MovieTheater {
    private List<Movie> movies;

    public MovieTheater() {
        this.movies = new ArrayList<>();
    }

    public void readMovies(InputStream is) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(is));
        int n = Integer.parseInt(bf.readLine());
        for (int i = 0; i < n; i++) {
            String title = bf.readLine();
            String genre = bf.readLine();
            int year = Integer.parseInt(bf.readLine());
            String[] ratings = bf.readLine().split("\\s+");
            double averageRating = 0;
            for (int j = 0; j < ratings.length; j++) {
                averageRating += Double.parseDouble(ratings[j]);
            }
            averageRating /= ratings.length;
            movies.add(new Movie(title, genre, year, averageRating));
        }
    }

    public void printByGenreAndTitle() {
        List<Movie> sortedMovies = movies.stream()
                .sorted(Comparator.comparing((Movie m) -> m.getGenre())
                        .thenComparing(m -> m.getTitle())).collect(Collectors.toList());
        for (Movie movie : sortedMovies)
            System.out.println(movie);
    }

    public void printByYearAndTitle() {
        List<Movie> sortedMovies = movies.stream()
                .sorted(Comparator.comparingInt((Movie m) -> m.getYear())
                        .thenComparing(m -> m.getTitle())).collect(Collectors.toList());
        for (Movie movie : sortedMovies)
            System.out.println(movie);
    }

    public void printByRatingAndTitle() {
        List<Movie> sortedMovies = movies.stream()
                .sorted(Comparator.comparingDouble((Movie m) -> -m.getAverageRating())
                        .thenComparing(m -> m.getTitle())).collect(Collectors.toList());
        for (Movie movie : sortedMovies)
            System.out.println(movie);
    }
}


public class MovieTheaterTester {
    public static void main(String[] args) {
        MovieTheater mt = new MovieTheater();
        try {
            mt.readMovies(System.in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("SORTING BY RATING");
        mt.printByRatingAndTitle();
        System.out.println("\nSORTING BY GENRE");
        mt.printByGenreAndTitle();
        System.out.println("\nSORTING BY YEAR");
        mt.printByYearAndTitle();
    }
}