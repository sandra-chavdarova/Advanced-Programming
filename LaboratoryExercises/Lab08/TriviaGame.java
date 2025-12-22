package LaboratoryExercises.Lab08;

import java.util.ArrayList;
import java.util.Scanner;

interface GameState {
    void input(TriviaGame game, String input);

    void setState(TriviaGame game); // called when state becomes active
}

class TriviaQuestion {
    private static final int TRUEFALSE = 0;
    private static final int FREEFORM = 1;
    private String question;        // Actual question
    private String answer;        // Answer to question
    private int value;            // Point value of question
    private int type;            // Question type, TRUEFALSE or FREEFORM

    public TriviaQuestion() {
        question = "";
        answer = "";
        value = 0;
        type = FREEFORM;
    }

    public TriviaQuestion(String q, String a, int v, int t) {
        question = q;
        answer = a;
        value = v;
        type = t;
    }

    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(answer);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    public static int getTRUEFALSE() {
        return TRUEFALSE;
    }

    public static int getFREEFORM() {
        return FREEFORM;
    }
}

class TriviaData {

    private ArrayList<TriviaQuestion> data;

    public TriviaData() {
        data = new ArrayList<TriviaQuestion>();
    }

    public void addQuestion(String q, String a, int v, int t) {
        TriviaQuestion question = new TriviaQuestion(q, a, v, t);
        data.add(question);
    }

    public int numQuestions() {
        return data.size();
    }

    public TriviaQuestion getQuestion(int index) {
        return data.get(index);
    }
}

class AskingQuestionState implements GameState {

    @Override
    public void input(TriviaGame game, String input) {
        TriviaQuestion q = game.getCurrentQuestion();
        boolean correct = q.checkAnswer(input);
        if (correct) {
            System.out.println("That is correct!  You get " + q.getValue() + " points.");
            game.setScore(game.getScore() + q.getValue());
        } else {
            System.out.println("Wrong, the correct answer is " + q.getAnswer());
        }
        game.setState(new ShowingScoreState());
    }

    @Override
    public void setState(TriviaGame game) {
        TriviaQuestion q = game.getCurrentQuestion();
        System.out.println("Question " + (game.getQuestionIndex() + 1) + ".  " + q.getValue() + " points.");
        if (q.getType() == TriviaQuestion.getTRUEFALSE()) {
            System.out.println(q.getQuestion());
            System.out.println("Enter 'T' for true or 'F' for false.");
        } else {
            System.out.println(q.getQuestion());
        }
    }
}

class ShowingScoreState implements GameState {

    @Override
    public void input(TriviaGame game, String input) {
        game.nextQuestion();
        if (game.getQuestionIndex() >= game.getQuestions().numQuestions()) {
            game.setState(new GameOverState());
        } else {
            game.setState(new AskingQuestionState());
        }
    }

    @Override
    public void setState(TriviaGame game) {
        System.out.println("Your score is " + game.getScore());
    }
}

class GameOverState implements GameState {

    @Override
    public void input(TriviaGame game, String input) {

    }

    @Override
    public void setState(TriviaGame game) {
        System.out.println("Game over!  Thanks for playing!");
    }
}

public class TriviaGame {

    private TriviaData questions = new TriviaData(); // Questions
    private int score = 0;
    private int currentQuestion = 0;
    private GameState state;

    public TriviaGame() {
        // Load questions
        questions = new TriviaData();
        questions.addQuestion("The possession of more than two sets of chromosomes is termed?",
                "polyploidy", 3, TriviaQuestion.getFREEFORM());
        questions.addQuestion("Erling Kagge skiied into the north pole alone on January 7, 1993.",
                "F", 1, TriviaQuestion.getTRUEFALSE());
        questions.addQuestion("1997 British band that produced 'Tub Thumper'",
                "Chumbawumba", 2, TriviaQuestion.getFREEFORM());
        questions.addQuestion("I am the geometric figure most like a lost parrot",
                "polygon", 2, TriviaQuestion.getFREEFORM());
        questions.addQuestion("Generics were introducted to Java starting at version 5.0.",
                "T", 1, TriviaQuestion.getTRUEFALSE());
        state = new AskingQuestionState();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            state.setState(this);
            if (state instanceof AskingQuestionState) {
                String input = scanner.nextLine();
                state.input(this, input);
            } else if (state instanceof ShowingScoreState) {
                state.input(this, "");
            } else if (state instanceof GameOverState) {
                state.input(this, "");
                break;
            }
        }
    }

    public TriviaData getQuestions() {
        return questions;
    }

    public int getScore() {
        return score;
    }

    public TriviaQuestion getCurrentQuestion() {
        return questions.getQuestion(currentQuestion);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public int getQuestionIndex() {
        return currentQuestion;
    }

    public void nextQuestion() {
        this.currentQuestion++;
    }

    // Main game
    public static void main(String[] args) {
        new TriviaGame().play();
    }
}
