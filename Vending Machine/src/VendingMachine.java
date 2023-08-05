import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class VendingMachine implements VendingMachineAPI{

	private static final Random rand = new Random();

	ProductStock stock = new ProductStock();
	CashBox cashBox = CashBox.getCashBox();
	Keypad keypad = new Keypad();
	private int row=5;
	private int col=5;


	State currState, idleState, productSelectedState, soldOutState, soldState;
	Product currProduct;
	double currBalance;



	public VendingMachine() {
		currBalance = 0;
		currProduct = null;

		idleState = new IdleState(this);
		productSelectedState = new ProductSelectedState(this);
		soldOutState = new SoldOutState(this);
		soldState = new SoldState(this);

		currState = idleState;

		initializeValues();

	}

	public void reset(){
		this.currBalance = 0;
		this.currProduct = null;
		currState = idleState;
		cashBox.clear();
		initializeValues();


	}
	
	public void insertCash(Cash cash) {
		currState.insertCash(cash);

	}

	//Done
	public double showPriceForSelectedProduct() {
		String num = keypad.getInput();
		return currState.getPriceForSelectedProduct(num);
	}

	public double showPriceForSelectedProduct(String num) {
		return currState.getPriceForSelectedProduct(num);
	}
	
	public void cancel() {

	}

	public Map<Cash, Integer> cancelAndRefund() {
		return currState.refund();
	}

	public Map<Cash, Integer> getChange() {
		Map<Cash, Integer> map = null;
		
		try {
			map = currState.getChange();
		}catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
		return map;
	}

	public Product dispense() {
		return currState.dispense();
	}	

	public Map<Cash, Integer> getChange(double amount) {
		
		Map <Cash,Integer> change = new HashMap<>();
		
		if(amount>=Cash.FIFTY_DOLLARS.value() && cashBox.hasBill(Cash.FIFTY_DOLLARS)) {
			int count = (int) (amount/Cash.FIFTY_DOLLARS.value());
			change.put(Cash.FIFTY_DOLLARS, count);
			amount = amount % Cash.FIFTY_DOLLARS.value();
			cashBox.withdrawCash(Cash.FIFTY_DOLLARS, count);
		}
		if(amount>=Cash.TWENTY_DOLLARS.value() && cashBox.hasBill(Cash.TWENTY_DOLLARS)) {
			int count = (int) (amount/Cash.TWENTY_DOLLARS.value());
			change.put(Cash.TWENTY_DOLLARS, count);
			amount = amount % Cash.TWENTY_DOLLARS.value();
			cashBox.withdrawCash(Cash.TWENTY_DOLLARS, count);
		}

		if(amount>=Cash.ONE_DOLLAR.value() && cashBox.hasBill(Cash.ONE_DOLLAR)) {
			int count = (int) (amount/Cash.ONE_DOLLAR.value());
			change.put(Cash.ONE_DOLLAR, count);
			amount = amount % Cash.ONE_DOLLAR.value();
			cashBox.withdrawCash(Cash.ONE_DOLLAR, count);
		}
		if(amount>=Cash.FIFTY_CENTS.value() && cashBox.hasBill(Cash.FIFTY_CENTS)) {
			int count = (int) (amount/Cash.FIFTY_CENTS.value());
			change.put(Cash.FIFTY_CENTS, count);
			amount = amount % Cash.FIFTY_CENTS.value();
			cashBox.withdrawCash(Cash.FIFTY_CENTS, count);
		}

		if(amount>=Cash.TWENTY_CENTS.value() && cashBox.hasBill(Cash.TWENTY_CENTS)) {
			int count = (int) (amount/Cash.TWENTY_CENTS.value());
			change.put(Cash.TWENTY_CENTS, count);
			amount = amount % Cash.TWENTY_CENTS.value();
			cashBox.withdrawCash(Cash.TWENTY_CENTS, count);
		}

		if(amount>=Cash.TEN_CENTS.value() && cashBox.hasBill(Cash.TEN_CENTS)) {
			int count = (int) (amount/Cash.TEN_CENTS.value());
			change.put(Cash.TEN_CENTS, count);
			amount = amount % Cash.TEN_CENTS.value();
			cashBox.withdrawCash(Cash.TEN_CENTS, count);
		}


		if(amount > 0 && cashBox.isEmpty()) {
			throw new CashBoxException("There is not enough change to return!");
		}

		return change;

	}

	public boolean hasEnoughChange(double amount) {

		try {

			Map  <Cash,Integer> map = getChange(amount);

			for(Cash c: map.keySet()) {
				cashBox.addCash(c, map.get(c));
			}
		}catch(CashBoxException e) {
			return false;
		}
		return true;
	}


	public void displayProducts() {
		for (Slot name : stock.slotsSet) {
			System.out.println(name);
		}
	}
	
	private void initializeValues() {
		stock.fillStock(fillSlotsWithRandomValues());
		fillCashBox();
	}

	private  Set<Slot> fillSlotsWithRandomValues() {
		Set<Slot> slots = new TreeSet<>();
		int count = 0 ;
		for (int i = 0; i < row; i++) {
			char colChar = 'A';

			for(int j=0;j<col;j++) {

				String slotNumber = i+""+j;
				String productName = "Snack " + (count++);
				double priceInCents = (rand.nextDouble() * 1000)+10; // Random price between 0 and 1000
				int quantity = (int)(rand.nextDouble() * 5)+1; // Random quantity between 0 and 100


				if(i==0&j==0) {
					Product product = new Product("special product", 2, 1000);
					Slot slot = new Slot("00", product);
					slots.add(slot);
				}else {
					Product product = new Product(productName, quantity, priceInCents);
					Slot slot = new Slot(slotNumber, product);
					slots.add(slot);
				}
				colChar++;
			}}
		return slots;
	}

	private void fillCashBox(){
		for(Cash c: Cash.values()) {
			cashBox.addCash(c, 30);
		}
	}

	public void changeState(State newState) {
		currState = newState;
	}

	public State getIdleState() {
		return idleState;
	}

	public void setIdleState(IdleState idleState) {
		this.idleState = idleState;
	}

	public State getProductSelectedState() {
		return productSelectedState;
	}

	public void setProductSelectedState(ProductSelectedState productSelectedState) {
		this.productSelectedState = productSelectedState;
	}

	public ProductStock getStock() {
		return stock;
	}

	public void setStock(ProductStock stock) {
		this.stock = stock;
	}

	public CashBox getCashBox() {
		return cashBox;
	}

	public void setCashBox(CashBox cashBox) {
		this.cashBox = cashBox;
	}

	public Keypad getKeypad() {
		return keypad;
	}

	public void setKeypad(Keypad keypad) {
		this.keypad = keypad;
	}

	public State getSoldOutState() {
		return soldOutState;
	}

	public void setSoldOutState(SoldOutState soldOutState) {
		this.soldOutState = soldOutState;
	}

	public State getSoldState() {
		return soldState;
	}

	public void setSoldState(SoldState soldState) {
		this.soldState = soldState;
	}

	public State getCurrState() {
		return currState;
	}

	public void setCurrState(State currState) {
		this.currState = currState;
	}

	public Product getCurrProduct() {
		return currProduct;
	}

	public void setCurrProduct(Product currProduct) {
		this.currProduct = currProduct;
	}

	public double getCurrBalance() {
		return currBalance;
	}

	public void setCurrBalance(double currBalance) {
		this.currBalance = currBalance;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}


}
