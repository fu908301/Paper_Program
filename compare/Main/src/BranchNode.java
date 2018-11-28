import java.util.HashMap;
public class BranchNode extends Node{
    public BranchNode(char c){
        super.setC(c);
        super.setType((byte) 1);
        super.setCount(0);
        super.setTotalCount(1);
        super.setWeight(0);
        super.setChildren(new HashMap<String, Node>());
    }
}
