import java.util.ArrayList;

public class occ_period {
    private int period;
    private ArrayList<Integer> occ;
    occ_period(){
        this.period = 0;
        this.occ = new ArrayList<>();
    }
    occ_period(int period, ArrayList<Integer> occ){
        this.period = period;
        this.occ = new ArrayList<>(occ);
    }
    public void setPeriod(int input){
        this.period = input;
    }
    public void setOcc(ArrayList<Integer> occ){
        this.occ.clear();
        this.occ.addAll(occ);
    }
    public int getPeriod(){
        return period;
    }
    public ArrayList<Integer> getOcc(){
        return occ;
    }
    public boolean containPeriod(int input){
        if(input != period){
            return false;
        }
        else {
            return true;
        }
    }
    public void print(){
        System.out.print("Period : " + this.period + " Occ : ");
        for(int i = 0; i < occ.size(); i++){
            System.out.print(this.occ.get(i) + " ");
        }
        System.out.println();
    }
}
