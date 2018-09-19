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
        while(stringBuffer.length() > 0){
            insert(stringBuffer.toString() + '$');
            symbolAccount++;
            stringBuffer.deleteCharAt(0);
        }
    }

    @Override
    public boolean insert(String word) {
        Node currentNode = root;
        char c;
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            if (c == '$') {
                if (currentNode.contain(c))
                    currentNode.increseCount();
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
                }
            }
        }
        return true;
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

    @Override
    public boolean delete(String word) {
        return false;
    }

    @Override
    public void save(String path){}

    @Override
    public void read(String path){}
}
