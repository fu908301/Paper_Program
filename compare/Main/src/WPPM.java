
import java.io.*;
import java.util.*;

public class WPPM {
    private SuffixTrie trie = null;
    public WPPM(){
    }
    public void WPPM_run(){
        int star_limit = 3;
        double threshold = 2.0;
        int periodicity = 8;
        Other_Cal OC = new Other_Cal();
        String input = input();
        String input_S = "";
        for(int i = 0; i < 1000; i++){   //i = 0 to n
            input_S = input_S + Character.toString(input.charAt(i));
        }
        ArrayList<Character> input_DB = stringToArrayList(input_S);
        ArrayList<Character> unique = getUnique(input_DB);
        Map<Character, ArrayList<Integer>> tempMap = new HashMap<>();

        trie = new SuffixTrie();
        trie.build(input_S);
        Node root = trie.getRoot();
        ArrayList<Pattern_Occ> next_answer = new ArrayList<>();
        ArrayList<Pattern_Occ> next_answer2 = new ArrayList<>();
        ArrayList<Pattern_Occ> answer = new ArrayList<>();
        ArrayList<Node> next_level = new ArrayList<>();
        ArrayList<occ_period> after_PD;
        ArrayList<Integer> PD_Occ;
        Periodicity_detection PD;
        for (String key : root.getChildren().keySet()){
            PD_Occ = new ArrayList<>();
            boolean have = false;
            next_answer.clear();
            next_answer2.clear();
            next_level.clear();
            Node nextNode = root.getChildren().get(key); //level one
            PD = new Periodicity_detection(nextNode.getOcc_vec(), 0.5, input_S.length());
            after_PD = PD.getOP();
            for(int  i = 0; i < after_PD.size(); i++){
                if(after_PD.get(i).getPeriod() == periodicity){
                    have = true;
                    PD_Occ = after_PD.get(i).getOcc();
                    break;
                }
            }
            //PD.print();
            if(have) {
                if ((double)PD_Occ.size() * OC.getWeight(key.charAt(0)) >= threshold) { //如果第一層有過門檻
                    Pattern_Occ PO = new Pattern_Occ();
                    PO.add_character(nextNode.getC());
                    PO.setOcc(PD_Occ);
                    answer.add(PO);
                }
                Pattern_Occ PO2 = new Pattern_Occ();
                PO2.add_character(nextNode.getC());
                PO2.setOcc(PD_Occ);
                next_answer.add(PO2);
                next_level.add(nextNode);
            /*for(int i = 0; i < answer.size();i++){
                answer.get(i).print();
            }
            for(int i = 0; i < next_answer.size(); i++){
                next_answer.get(i).print();
            }*/
                for (int i = 0; i < periodicity - 1; i++) { //第二層以後
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
                        if (preNode.getChildren() != null) {
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
                    next_answer2 = new ArrayList<>();
                    Pattern_Occ tempPO;
                    Pattern_Occ tempPO2;
                    for (int k = 0; k < next_answer.size(); k++) { //這邊是結合上一層與下一層的OCC
                        for (Character key3 : tempMap.keySet()) {
                            tempPO = new Pattern_Occ();
                            for (int z = 0; z < next_answer.get(k).getPattern().size(); z++) {
                                tempPO.add_character(next_answer.get(k).getPattern().get(z));
                            }
                            tempPO.add_character(key3);
                            tempPO2 = new Pattern_Occ();
                            for (int z = 0; z < next_answer.get(k).getPattern().size(); z++) {
                                tempPO2.add_character(next_answer.get(k).getPattern().get(z));
                            }
                            tempPO2.add_character(key3);
                            for (int l = 0; l < next_answer.get(k).getOcc().size(); l++) {
                                for (int m = 0; m < tempMap.get(key3).size(); m++) {
                                    int next_A = next_answer.get(k).getOcc().get(l);
                                    int temp_M = tempMap.get(key3).get(m);
                                /*System.out.println("Next answer : " + next_A);
                                System.out.println("Temp Map : " + temp_M);*/
                                    if (next_A == temp_M) {
                                        tempPO.addOcc(next_answer.get(k).getOcc().get(l));
                                        tempPO2.addOcc(next_answer.get(k).getOcc().get(l));
                                    }
                                }
                            }
                        /*System.out.println("TEMP");
                        tempPO.print();
                        System.out.println("TEMP FINISH");*/
                            if ((double)tempPO.getOcc().size() * tempPO.average_weight() >= threshold) {
                            /*System.out.println("TEMP PO");
                            tempPO.print();
                            tempPO2.print();*/
                                answer.add(tempPO);
                                next_answer2.add(tempPO2);
                            }
                        }
                    }

                    for (int x = 0; x < next_answer.size(); x++) {
                        Pattern_Occ tempPO3 = new Pattern_Occ();
                        for (int z = 0; z < next_answer.get(x).getPattern().size(); z++) {
                            tempPO3.add_character(next_answer.get(x).getPattern().get(z));
                        }
                        tempPO3.add_character('*');
                        for (int z = 0; z < next_answer.get(x).getOcc().size(); z++) {
                            tempPO3.addOcc(next_answer.get(x).getOcc().get(z));
                        }
                        if (tempPO3.star_count() <= star_limit) {
                            next_answer2.add(tempPO3);
                        }
                        //next_answer2.add(tempPO3);
                    }
                    next_answer = next_answer2;
                    ArrayList<Node> next_level2 = new ArrayList<>();
                    for (int x = 0; x < next_level.size(); x++) {
                        if (next_level.get(x).getChildren() != null) {
                            for (Object c : next_level.get(x).getChildren().keySet()) {
                                next_level2.add(next_level.get(x).getChildren().get(c));
                            }
                        }
                    }
                    next_level = next_level2;
                }
            }
        }
        /*System.out.println("Answer print.");
        for(int z = 0; z < answer.size(); z++){
            answer.get(z).print();
        }
        System.out.println("Answer Size : " + answer.size());
        System.out.println("Answer print finished.");*/
        System.out.println("Answer Size : " + answer.size());

    }
    private String input(){
        String input_S = oil();
        /*try{
            FileReader FR = new FileReader("C://Users//AndyFu//Desktop//Paper_code//Paper_Program//test.txt");
            BufferedReader BR = new BufferedReader(FR);
            while (BR.ready()){
                input_S = input_S + BR.readLine();
            }
            FR.close();
        }catch (IOException e){
            e.printStackTrace();
        }*/

        return input_S;
    }
    private String oil(){
        String input_S="";
        try{
            char c;
            InputStreamReader isr = new InputStreamReader(new FileInputStream("C://Users//AndyFu//Desktop//Paper_code//Paper_Program//Data7Y.csv"));
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

    private  String bike(){
        String input_S = "";
        try{
            char c;
            InputStreamReader isr = new InputStreamReader(new FileInputStream("C://Users//AndyFu//Desktop//Paper_code//Paper_Program/bikedata//bike_sharing_hourly.csv"));
            BufferedReader reader = new BufferedReader(isr);
            String line = null;
            reader.readLine();
            int x = 0;
            while(x < 3000){
                line = reader.readLine();
                String item[] = line.split(",");
                String convert = item[16].replace("\"", "");
                double num = Double.parseDouble(convert);
                c = change2(num);
                //System.out.println(num);
                input_S = Character.toString(c) + input_S;
                x++;
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
        if(input >= 10 && input < 20){
            _return = 'a';
        }
        else if (input >= 20 && input < 30){
            _return = 'b';
        }
        else if (input >= 30 && input < 40){
            _return = 'c';
        }
        else if (input >= 40 && input < 50){
            _return = 'd';
        }
        else if (input >= 50 && input < 60){
            _return = 'e';
        }
        else if (input >= 60 && input < 70){
            _return = 'f';
        }
        else if (input >= 70 && input < 80){
            _return = 'g';
        }
        else if (input >= 80 && input < 90){
            _return = 'h';
        }
        else if(input >= 90 && input < 100){
            _return = 'i';
        }
        else if(input >= 100 && input < 110){
            _return = 'j';
        }
        else if(input >= 110 && input < 120){
            _return = 'k';
        }
        else if(input >= 120 && input < 130){
            _return = 'l';
        }
        else if(input >= 130 && input < 140){
            _return = 'm';
        }
        return _return;
    }

    private static char change2(double input){
        char _return = 'x';
        if(input < 20){
            _return = 'a';
        }
        else if (input >= 20 && input < 40){
            _return = 'b';
        }
        else if (input >= 40 && input < 60){
            _return = 'c';
        }
        else if (input >= 60 && input < 80){
            _return = 'd';
        }
        else if (input >= 80 && input < 100){
            _return = 'e';
        }
        else if (input >= 100 && input < 120){
            _return = 'f';
        }
        else if (input >= 120 && input < 140){
            _return = 'g';
        }
        else if (input >= 140 && input < 160){
            _return = 'h';
        }
        else if(input >= 160 && input < 180){
            _return = 'i';
        }
        else if(input >= 180 && input < 200){
            _return = 'j';
        }
        else if(input >= 200 && input < 220){
            _return = 'k';
        }
        else if(input >= 220 && input < 240){
            _return = 'l';
        }
        else if(input >= 240){
            _return = 'm';
        }
        return _return;
    }
}
