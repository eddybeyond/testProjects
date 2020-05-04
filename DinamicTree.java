import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DinamicTree {

    Map tree = new HashMap<String, Node>();

    public static void main(String[] args) {

        DinamicTree dt = new DinamicTree();

        //Gets data from file
        Path dir = Paths.get("C:\\test\\data.txt");
        Stream<String> file = null;
        try {
            file = Files.lines(dir, Charset.forName("ISO-8859-1"));
        } catch (IOException e) {
            System.out.println("Error loading data: "+e.getMessage());
        }
        //adds each line as a Node to the three
        file.sorted(Collections.reverseOrder()).forEach(line -> dt.addNode(line));
        //starts console interaction
        dt.interactuar();
        //prints tree
        dt.printTree();

    }


    private void addNode(String line){
        String val = "a. Auto 1.b, 1.c, 4.d, 356.h";
        String[] elements = new String[7];
        String pattern = "([a-z]?)\\.\\s(\\w+)\\s*((\\d*\\.\\w\\,\\s)*)((\\d*\\.\\w)*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        while (m.find( )) {
            for(int i = 0; i < m.groupCount()+1; i++){
                elements[i] = m.group(i);
            }
            Node n = new Node(elements[1], elements[2]);
            //valid 3
            if(!elements[3].equals("")){
                String[] children = elements[3].trim().split(",");
                for (String child : children) {
                    n.addChild(nodeToChild(child));
                }
            }
            //valid 5
            if(!elements[5].equals("")){
                n.addChild(nodeToChild(elements[5]));
            }
            tree.put(elements[2], n);
         }
    }

    private Node nodeToChild(String line){
        String[] childDetail= line.split("\\.");
        AtomicReference<Node> childNode = new AtomicReference<>();
        tree.forEach((k,v) ->
                {
                    if(((Node)v).getLetter().equals(childDetail[1])){
                        childNode.set((Node) v);
                    }
                }
            );
        Node childNodeOk = childNode.get();
        childNodeOk.setQuantity(childDetail[0]);
        return childNodeOk;
    }

    private void interactuar(){
        System.out.println("X+enter to exit");
        System.out.print("QUESTION:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sTexto = "";
        do {
            try {
                sTexto = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ANSWER: "+getAnswer(sTexto));
            System.out.print("QUESTION:");
        } while(!sTexto.equals("X"));
    }

    private String getAnswer(String question){
        Node n = (Node)tree.get(question);
        AtomicReference<String> answer = new AtomicReference<>("");
        if(n != null){
            answer.set("it has ");
            if(n.getChildren().size()==0){
                answer.set(answer + "no parts.");
            }
            n.getChildren().forEach(t -> {
                answer.set(answer + t.getQuantity() + " " + t.getName()+ ", ");
            });
        }else{
            answer.set(question + " is not an element of the tree.");
        }
        return answer.get();
    }

    private void printTree(){
        System.out.println("Printing tree...");
        tree.forEach((s,n) -> System.out.println("Nombre: "+s+", nodo: "+n.toString()));
    }

}


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Node {

    private String letter;
    private String name;
    private String quantity;
    private ArrayList<Node> children;

    public Node(String letter, String name) {
        this.letter = letter;
        this.name = name;
        children = new ArrayList<>();
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    @Override
    public String toString() {
        AtomicReference<String> childrenStr = new AtomicReference<>("");
        children.forEach(n -> childrenStr.set(childrenStr + n.toString()));
        return "Node{" +
                "letter='" + letter + '\'' +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", children=" + childrenStr.get() +
                '}';
    }
}
