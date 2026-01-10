package LaboratoryExercises.Lab09;

import java.util.*;

interface IDocument {
    String display();

    String getId();

    List<String> getText();
}

class Document implements IDocument {
    private String id;
    private List<String> text;

    public Document(String id, String text) {
        this.id = id;
        this.text = new ArrayList<>();
        String[] parts = text.split("\n");
        this.text.addAll(Arrays.asList(parts));
    }

    @Override
    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Document ").append(id).append(" ===\n");
        for (String t : text) {
            sb.append(t).append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<String> getText() {
        return text;
    }
}

abstract class DocumentDecorator implements IDocument {
    IDocument document;

    public DocumentDecorator(IDocument document) {
        this.document = document;
    }
}

class LineNumberDecorator extends DocumentDecorator {

    public LineNumberDecorator(IDocument document) {
        super(document);
    }

    @Override
    public String display() {
        String base = document.display();
        String[] parts = base.split("\n");
        StringBuilder sb = new StringBuilder();
        sb.append(parts[0]).append("\n");
        for (int i = 1; i < parts.length; i++) {
            sb.append(i).append(": ").append(parts[i]).append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String getId() {
        return document.getId();
    }

    @Override
    public List<String> getText() {
        return document.getText();
    }
}

class WordCountDecorator extends DocumentDecorator {

    public WordCountDecorator(IDocument document) {
        super(document);
    }

    @Override
    public String display() {
        String base = document.display();
        int words = document.display().split("\\s+").length-4;
        StringBuilder sb = new StringBuilder();
        sb.append(base).append("\n");
        sb.append("Words: ").append(words);
        return sb.toString().trim();
    }

    @Override
    public String getId() {
        return document.getId();
    }

    @Override
    public List<String> getText() {
        return document.getText();
    }
}

class RedactionDecorator extends DocumentDecorator {
    private List<String> forbiddenWords;

    public RedactionDecorator(IDocument document, List<String> forbiddenWords) {
        super(document);
        this.forbiddenWords = forbiddenWords;
    }

    @Override
    public String display() {
        String base = document.display();
        String[] parts = base.split("\n");
        StringBuilder sb = new StringBuilder();
        sb.append(parts[0]).append("\n");
        for (int i = 1; i < parts.length; i++) {
            String[] words = parts[i].split("\\s+");
            for (int j = 0; j < words.length; j++) {
                if (forbiddenWords.contains(words[j].toLowerCase())) {
                    words[j] = "*";
                }
                sb.append(words[j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String getId() {
        return document.getId();
    }

    @Override
    public List<String> getText() {
        return document.getText();
    }
}

class DocumentViewer {
    private Map<String, IDocument> documents;

    public DocumentViewer() {
        this.documents = new HashMap<>();
    }

    public void addDocument(String id, String text) {
        Document document = new Document(id, text);
        documents.put(id, document);
    }

    public void enableLineNumbers(String id) {
        IDocument document = documents.get(id);
        document = new LineNumberDecorator(document);
        documents.put(id, document);
    }

    public void enableWordCount(String id) {
        IDocument document = documents.get(id);
        document = new WordCountDecorator(document);
        documents.put(id, document);
    }

    public void enableRedaction(String id, List<String> forbiddenWords) {
        IDocument document = documents.get(id);
        document = new RedactionDecorator(document, forbiddenWords);
        documents.put(id, document);
    }

    public void display(String id) {
        IDocument document = documents.get(id);
        System.out.println(document.display());
    }

    public IDocument getDocument(String id) {
        return documents.get(id);
    }
}

public class DocumentViewerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        DocumentViewer documentViewer = new DocumentViewer();
        for (int i = 0; i < n; i++) {
            String id = scanner.nextLine();
            int m = scanner.nextInt();
            scanner.nextLine();
            StringBuilder text = new StringBuilder();
            for (int j = 0; j < m; j++) {
                text.append(scanner.nextLine()).append("\n");
            }
            documentViewer.addDocument(id, text.toString());
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("enableLineNumbers")) {
                String id = line.split("\\s+")[1];
                documentViewer.enableLineNumbers(id);
            } else if (line.startsWith("enableWordCount")) {
                String id = line.split("\\s+")[1];
                documentViewer.enableWordCount(id);
            } else if (line.startsWith("enableRedaction")) {
                List<String> forbiddenWords = new ArrayList<>();
                String[] parts = line.split("\\s+");
                for (int k = 2; k < parts.length; k++) {
                    forbiddenWords.add(parts[k].toLowerCase());
                }
                documentViewer.enableRedaction(parts[1], forbiddenWords);
            } else if (line.startsWith("display")) {
                String id = line.split("\\s+")[1];
                documentViewer.display(id);
            } else
                return;
        }
    }
}
