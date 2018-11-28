import java.util.*;
public class Periodicity_detection {
    private ArrayList<Integer> occ;
    private int size;
    private int threshold;
    private int length;
    private ArrayList<Integer> V;
    private ArrayList<occ_period> OP;

    Periodicity_detection(){
    }
    Periodicity_detection(ArrayList<Integer> occ,int size, int threshold, int length){
        this.size = size;
        this.occ = occ;
        this.threshold = threshold;
        this.length = length;
        V = new ArrayList<>();
        OP = new ArrayList<>();
    }

    public void algorithm(){
        int st, sigma, period, PP, support;
        occ_period tempOP;
        for (int i = 1; i <= size - 1; i++){
            st = occ.get(i - 1);
            for(int j = i; j <= size - 1; j++){
                sigma = occ.get(j);
                period = sigma - st;
                V.clear();
                V.add(st);
                V.add(sigma);
                for(int m = j + 1; m <= size - 1; m++){
                    if((occ.get(m) - st) % period == 0){
                        V = union(V, occ.get(m));
                    }
                }
                PP = (length - st + 1) / period;
                support = V.size() / PP;
                if(support >= threshold){
                    tempOP = new occ_period();
                    tempOP.setPeriod(period);
                    tempOP.setOcc(V);
                    check_add(period,tempOP);
                }
            }
        }
    }
    public void print(){
       for(int i = 0; i < OP.size(); i++){
           OP.get(i).print();
       }
    }
    public ArrayList <occ_period> getOP(){
        algorithm();
        return OP;
    }
    private void check_add(int period, occ_period tempOP){
        boolean check = true;
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 0; i < OP.size(); i++){
            if(OP.get(i).containPeriod(period)){
                check = false;
                temp = union(OP.get(i).getOcc(), tempOP.getOcc());
                OP.get(i).setOcc(temp);
            }
        }
        if(check){
            OP.add(tempOP);
        }
    }

    private ArrayList<Integer> union(ArrayList<Integer> input1, int input2){
        ArrayList<Integer> result = new ArrayList<>();
        result.addAll(input1);
        if(!result.contains(input2)){
            result.add(input2);
        }
        return result;
    }
    private ArrayList<Integer> union(ArrayList<Integer> input1, ArrayList<Integer> input2){
        ArrayList<Integer> result = new ArrayList<>();
        result.addAll(input1);

        for(int i = 0; i < input2.size(); i++){
            if(!result.contains(input2.get(i))){
                result.add(input2.get(i));
            }
        }
        return result;
    }

}
