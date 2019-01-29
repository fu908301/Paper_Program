import java.util.ArrayList;

public class WPM {
    private  String inputS; //輸入的字串
    private ArrayList<ArrayList<Occ_Weight>> outWPM; //外層的ArrayList,這邊用ArrayList去存ArrayList達到二維陣列的效果
    private ArrayList<Occ_Weight> inWPM; //內層的ArrayList
    private int unique; //看字串中有多少不同的字元
    private Other_Cal OC;
    public WPM(){
        outWPM = new ArrayList<ArrayList<Occ_Weight>>();
        OC = new Other_Cal();
    }

    public WPM(String inputS){
        outWPM = new ArrayList<ArrayList<Occ_Weight>>();
        this.inputS = inputS;
        OC = new Other_Cal();
    }

    public void setInput(String inputS){
        this.inputS = inputS;
    }

    private void setUnique(){ //設定unique的function
        ArrayList<Character> tempL = new ArrayList<>();
        for(int i = 0; i < inputS.length(); i++){
            if(!tempL.contains(inputS.charAt(i))){
                tempL.add(inputS.charAt(i));
            }
        }
        unique = tempL.size();
    }

    private void setWPM(){
        setUnique();
        int x,y; //只是為了記錄位置
        double weight;
        Occ_Weight tempOW; //每個都要新的存進去
        for(int i = 0; i < unique; i++){
            inWPM = new ArrayList<>();
            for(int j = 0; j < unique; j++){
                tempOW = new Occ_Weight();
                inWPM.add(tempOW);
            }
            outWPM.add(inWPM);
        }
        for(int i = 0; i < inputS.length() - 1; i++){
            x = OC.CharToInt(inputS.charAt(i));
            y = OC.CharToInt(inputS.charAt(i+1));
            outWPM.get(x).get(y).print();
            System.out.println("X Y = " + x + " " + y + " = " + inputS.charAt(i) + " " + inputS.charAt(i+1));
            weight = (OC.getWeight(inputS.charAt(i)) + OC.getWeight(inputS.charAt(i+1))) / 2;
            if(outWPM.get(x).get(y).getWeight() == 0) {
                outWPM.get(x).get(y).set_All(weight, i);
            }
            else {
                outWPM.get(x).get(y).setOcc(i);
            }
            outWPM.get(x).get(y).print();
            System.out.println();
        }
    }

    public ArrayList<ArrayList<Occ_Weight>> getWPM(){
        setWPM();
        return outWPM;
    }

    public void print(){
        setWPM();
        for(int i = 0; i < unique; i++){
            for(int j = 0; j < unique; j++){
                if(outWPM.get(i).get(j).getWeight() != 0){
                    System.out.println(OC.IntToChar(i) + " " + OC.IntToChar(j));
                    outWPM.get(i).get(j).print();
                }
            }
        }
    }
}
