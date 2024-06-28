/*
Program: SeheonCarClassDemo
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 9/10/20
Purpose: To practice classes
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include "CarClass.h";
using namespace std;

int main()
{
	
	cout << "This is a program to make different types of cars." << endl;

	string make1{ "Mercedes" };
	string model1{ "S450" };
	int year1{ 2020 };
	string color1{ "Silver" };

	string make2;
	string model2;
	int year2;
	string color2;

	Car car0;
	cout << car0.GetCarInfo();

	Car car1;

	cout << "\n Please enter the make of your car: ";
	getline(cin, make2);

	cout << "\n Please enter the model of your car: ";
	getline(cin, model2);
	cout << "\n Please enter the year of your car: ";
	cin >> year2;
	cin.ignore();

	cout << "\n Please enter the color of your car: ";
	getline(cin, color2);

	car1.SetCarInfo(make1, model1, year1, color1);
	cout << car1.GetCarInfo();
	Car car2{ make2, model2, year2, color2 };
	cout << car2.GetCarInfo();
	cout << "\n Don't you like making cars with use in C++?";

	return 0;
}
