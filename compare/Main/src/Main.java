public class Main {
    public static void main(String[] args){
        //SuffixTrieTest suffixTrieTest = new SuffixTrieTest();
        //suffixTrieTest.test();
        int input[] = {2, 3, 7, 9, 11, 15};
        int size = 6;
        int threshold = 2;
        int length = 16;
        Periodicity_detection p = new Periodicity_detection(input, size, threshold, length);
        p.algorithm();
        p.print();
    }
}
