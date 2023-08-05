import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		VendingMachineFactory factory = new VendingMachineFactory();
		VendingMachine mach =  factory.createMachine(VendingMachineType.SNACKS);

//		mach.stock.slotsSet.clear();
		System.out.println("products: ");
		mach.displayProducts();
		System.out.println("\n================= buy snakc with change =======================");

		//	mach.setCol(5);
		//	mach.setRow(5);
		//		System.out.println(mach);
		//				Map <Cash, Integer> map = mach.cashBox.VMCash;
		//		Set<Slot> set = mach.stock.slotsSet;
		//		for (Slot name : set) {
		//		    System.out.println(name);
		//		}
		//		System.out.println("=====================");
		//		   for (Map.Entry<Cash, Integer> entry : map.entrySet()) {
		//		        System.out.println(entry.getKey() + ":" + entry.getValue());
		//		    }

		System.out.println("enter product number (00, 01, 02, ..., 44)");

		System.out.println("<<Program Chooses slot number (00) automatically for demonstration reasons>>");
		System.out.println( "Price: "+mach.showPriceForSelectedProduct("00"));//1000
		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TEN_CENTS);
		mach.insertCash(Cash.TEN_CENTS);
		mach.insertCash(Cash.TEN_CENTS);
		mach.insertCash(Cash.FIFTY_DOLLARS);
		Product p = mach.dispense();
		System.out.println(p);
		Map <Cash,Integer> mapChange = mach.getChange();
		System.out.println("change: ");
		if(mapChange != null) {
			for (Map.Entry<Cash, Integer> entry : mapChange.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
		}

		System.out.println("count od 20 dollar bill in cash box: " + mach.cashBox.getBillCount(Cash.TWENTY_DOLLARS));

		System.out.println("====================not enough money to buy, cancel and get a refund ===================");
		mach.reset();
		System.out.println("enter product number (00, 01, 02, ..., 44)");
		System.out.println("\n <<Program Chooses slot number (00) automatically for demonstration reasons>>");

		System.out.println(  "Price: " + mach.showPriceForSelectedProduct("00"));//1000
		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TWENTY_CENTS);
		//		mach.insertCash(Cash.TWENTY_CENTS);
		//
		//		mach.insertCash(Cash.ONE_DOLLAR);
		//		mach.insertCash(Cash.ONE_DOLLAR);
		//		mach.insertCash(Cash.ONE_DOLLAR);
		//		mach.insertCash(Cash.ONE_DOLLAR);
		//		mach.insertCash(Cash.ONE_DOLLAR);
		//		mach.insertCash(Cash.ONE_DOLLAR);
		//		mach.insertCash(Cash.ONE_DOLLAR);
		//		mach.insertCash(Cash.ONE_DOLLAR);
		//		mach.insertCash(Cash.ONE_DOLLAR);

		//		System.out.println(mach.cashBox.getBillCount(Cash.TWENTY_CENTS));

		System.out.println("\n\nUser choose to get their money back becuase they are poor :(. The Refund: ");

		Map <Cash,Integer> map = mach.cancelAndRefund();
		if(map != null) {
			for (Map.Entry<Cash, Integer> entry : map.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
		}
		
		System.out.println("===================sold out case =================\n");
		mach.reset();
		System.out.println("enter product number (00, 01, 02, ..., 44)");
		System.out.println(" <<Program Chooses slot number (00) automatically for demonstration reasons>>\n");
		String productNum = "00";
		mach.stock.getProduct("00").setQuantity(0);
		System.out.println("the quantity of the product at slot (00) was set to zero.");
		int priceInCents = (int)mach.showPriceForSelectedProduct(productNum);
		System.out.println(priceInCents);
		
		


	}
}
