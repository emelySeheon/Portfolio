public class Lab1_eseheon1 {
	public static void main(String[] args) {

        System.out.println(" Name: Emely Seheon");

        double a = 23.4;
        double b = 56.5;
        double c = 12.2;
        double d = 9.0;
        double e = 10.3;

        double average = (a+b+c+d+e)/5;
        double variance = (((a-average)*(a-average))+((b-average)*(b-average))+((c-average)*(c-average))+((d-average)*(d-average))+((e-average)*(e-average))) / 5;
        double stanDiviat = Math.sqrt(variance);

        System.out.println(" The average is: "+ average );
        System.out.println(" The variance is: "+ variance);
        System.out.println(" The standard deviation is: "+ stanDiviat);

        if (stanDiviat >= 10) {
            System.out.println(" Standard deviation is greater than 10.");
        } else {
            System.out.println(" Standard deviation is less than 10.");
        }

	}
}