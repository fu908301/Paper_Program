
import java.io.*;
import java.util.*;

public class WPPM {
    private SuffixTrie trie = null;
    public WPPM(){
    }
    public void WPPM_run(){
        int threshold = 2;
        int periodicity = 7;
        Other_Cal OC = new Other_Cal();
        String input_S = input();
        ArrayList<Character> input_DB = stringToArrayList(input_S);
        ArrayList<Character> unique = getUnique(input_DB);
        Map<Character, ArrayList<Integer>> tempMap = new HashMap<>();
        //printTest(input_DB, unique, weight);

        trie = new SuffixTrie();
        trie.build(input_S);
        Node root = trie.getRoot();
        ArrayList<Pattern_Occ> next_answer = new ArrayList<>();
        ArrayList<Pattern_Occ> next_answer2 = new ArrayList<>();
        ArrayList<Pattern_Occ> answer = new ArrayList<>();
        ArrayList<Node> next_level = new ArrayList<>();
        for (String key : root.getChildren().keySet()){
            next_answer.clear();
            next_answer2.clear();
            next_level.clear();
            Node nextNode = root.getChildren().get(key); //level one
            if(nextNode.getOcc_vec().size() * OC.getWeight(key.charAt(0)) >= threshold){ //如果第一層有過門檻
                Pattern_Occ PO = new Pattern_Occ();
                PO.add_character(nextNode.getC());
                PO.setOcc(nextNode.getOcc_vec());
                answer.add(PO);
            }
            Pattern_Occ PO2 = new Pattern_Occ();
            PO2.add_character(nextNode.getC());
            PO2.setOcc(nextNode.getOcc_vec());
            next_answer.add(PO2);
            next_level.add(nextNode);
            /*for(int i = 0; i < answer.size();i++){
                answer.get(i).print();
            }
            for(int i = 0; i < next_answer.size(); i++){
                next_answer.get(i).print();
            }*/
            for(int i = 0; i < periodicity - 1; i++) { //第二層以後
                /*System.out.println("Next Level : ");
                for(int x = 0 ; x < next_level.size(); x++){
                    System.out.print(next_level.get(x).getC() + " : ");
                    for(int y = 0; y < next_level.get(x).getOcc_vec().size(); y++){
                        System.out.print(next_level.get(x).getOcc_vec().get(y) + " ");
                    }
                    System.out.println();
                }

                System.out.println("Next level finished");*/
                tempMap = new HashMap<>();
                for (int j = 0; j < next_level.size(); j++) {
                    Node preNode = next_level.get(j);
                    if(preNode.getChildren() != null) {
                        for (String key2 : preNode.getChildren().keySet()) { // 這個迴圈是把下一層NODE的OCC放進去tempMap
                            Node nextNode2 = preNode.getChildren().get(key2);
                            char c = key2.charAt(0);
                            if (!tempMap.containsKey(c)) {
                                ArrayList<Integer> tempOcc = new ArrayList<>();
                                for (int k = 0; k < nextNode2.getOcc_vec().size(); k++) {
                                    tempOcc.add(nextNode2.getOcc_vec().get(k));
                                }
                                tempMap.put(c, tempOcc);
                            } else {
                                for (int k = 0; k < nextNode2.getOcc_vec().size(); k++) {
                                    tempMap.get(c).add(nextNode2.getOcc_vec().get(k));
                                }
                            }
                        }
                    }
                }
                /*System.out.println("temp test");
                for(Object x : tempMap.keySet()){
                    System.out.println(x  + ":" +  tempMap.get(x));
                }
                System.out.println("temp finish");
                System.out.println("next answer test");
                for(int z = 0; z < next_answer.size(); z++){
                    next_answer.get(z).print();
                }
                System.out.println("next answer finish");*/
                next_answer2 = new ArrayList<>();
                Pattern_Occ tempPO;
                Pattern_Occ tempPO2;
                for(int k = 0; k < next_answer.size(); k++){ //這邊是結合上一層與下一層的OCC
                    for(Character key3 : tempMap.keySet()){
                        tempPO = new Pattern_Occ();
                        for(int z = 0; z < next_answer.get(k).getPattern().size(); z++){
                            tempPO.add_character(next_answer.get(k).getPattern().get(z));
                        }
                        tempPO.add_character(key3);
                        tempPO2 = new Pattern_Occ();
                        for(int z = 0; z < next_answer.get(k).getPattern().size(); z++){
                            tempPO2.add_character(next_answer.get(k).getPattern().get(z));
                        }
                        tempPO2.add_character(key3);
                        for(int l = 0; l < next_answer.get(k).getOcc().size(); l++){
                            for(int m = 0; m < tempMap.get(key3).size(); m++){
                                int next_A = next_answer.get(k).getOcc().get(l);
                                int temp_M = tempMap.get(key3).get(m);
                                /*System.out.println("Next answer : " + next_A);
                                System.out.println("Temp Map : " + temp_M);*/
                                if(next_A == temp_M){
                                    tempPO.addOcc(next_answer.get(k).getOcc().get(l));
                                    tempPO2.addOcc(next_answer.get(k).getOcc().get(l));
                                }
                            }
                        }
                        /*System.out.println("TEMP");
                        tempPO.print();
                        System.out.println("TEMP FINISH");*/
                        if(tempPO.getOcc().size() * tempPO.average_weight() >= threshold){
                            /*System.out.println("TEMP PO");
                            tempPO.print();
                            tempPO2.print();*/
                            answer.add(tempPO);
                            next_answer2.add(tempPO2);
                        }
                    }
                }
               /* System.out.println("Answer print");
                for(int c = 0; c < answer.size(); c++){
                    answer.get(c).print();
                }
                System.out.println("Answer print finish");
                System.out.println("Next Answer2 print");
                for(int c = 0; c < next_answer2.size(); c++){
                    next_answer2.get(c).print();
                }
                System.out.println("Next Answer2 print finish");*/

                for(int x = 0; x < next_answer.size(); x++){
                    Pattern_Occ tempPO3 = new Pattern_Occ();
                    for(int z = 0; z < next_answer.get(x).getPattern().size(); z++){
                        tempPO3.add_character(next_answer.get(x).getPattern().get(z));
                    }
                    tempPO3.add_character('*');
                    for(int z = 0; z < next_answer.get(x).getOcc().size(); z++){
                        tempPO3.addOcc(next_answer.get(x).getOcc().get(z));
                    }
                    next_answer2.add(tempPO3);
                }
                next_answer = next_answer2;
                ArrayList<Node> next_level2 = new ArrayList<>();
                for(int x = 0; x < next_level.size(); x++){
                    if(next_level.get(x).getChildren() != null) {
                        for (Object c : next_level.get(x).getChildren().keySet()) {
                            next_level2.add(next_level.get(x).getChildren().get(c));
                        }
                    }
                }
                next_level = next_level2;
            }
        }
        /*System.out.println("Answer print.");
        for(int z = 0; z < answer.size(); z++){
            answer.get(z).print();
        }
        System.out.println("Answer Size : " + answer.size());
        System.out.println("Answer print finised.");*/
        System.out.println("Answer Size : " + answer.size());
        /*for (Object key : root.getChildren().keySet()){
            Node nextNode = root.getChildren().get(key);
            double test = getMaxWeight(nextNode);
            Periodicity_detection tempPD = new Periodicity_detection(nextNode.getOcc_vec(), nextNode.getOcc_vec().size(), 1, input_S.length());
            ArrayList<occ_period> ST1 = tempPD.getOP();
            if(ST1.size() * test >= threshold){
                C.clear();
                C.put(nextNode.getC(),ST1);
                len_vec = trie.len_vec(nextNode, len_vec);
                LadderFactor = _LadderFactor(len_vec);
                CN = union(CN, nextNode);
                while(LadderFactor > 0){
                    N.clear();
                    for(int i = 0; i < CN.size(); i++){
                        Node thisNode = CN.get(i);
                        for (Object key2 : thisNode.getChildren().keySet()){
                            Node thisNode2 = thisNode.getChildren().get(key2);
                            if(joinable(thisNode, nextNode)){
                                //PT1 =
                            }
                        }
                    }
                    LadderFactor--;
                }
            }
            System.out.println(key);
            for(int i = 0; i < ST1.size(); i++){
                ST1.get(i).print();
            }
        }*/

    }
    private String input(){
        String input_S = new String();
        try{
            char c;
            InputStreamReader isr = new InputStreamReader(new FileInputStream("C://Users//AndyFu//Desktop//Paper_code//compare//Data2.csv"));
            BufferedReader reader = new BufferedReader(isr);
            String line = null;
            reader.readLine();
            while((line = reader.readLine()) != null){
                String item[] = line.split(",");
                String convert = item[2].replace("\"", "");
                double num = Double.parseDouble(convert);
                c = change(num);
                //System.out.println(num);
                input_S = Character.toString(c) + input_S;
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.fillInStackTrace();
        }catch (NumberFormatException e) {
            System.out.println(" parse int error!! " + e);
        }
        return input_S;
    }

    private ArrayList<Character> stringToArrayList(String input){
        ArrayList<Character> tempArrayList = new ArrayList<>();
        for(int i = 0; i < input.length(); i++){
            tempArrayList.add(input.charAt(i));
        }
        return tempArrayList;
    }

    private ArrayList<Character> getUnique(ArrayList<Character> input){
        ArrayList<Character> tempArrayList = new ArrayList<>();
        for(int i = 0; i < input.size(); i++){
            if(!tempArrayList.contains(input.get(i))){
                tempArrayList.add(input.get(i));
            }
        }
        return tempArrayList;
    }

    private char change(double input){
        char _return = 'x';
        if(input >= 25 && input < 30){
            _return = 'm';
        }
        if(input >= 30 && input < 35){
            _return = 'l';
        }
        if(input >= 35 && input < 40){
            _return = 'k';
        }
        if(input >= 40 && input < 45){
            _return = 'j';
        }
        if(input >= 45 && input < 50){
            _return = 'i';
        }
        if(input >= 50 && input < 55){
            _return = 'a';
        }
        else if (input >= 55 && input < 60){
            _return = 'b';
        }
        else if (input >= 60 && input < 65){
            _return = 'c';
        }
        else if (input >= 65 && input < 70){
            _return = 'd';
        }
        else if (input >= 70 && input < 75){
            _return = 'e';
        }
        else if (input >= 75 && input < 80){
            _return = 'f';
        }
        else if (input >= 80 && input < 85){
            _return = 'g';
        }
        else if (input >= 85 && input < 90){
            _return = 'h';
        }
        return _return;
    }
}
