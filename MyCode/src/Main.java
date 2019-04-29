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
        WPM testWPM = new WPM(str);
        ArrayList<ArrayList<Occ_Weight>> WPM = testWPM.getWPM();
        WDG testWDG = new WDG(testWPM.getNodeMap());
        Map<Character, Node> nodes = testWDG.getNode(WPM);
        Mining_Process MP = new Mining_Process(nodes);
        MP.print();
        long time2 = System.currentTimeMillis();
        System.out.println("Time : " + (time2 - time1));
    }
    private static String input(){
        String input_S = "";
        try{
            FileReader FR = new FileReader("C://Users//AndyFu//Desktop//Paper_code//compare//test.txt");
            BufferedReader BR = new BufferedReader(FR);
            while (BR.ready()){
                input_S = input_S + BR.readLine();
            }
            FR.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        /*try{
            char c;
            InputStreamReader isr = new InputStreamReader(new FileInputStream("C://Users//AndyFu//Desktop//Paper_code//compare//Data3.csv"));
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
        }*/
        return input_S;
    }

    private static char change(double input){
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
    public static void output(){
        try {
            FileWriter FW = new FileWriter("C://Users//AndyFu//Desktop//Paper_code//compare//test.txt");
            String S = "";
            Random ran = new Random();
            Other_Cal OC = new Other_Cal();
            for(int x = 0; x < 3000; x++) {
                int i = ran.nextInt(5);
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

