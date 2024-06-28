import java.util.Scanner;

public class Exercise4 {
	public static void main(String[] args) {
		
		double price = 10.50;
		double overSixty =price*0.9;
		double underSixty= price*0.8;
		
		Scanner input = new Scanner (System.in);
		System.out.print("Please enter your age: ");
		int ageOfPerson = input.nextInt();
		input.close();

		if (ageOfPerson >= 60) {
			System.out.printf("Your ticket will cost: $ %.2f", overSixty);
		} else{
			System.out.printf("Your ticket will cost: $ %.2f", underSixty);
		}
	
	}
}