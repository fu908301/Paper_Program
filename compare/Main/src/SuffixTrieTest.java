import java.util.Scanner;

public class SuffixTrieTest {
    SuffixTrie trie = null;
    public void test(){
        this.trie = new SuffixTrie();

        System.out.println("輸入");
        Scanner input = new Scanner(System.in);
        String str = input.next();
        trie.build(str);
        System.out.println("a 出現的次數 " + trie.getOccurences("a"));
        System.out.println("Root is " );
        trie.getRoot().getValue();
        trie.run_all_tree(trie.getRoot());
        input.close();
    }
}
