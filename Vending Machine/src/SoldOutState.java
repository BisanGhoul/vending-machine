import java.util.Map;

public class SoldOutState extends State{



	private final static String NO_CHANGE_MSG = "There is not enough change in the machine, please click on the refund button.";
	private final static String NOT_ENOUGH_MSG = "You need to insert more money!";
	final static String IDLE_INSERT_COIN = "you havent selected anything yet, insert coin after selecting a snack";
	final static String IDLE_CANCEL = "You havent entered anything to get a refund!";
	final static String IDLE_DISPENSE = "you need topay first.";
	final static String IDLE_GET_CHANGE = "No money was entered. There is no change.";


	private VendingMachine machine;



	public SoldOutState(VendingMachine machine) {
		this.machine = machine;
	}


	@Override
	public Product dispense() {
		System.out.println("VM is emoty, sorry.");
		return null;
	}

	@Override
	public Map<Cash,Integer> getChange(){
		System.out.println("VM is emoty, sorry.");
		return null;
	}

	@Override
	public void insertCash(Cash cash) {
		System.out.println("VM is emoty, sorry.");

	}




	@Override
	public double getPriceForSelectedProduct(String num) {
		System.out.println("VM is emoty, sorry.");
		return 0;
	}

	public void cancel() {
		System.out.println(IDLE_CANCEL);
		//		throw new IllegalStateException();

	}






}

