import java.util.ArrayList;

public class WPM {
    private  String inputS; //輸入的字串
    private ArrayList<ArrayList<Occ_Weight>> WPM; //外層的ArrayList,這邊用ArrayList去存ArrayList達到二維陣列的效果
    private ArrayList<Occ_Weight> inWPM; //內層的ArrayList
    private int unique; //看字串中有多少不同的字元
    public WPM(){
        WPM = new ArrayList<ArrayList<Occ_Weight>>();
    }

    public WPM(String inputS){
        WPM = new ArrayList<ArrayList<Occ_Weight>>();
        this.inputS = inputS;
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
            WPM.add(inWPM);
        }
        for(int i = 0; i < inputS.length() - 1; i++){
            x = CharToInt(inputS.charAt(i));
            y = CharToInt(inputS.charAt(i+1));
            WPM.get(x).get(y).print();
            System.out.println("X Y = " + x + " " + y + " = " + inputS.charAt(i) + " " + inputS.charAt(i+1));
            weight = (getWeight(inputS.charAt(i)) + getWeight(inputS.charAt(i+1))) / 2;
            if(WPM.get(x).get(y).getWeight() == 0) {
                WPM.get(x).get(y).set_All(weight, i);
            }
            else {
                WPM.get(x).get(y).setOcc(i);
            }
            WPM.get(x).get(y).print();
            System.out.println();
        }
    }

    public ArrayList<ArrayList<Occ_Weight>> getWPM(){
        setWPM();
        return WPM;
    }

    public void print(){
        setWPM();
        for(int i = 0; i < unique; i++){
            for(int j = 0; j < unique; j++){
                if(WPM.get(i).get(j).getWeight() != 0){
                    System.out.println(IntToChar(i) + " " + IntToChar(j));
                    WPM.get(i).get(j).print();
                }
            }
        }
    }

    public double getWeight(char c){
        double weight = 0;
        if(c == 'a'){
            weight = 0.8;
        }
        else if(c == 'b'){
            weight = 0.6;
        }
        else if (c == 'c'){
            weight = 0.7;
        }
        else if (c == 'd'){
            weight = 0.5;
        }
        return weight;
    }
    public int CharToInt(char c){
        int _return = -1;
        if(c == 'a'){
            _return = 0;
        }
        else if(c == 'b'){
            _return = 1;
        }
        else if(c == 'c'){
            _return = 2;
        }
        else if (c == 'd'){
            _return = 3;
        }
        return _return;
    }

    public char IntToChar(int i){
        char _return = 'x';
        if(i == 0){
            _return = 'a';
        }
        else if(i == 1){
            _return = 'b';
        }
        else if(i == 2){
            _return = 'c';
        }
        else if (i == 3){
            _return = 'd';
        }
        return _return;
    }
}
