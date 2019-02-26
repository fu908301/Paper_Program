import java.util.ArrayList;
import java.util.HashMap;
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


    public void mining_set(){
        node_now = nodes.get('a');//之後加迴圈去跑
        double tempWeight;
        int count = 0; //計算第三層開始的第幾次
        Pattern_Occ PO;
        ArrayList<Node_Occ> next_level = new ArrayList<>(); //下一層用的,而且放的是Edge
        ArrayList<Node_Occ> next_level2 = new ArrayList<>();
        ArrayList<Pattern_Occ> next_Answer = new ArrayList<>(); //下一層比對的答案
        ArrayList<Pattern_Occ> next_Answer2 = new ArrayList<>();
        if(node_now.getOcc().size() * OC.getWeight(node_now.getC()) >= threshold){ //First level
            PO = new Pattern_Occ(); //給新的PO
            PO.add_character(node_now.getC()); //加入起始點的character
            PO.setOcc(node_now.getOcc()); //加入起始點的OCC_VEC
            answer.add(PO);  //把PO加入answer
        }
        //第一層確定沒問題
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
        System.out.println("Next Answer:");
        for(int a = 0; a < next_Answer.size(); a++){
            next_Answer.get(a).print();
        }
        System.out.println("Next Answer finished");
        System.out.println("Next Level:");
        for(int a = 0; a < next_level.size(); a++){
            System.out.print(next_level.get(a).getNode().getC() + " : ");
            for(int b = 0; b < next_level.get(a).getOcc().size(); b++){
                System.out.print(next_level.get(a).getOcc().get(b) + " ");
            }
            System.out.println();
        }
        System.out.println("Next Level finished");
        Map <Character, ArrayList<Integer>> this_map = new HashMap<>();
        ArrayList<Integer> this_map_occ;
        ArrayList<Integer> temp_occ = new ArrayList<>();
        for(int i = 3; i < periodicity; i++){ //第三層以後
            count++;
            this_map.clear();
            for(int j = 0; j < next_level.size(); j++){//比對該層的OCC跟下一層的OCC能不能連結
                for(Character key : next_level.get(j).getNode().getEdge().keySet()){ //這個是下一層node的EDGE數量
                    for(int k = 0; k < next_level.get(j).getOcc().size(); k++){ // 這個是去跟人家比對的前一層
                        for(int l = 0; l < next_level.get(j).getNode().getEdge().get(key).getOcc().size(); l++){ //下一層OCC的量
                            if(next_level.get(j).getOcc().get(k) + 1 == next_level.get(j).getNode().getEdge().get(key).getOcc().get(l)){ //如果前一層的OCC+1 = 下一層的OCC
                                if(!this_map.containsKey(next_level.get(j).getNode().getEdge().get(key).getNode().getC())){
                                    this_map_occ = new ArrayList<>(); //新的OCC_VEC
                                    this_map_occ.add(next_level.get(j).getNode().getEdge().get(key).getOcc().get(l) - count); //把OCC的值-COUNT放進去
                                    this_map.put(next_level.get(j).getNode().getEdge().get(key).getNode().getC(), this_map_occ);
                                }
                                else if(this_map.containsKey(next_level.get(j).getNode().getEdge().get(key).getNode().getC())){
                                    this_map.get(next_level.get(j).getNode().getEdge().get(key).getNode().getC()).add(next_level.get(j).getNode().getEdge().get(key).getOcc().get(l) - count);
                                }
                            }
                        }
                    }
                }
            }
            next_Answer2.clear();
            System.out.println("This Map Test");
            for(Character key : this_map.keySet()){
                System.out.print(key + ":");
                for(int a = 0; a < this_map.get(key).size(); a++){
                    System.out.print(this_map.get(key).get(a) + " ");
                }
                System.out.println();
            }
            System.out.println("This Map Test Finished");
            for(int x = 0; x < next_Answer.size(); x++){
                for(Character key : this_map.keySet()){
                    for(int y = 0; y < next_Answer.get(x).getOcc().size(); y++){
                        for(int z = 0; z < this_map.get(key).size(); z++){
                            if(next_Answer.get(x).getOcc().get(y) == this_map.get(key).get(z)){
                                temp_occ.add(this_map.get(key).get(z));
                            }
                        }
                    }
                    double weight = (next_Answer.get(x).average_weight() + OC.getWeight(key)) / (next_Answer.get(x).getPattern().size() + 1);
                    if(weight * temp_occ.size() > threshold){
                        PO = new Pattern_Occ();
                        PO.setPattern(next_Answer.get(x).getPattern());
                        PO.add_character(key);
                        PO.setOcc(temp_occ);
                        answer.add(PO);
                        next_Answer2.add(PO);
                    }
                    temp_occ.clear();
                }
            }
            /*for(int x = 0; x < next_Answer.size(); x++){ // 給下一層比對用的答案
                Pattern_Occ tempPO = next_Answer.get(x);
                tempPO.add_character('*');
                next_Answer2.add(tempPO);
            }
            next_Answer = next_Answer2;
            for(int x = 0; x < next_level.size(); x++){  //  下一層比對用的Edge
                for(Object key: next_level.get(x).getNode().getEdge().keySet())
                    next_level2.add(next_level.get(x).getNode().getEdge().get(key));
            }
            next_level = next_level2;*/
        }
    }

    public void print(){
        mining_set();
        System.out.println("Answer Print");
        for(int i = 0; i < answer.size(); i++){
            answer.get(i).print();
        }
        System.out.println("Answer Print Finished");
    }
}
