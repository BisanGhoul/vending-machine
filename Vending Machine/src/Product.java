import java.util.Objects;

public class Product {

	
	private String name;
	private int quantity = 0;
	private double priceInCents;
	private double priceInDolars;
	
	public Product(String name, int quantity, double priceInCents) {
		setName(name);
		setQuantity(quantity);
		setPriceInCents(priceInCents);
		setPriceInDolars();
	}

	
	
	public double getPriceInDolars() {
		return priceInDolars;
	}


	

	public void setPriceInDolars() {
		this.priceInDolars = (getPriceInCents()/100);
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPriceInCents() {
		return priceInCents;
	}

	public void setPriceInCents(double priceInCents) {
		this.priceInCents = Math.round(priceInCents);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, priceInCents, quantity);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (!(obj instanceof Product))
	        return false;
	    Product other = (Product) obj;
	    return Objects.equals(name, other.name)
	            && Double.compare(priceInCents, other.priceInCents) == 0
	            && quantity == other.quantity;
	}



	@Override
	public String toString() {
		return "Product [name=" + name + ", quantity=" + quantity + ", priceInCents=" + priceInCents
				+ ", priceInDolars=" + priceInDolars + "]";
	}
	
		
	
}
