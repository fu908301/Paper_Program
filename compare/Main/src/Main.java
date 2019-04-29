import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        /*SuffixTrieTest suffixTrieTest = new SuffixTrieTest();
        suffixTrieTest.test();*/
        /*int input[] = {1,5,13};
        int size = 3;
        int threshold = 1;
        int length = 16;
        ArrayList<Integer> test = new ArrayList<>();
        for(int i = 0; i < size; i++){
            test.add(input[i]);
        }
        Periodicity_detection p = new Periodicity_detection(test, threshold, length);
        p.algorithm();
        p.print();*/
        long time1 = System.currentTimeMillis();
        WPPM testWPPM = new WPPM();
        testWPPM.WPPM_run();
        long time2 = System.currentTimeMillis();
        System.out.println("Time : " + (time2 - time1));
    }
}
