import  java.util.Map;
public class Node {
    private char c; //字母串中的一個字元
    private byte type; //node 的類型
    private int count; //計算從root到這個node出現次數

    private Map<String, Node> children;
    public Node(){
        this.c = ' ';
        this.type = -1;
        this.count = 0;
        this.children = null;
    }

    public int getCount(){
        return  count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void increseCount(){
        this.count++;
    }

    public char getC(){
        return c;
    }

    public void setC(char c){
        this.c = c;
    }

    public byte getType(){
        return type;
    }

    public void setType(byte type){
        this.type = type;
    }

    public Map<String, Node> getChildren(){
        return children;
    }

    public void setChildren(Map<String, Node> children){
        this.children = children;
    }

    public boolean contain(char c){
        if(children.containsKey(c + ""))
            return true;
        else
            return false;
    }

    public int size(){
        return children.size();
    }

    public Node next(char c){
        return children.get(c + "");
    }

    public void addChild(char c,Node child){
        children.put(c + "", child);
    }

    public void removeChild(char c){
        children.remove(c + "");
    }
}
