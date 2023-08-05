import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ProductStock {


	Set <Slot>slotsSet = new TreeSet<>();


	public ProductStock() {

	}

	public void fillStock(Set<Slot> newProducts) {
		slotsSet = newProducts;
	}

	public boolean containsProduct(Product product) {

		for (Slot slot : slotsSet) {
			if (slot.getProduct().equals(product)) {
				return true;
			}
		}
		return false;
	}

	public Product getProduct(String slotNumber) {

		Product product = null;

		for (Slot slot : slotsSet) {
			if (slot.getNumber().equalsIgnoreCase(slotNumber)) {
				return slot.getProduct();
			}
		}


		return product;

	}

	public Product dispenseProduct(String slotNumber) {

		Product product = getProduct(slotNumber);
		if(product != null) {
			if(product.getQuantity() > 0) {
				product.setQuantity(product.getQuantity()-1);
			}else {
				System.out.println("Sorry, Product is out of stock!");
			}
		}else {
			System.out.println("No such product was found.");
		}

		return product;

	}

	public Product dispenseProduct(Product product) {

		if(product != null) {
			if(product.getQuantity() > 0) {
				product.setQuantity(product.getQuantity()-1);
			}else {
				System.out.println("Sorry, Product is out of stock!");
			}
		}else {
			System.out.println("No such product was found.");
		}

		return product;

	}

	public int getProductCount(String slotNumber) {

		Product product = getProduct(slotNumber);


		return product.getQuantity();

	}


	public int getProductCount(Product product) {		

		return product.getQuantity();

	}

	public double getProductPrice(Product product) {		

		return product.getPriceInCents();

	}

	public double getProductPrice(String slotNumber) {		

		Product product = getProduct(slotNumber);
		return product.getPriceInCents();

	}

	public boolean isProductInStock(Product product) {
		//		if(getProductCount(product))	
		return false;			
	}
	
	public boolean isEmpty() {
		return slotsSet.isEmpty();
	}


}
