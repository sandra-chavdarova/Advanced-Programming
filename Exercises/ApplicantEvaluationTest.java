package Exercises;

import java.util.Scanner;

class InvalidEvaluation extends Exception {
}


class Applicant {
    private String name;
    private int creditScore;
    private int employmentYears;
    private boolean hasCriminalRecord;

    public Applicant(String name, int creditScore, int employmentYears, boolean hasCriminalRecord) {
        this.name = name;
        this.creditScore = creditScore;
        this.employmentYears = employmentYears;
        this.hasCriminalRecord = hasCriminalRecord;
    }

    public String getName() {
        return name;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public int getEmploymentYears() {
        return employmentYears;
    }

    public boolean hasCriminalRecord() {
        return hasCriminalRecord;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nCredit score: %d\nExperience: %d\nCriminal record: %s\n",
                name, creditScore, employmentYears, hasCriminalRecord ? "Yes" : "No");
    }
}

interface Evaluator {
    enum TYPE {
        NO_CRIMINAL_RECORD,
        MORE_EXPERIENCE,
        MORE_CREDIT_SCORE,
        NO_CRIMINAL_RECORD_AND_MORE_EXPERIENCE,
        MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE,
        NO_CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE,
        INVALID // should throw exception
    }

    boolean evaluate(Applicant applicant);
}

class EvaluatorBuilder {
    public static Evaluator build(Evaluator.TYPE type) throws InvalidEvaluation {
        Evaluator evaluator = new BaseEvaluator();
        switch (type) {
            case NO_CRIMINAL_RECORD:
                return new NoCriminalRecordDecorator(evaluator);
            case MORE_CREDIT_SCORE:
                return new MoreCreditScoreDecorator(evaluator);
            case MORE_EXPERIENCE:
                return new MoreExperienceDecorator(evaluator);
            case NO_CRIMINAL_RECORD_AND_MORE_EXPERIENCE:
                evaluator = new NoCriminalRecordDecorator(evaluator);
                return new MoreExperienceDecorator(evaluator);
            case MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE:
                evaluator = new MoreExperienceDecorator(evaluator);
                return new MoreCreditScoreDecorator(evaluator);
            case NO_CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE:
                evaluator = new NoCriminalRecordDecorator(evaluator);
                return new MoreCreditScoreDecorator(evaluator);
            default:
                throw new InvalidEvaluation();
        }
    }
}

class BaseEvaluator implements Evaluator {

    @Override
    public boolean evaluate(Applicant applicant) {
        return true;
    }
}

abstract class EvaluatorDecorator implements Evaluator {
    protected Evaluator evaluator;

    public EvaluatorDecorator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }
}

class NoCriminalRecordDecorator extends EvaluatorDecorator {
    public NoCriminalRecordDecorator(Evaluator evaluator) {
        super(evaluator);
    }

    @Override
    public boolean evaluate(Applicant applicant) {
        return evaluator.evaluate(applicant) && !applicant.hasCriminalRecord();
    }
}

class MoreExperienceDecorator extends EvaluatorDecorator {
    public MoreExperienceDecorator(Evaluator evaluator) {
        super(evaluator);
    }

    @Override
    public boolean evaluate(Applicant applicant) {
        return evaluator.evaluate(applicant) && applicant.getEmploymentYears() >= 10;
    }
}

class MoreCreditScoreDecorator extends EvaluatorDecorator {
    public MoreCreditScoreDecorator(Evaluator evaluator) {
        super(evaluator);
    }

    @Override
    public boolean evaluate(Applicant applicant) {
        return evaluator.evaluate(applicant) && applicant.getCreditScore() >= 500;
    }
}

public class ApplicantEvaluationTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int creditScore = scanner.nextInt();
        int employmentYears = scanner.nextInt();
        boolean hasCriminalRecord = scanner.nextBoolean();
        int choice = scanner.nextInt();
        Applicant applicant = new Applicant(name, creditScore, employmentYears, hasCriminalRecord);
        Evaluator.TYPE type = Evaluator.TYPE.values()[choice];
        Evaluator evaluator = null;
        try {
            evaluator = EvaluatorBuilder.build(type);
            System.out.println("Applicant");
            System.out.println(applicant);
            System.out.println("Evaluation type: " + type.name());
            if (evaluator.evaluate(applicant)) {
                System.out.println("Applicant is ACCEPTED");
            } else {
                System.out.println("Applicant is REJECTED");
            }
        } catch (InvalidEvaluation invalidEvaluation) {
            System.out.println("Invalid evaluation");
        }
    }
}
