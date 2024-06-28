/*
Program: OverloadedFunctionsDemo
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/16/20
Purpose: To learn about overloaded functions.
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include <vector>;
using namespace std;

void SayGoodnight();
void SayGoodnight(string name1);
void SayGoodnight(string name1, string name2);
void SayGoodnight(int number);

int main()
{

	SayGoodnight();

	string name;
	getline(cin, name);
	SayGoodnight(name);

	cout << "Name one: ";
	string nameFirst;
	getline(cin, nameFirst);
	cout << "Name second: ";
	string nameSecond;
	getline(cin, nameSecond);
	SayGoodnight(nameFirst, nameSecond);

	cout << "Number: ";
	int number;
	cin >> number;
	SayGoodnight(number);

	return 0;
}

void SayGoodnight()
{
	cout << "Good night!" << endl;
}

void SayGoodnight (string name1)
{
	cout << "Good night " << name1 << "!" << endl;
}

void SayGoodnight(string name1, string name2)
{
	cout << "Good " << name1 << " and " << name2 << "!" << endl;
}

void SayGoodnight(int number)
{
	for (int i = 0; i < number; ++i)
	{
		cout << "Good night! " << endl;
	}
}