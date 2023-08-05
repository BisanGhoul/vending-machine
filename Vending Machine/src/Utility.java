import java.util.HashMap;
import java.util.Map;

public class Utility {

	public static Map<Cash,Integer> returnNotesAndCoins(double amount){
		Map<Cash,Integer> change = new HashMap<>();
		if(amount>=Cash.FIFTY_DOLLARS.value() ) {
			int count = (int) (amount/Cash.FIFTY_DOLLARS.value());
			change.put(Cash.FIFTY_DOLLARS, count);
			amount = amount % Cash.FIFTY_DOLLARS.value();
		}
		if(amount>=Cash.TWENTY_DOLLARS.value() ) {
			int count = (int) (amount/Cash.TWENTY_DOLLARS.value());
			change.put(Cash.TWENTY_DOLLARS, count);
			amount = amount % Cash.TWENTY_DOLLARS.value();
		}

		if(amount>=Cash.ONE_DOLLAR.value()) {
			int count = (int) (amount/Cash.ONE_DOLLAR.value());
			change.put(Cash.ONE_DOLLAR, count);
			amount = amount % Cash.ONE_DOLLAR.value();
		}
		if(amount>=Cash.FIFTY_CENTS.value() ) {
			int count = (int) (amount/Cash.FIFTY_CENTS.value());
			change.put(Cash.FIFTY_CENTS, count);
			amount = amount % Cash.FIFTY_CENTS.value();
		}

		if(amount>=Cash.TWENTY_CENTS.value()) {
			int count = (int) (amount/Cash.TWENTY_CENTS.value());
			change.put(Cash.TWENTY_CENTS, count);
			amount = amount % Cash.TWENTY_CENTS.value();
		}

		if(amount>=Cash.TEN_CENTS.value() ) {
			int count = (int) (amount/Cash.TEN_CENTS.value());
			change.put(Cash.TEN_CENTS, count);
			amount = amount % Cash.TEN_CENTS.value();
		}
		return change;


	}
}
