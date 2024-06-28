//A program with a function that uses 
//pointers with indirection operators to 
//return three pieces of information to main.

//include statements
#include <iostream>
#include <string>
#include "Functions.h"
using namespace std;


int main()
{
	string name ="";
	int age =0, numPets =0;

	//Use the address operator to pass the variable
	//addresses to the function.
	AskForInfo(name, age, numPets);

	//No need to use pointers here. We can pass
	//the values to the Write function.
	WriteInfo(name, age, numPets);

	return 0;
}





