import java.util.ArrayList;
//這是為了儲存Weight and occ_vec
public class Occ_Weight {
    private double weight;
    private ArrayList<Integer> occ;

    public Occ_Weight(){
        this.weight = 0;
        this.occ = new ArrayList<>();
    }

    public Occ_Weight(double weight, ArrayList<Integer> occ){
        this.weight = weight;
        this.occ = occ;
    }

    public void set_All(double weight, int occ){   //兩個一起設定
        this.weight = weight;
        this.occ.add(occ);
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public void setOcc(int occ){
        this.occ.add(occ);
    }

    public ArrayList<Integer> getOcc(){
        return occ;
    }

    public double getWeight(){
        return weight;
    }

    public void print(){
        System.out.println("Weight : " + weight);
        System.out.print("Occ : ");
        for(int i = 0; i < occ.size(); i++){
            System.out.print(occ.get(i) + " ");
        }
        System.out.println();

    }
}
