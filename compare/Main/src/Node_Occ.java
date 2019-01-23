import java.util.ArrayList;

public class Node_Occ {
    private ArrayList<Character> pattern;
    private ArrayList<Integer> occ;
    public Node_Occ(){
        pattern = new ArrayList<>();
        occ = new ArrayList<>();
    }

    public Node_Occ(ArrayList<Character> input1, ArrayList<Integer> input2){
        this.pattern = input1;
        this.occ = input2;
    }

    public void set(ArrayList<Character> input1, ArrayList<Integer> input2){
        this.pattern = input1;
        this.occ = input2;
    }

    public ArrayList<Integer> get_occ(){
        return occ;
    }

    public ArrayList<Character> getNode(){
        return pattern;
    }
}
