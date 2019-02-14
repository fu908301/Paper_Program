import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {
    private char c; //這個node代表的字元
    private ArrayList<Integer> occ; //這個node的occurrence vector
    private double weight;
    private Map<Character, Node_Occ> edge; //指向下一個node的edge, character放的是下一個Node的名稱
    public Node(){
        edge = new HashMap<>();
        c = 'x';
        weight = 0;
    }
    public Node(char c, double weight){
        this.c = c;
        this.weight = weight;
        edge = new HashMap<>();
    }

    public void setC(char c){
        this.c = c;
    }

    public void setOcc(ArrayList<Integer> occ){
        this.occ = occ;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setEdge(Map<Character, Node_Occ> edge) {
        this.edge = edge;
    }

    public void addEdge(Character c, Node_Occ NO){
        edge.put(c, NO);
    }

    public char getC() {
        return c;
    }
    public double getWeight(){
        return weight;
    }

    public ArrayList<Integer> getOcc(){
        return occ;
    }

    public Map<Character, Node_Occ> getEdge(){
        return edge;
    }
}
