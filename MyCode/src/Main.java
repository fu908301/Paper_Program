import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //System.out.println("輸入");
        //Scanner input = new Scanner(System.in);
        //String str = input.next();
        String str = input();
        System.out.println(str);
        WPM testWPM = new WPM(str);
        ArrayList<ArrayList<Occ_Weight>> WPM = testWPM.getWPM();
        WDG testWDG = new WDG(testWPM.getNodeMap());
        //testWDG.print(WPM);
        //testWPM.print();
        Map<Character, Node> nodes = testWDG.getNode(WPM);
        for(Object key : nodes.keySet()){
            System.out.println(key);
        }
        Mining_Process MP = new Mining_Process(nodes);

        MP.print();
    }
    private static String input(){
        String input_S = new String();
        try{
            char c;
            InputStreamReader isr = new InputStreamReader(new FileInputStream("C://Users//AndyFu//Desktop//Paper_code//compare//Data.csv"));
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

    private static char change(double input){
        char _return = 'x';
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

