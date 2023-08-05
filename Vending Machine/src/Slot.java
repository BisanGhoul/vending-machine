
public class Slot implements Comparable<Slot>{

	private String number;
	private int row;
	private int column;
	private Product product;
	
	
	
	public Slot(String number, Product product) {
		this.number = number;
		this.product = product;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getRow() {
		
		this.row = convertToRowNumber(number.charAt(0)); 
		return row;
	}

	public static int convertToRowNumber(char ch) {
		return 0;
	}
	
	public static int convertToColumnNumber(char ch) {
		return 0;
	}
	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Slot(String number, int row, int column, Product product) {
		super();
		this.number = number;
		this.row = row;
		this.column = column;
		this.product = product;
	}

	
	
	public int compareTo(Slot s) {
		return this.number.compareToIgnoreCase(s.number);
	}

	@Override
	public String toString() {
		return 
		"product: "+product.getName()+" price (cents):"+product.getPriceInCents()+" number: "+number		;
	}
	
	
}
