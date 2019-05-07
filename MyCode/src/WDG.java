import java.util.*;
public class WDG {
    private Map<Character, Node> tempNode;
    private Map<Character, ArrayList<Integer>> nodeMap;
    private Node node;
    private Node_Occ edge;
    private Other_Cal OC;

    public WDG(){
        tempNode = new HashMap<>();
        OC = new Other_Cal();
    }
    public WDG(Map<Character, ArrayList<Integer>> nodeMap){
        tempNode = new HashMap<>();
        OC = new Other_Cal();
        this.nodeMap = nodeMap;
    }
    public void setNodeMap(Map<Character, ArrayList<Integer>> nodeMap){
        this.nodeMap = nodeMap;
    }


    public void construct(ArrayList<ArrayList<Occ_Weight>> outWPM){
        char x, y;
        Node firstNode, secondNode;
        ArrayList<Integer> occ;
        Node_Occ NO;
        for(int i = 0; i < outWPM.size(); i++){
            for(int j = 0; j < outWPM.get(i).size(); j++) {
                if (outWPM.get(i).get(j).getWeight() != 0) {
                    x = OC.IntToChar(i);
                    y = OC.IntToChar(j);
                    if (!tempNode.containsKey(x)) {
                        firstNode = new Node(x, OC.getWeight(x));
                        firstNode.setOcc(nodeMap.get(x));
                        tempNode.put(x, firstNode);
                    } else {
                        firstNode = tempNode.get(x);
                    }
                    if (!tempNode.containsKey(y)) {
                        secondNode = new Node(y, OC.getWeight(y));
                        secondNode.setOcc(nodeMap.get(y));
                        tempNode.put(y, secondNode);
                    } else {
                        secondNode = tempNode.get(y);
                    }
                    occ = outWPM.get(i).get(j).getOcc();
                    NO = new Node_Occ(occ, secondNode);
                    firstNode.addEdge(y, NO);
                }
            }
        }
    }
    public void incremental(ArrayList<ArrayList<Occ_Weight>> outWPM){
        char x, y;
        Node firstNode, secondNode;
        ArrayList<Integer> occ;
        Node_Occ NO;
        for(int i = 0; i < outWPM.size(); i++){
            for(int j = 0; j < outWPM.get(i).size(); j++) {
                if (outWPM.get(i).get(j).getWeight() != 0) {
                    x = OC.IntToChar(i);
                    y = OC.IntToChar(j);
                    if (!tempNode.containsKey(x)) {
                        firstNode = new Node(x, OC.getWeight(x));
                        firstNode.setOcc(nodeMap.get(x));
                        tempNode.put(x, firstNode);
                    } else {
                        firstNode = tempNode.get(x);

                    }
                    if (!tempNode.containsKey(y)) {
                        secondNode = new Node(y, OC.getWeight(y));
                        secondNode.setOcc(nodeMap.get(y));
                        tempNode.put(y, secondNode);
                    } else {
                        secondNode = tempNode.get(y);
                    }
                    if(firstNode.getEdge().get(y) != null){
                        occ = outWPM.get(i).get(j).getOcc();
                        System.out.println(firstNode.getC() + "" + y + occ);
                        for(int z = 0; z < occ.size(); z++){
                            firstNode.getEdge().get(y).addOcc(occ.get(z));
                        }
                    }
                    else{
                        occ = outWPM.get(i).get(j).getOcc();
                        NO = new Node_Occ(occ, secondNode);
                        firstNode.addEdge(y, NO);
                    }
                }
            }
        }
    }

    public Map<Character, Node> getNode(ArrayList<ArrayList<Occ_Weight>> outWPM){ // 一次回傳全部node
        construct(outWPM);
        return tempNode;
    }
    public Map<Character, Node> getincremental(ArrayList<ArrayList<Occ_Weight>> outWPM){ // 一次回傳全部node
        incremental(outWPM);
        for(Object keys : tempNode.keySet()){
            System.out.println(keys + " " + tempNode.get(keys).getOcc());
        }
        return tempNode;
    }




}
