import java.util.Map;

public class SoldState extends State {
	private final static String NO_CHANGE_MSG = "There is not enough change in the machine, please click on the refund button.";
	private final static String NOT_ENOUGH_MSG = "You need to insert more money!";
	final static String IDLE_INSERT_COIN = "you havent selected anything yet, insert coin after selecting a snack";
	 final static String IDLE_CANCEL = "You havent entered anything to get a refund!";
	 final static String IDLE_DISPENSE = "you need topay first.";
	 final static String IDLE_GET_CHANGE = "No money was entered. There is no change.";

		private VendingMachine machine;
		
		

		public SoldState(VendingMachine machine) {
			this.machine = machine;
		}
			
	 
		@Override
		public Product dispense() {
			Product productToReturn = null;
			if(!machine.stock.isEmpty()) {
				machine.currBalance = machine.currBalance - machine.currProduct.getPriceInCents();
				machine.stock.dispenseProduct(machine.currProduct);
				 productToReturn = machine.currProduct;
				machine.currProduct = null;
				
			}else {
				machine.changeState(new SoldOutState(machine));//TODO soldout
			}
			
			return productToReturn;
		}
		
	    @Override
	    public Map<Cash,Integer> getChange(){
	    	Map<Cash,Integer> change = machine.getChange(machine.currBalance);
	        //TODO change state to idle again and balance = 0
	    	machine.currBalance = 0;
	    	machine.changeState(machine.getIdleState());
	        return change;
	    }

	@Override
	public void insertCash(Cash cash) {
		System.out.println("You have inserted enough money, please wait your snack is dispensing.");
		
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
	
	
	@Override
	public Map<Cash,Integer> refund() {
		Map<Cash,Integer> change = machine.getChange(machine.currBalance);
		machine.currBalance = 0;
		machine.currProduct = null;
		machine.changeState(new IdleState(machine));//TODO change this maybe
		return change;
		
	}





}