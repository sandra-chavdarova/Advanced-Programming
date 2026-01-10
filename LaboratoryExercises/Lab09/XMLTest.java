package LaboratoryExercises.Lab09;

import java.util.*;

interface XMLComponent {

    void addAttribute(String attribute, String value);

    String print(int level);
}

class XMLLeaf implements XMLComponent {
    private String tag;
    private String value;
    private Map<String, String> attributes;

    public XMLLeaf(String tag, String value) {
        this.tag = tag;
        this.value = value;
        this.attributes = new LinkedHashMap<>();
    }

    @Override
    public void addAttribute(String attribute, String value) {
        attributes.put(attribute, value);
    }

    @Override
    public String print(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s<%s", "    ".repeat(level), tag));
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            sb.append(String.format(" %s=\"%s\"", entry.getKey(), entry.getValue()));
        }
        sb.append(">");
        sb.append(String.format("%s</%s>\n", value, tag));
        return sb.toString();
    }
}

class XMLComposite implements XMLComponent {
    private String tag;
    private List<XMLComponent> values;
    private Map<String, String> attributes;

    public XMLComposite(String tag) {
        this.tag = tag;
        this.values = new ArrayList<>();
        this.attributes = new LinkedHashMap<>();
    }

    @Override
    public void addAttribute(String attribute, String value) {
        attributes.put(attribute, value);
    }

    @Override
    public String print(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s<%s", "    ".repeat(level), tag));
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            sb.append(String.format(" %s=\"%s\"", entry.getKey(), entry.getValue()));
        }
        sb.append(">");
        sb.append("\n");
        for (XMLComponent c : values) {
            sb.append(c.print(level + 1));
        }
        sb.append(String.format("%S</%s>\n", "    ".repeat(level), tag));
        return sb.toString();
    }

    public void addComponent(XMLComponent component) {
        values.add(component);
    }
}


public class XMLTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        XMLComponent component = new XMLLeaf("student", "Trajce Trajkovski");
        component.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        XMLComposite composite = new XMLComposite("name");
        composite.addComponent(new XMLLeaf("first-name", "trajce"));
        composite.addComponent(new XMLLeaf("last-name", "trajkovski"));
        composite.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        if (testCase == 1) {
            // TODO Print the component object
            System.out.println(component.print(0));
        } else if (testCase == 2) {
            // TODO print the composite object
            System.out.println(composite.print(0));
        } else if (testCase == 3) {
            XMLComposite main = new XMLComposite("level1");
            main.addAttribute("level", "1");
            XMLComposite lvl2 = new XMLComposite("level2");
            lvl2.addAttribute("level", "2");
            XMLComposite lvl3 = new XMLComposite("level3");
            lvl3.addAttribute("level", "3");
            lvl3.addComponent(component);
            lvl2.addComponent(lvl3);
            lvl2.addComponent(composite);
            lvl2.addComponent(new XMLLeaf("something", "blabla"));
            main.addComponent(lvl2);
            main.addComponent(new XMLLeaf("course", "napredno programiranje"));

            // TODO print the main object
            System.out.println(main.print(0));
        }
    }
}
