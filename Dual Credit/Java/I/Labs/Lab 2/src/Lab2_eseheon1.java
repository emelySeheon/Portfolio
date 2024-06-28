/*
Lab2 is a program to calculate income and taxes.

Programmer: Emely Seheon
 */

import java.util.Scanner;

public class Lab2_eseheon1 {

    public static void main(String[] args) {

        //Getting user input
        Scanner input = new Scanner (System.in);
        System.out.print("Number of Exemptions: ");
        double exemptions = input.nextDouble();

        if (exemptions <0)
        {
            System.out.println("Invalid number. Please try again.");
            System.out.print("Number of Exemptions: ");
            exemptions = input.nextDouble();
        }

        System.out.print("Gross Salary: ");
        double grossSalary = input.nextDouble();

        if (grossSalary <0)
        {
            System.out.println("Invalid number. Please try again.");
            System.out.print("Gross Salary: ");
            grossSalary = input.nextDouble();
        }

        System.out.print("Interest Income: ");
        double interestInc = input.nextDouble();

        if (interestInc <0)
        {
            System.out.println("Invalid number. Please try again.");
            System.out.print("Interest Income: ");
            interestInc = input.nextDouble();
        }

        System.out.print("Capital Gains: ");
        double capitalGain = input.nextDouble();

        if (capitalGain <0)
        {
            System.out.println("Invalid number. Please try again.");
            System.out.print("Capital Gains: ");
            capitalGain = input.nextDouble();
        }

        System.out.print("Charitable Contributions: ");
        double charityCont = input.nextDouble();

        if (charityCont <0)
        {
            System.out.println("Invalid number. Please try again.");
            System.out.print("Charitable Contributions: ");
            charityCont = input.nextDouble();
        }

        System.out.println("");

        //Calculations
        double totalIncome = grossSalary+interestInc+capitalGain;
        double adjIncome = totalIncome-(exemptions*1500.00)-charityCont;

        //Outputting results to user
        if (adjIncome>=0 && adjIncome<10000)
        {
            double totalTax = 0;
            System.out.println("Total Income: "+ totalIncome);
            System.out.println("Adjusted Income: "+ adjIncome);
            System.out.printf("Total Tax: %.2f", totalTax);
        } else if (adjIncome >= 10000 && adjIncome<32000)
        {
            double totalTax = 0.15*(adjIncome-10000);
            System.out.println("Total Income: "+ totalIncome);
            System.out.println("Adjusted Income: "+ adjIncome);
            System.out.printf("Total Tax: %.2f", totalTax);
        } else if (adjIncome >= 32000 && adjIncome<50000)
        {
            double totalTax = (0.15*(32000-10000))+(0.23*(adjIncome-32000));
            System.out.println("Total Income: "+ totalIncome);
            System.out.println("Adjusted Income: "+ adjIncome);
            System.out.printf("Total Tax: %.2f", totalTax);
        } else if (adjIncome >= 50000)
        {
            double totalTax = (0.15*(32000-10000))+(0.23*(50000-32000)+(0.28*(adjIncome-50000)));
            System.out.println("Total Income: "+ totalIncome);
            System.out.println("Adjusted Income: "+ adjIncome);
            System.out.printf("Total Tax: %.2f", totalTax);
        }
    }
}
