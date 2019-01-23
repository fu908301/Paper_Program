import java.util.ArrayList;
import  java.util.Map;
public class Node {
    private char c; //字母串中的一個字元
    private byte type; //node 的類型
    private int count; //計算從root到這個node出現次數
    private int totalCount;//計算該點是第幾個insert
    private double weight;//該Node之Weight
    private int depth;
    private Map<String, Node> children;
    private ArrayList <Integer> occ_vec;
    public Node(){
        this.c = ' ';
        this.type = -1;
        this.count = 0;
        this.totalCount = 1;
        this.children = null;
        this.depth = 0;
        occ_vec = new ArrayList<>();
    }
    public void newOcc(){occ_vec = new ArrayList<>();}
    public void setOcc_vec(int input){
        occ_vec.add(input);
    }
    public ArrayList<Integer> getOcc_vec(){
        return occ_vec;
    }
    public void setTotalCount(int input){
        this.totalCount = input;
    }
    public int getTotalCount(){
        return this.totalCount;
    }
    public int getCount(){
        return  count;
    }
    public void setCount(int count){
        this.count = count;
    }
    public void setDepth(int depth){ this.depth = depth;}
    public int getDepth(){return this.depth;}
    public void setWeight(double weight){this.weight = weight;}
    public double getWeight(){return weight;}

    public void increaseCount(){
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
    public void getValue(){
        for (Object key : children.keySet()) {
            System.out.println(key + " : " + children.get(key));
        }
    }
    public void removeChild(char c){
        children.remove(c + "");
    }
}
