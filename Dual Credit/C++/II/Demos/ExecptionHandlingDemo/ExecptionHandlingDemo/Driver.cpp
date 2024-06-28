//Emely Seheon eseheon1@cnm.edu
//Exception Handling Demo

//Driver.cpp

#include <iostream>
#include <vector>
#include <stdexcept>
#include "DivideByZeroException.h""
using namespace std;

double quotient(int numerator, int denominator);

int main()
{
	cout << "\n\n This is a demonstration of some Exception handling in C++."<<endl;

	//1. C++ anything goes exception
	//int age{ 15 };
	//cout << "\n Enter your age: ";
	//cin >> age;
	//	
	//if (age > 18) {
	//	cout << "Access granted - you are old enough."<<endl;
	//}

	


	//2. Vector OutOfRange Exception
	//int length{ 0 };
	//cout << "\n Enter vector length  ";
	//cin >> length;
	//try {
	//	vector<int> vec(length);
	//	cout << "\n Vec size: " << vec.size();
	//	vec.at(8) = 7;
	//}
	//catch (out_of_range exc)
	//{
	//	cout << "\n Out of range exception caught" << endl
	//		<< exc.what() << endl;
	//}

		
	//3. Division By Zero
	//int number1{ 0 }, number2{ 0 };
	//cout << "\n Enter two numbers, with a space between them:";
	//
	//while (cin >> number1 >> number2)
	//{
	//	try {
	//		double result = quotient(number1, number2);
	//		cout << "The quotient is: " << result << endl;
	//	}
	//	catch (DivideByZeroException exc) {
	//		cout << "\n exception occurred: " << DivideByZeroException.what() << endl;
	//	}
	//	cout << "\n Enter two numbers, with a space between them:";
	//}

	//cout << "\n Done" << endl;



//4. Catch any Exception

	try {
		int newAge{ 18 };
		cout << "\n Please enter your age: ";
		cin >> newAge;
		if (newAge > 18) {
			cout << "Access granted - you are old enough." << endl;
		}
		else {
			throw(505);
		}
	}
	catch (...) {
		cout << "Access denied - You must be at least 18 years old.\n";
	}

		
	return 0;
}

double quotient(int numerator, int denominator)
{
	return (static_cast<double>(numerator) / denominator);
}