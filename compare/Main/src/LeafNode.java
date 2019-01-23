public class LeafNode  extends Node{
    public LeafNode(){
        super.setC('$');
        super.setCount(0);
        super.setTotalCount(1);
        super.setWeight(0);
        super.setDepth(0);
        super.setType((byte) 1);
        super.newOcc();
    }
}
