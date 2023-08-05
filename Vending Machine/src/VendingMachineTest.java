import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	VendingMachineFactory factory = new VendingMachineFactory();
	VendingMachine mach =  factory.createMachine(VendingMachineType.SNACKS);

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void buySnackAndGetChange() {
		mach.reset();
		String productNum = "00";
		int priceInCents = (int)mach.showPriceForSelectedProduct(productNum);
		assertEquals(priceInCents,1000);

		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TEN_CENTS);
		mach.insertCash(Cash.TEN_CENTS);
		mach.insertCash(Cash.TEN_CENTS);
		mach.insertCash(Cash.FIFTY_DOLLARS);

		assertEquals(5050,(int)mach.currBalance);
		//There is initially 30 of each type of coin/note in the vending machine's cash box
		assertEquals(33,(int)mach.cashBox.getBillCount(Cash.TEN_CENTS));
		assertEquals(31,(int)mach.cashBox.getBillCount(Cash.TWENTY_CENTS));
		assertEquals(31,(int)mach.cashBox.getBillCount(Cash.FIFTY_DOLLARS));

		Product snack = mach.dispense();
		//		System.out.println(snack);

		//quantity decreased by one because snack has been dispensed
		assertEquals(new Product("special product", 1, 1000).equals(snack),true);

		//total balance(5050) - price(1000) = 4050 => 2 Twenty_Dollars (2000X2 cents) + 1 Fifty_cent
		Map <Cash,Integer> change = mach.getChange();
		int bill1Count = change.get(Cash.TWENTY_DOLLARS);
		int bill2Count = change.get(Cash.FIFTY_CENTS);

		assertEquals(bill1Count,2);
		assertEquals(bill2Count,1);
		//System.out.println("count" + mach.cashBox.getBillCount(Cash.TWENTY_DOLLARS));
		assertEquals(28,(int)mach.cashBox.getBillCount(Cash.TWENTY_DOLLARS));


	}

	@Test
	public void buySnackPayExactPriceNoChangeToReturn() {

		mach.reset();
		String productNum = "00";
		int priceInCents = (int)mach.showPriceForSelectedProduct(productNum);
		assertEquals(priceInCents,1000);

		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TWENTY_CENTS);

		mach.insertCash(Cash.ONE_DOLLAR);
		mach.insertCash(Cash.ONE_DOLLAR);
		mach.insertCash(Cash.ONE_DOLLAR);
		mach.insertCash(Cash.ONE_DOLLAR);
		mach.insertCash(Cash.ONE_DOLLAR);
		mach.insertCash(Cash.ONE_DOLLAR);
		mach.insertCash(Cash.ONE_DOLLAR);
		mach.insertCash(Cash.ONE_DOLLAR);
		mach.insertCash(Cash.ONE_DOLLAR);



		assertEquals(1000,(int)mach.currBalance);
		System.out.println(mach.cashBox.getBillCount(Cash.TWENTY_CENTS));

		//There is initially 30 of each type of coin/note in the vending machine's cash box
		assertEquals(35,(int)mach.cashBox.getBillCount(Cash.TWENTY_CENTS));
		assertEquals(39,(int)mach.cashBox.getBillCount(Cash.ONE_DOLLAR));

		Product snack = mach.dispense();
		//		System.out.println(snack);

		//quantity decreased by one because snack has been dispensed
		assertEquals(new Product("special product", 1, 1000).equals(snack),true);

		//total balance(5050) - price(1000) = 4050 => 2 Twenty_Dollars (2000X2 cents) + 1 Fifty_cent
		Map <Cash,Integer> change = mach.getChange();

		assertEquals(change.size(),0);

	}

	@Test
	public void buySnackNotEnoughCashThenCancelAndRefund() {

		mach.reset();
		String productNum = "00";
		int priceInCents = (int)mach.showPriceForSelectedProduct(productNum);
		assertEquals(priceInCents,1000);

		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TWENTY_CENTS);
		mach.insertCash(Cash.TWENTY_CENTS);

		assertEquals(80,(int)mach.currBalance);

		//There is initially 30 of each type of coin/note in the vending machine's cash box
		assertEquals(34,(int)mach.cashBox.getBillCount(Cash.TWENTY_CENTS));

		Product snack = mach.dispense();
		System.out.println(snack);
		assertEquals(null,snack);
		Map <Cash,Integer> refund = mach.cancelAndRefund();

		int bill1Count = refund.get(Cash.FIFTY_CENTS);
		int bill2Count = refund.get(Cash.TWENTY_CENTS);
		int bill3Count = refund.get(Cash.TEN_CENTS);

		//80 cents = 50 + 20 + 10
		assertEquals(bill1Count,1);
		assertEquals(bill2Count,1);
		assertEquals(bill3Count,1);



	}


	@Test
	public void snackSoldOut() {
		mach.reset();
		System.out.println("enter product number (00, 01, 02, ..., 44)");
		System.out.println(" <<Program Chooses slot number (00) automatically for demonstration reasons>>\n");
		String productNum = "00";
		mach.stock.getProduct("00").setQuantity(0);
		System.out.println("the quantity of the product at slot (00) was set to zero.");
		int priceInCents = (int)mach.showPriceForSelectedProduct(productNum);
		assertEquals((int)priceInCents,0);
//		assertEquals(mach.currState,new IdleState(mach));
//System.out.println(mach.currState);


	}




}