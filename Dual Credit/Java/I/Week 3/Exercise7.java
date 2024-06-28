/*
Name: Emely Seheon
Program: Exercise 7
Date: 6/6/20
Purpose: To learn how to use loops.
*/

public class Exercise7 {
    public static void main(String[] args) {

        double amount = 0.0;
        double principle = 1000;
        double rate= 0.05;

        System.out.println("Year        Amount on deposit\n");

        for(int i=10; i>0; i--){
            amount=principle*Math.pow(1+rate,i);
            System.out.println(i+"          "+ amount);
        }
    }
}