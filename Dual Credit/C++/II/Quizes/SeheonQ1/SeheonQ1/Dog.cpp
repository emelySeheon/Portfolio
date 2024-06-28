#include "Dog.h"
#include <iostream>
#include <sstream>
using namespace std;

Dog::Dog()
{
}

Dog::Dog(string breeds, string names, int ages)
{
	breed = breeds;
	name = names;
	age = ages;
}

void Dog::SetData(string breeds, string names, int ages)
{
	breed = breeds;
	name = names;
	age = ages;
}

string Dog::GetFormattedString()
{
	stringstream doggoInfo;
	doggoInfo << "Introducing: " << name << "! " << name << " is a " << age << " year old " << breed << "! :)" << endl;

	string doggo = doggoInfo.str();

	return doggo;
}
