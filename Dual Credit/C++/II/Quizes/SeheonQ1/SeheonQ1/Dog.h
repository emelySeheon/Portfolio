#ifndef DOG_H
#define DOG_H

#include <iostream>
using namespace std;

class Dog
{
private:
	string breed = "Labrador Retriever";
	string name = "Zelda";
	int age = 9;

public:
	Dog();
	Dog(string breeds, string names, int ages);
	void SetData(string breeds, string names, int ages);
	string GetFormattedString();

};

#endif