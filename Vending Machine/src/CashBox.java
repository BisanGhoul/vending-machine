import java.util.HashMap;
import java.util.Map;

public class CashBox {

	private Map <Cash,Integer> VMCash;

    private static CashBox cashBox;
    
    // private constructor to force use of
    // getInstance() to create Singleton object
    private CashBox() {
		this.VMCash = new HashMap<>();

    }
 
    public static CashBox getCashBox()
    {
        if (cashBox==null)
        	cashBox = new CashBox();
        return cashBox;
    }
	


	public void addCash(Cash cashType, int count) {
		if(VMCash.containsKey(cashType)) {
			VMCash.put(cashType, (VMCash.get(cashType)+count) );
		}else {
			VMCash.put(cashType, count);

		}
	}

	public int withdrawCash(Cash cashType, int count) {

		int numOfBillsLeft = 0;
		
		if (VMCash.containsKey(cashType)) {
			int billTypeCount = VMCash.get(cashType);

			if (count <= billTypeCount) {
				VMCash.put(cashType, billTypeCount - count);
				 numOfBillsLeft = billTypeCount - count;
				return numOfBillsLeft;
			}
		}
		return numOfBillsLeft;
	}
	
	public boolean hasBill(Cash cashType) {
		if(VMCash.get(cashType)>=1 && VMCash.containsKey(cashType)) {
			return true;
		}
		return false;
	}
	
	public int getBillCount(Cash cash) {
		return VMCash.get(cash);
	}
	
	public boolean isEmpty() {
		return VMCash.isEmpty();
	}
	
	public void clear() {
		VMCash.clear();
	}
}
