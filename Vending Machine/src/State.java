import java.util.List;
import java.util.Map;

public abstract class State {

	
	public void insertCash(Cash cash) {
		throw new IllegalStateException();

	}
	
	public double getPriceForSelectedProduct(String Number) {
		throw new IllegalStateException();
		
		
	}
	
	public void cancel() {
		throw new IllegalStateException();

	}
	
	public Map<Cash,Integer> refund() {
		throw new IllegalStateException();
		
	}
	
	public Product dispense() {
		throw new IllegalStateException();
		
	}	
	
	public Map<Cash,Integer> getChange() {
		throw new IllegalStateException();
		
	}
}
