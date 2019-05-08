import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        //System.out.println("輸入");
        //Scanner input = new Scanner(System.in);
        //String str = input.next();
        long time1 = System.currentTimeMillis();
        //output();
        String str = input();
        String str1 = "";
        for(int i = 0; i < str.length(); i++){   //i = 0 to n
            str1 = str1 + Character.toString(str.charAt(i));
        }

        WPM testWPM = new WPM(str1, 0);
        ArrayList<ArrayList<Occ_Weight>> WPM = testWPM.getWPM();
        //testWPM.print();
        //testWPM.printnodeMap();
        WDG testWDG = new WDG(testWPM.getNodeMap());
        Map<Character, Node> nodes = testWDG.getincremental(WPM);
        Mining_Process MP = new Mining_Process(nodes, str1.length());
        MP.print();

        /*String str2 = "";
        int count = 999;
        for (int i = count; i < 1300 ; i++) {   //i = n-1 to m
            str2 = str2 + Character.toString(str.charAt(i));
        }
        WPM testWPM2 = new WPM(str2, count, testWPM.getNodeMap()); // count = n-1
        ArrayList<ArrayList<Occ_Weight>> WPM2 = testWPM2.getWPM();
        testWDG.setNodeMap(testWPM2.getNodeMap());
        nodes = testWDG.getincremental(WPM2);
        int length = str1.length() + str2.length() - 1;
        Mining_Process MP2 = new Mining_Process(nodes, length);
        MP2.print();

        String str3 = "";
        int count2 = 1299;
        for (int i = count2; i < 1600 ; i++) {   //i = n-1 to m
            str3 = str3 + Character.toString(str.charAt(i));
        }
        WPM testWPM3 = new WPM(str3, count2, testWPM2.getNodeMap()); // count = n-1
        ArrayList<ArrayList<Occ_Weight>> WPM3 = testWPM3.getWPM();
        testWDG.setNodeMap(testWPM3.getNodeMap());
        nodes = testWDG.getincremental(WPM3);
        int length2 = str1.length() + str2.length()  + str3.length() - 2;
        Mining_Process MP3 = new Mining_Process(nodes, length2);
        MP3.print();

        String str4 = "";
        int count3 = 1599;
        for (int i = count3; i < 1900 ; i++) {   //i = n-1 to m
            str4 = str4 + Character.toString(str.charAt(i));
        }
        WPM testWPM4 = new WPM(str4, count3, testWPM3.getNodeMap()); // count = n-1
        ArrayList<ArrayList<Occ_Weight>> WPM4 = testWPM4.getWPM();
        testWDG.setNodeMap(testWPM4.getNodeMap());
        nodes = testWDG.getincremental(WPM4);
        int length3 = str1.length() + str2.length()  + str3.length() + str4.length() - 3;
        Mining_Process MP4 = new Mining_Process(nodes, length3);
        MP4.print();

        String str5 = "";
        int count4 = 1899;
        for (int i = count4; i < 2200 ; i++) {   //i = n-1 to m
            str5 = str5 + Character.toString(str.charAt(i));
        }
        WPM testWPM5 = new WPM(str5, count4, testWPM4.getNodeMap()); // count = n-1
        ArrayList<ArrayList<Occ_Weight>> WPM5 = testWPM5.getWPM();
        testWDG.setNodeMap(testWPM5.getNodeMap());
        nodes = testWDG.getincremental(WPM5);
        int length4 = str1.length() + str2.length()  + str3.length() + str4.length() + str5.length() - 4;
        Mining_Process MP5 = new Mining_Process(nodes, length4);
        MP5.print();*/

        long time2 = System.currentTimeMillis();
        System.out.println("Time : " + (time2 - time1));
    }
    private static String input(){
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
    private static String oil(){
        String input_S = "";
        try{
            char c;
            InputStreamReader isr = new InputStreamReader(new FileInputStream("C://Users//AndyFu//Desktop//Paper_code//Paper_Program//Data11Y.csv"));
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
    private static String bike(){
        String input_S = "";
        try{
            char c;
            InputStreamReader isr = new InputStreamReader(new FileInputStream("C://Users//AndyFu//Desktop//Paper_code//Paper_Program/bikedata//bike_sharing_hourly.csv"));
            BufferedReader reader = new BufferedReader(isr);
            String line = null;
            reader.readLine();
            int x = 0;
            while(x < 2000){
                line = reader.readLine();
                String item[] = line.split(",");
                String convert = item[16].replace("\"", "");
                double num = Double.parseDouble(convert);
                c = change2(num);
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
    private static char change(double input){
        char _return = 'x';
        if(input < 20){
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
        else if(input >= 130){
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
    public static void output(){
        try {
            FileWriter FW = new FileWriter("C://Users//AndyFu//Desktop//Paper_code//Paper_Program//test.txt");
            String S = "";
            Random ran = new Random();
            Other_Cal OC = new Other_Cal();
            for(int x = 0; x < 2000; x++) {
                int i = ran.nextInt(4);
                char c = OC.IntToChar(i);
                S = S + Character.toString(c);
            }
            FW.write(S);
            FW.flush();
            FW.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

