import java.util.ArrayList;

public class Pattern_Occ {
    private ArrayList <Character> pattern;
    private ArrayList <Integer> Occ;

    public Pattern_Occ(){
        pattern = new ArrayList<>();
        Occ = new ArrayList<>();
    }

    public Pattern_Occ(ArrayList<Character> pattern, ArrayList<Integer> Occ){
        this.pattern = pattern;
        this.Occ = Occ;
    }

    public void setPattern(ArrayList<Character> pattern){
        this.pattern = pattern;
    }

    public void add_character(char c){
        pattern.add(c);
    }

    public void setOcc(ArrayList<Integer> Occ){
        this.Occ = Occ;
    }

    public void addOcc(int i){
        Occ.add(i);
    }

    public ArrayList<Character> getPattern() {
        return pattern;
    }

    public ArrayList<Integer> getOcc() {
        return Occ;
    }
}
