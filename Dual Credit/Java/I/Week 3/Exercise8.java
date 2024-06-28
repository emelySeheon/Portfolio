/*
Name: Emely Seheon
Program: Exercise 8
Date: 6/6/20
Purpose: To learn how to use loops.
*/

public class Exercise8 {
    public static void main(String[] args) {
        double amount = 0.0;
        double principle = 1000;
        double rate= 0.05;
        int year=1;

        System.out.println("Year        Amount on deposit\n");

        while(year<=10){
            amount=principle*Math.pow(1+rate,year);
            System.out.println(year+"          "+ amount);
            year=year+1;
        }
    }
}