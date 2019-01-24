import java.util.ArrayList;

public class Node_Occ {
    ArrayList<Integer> occ;
    Node node;
    public Node_Occ(){
        occ = new ArrayList<>();
        node = new Node();
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

}
