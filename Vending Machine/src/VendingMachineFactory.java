
public class VendingMachineFactory {

	public VendingMachine createMachine(VendingMachineType type) {
		VendingMachine machine = null;
		if(type.equals(type.BEVERAGES)) {
			machine = new BeverageVendingMachine(); 
		}else if(type.equals(type.COFFE)) {
			machine = new CoffeeVendingMachine(); 
		}else if(type.equals(type.SNACKS)) {
//			System.out.println("snack mahcine creted");
			machine = new SnackVendingMachine(); 
		}
		return machine;
	}
}
