/*
Program: Demo 4
Programmer: Emely Seheon eseheon1@cnm.edu
Date: 5/27/20
Purpose: To learn formattiong output and strings
*/

#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

int main()
{
	const double pi = 3.141592653589793;
	double feet = 5280;
	double number = 123.456789;

	cout << "\nFirst write: not setting any flags"
		<< "\nPi = " << pi
		<< "\nFeet = " << feet
		<< "\nNumber " << number << endl;
	//pi = 3;

	//precision fixes how many spaces are used to write values
	cout.precision(5);
	//showpoint says to write the decimal point
	cout.setf(ios::showpoint);
	cout<< "\nSecond write: set prec to 5 and showpoint"
		<< "\nPi = " << pi
		<< "\nFeet = " << feet
		<< "\nNumber " << number << endl;

	//fixed adds a decimal place to make sure value reaches precision value
	cout.setf(ios::fixed);
	cout<<"\nThird write: set fixed with precision of 5"
		<< "\nPi = " << pi
		<< "\nFeet = " << feet
		<< "\nNumber " << number << endl;

	//<iomanip>
	//setw() automatically right-justifies data, makes sure the number takes up a certain amount of spaces
	cout << "\nFourth write: use setw(12)"
		<< "\n     Pi = " << setw(12)<< pi
		<< "\n   Feet = " << setw(12) << feet
		<< "\nNumber " << setw(12) << number << endl;

	//Now write in scientific notation
	//Gotta get rid of fixed to do that
	cout.unsetf(ios::fixed);
	cout << "\nFifth write: scientific notation"
		<<setiosflags(ios::scientific)
		<< "\n     Pi = " << setw(12) << pi
		<< "\n   Feet = " << setw(12) << feet
		<< "\nNumber " << setw(12) << number << endl;

	//strings
	//declare 3 strings, initialize 1
	string name;
	string school;
	string favLang = "C++, of course";

	cout << "\nC++ Programing Student Demographics";
	cout << "\nPlease enter your name: ";
	getline(cin, name);

	cout << "Please enter your school: ";
	getline(cin, school);

	cout << "\nProgramming Student Data:"
		<< "\nName: " << name
		<< "\nSchool" << school
		<< "\nFavorite language: " << favLang << endl;

	string sentence;
	string word;

	cout << "\nC++ String Demo";

	cout << "\n\nPlease enter a sentence  \n==> ";
	getline(cin, sentence);

	cout << "\nPlease enter a word ==> ";
	getline(cin, word);

	int sentSize = sentence.size();
	int wordSize = word.size();

	int wordInSentence = sentence.find(word);

	cout << "String Demo Results"
		<< "\nSentence: " << sentence
		<< "\nword: " << word
		<< "\nSentence size: " << sentSize
		<< "\nWord size: " << wordSize
		<< "\nWord location in sentence: "
		<< "(-1 not in sentence): " << wordInSentence << endl;

	return 0;
}