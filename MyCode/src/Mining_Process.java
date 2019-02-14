import java.util.ArrayList;
import java.util.Map;

public class Mining_Process {
    private Map<Character, Node> nodes;
    private Node node_now;
    private int periodicity;
    private int threshold;
    private ArrayList<Pattern_Occ> answer;
    private ArrayList<Character> temp_answer;
    private Other_Cal OC;
    public Mining_Process(){}
    public Mining_Process(Map<Character, Node> nodes){
        this.nodes = nodes;
        this.periodicity = 4;
        this.threshold = 2;
        OC = new Other_Cal();
        answer = new ArrayList<>();
    }

    public void mining(){

    }

    public void mining_set(){
        node_now = nodes.get(0);//之後加迴圈去跑
        double tempWeight;
        int count = 0; //計算第三層開始的第幾次
        Pattern_Occ PO;
        ArrayList<Node_Occ> next_level = new ArrayList<>(); //下一層用的,而且放的是Edge
        ArrayList<Pattern_Occ> next_Answer = new ArrayList<>(); //下一層比對的答案
        if(node_now.getOcc().size() * OC.getWeight(node_now.getC()) >= threshold){ //First level
            PO = new Pattern_Occ(); //給新的PO
            PO.add_character(node_now.getC()); //加入起始點的character
            PO.setOcc(node_now.getOcc()); //加入起始點的OCC_VEC
            answer.add(PO);  //把PO加入answer
        }
        for (Object key : node_now.getEdge().keySet()){  //Second level
            PO = new Pattern_Occ();  //新的PO
            PO.add_character(node_now.getC());  //加入起始點的C
            PO.add_character(node_now.getEdge().get(key).getNode().getC()); //加入下一層的C
            PO.setOcc(node_now.getEdge().get(key).getOcc()); //加入下一層的OCC
            next_level.add(node_now.getEdge().get(key)); //下一層的edge放進去
            tempWeight = (OC.getWeight(node_now.getC()) + OC.getWeight(node_now.getEdge().get(key).getNode().getC())) / 2;
            if(tempWeight * node_now.getEdge().get(key).getOcc().size() >= threshold){
                answer.add(PO); //如果超過門檻,加入答案
                next_Answer.add(PO);//下一次的Mining用到
            }
        }
        PO = new Pattern_Occ();
        PO.add_character(node_now.getC());
        PO.add_character('*');
        PO.setOcc(node_now.getOcc());
        next_Answer.add(PO); //把尾端包含*的pattern加入下一層用的答案
        for(int i = 3; i < periodicity; i++){ //第三層以後
            count++;

        }
    }
}
