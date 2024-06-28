import java.util.Scanner;

public class Exercise5 {
	public static void main(String[] args) {
		
		double price = 10.50;
		double overSixty = price*0.9;
        double overFifty= price*0.8;
        double underFifty= price*0.7;
		
		Scanner input = new Scanner (System.in);
		System.out.print("Please enter your age: ");
		int ageOfPerson = input.nextInt();
		input.close();

		if (ageOfPerson >= 60) {
			System.out.printf("Your ticket will cost: $ %.2f", overSixty);
		} else if (ageOfPerson >= 50) {
			System.out.printf("Your ticket will cost: $ %.2f", overFifty);
		} else {
            System.out.printf("Your ticket will cost: $ %.2f", underFifty);
        }
	
	}
}