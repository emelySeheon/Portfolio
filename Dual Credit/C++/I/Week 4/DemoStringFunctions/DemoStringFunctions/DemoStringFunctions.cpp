/*
Program: DemoStringFunctions
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/11/20
Purpose: Demo functions and strings.
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include <vector>;
using namespace std;

void printHeader();
string getTextFromUser();
string convertTextToUpper(string text);
string convertToLower(string text);
string convertTextToLower(string text);

int main()
{
	
	printHeader();

	//get input from user
	string textToConvert = getTextFromUser();

	//show text in upper case
	string upperCaseText = convertTextToUpper(textToConvert);
	cout << "Upper case version: " << upperCaseText << endl;

	//show text in lower case
	string lowerCaseText = convertToLower(textToConvert);

	//show text in lower case
	lowerCaseText = convertTextToLower(textToConvert);
	cout << "Lower case version: " << lowerCaseText << endl;

	//ask user to input some text to search for
	cout << endl << "Let's find something in your text." << endl;
	string textToFind = getTextFromUser();

	//find and report the positiono of that text
	int textLocation = textToConvert.find(textToFind);
	if (textLocation != string::npos)
	{
		cout << "Found " << textToFind << " in " << textToConvert
			<< " at " << textLocation << "!" << endl;
	}
	else
	{
		cout << "Text not Found!" << endl;
	}

	return 0;
}

void printHeader()
{
	cout << "********************************" << endl;
	cout << "Welcome to string function demo!" << endl;
	cout << "********************************" << endl;
}

//Get user input
string getTextFromUser()
{
	string lineOfText;
	cout << "Please enter a line of text: ";
	getline(cin, lineOfText);
	return lineOfText;
}

//Convert text to upper case
string convertTextToUpper(string text)
{
	string resultText = "";
	for (int i = 0;i < text.size();++i)
	{
		resultText += toupper(text[i]);
	}
	return resultText;
}

//Convert text to lower case
string convertToLower(string text)
{
	string resultText = "";
	for (int i = 0; i < text.size();++i)
	{
		resultText += tolower(text[i]);
	}
	return resultText;
}

//Convert text to lowercase
string convertTextToLower(string text)
{
	string resultText = "";
	for (int i = 0; i < text.size();++i)
	{
		resultText += tolower(text[i]);
	}
	return resultText;
}