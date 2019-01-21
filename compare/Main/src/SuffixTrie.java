import java.io.BufferedReader;
import java.util.ArrayList;

public class SuffixTrie implements Trie{
    private Node root = null;
    private int symbolAccount = 0;

    public SuffixTrie(){
        root = new BranchNode(' ');
    }

    public Node getRoot(){
        return root;
    }
    public void setRoot(Node root){
        this.root = root;
    }


    @Override
    public void build(String input){
        StringBuffer stringBuffer = new StringBuffer(input);
        int TotalCount = 1;
        while(stringBuffer.length() > 0){
            TotalCount = insert(stringBuffer.toString() + '$', TotalCount, input);
            symbolAccount++;
            stringBuffer.deleteCharAt(0);
        }
    }

    @Override
    public int insert(String word, int input, String real_word) {
        int occ = real_word.length() - word.length() + 1;
        Node currentNode = root;
        char c;
        for (int i = 0; i < word.length(); i++) {
            int nextDepth = currentNode.getDepth() + 1;
            input++;
            c = word.charAt(i);
            if (c == '$') {
                if (currentNode.contain(c)) {
                    currentNode.increaseCount();
                }
                else {
                    Node leaf = new LeafNode();
                    currentNode.addChild('$', leaf);
                    currentNode.increaseCount();
                }
            } else {    //未到序列末尾，就增加枝结点
                if (currentNode.contain(c)) {
                    currentNode.increaseCount();
                    currentNode.setOcc_vec(occ);
                    System.out.println("2 " + c + " : " + occ);
                    currentNode = currentNode.next(c);
                } else {
                    if(!currentNode.getOcc_vec().contains(occ)){
                        currentNode.setOcc_vec(occ);
                    }
                    System.out.println("3 " + c + " : " + occ);
                    Node branch = new BranchNode(c);
                    if(c == 'a'){
                        branch.setWeight(0.8);
                    }
                    else if(c == 'b'){
                        branch.setWeight(0.6);
                    }
                    else if(c == 'c'){
                        branch.setWeight(0.7);
                    }
                    else if(c == 'd'){
                        branch.setWeight(0.5);
                    }
                    else if(c == '$'){
                        branch.setWeight(0.0);
                    }
                    currentNode.addChild(c, branch);
                    currentNode = branch;
                    currentNode.increaseCount();
                    currentNode.setTotalCount(input);
                    currentNode.setDepth(nextDepth);
                    currentNode.setOcc_vec(occ);
                }
            }
        }
        return input;
    }

    @Override
    public boolean search(String word){
        word += word + '$';
        Node currentNode = root;
        char c;
        for(int i = 0; i < word.length(); i++){
            c = word.charAt(i);
            if(c == '$' && currentNode.contain(c)){
                return true;
            }
            else {
                if(currentNode.contain(c)){
                    currentNode = currentNode.next(c);
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }


    public ArrayList<Integer> len_vec(Node currNode, ArrayList<Integer> input){
        if(currNode.getChildren() != null){
                for (Object key : currNode.getChildren().keySet()) {
                    Node nextNode = currNode.getChildren().get(key);
                    if(nextNode.getType() == (byte)1){
                        System.out.println("Count: " + currNode.getDepth());
                        input.add(currNode.getDepth());
                    }
                    len_vec(nextNode, input);
                }
            }
        return input;
    }

    public boolean searchString(String string) {
        Node currNode = root;
        char c;
        for (int i = 0; i < string.length(); i++) {
            c = string.charAt(i);
            if (!currNode.contain(c)) {
                return false;
            }else {
                currNode = currNode.next(c);
            }
        }
        return true;
    }

    public int getOccurences(String string) {
        Node currNode = root;
        char c;
        for (int i = 0; i < string.length(); i++) {
            c = string.charAt(i);
            if (!currNode.contain(c)) {
                return -1;
            }else {
                currNode = currNode.next(c);
            }
        }
        return currNode.getCount();
    }

    public void run_all_tree(Node currentNode){
        if(currentNode.getChildren() != null) {
            for (Object key : currentNode.getChildren().keySet()) {
                Node nextNode = currentNode.getChildren().get(key);
                System.out.println("Node: " + currentNode.getTotalCount()  + " edge key " + key + " weight : " + nextNode.getWeight());
                for(int i = 0; i < nextNode.getOcc_vec().size(); i++){
                    System.out.println("OCC:" + nextNode.getOcc_vec().get(i));
                }
                run_all_tree(nextNode);
            }
        }

    }

    @Override
    public boolean delete(String word) {
        return false;
    }

    @Override
    public void save(String path){}

    @Override
    public void read(String path){}
}
