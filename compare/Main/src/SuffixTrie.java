import java.io.BufferedReader;

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
            TotalCount = insert(stringBuffer.toString() + '$', TotalCount);
            symbolAccount++;
            stringBuffer.deleteCharAt(0);
        }
    }

    @Override
    public int insert(String word, int input) {
        Node currentNode = root;
        char c;
        for (int i = 0; i < word.length(); i++) {
            input++;
            c = word.charAt(i);
            if (c == '$') {
                if (currentNode.contain(c)) {
                    currentNode.increseCount();
                }
                else {
                    Node leaf = new LeafNode();
                    currentNode.addChild('$', leaf);
                    currentNode.increseCount();
                }
            } else {    //未到序列末尾，就增加枝结点
                if (currentNode.contain(c)) {
                    currentNode.increseCount();
                    currentNode = currentNode.next(c);
                } else {
                    Node branch = new BranchNode(c);
                    currentNode.addChild(c, branch);
                    currentNode.increseCount();
                    currentNode = branch;
                    currentNode.setTotalCount(input);
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
                System.out.println("Node: " + currentNode.getTotalCount()  + " edge key " + key);
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
