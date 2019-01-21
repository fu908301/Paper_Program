
import java.util.*;

public class SuffixTrieTest {
    private SuffixTrie trie = null;
    public void test(){
        this.trie = new SuffixTrie();
        ArrayList<Integer> test = new ArrayList<>();
        System.out.println("輸入");
        Scanner input = new Scanner(System.in);
        String str = input.next();
        trie.build(str);
        System.out.println("a 出現的次數 " + trie.getOccurences("a"));
        for (Object key : trie.getRoot().getChildren().keySet()){
            Node nextNode = trie.getRoot().getChildren().get(key);
            ArrayList<Integer> t = nextNode.getOcc_vec();
            System.out.print(key + ":" );
            for(int i = 0; i < t.size(); i++){
                System.out.print(t.get(i) + ", ");
            }
            System.out.println();
        }
        /*test = trie.len_vec(trie.getRoot().getChildren().get("a"), test);
        System.out.print("Length vector : ");
        Collections.sort(test);//由小到大排
        for(int i = 0; i < test.size(); i++){
            System.out.print(test.get(i) + " ");
        }
        System.out.println();*/

        trie.run_all_tree(trie.getRoot());
        input.close();
    }
}
