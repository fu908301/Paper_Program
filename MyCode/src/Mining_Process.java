import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mining_Process {
    private Map<Character, Node> nodes;
    private Node node_now;
    private int periodicity;
    private double threshold;
    private int star_limit;
    private int length;
    private ArrayList<Pattern_Occ> answer;
    private ArrayList<Character> temp_answer;
    private Other_Cal OC;
    public Mining_Process(){}
    public Mining_Process(Map<Character, Node> nodes, int length){
        this.length = length;
        this.nodes = nodes;
        this.periodicity = 8;
        this.threshold = 2.0;
        this.star_limit = 3;
        OC = new Other_Cal();
        answer = new ArrayList<>();
    }


    public void mining_set(){
        ArrayList<occ_period> after_PD;
        ArrayList<Integer> PD_Occ;
        Periodicity_detection PD;
        for(Object keys : nodes.keySet()) {
            PD_Occ = new ArrayList<>();
            boolean have = false;
            node_now = nodes.get(keys);//之後加迴圈去跑

            PD = new Periodicity_detection(node_now.getOcc(), 0.5, length);
            after_PD = PD.getOP();
            for(int  i = 0; i < after_PD.size(); i++){
                if(after_PD.get(i).getPeriod() == periodicity){
                    have = true;
                    PD_Occ = after_PD.get(i).getOcc();
                    break;
                }
            }
            double tempWeight;
            int count = 0; //計算第三層開始的第幾次
            Pattern_Occ PO;
            ArrayList<Node_Occ> next_level = new ArrayList<>(); //下一層用的,而且放的是Edge
            ArrayList<Node_Occ> next_level2 = new ArrayList<>();
            ArrayList<Pattern_Occ> next_Answer = new ArrayList<>(); //下一層比對的答案
            ArrayList<Pattern_Occ> next_Answer2 = new ArrayList<>();
            if(have) {
                if ((double)PD_Occ.size() * OC.getWeight(node_now.getC()) >= threshold) { //First level
                    PO = new Pattern_Occ(); //給新的PO
                    PO.add_character(node_now.getC()); //加入起始點的character
                    PO.setOcc(PD_Occ); //加入起始點的OCC_VEC
                    answer.add(PO);  //把PO加入answer
                }
                //第一層確定沒問題
                for (Object key : node_now.getEdge().keySet()) {  //Second level
                    PO = new Pattern_Occ();  //新的PO
                    Pattern_Occ PO2 = new Pattern_Occ();
                    PO.add_character(node_now.getC());  //加入起始點的C
                    PO.add_character(node_now.getEdge().get(key).getNode().getC()); //加入下一層的C
                    for(int x = 0; x < PD_Occ.size(); x++){                         //加入下一層的OCC
                        for(int y = 0; y < node_now.getEdge().get(key).getOcc().size(); y++){
                            int first = PD_Occ.get(x);
                            int second = node_now.getEdge().get(key).getOcc().get(y);
                            if(first == second){
                                PO.addOcc(first);
                                break;
                            }
                        }
                    }
                    PO2.add_character(node_now.getC());  //加入起始點的C
                    PO2.add_character(node_now.getEdge().get(key).getNode().getC());
                    for(int x = 0; x < PD_Occ.size(); x++){                         //加入下一層的OCC
                        for(int y = 0; y < node_now.getEdge().get(key).getOcc().size(); y++){
                            int first = PD_Occ.get(x);
                            int second = node_now.getEdge().get(key).getOcc().get(y);
                            if(first == second){
                                PO2.addOcc(first);
                                break;
                            }
                        }
                    }
                    next_level.add(node_now.getEdge().get(key)); //下一層的edge放進去
                    tempWeight = (OC.getWeight(node_now.getC()) + OC.getWeight(node_now.getEdge().get(key).getNode().getC())) / 2;
                    double size = (double)PO.getOcc().size();
                    if (tempWeight * size >= threshold) {
                        answer.add(PO); //如果超過門檻,加入答案
                        next_Answer.add(PO2);//下一次的Mining用到
                    }
                }
                Pattern_Occ PO3 = new Pattern_Occ();
                PO3.add_character(node_now.getC());
                PO3.add_character('*');
                PO3.setOcc(PD_Occ);
                next_Answer.add(PO3); //把尾端包含*的pattern加入下一層用的答案
            /*System.out.println("Next Answer:");
            for (int a = 0; a < next_Answer.size(); a++) {
                next_Answer.get(a).print();
            }
            System.out.println("Next Answer finished");
            System.out.println("Next Level:");
            for (int a = 0; a < next_level.size(); a++) {
                System.out.print(next_level.get(a).getNode().getC() + " : ");
                for (int b = 0; b < next_level.get(a).getOcc().size(); b++) {
                    System.out.print(next_level.get(a).getOcc().get(b) + " ");
                }
                System.out.println();
            }
            System.out.println("Next Level finished");*/
                Map<Character, ArrayList<Integer>> this_map;
                ArrayList<Integer> this_map_occ;
                ArrayList<Integer> temp_occ = new ArrayList<>();
                for (int i = 3; i <= periodicity; i++) { //第三層以後
                    next_level2 = new ArrayList<>();
                    count++;
                    this_map = new HashMap<>();
                    for (int j = 0; j < next_level.size(); j++) {//比對該層的OCC跟下一層的OCC能不能連結
                        for (Character key : next_level.get(j).getNode().getEdge().keySet()) { //這個是下一層node的EDGE數量
                            for (int k = 0; k < next_level.get(j).getOcc().size(); k++) { // 這個是去跟人家比對的前一層
                                for (int l = 0; l < next_level.get(j).getNode().getEdge().get(key).getOcc().size(); l++) { //下一層OCC的量
                                    if (next_level.get(j).getOcc().get(k) + 1 == next_level.get(j).getNode().getEdge().get(key).getOcc().get(l)) { //如果前一層的OCC+1 = 下一層的OCC
                                        if (!this_map.containsKey(next_level.get(j).getNode().getEdge().get(key).getNode().getC())) {
                                            this_map_occ = new ArrayList<>(); //新的OCC_VEC
                                            this_map_occ.add(next_level.get(j).getNode().getEdge().get(key).getOcc().get(l) - count); //把OCC的值-COUNT放進去
                                            this_map.put(next_level.get(j).getNode().getEdge().get(key).getNode().getC(), this_map_occ);
                                        } else if (this_map.containsKey(next_level.get(j).getNode().getEdge().get(key).getNode().getC())) {
                                            this_map.get(next_level.get(j).getNode().getEdge().get(key).getNode().getC()).add(next_level.get(j).getNode().getEdge().get(key).getOcc().get(l) - count);
                                        }
                                        if (next_level2.contains(next_level.get(j).getNode().getEdge().get(key))) {
                                        } else {
                                            next_level2.add(next_level.get(j).getNode().getEdge().get(key));
                                        }
                                        break;
                                    } else if (next_level.get(j).getOcc().get(k) + 1 < next_level.get(j).getNode().getEdge().get(key).getOcc().get(0)) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
               /* System.out.println("This Map Test");
                for (Character key : this_map.keySet()) {
                    System.out.print(key + ":");
                    for (int a = 0; a < this_map.get(key).size(); a++) {
                        System.out.print(this_map.get(key).get(a) + " ");
                    }
                    System.out.println();
                }
                System.out.println("This Map Test Finished");*/
                    //int _count = 0;
                    for (int x = 0; x < next_Answer.size(); x++) {
                        for (Character key : this_map.keySet()) {
                            for (int y = 0; y < next_Answer.get(x).getOcc().size(); y++) {
                                for (int z = 0; z < this_map.get(key).size(); z++) {
                                    int next_A = next_Answer.get(x).getOcc().get(y);
                                    int this_M = this_map.get(key).get(z);
                                    if (next_A == this_M) {
                                        temp_occ.add(this_map.get(key).get(z));
                                        break;
                                    }
                                }
                            }
                            double weight = (next_Answer.get(x).average_weight() + OC.getWeight(key)) / (next_Answer.get(x).getPattern().size() + 1);
                        /*for (int z = 0; z < next_Answer.get(x).getPattern().size(); z++){
                            System.out.print("TempPattern2 : " );
                            next_Answer.get(x).print();
                        }
                        System.out.println("OCC : " + temp_occ);*/
                            if (weight * (double)temp_occ.size() >= threshold) {
                                Pattern_Occ PO5 = new Pattern_Occ();
                                ArrayList<Character> tempPattern2 = new ArrayList<>();
                                for (int z = 0; z < next_Answer.get(x).getPattern().size(); z++) {
                                    tempPattern2.add(next_Answer.get(x).getPattern().get(z));
                                }
                                PO5.setPattern(tempPattern2);
                                PO5.add_character(key);
                                PO5.setOcc(temp_occ);
                            /*System.out.println("PO5");
                            PO5.print();
                            System.out.println("PO5 finished");*/
                                answer.add(PO5);
                                Pattern_Occ PO4 = new Pattern_Occ();
                                ArrayList<Character> tempPattern = new ArrayList<>();
                                for (int z = 0; z < next_Answer.get(x).getPattern().size(); z++) {
                                    tempPattern.add(next_Answer.get(x).getPattern().get(z));
                                }
                                PO4.setPattern(tempPattern);
                                PO4.add_character(key);
                                PO4.setOcc(temp_occ);
                            /*System.out.println("PO4");
                            PO4.print();
                            System.out.println("PO4 finished");*/
                                next_Answer2.add(PO4);
                            }
                            temp_occ = new ArrayList<>();
                        }
                    }
                    for (int x = 0; x < next_Answer.size(); x++) {
                        Pattern_Occ tempPO3 = new Pattern_Occ();
                        for (int z = 0; z < next_Answer.get(x).getPattern().size(); z++) {
                            tempPO3.add_character(next_Answer.get(x).getPattern().get(z));
                        }
                        tempPO3.add_character('*');
                        for (int z = 0; z < next_Answer.get(x).getOcc().size(); z++) {
                            tempPO3.addOcc(next_Answer.get(x).getOcc().get(z));
                        }
                        if (tempPO3.star_count() <= star_limit) {
                            next_Answer2.add(tempPO3);
                        }
                    }
                    next_Answer = next_Answer2;
                    next_Answer2 = new ArrayList<>();
                    next_level = next_level2;

                }
            }
        }
    }

    public void print(){
        mining_set();
        /*System.out.println("Answer Print");
        for(int i = 0; i < answer.size(); i++){
            answer.get(i).print();
        }
        System.out.println("Answer Size : " + answer.size());
        System.out.println("Answer Print Finished");*/
        System.out.println("Answer Size : " + answer.size());
    }
}
