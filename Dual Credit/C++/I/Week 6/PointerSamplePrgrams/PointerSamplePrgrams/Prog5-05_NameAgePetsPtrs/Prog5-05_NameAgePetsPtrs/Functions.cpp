//A program with a function that uses 
//pointers with indirection operators to 
//return three pieces of information to main.

//Functions.cpp

//include statements
#include <iostream>
#include <string>
#include "Functions.h"
using namespace std;

void AskForInfo(string pName, int pAge, int pNumPets)
{
	cout << "Please enter your name:  ";
	string myName = pName;
	getline(cin, myName);
	pName = myName;
	cout << "Please enter your age:  ";
	cin >> pAge;
	cout << "Please enter the number of pets that you own:  ";
	cin >> pNumPets;
	cin.ignore();
}

void WriteInfo(string name, int age, int numPets)
{
	cout << "\nHi " << name << "!\nI see that you are "
		<< age << " years old and have " << numPets << " pets." << endl;
}