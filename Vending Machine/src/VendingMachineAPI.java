import java.util.Map;

public interface VendingMachineAPI {

	public void insertCash(Cash cash);
	
	public double showPriceForSelectedProduct();
	
	public Map<Cash, Integer> cancelAndRefund();

	public Map<Cash, Integer> getChange();

	public Product dispense();
	
}
