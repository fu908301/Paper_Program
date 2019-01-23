import javax.print.attribute.standard.NumberOfDocuments;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class WPPM {
    private SuffixTrie trie = null;
    public WPPM(){
    }
    public void WPPM_run(){
        int threshold = 2;
        int LadderFactor;
        String input_S = input();
        ArrayList<Character> input_DB = stringToArrayList(input_S);
        ArrayList<Character> unique = getUnique(input_DB);
        ArrayList<Integer> len_vec = new ArrayList<>();
        ArrayList<Node> CN = new ArrayList<>();
        ArrayList<Node> N = new ArrayList<>();
        HashMap<Character, ArrayList> C = new HashMap<>();
        HashMap<Character, Float> weight = setWeight(unique);
        Node_Occ PT1 = new Node_Occ();
        //printTest(input_DB, unique, weight);
        trie = new SuffixTrie();
        trie.build(input_S);
        Node root = trie.getRoot();
        trie.run_all_tree(root);
        for (Object key : root.getChildren().keySet()){
            Node nextNode = root.getChildren().get(key);
            double test = getMaxWeight(nextNode);
            Periodicity_detection tempPD = new Periodicity_detection(nextNode.getOcc_vec(), nextNode.getOcc_vec().size(), 1, input_S.length());
            ArrayList<occ_period> ST1 = tempPD.getOP();
            if(ST1.size() * test >= threshold){
                C.clear();
                C.put(nextNode.getC(),ST1);
                len_vec = trie.len_vec(nextNode, len_vec);
                LadderFactor = _LadderFactor(len_vec);
                CN = union(CN, nextNode);
                while(LadderFactor > 0){
                    N.clear();
                    for(int i = 0; i < CN.size(); i++){
                        Node thisNode = CN.get(i);
                        for (Object key2 : thisNode.getChildren().keySet()){
                            Node thisNode2 = thisNode.getChildren().get(key2);
                            if(joinable(thisNode, nextNode)){
                                //PT1 =
                            }
                        }
                    }
                    LadderFactor--;
                }
            }
            /*System.out.println(key);
            for(int i = 0; i < ST1.size(); i++){
                ST1.get(i).print();
            }*/
        }
    }
   /* private Node_Occ join(Node input1, Node input2){
        Node_Occ _return = new Node_Occ();
        ArrayList<Character> pattern = new ArrayList<>();

    }*/
    private ArrayList<Node> union(ArrayList<Node> inputN, Node inputP){
        boolean in = false;
        for(int i = 0; i < inputN.size(); i++){
            if(inputN.get(i).getC() == inputP.getC()){
                in = true;
                break;
            }
        }
        if(!in){
            inputN.add(inputP);
        }
        return inputN;
    }
    private boolean joinable(Node input1, Node input2){
        boolean join = false;
        for(int i = 0; i < input1.getOcc_vec().size(); i++){
            if(input2.getOcc_vec().contains(input1.getOcc_vec().get(i))) {
                join = true;
                break;
            }
        }
        return join;
    }
    private int _LadderFactor(ArrayList<Integer> input){
        int _return = 0;
        int loc;
        Collections.sort(input);
        if(input.size() % 2 == 0){
            loc = input.size() / 2;
            _return = input.get(loc);
        }
        else if(input.size() % 2 != 0){
            loc = input.size() / 2 - 1;
            _return = input.get(loc);
        }
        return _return;
    }
    private String input(){
        String input_S;
        Scanner scanner = new Scanner(System.in);
        System.out.println("輸入字串");
        input_S = scanner.nextLine();
        return input_S;
    }

    private ArrayList<Character> stringToArrayList(String input){
        ArrayList<Character> tempArrayList = new ArrayList<>();
        for(int i = 0; i < input.length(); i++){
            tempArrayList.add(input.charAt(i));
        }
        return tempArrayList;
    }

    private ArrayList<Character> getUnique(ArrayList<Character> input){
        ArrayList<Character> tempArrayList = new ArrayList<>();
        for(int i = 0; i < input.size(); i++){
            if(!tempArrayList.contains(input.get(i))){
                tempArrayList.add(input.get(i));
            }
        }
        return tempArrayList;
    }

    private HashMap<Character, Float> setWeight(ArrayList<Character> input){
        HashMap<Character, Float> tempHash = new HashMap<>();
        for(int i = 0; i < input.size(); i++){
            if(input.get(i) == 'a'){
                tempHash.put(input.get(i), 0.8f);
            }
            else if(input.get(i) == 'b'){
                tempHash.put(input.get(i), 0.6f);
            }
            else if(input.get(i) == 'c'){
                tempHash.put(input.get(i), 0.7f);
            }
            else if(input.get(i) == 'd'){
                tempHash.put(input.get(i), 0.5f);
            }
        }
        return tempHash;
    }

    private void printTest(ArrayList<Character> input1, ArrayList<Character> input2, HashMap<Character, Float> input3){
        System.out.println("Input");
        for(int i = 0; i < input1.size(); i++){
            System.out.println(input1.get(i));
        }
        System.out.println("Unique");
        for(int i = 0; i < input2.size(); i++){
            System.out.println(input2.get(i));
        }
        System.out.println("Item with weight");
        for(Object key : input3.keySet()){
            System.out.println(key + ":" + input3.get(key));
        }
    }

    private double getMaxWeight(Node input){
        double currentW = input.getWeight();
        if(input.getChildren() != null) {
            for (Object key : input.getChildren().keySet()) {
                Node nextNode = input.getChildren().get(key);
                double nextW = getMaxWeight(nextNode);
                if(currentW > nextW){
                    return currentW;
                }
                else{
                    return nextW;
                }
            }
        }
      return currentW;
    }
}
