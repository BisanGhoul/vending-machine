import java.util.Scanner;

public class Keypad {

	String input;
	Scanner in = new Scanner(System.in);
	
	public String getInput() {
		String input = "";
		input = in.next();
		return input;
	}
	
	public void setThenGetInput(String i) {
		this.input = i;
	}
}
