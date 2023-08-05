
public enum Cash {

	FIFTY_CENTS(50), TEN_CENTS(10), TWENTY_CENTS(20), ONE_DOLLAR(100), TWENTY_DOLLARS(2000), FIFTY_DOLLARS(5000);
	
    private int value;
    private Cash(int value){
        this.value = value;
    }

    public int value(){
        return value;
    }



}
