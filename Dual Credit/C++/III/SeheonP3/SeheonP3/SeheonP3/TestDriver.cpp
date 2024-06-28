// Name: Emely Seheon
// Email: eseheon1@cnm.edu
// File: SeheonP3

#include "PersonGen.h"
#include "Search.h"
#include "PersonSort.h"
#include "Person.h";
#include <fstream>
#include <iostream>
#include <sstream>

using namespace std;

int main()
{
	cout << "Name: Emely Seheon" << endl;
	cout << "Program: SeheonP3" << endl;
	cout << "Objective: to search for a name in a list of person objects." << endl;

	//Declaring and initializing variables
	ofstream writeFile;
	ifstream readfile;
	int fileCount = 0;
	string lastN;
	string firstN;
	int month;
	int day;
	int year;
	string fullName;
	int num = 1000;
	Person* arrOne = nullptr;
	try {
		arrOne = new Person[1000];
	}
	catch (exception const& ex) {
		cout << "Error";
	}
	PersonSort sorting;
	Search searching;
	PersonGen* generating = new PersonGen();
	int initializer = -2;
	int initializerr = -2;
	int* position = &initializer;
	int* pNumCompares = &initializerr;
	bool found = false;
	string name;
	bool lastName = false;
	string nameInput;
	string first;
	string last;
	string yesOrNo;
	string again = "y";
	bool goAgain = true;

	generating->CreateFile("test.txt", num);

	//Open File
	generating->UseFile("P3_Searches_Test.txt");

	//Read file into array
	for (int i = 0; i < num; ++i) {
		char c;
		stringstream ss;
		ss << lastN << " " << firstN;
		fullName = ss.str();
		arrOne[i].SetName(generating->GetNewPerson()->GetName());
		arrOne[i].SetBirthday(generating->GetNewPerson()->GetBirthDay());
	}

	//Displaying first 20
	readfile.open("P3_Searches_Test.txt");
	if (!readfile.is_open()) {
		cout << "Unable to open read file";
	}
	writeFile.open("log.txt");
	if (!writeFile.is_open()) {
		cout << "Unable to open write file";
	}
	if (readfile.is_open()) {
		readfile >> fileCount;
	}
	for (int i = 0; i < 20; ++i) {
		cout << arrOne[i].GetName() << endl;
		writeFile << arrOne[i].GetName() << endl;
	}

	//Sort
	sorting.ShellSort(arrOne, num);

	while (goAgain) {
		//Getting name
		cout << endl << "Please enter the name of the person you would like to search for (capitalized, first then last): ";
		cin >> first >> last;

		name = searching.FormatName(first, last);

		//Sequential
		found = searching.SequentialSearch(arrOne, num, name, position, pNumCompares);
		if (found) {
			yesOrNo = "Yes";
		}
		else {
			yesOrNo = "No";
		}
		cout << endl << "Sequential";
		cout << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl;
		writeFile << endl << "Sequential" << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl << endl;

		//Binary
		found = searching.BinarySearch(arrOne, num, name, position, pNumCompares);
		if (found) {
			yesOrNo = "Yes";
		}
		else {
			yesOrNo = "No";
		}
		cout << endl << "Binary";
		cout << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl;
		writeFile << endl << "Binary" << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl << endl;

		//Recursive
		found = searching.RunRecursiveSearch(arrOne, num, name, position, pNumCompares);
		if (found) {
			yesOrNo = "Yes";
		}
		else {
			yesOrNo = "No";
		}
		cout << endl << "Recursive";
		cout << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl;
		writeFile << endl << "Recursive" << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl << endl;

		//Go Again
		cout << "Would you like to go again? (y/n) ";
		cin >> again;
		if (again == "y") {
			goAgain = true;
		}
		else {
			goAgain = false;
		}
	}
	writeFile.close();

	return 0;
}