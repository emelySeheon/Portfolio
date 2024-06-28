/*
Program: Seheon P1
Programmer: Emely Seheon eseheon1@cnm.edu
Date: 5/27/20
Purpose: To calculate the C++ Movie Theater's income
*/

#include <iostream>
#include <iomanip>
#include<string>

using namespace std;

int main()
{
	cout << " Name: Emely Seheon \n Program: SeheonP1 \n Objective: To calculate the C++ Movie Theater's income"<<endl;

	//declarations
	float theaterGross = 20;
	float adultTicket = 10.50;
	float childTicket = 6.25;
	int adultNum; //number of adult tickets purchased
	int childNum; //number of child tickets purchased
	string movie;

	cout << fixed << setprecision(2);
	cout << "\n Welcome to the C++ Movie Theater!"
		<< "\n The theater keeps " << theaterGross << "% of all profits"
		<< "\n Adult ticket: $" << adultTicket
		<< "\n Child ticket: $" << childTicket << endl;

	//Purchase information
	cout << "\n What movie will you be watching?  ";
	getline(cin, movie);
	cout << "\n How many adult tickets would you like?  ";
	cin >> adultNum;
	cout << "\n How many child tickets would you like?  ";
	cin >> childNum;
	cout << endl;

	//Calculations
	float adultPrice = adultNum * adultTicket;
	float childPrice = childNum * childTicket;
	float total = adultPrice + childPrice;
	float net = (total * theaterGross) / 100;
	float distrib = total - net;

	//Prices
	cout << "\n Number of adult tickets purchased: " << adultNum
		<< "\n Price of adult tickets: $" << adultPrice
		<< "\n Number of child tickets purchased: " << childNum
		<< "\n Price of child tickers: $" << childPrice
		<< "\n Total: $" << total
		<< "\n Gross profit: $" << total
		<< "\n Net profit: $" << net
		<< "\n Amount sent to distributer: $" << distrib << endl;

	cout << "\n Thank you for choosing the C++ Movie Theater"
		<< "\n Enjoy your movie and have a great day!" << endl;

	return 0;
}