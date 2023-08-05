import java.util.Map;

public class ProductSelectedState extends State {

	private final static String NO_CHANGE_MSG = "There is not enough change in the machine, please click on the refund button.";
	private final static String NOT_ENOUGH_MSG = "You need to insert more money!";
	final static String IDLE_INSERT_COIN = "you havent selected anything yet, insert coin after selecting a snack";
	 final static String IDLE_CANCEL = "You havent entered anything to get a refund!";
	 final static String IDLE_DISPENSE = "you need topay first.";
	 final static String IDLE_GET_CHANGE = "No money was entered. There is no change.";

	private VendingMachine machine;
	
	

	public ProductSelectedState(VendingMachine machine) {
		this.machine = machine;
	}


	@Override
	public void insertCash(Cash cash) {
	//item has been selected -> currProduct
		//check if currBalance >= product.price
		//if not go to 
		machine.cashBox.addCash(cash, 1);
		machine.currBalance = machine.currBalance + cash.value();
		if(machine.currBalance >= machine.currProduct.getPriceInCents()){
			double change = machine.currBalance - machine.currProduct.getPriceInCents();
			if(machine.hasEnoughChange(change)) {
				machine.changeState(machine.getSoldState());
			}else {
				System.out.println(NO_CHANGE_MSG);
			}
		}else {
			System.out.println(NOT_ENOUGH_MSG+ " Balance (in cents): " + machine.currBalance + ", Product Price: " + machine.currProduct.getPriceInCents());
		}
	}
	
	@Override
	public Map<Cash,Integer> refund() {
		Map<Cash,Integer> change = machine.getChange(machine.currBalance);
		machine.currBalance = 0;
		machine.currProduct = null;
		machine.changeState(new IdleState(machine));//TODO change this maybe
		return change;
		
	}
	

	@Override
	public double getPriceForSelectedProduct(String num) {
	System.out.println("You have already selected something.");
	return 0;
	}
	
	public void cancel() {
		System.out.println(IDLE_CANCEL);
//		throw new IllegalStateException();

	}
	
	
	public Product dispense() {
		System.out.println("Not Enough Money.");

		return null;
		
	}	
	
	public Map<Cash,Integer> getChange() {
		System.out.println("you havent bought anything yet to get the change.");

		return null;
		
	}
	
}
