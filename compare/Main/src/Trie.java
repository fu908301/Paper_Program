public interface Trie {
    //Construct the tree
    public void build(String dictName);

    //Search if the pattern exists
    public boolean search(String word);

    //Insert a pattern into the tree
    public int insert(String word, int input, String real_word);

    //Delete a pattern from the tree
    public boolean delete(String word);

    //Save the tree to a document
    public  void save(String path);

    //Read the tree from a document
    public void read(String path);

}
