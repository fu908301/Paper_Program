import java.util.ArrayList;

public class Pattern_Occ {
    private ArrayList <Character> pattern;
    private ArrayList <Integer> Occ;
    private Other_Cal OC;

    public Pattern_Occ(){
        pattern = new ArrayList<>();
        Occ = new ArrayList<>();
        OC = new Other_Cal();
    }

    public Pattern_Occ(ArrayList<Character> pattern, ArrayList<Integer> Occ){
        this.pattern = pattern;
        this.Occ = Occ;
        OC = new Other_Cal();
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

    public double average_weight(){
        double _return = 0;
        for(int i = 0; i < pattern.size(); i++){
            _return += OC.getWeight(pattern.get(i));
        }
        return _return;
    }

    public ArrayList<Character> getPattern() {
        return pattern;
    }

    public ArrayList<Integer> getOcc() {
        return Occ;
    }
}
