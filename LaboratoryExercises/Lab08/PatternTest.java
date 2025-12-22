package LaboratoryExercises.Lab08;

import java.util.ArrayList;
import java.util.List;

class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Song{" + "title=" + title + ", " + "artist=" + artist + "}";
    }
}

interface State {
    void pressPlay(MP3Player player);

    void pressStop(MP3Player player);

    void pressFWD(MP3Player player);

    void pressREW(MP3Player player);
}

class PlayingState implements State {

    @Override
    public void pressPlay(MP3Player player) {
        System.out.println("Song is already playing");
    }

    @Override
    public void pressStop(MP3Player player) {
        System.out.println("Song " + player.getCurrentIndex() + " is paused");
        player.setState(new PausedState());
    }

    @Override
    public void pressFWD(MP3Player player) {
        System.out.println("Forward...");
        player.nextSong();
        player.setState(new PausedState());
    }

    @Override
    public void pressREW(MP3Player player) {
        System.out.println("Reward...");
        player.previousSong();
        player.setState(new PausedState());
    }
}

class PausedState implements State {

    @Override
    public void pressPlay(MP3Player player) {
        System.out.println("Song " + player.getCurrentIndex() + " is playing");
        player.setState(new PlayingState());
    }

    @Override
    public void pressStop(MP3Player player) {
        System.out.println("Songs are stopped");
        player.reset();
        player.setState(new StoppedState());
    }

    @Override
    public void pressFWD(MP3Player player) {
        System.out.println("Forward...");
        player.nextSong();
        player.setState(new PausedState());
    }

    @Override
    public void pressREW(MP3Player player) {
        System.out.println("Reward...");
        player.previousSong();
        player.setState(new PausedState());
    }
}

class StoppedState implements State {

    @Override
    public void pressPlay(MP3Player player) {
        System.out.println("Song " + player.getCurrentIndex() + " is playing");
        player.setState(new PlayingState());
    }

    @Override
    public void pressStop(MP3Player player) {
        System.out.println("Songs are already stopped");
    }

    @Override
    public void pressFWD(MP3Player player) {
        System.out.println("Forward...");
        player.nextSong();
        player.setState(new PausedState());
    }

    @Override
    public void pressREW(MP3Player player) {
        System.out.println("Reward...");
        player.previousSong();
        player.setState(new PausedState());
    }
}

class MP3Player {

    private List<Song> songs;
    private int currentIndex;
    private State state;

    public MP3Player(List<Song> songs) {
        this.songs = songs;
        this.currentIndex = 0;
        this.state = new StoppedState();
    }

    void setState(State state) {
        this.state = state;
    }

    int getCurrentIndex() {
        return currentIndex;
    }

    void nextSong() {
        currentIndex = (currentIndex + 1) % songs.size();
    }

    void previousSong() {
        currentIndex = (currentIndex - 1 + songs.size()) % songs.size();
    }

    void reset() {
        currentIndex = 0;
    }

    void printCurrentSong() {
        System.out.println(songs.get(currentIndex));
    }

    public void pressPlay() {
        state.pressPlay(this);
    }

    public void pressStop() {
        state.pressStop(this);
    }

    public void pressFWD() {
        state.pressFWD(this);
    }

    public void pressREW() {
        state.pressREW(this);
    }

    @Override
    public String toString() {
        return "MP3Player{currentSong = " + currentIndex + ", songList = " + songs + "}";
    }
}

public class PatternTest {
    public static void main(String args[]) {
        List<Song> listSongs = new ArrayList<Song>();
        listSongs.add(new Song("first-title", "first-artist"));
        listSongs.add(new Song("second-title", "second-artist"));
        listSongs.add(new Song("third-title", "third-artist"));
        listSongs.add(new Song("fourth-title", "fourth-artist"));
        listSongs.add(new Song("fifth-title", "fifth-artist"));
        MP3Player player = new MP3Player(listSongs);


        System.out.println(player.toString());
        System.out.println("First test");


        player.pressPlay();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Second test");


        player.pressStop();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Third test");


        player.pressFWD();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();

        System.out.println(player.toString());
    }
}