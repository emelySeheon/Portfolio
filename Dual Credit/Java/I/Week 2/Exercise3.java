import java.util.Scanner;

public class Exercise3 {
	public static void main(String[] args) {
		
		double price = 10.50;
		double discount =price*0.9;
		
		Scanner input = new Scanner (System.in);
		System.out.print("Please enter your age: ");
		int ageOfPerson = input.nextInt();
		input.close();

		if (ageOfPerson >= 60) {
			System.out.printf("Your ticket will cost: $ %.2f", discount);
		} else{
			System.out.printf("Your ticket will cost: $ %.2f", price);
		}
	
		
		
	}
}