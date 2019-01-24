import java.util.ArrayList;
import java.util.Map;

public class Node {
    char c; //這個node代表的字元
    ArrayList<Integer> occ; //這個node的occurrence vector
    double weight;
    Map<Character, Node_Occ> edge; //指向下一個node的edge

    public Node(){
        occ = new ArrayList<>();
        c = 'x';
        weight = 0;
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
