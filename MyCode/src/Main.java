import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //System.out.println("輸入");
        //Scanner input = new Scanner(System.in);
        //String str = input.next();
        String str = "abccabdcacdcabdc";
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
}
