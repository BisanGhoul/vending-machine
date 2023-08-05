import java.util.List;
import java.util.Map;

//import static Messages.IDLE_INSERT_COIN;

public class IdleState extends State{


	final static String IDLE_INSERT_COIN = "you havent selected anything yet, insert coin after selecting a snack";
	final static String IDLE_CANCEL = "You havent entered anything to get a refund!";
	final static String IDLE_DISPENSE = "you need topay first.";
	final static String IDLE_GET_CHANGE = "No money was entered. There is no change.";

	private VendingMachine machine;


	public IdleState(VendingMachine machine) {
		super();
		this.machine = machine;
	}


	@Override
	public double getPriceForSelectedProduct(String num) {

		Product product = machine.stock.getProduct(num);
		if(product!=null) {
			//TODO edge cases
			if(machine.stock.getProductCount(product) >= 1) {
				machine.currProduct = product;
				machine.changeState(machine.getProductSelectedState());
				return machine.stock.getProductPrice(num);

			}else {
				System.out.println("product is out of stock.");
			}}
		else {
			System.out.println("prouct not found!");
		}
		return 0;
	}



	public void insertCash(Cash cash) {
		System.out.println(IDLE_INSERT_COIN);
	}

	public void cancel() {
		System.out.println(IDLE_CANCEL);

	}

	public Map<Cash,Integer> refund() {
		System.out.println(IDLE_CANCEL);

		return null;

	}

	public Product dispense() {
		System.out.println(IDLE_DISPENSE);

		return null;

	}	

	public Map<Cash,Integer> getChange() {
		System.out.println(IDLE_CANCEL);

		return null;

	}

}
