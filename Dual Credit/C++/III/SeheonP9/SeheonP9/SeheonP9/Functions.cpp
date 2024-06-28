#include "Functions.h"
#include "StudentUser.h"

#include <sstream>
using namespace std;

string WriteHeader()
{
	stringstream ss;
	ss << "Name: Emely Seheon";
	ss << "\nEmail: eseheon1@cnm.edu";
	ss << "\nProgram: SeheonP9";
	ss << "\nObjective: To use shared pointers to make the StudentUser class.\n";
	return ss.str();
}

int HowManyUsers()
{
	int number;
	cout << "\nHow many students would you like to add? ";
	cin >> number;
	return number;
}
