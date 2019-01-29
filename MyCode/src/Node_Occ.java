import java.util.ArrayList;

public class Node_Occ {
    ArrayList<Integer> occ;
    Node node;
    boolean check; //判斷是否走過
    public Node_Occ(){
        occ = new ArrayList<>();
        node = new Node();
        check = true;
    }

    public Node_Occ(ArrayList<Integer> occ, Node node){
        this.node = node;
        this.occ = occ;
        check = true;
    }

    public void setOcc(ArrayList<Integer> occ){
        this.occ = occ;
    }

    public void setNode(Node node){
        this.node = node;
    }

    public ArrayList<Integer> getOcc(){
        return occ;
    }

    public Node getNode(){
        return node;
    }
    public boolean getCheck(){
        return check;
    }

    public void changeCheck(boolean input){
        check = input;
    }
}
