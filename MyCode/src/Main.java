import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //System.out.println("輸入");
        //Scanner input = new Scanner(System.in);
        //String str = input.next();
        String str = "abccabdcacdcabdc";
        WPM testWPM = new WPM(str);
        testWPM.print();
    }
}
