
public enum Row {

    A(1), B(2), C(3), D(4), E(5);
	   
    private int num;
   
    private Row(int num){
        this.num = num;
    }
   
    public int getRowNum(char c){
        return c;
    }



}
