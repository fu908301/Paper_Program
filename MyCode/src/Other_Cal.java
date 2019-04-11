public class Other_Cal {

    public Other_Cal(){}
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
        }else if (c == 'e'){
            weight = 0.9;
        } else if (c == 'f'){
            weight = 0.55;
        }else if (c == 'g'){
            weight = 0.75;
        }else if (c == 'h'){
            weight = 0.85;
        }
        else if (c == 'i'){
            weight = 0.65;
        }
        else if (c == 'j'){
            weight = 0.75;
        }
        else if (c == 'k'){
            weight = 0.45;
        }else if (c == 'l'){
            weight = 0.85;
        }
        else if (c == 'm'){
            weight = 0.35;
        }
        else if (c == '*'){
            weight = 0.9;
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
        else if (c == 'e'){
            _return = 4;
        }
        else if (c == 'f'){
            _return = 5;
        }
        else if (c == 'g'){
            _return = 6;
        }
        else if (c == 'h'){
            _return = 7;
        }
        else if (c == 'i'){
            _return = 8;
        }
        else if (c == 'j'){
            _return = 9;
        }
        else if (c == 'k'){
            _return = 10;
        }
        else if (c == 'l'){
            _return = 11;
        }
        else if (c == 'm'){
            _return = 12;
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
        else if (i == 4){
            _return = 'e';
        }
        else if (i == 5){
            _return = 'f';
        }
        else if (i == 6){
            _return = 'g';
        }
        else if (i == 7){
            _return = 'h';
        }
        else if (i == 8){
            _return = 'i';
        }
        else if (i == 9){
            _return = 'j';
        }
        else if (i == 10){
            _return = 'k';
        }
        else if (i == 11){
            _return = 'l';
        }
        else if (i == 12){
            _return = 'm';
        }
        return _return;
    }
}
